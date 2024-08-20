package com.example.promoaction.controller;

import com.example.promoaction.entity.Prize;
import com.example.promoaction.repository.PrizeRepository;
import com.example.promoaction.service.PrizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller

public class PrizeController {

    private static final Logger log = LoggerFactory.getLogger(PrizeController.class);
    private PrizeService prizeService;






    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private PrizeRepository prizeRepository;


    public PrizeController(PrizeService prizeService) {
        this.prizeService = prizeService;
    }

    @GetMapping("/promo-admin-panel/add-prize")
    public String addClassGet() {
        return "admin/add_prize";
    }




    //POST REQUEST
    @PostMapping("/promo-admin-panel/add-prize")
    public String addClassPost(
            @RequestParam String name_prize,
            @RequestParam String promo_code,
            @RequestParam MultipartFile img_prize,
            Model model) {

        String[] promoCodes = promo_code.split("[,\\s;]");
        String path = prizeService.saveImageToDirAndReturnPath(img_prize);
        if (path == null) {
            model.addAttribute("error", "Failed to save image");
            return "admin/add_prize";
        }

        for (String code : promoCodes) {
            Prize prize = new Prize();
            prize.setName_prize(name_prize);
            prize.setPromoCode(code);
            prize.setImage_path(path);

            prizeService.add(prize);
        }

        model.addAttribute("message", "prize added");
        return "admin/add_prize";
    }


    @GetMapping("/prizes")
    public String prizeList(Model model) {
       List<Prize> allPrizesList=prizeService.findAllPrizes();
        model.addAttribute("prizes",allPrizesList);
        return "user/prizes";
    }


    @PostMapping("/check-promo")
    public String checkPromo(@RequestParam String promoCode, Model model) {

        boolean exists = prizeService.checkPromo(promoCode);
        if(!exists) {
            model.addAttribute("error", "Promo doesn't exist");
            log.info("Promo doesn't exist");
            return "user/check_promo";
        }

        boolean isNotActivate= prizeService.isNotActivationPromoCode(promoCode);
        if (!isNotActivate) {
            log.info("Promo isn't activated");
            model.addAttribute("message", "Промокод уже активирован");
            return "user/check_promo";
        }

        Prize prize = prizeService.getPrize(promoCode);
        model.addAttribute("img_path",prize.getImage_path());
        model.addAttribute("id_prize",prize.getId());
        model.addAttribute("name_prize",prize.getName_prize());
        return "user/form_get_prize";

    }
    @PostMapping("/form_get_prize")
    public String processPrizeForm(
            @RequestParam("id_prize") Long idPrize,
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            Model model) {

        // Найти приз по ID
        Prize prize = prizeService.getPrizeById(idPrize);

        if (prize != null) {
            // Обновить данные приза
            prize.setName_winner(name);
            prize.setNumber_winner(phone);
            prize.setEmail_winner(email);
            prize.setStatus_winner(true);  // Установить статус "приз получен"

            // Сохранить обновленный приз
            prizeService.updatePrize(prize);

            // Добавить информацию в модель для отображения на странице подтверждения
            model.addAttribute("name_prize", prize.getName_prize());
            model.addAttribute("name_winner", name);
        }

        // Перенаправить на страницу подтверждения
        return "user/confirmation";
    }

}








