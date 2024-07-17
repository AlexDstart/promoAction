package com.example.promoaction.controller;

import com.example.promoaction.entity.Prize;
import com.example.promoaction.repository.PrizeRepository;
import com.example.promoaction.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/promo-admin-panel")
public class PrizeController {

    private PrizeService prizeService;

    PrizeRepository prizeRepository;

    public void prizeList(PrizeRepository prizeRepository) {
        this.prizeRepository = prizeRepository;
    }

    @Autowired
    private ResourceLoader resourceLoader;



    public PrizeController(PrizeService prizeService) {
        this.prizeService = prizeService;
    }

    @GetMapping("/add-prize")
    public String addClassGet() {
        return "admin/add_prize";
    }




    //POST REQUEST
    @PostMapping("/add-prize")
    public String addClassPost(
            @RequestParam String name_prize,
            @RequestParam String promo_code,
            @RequestParam MultipartFile img_prize,
            Model model) {

        String[] promoCodes = promo_code.split("[,\\s;]");
        String path = prizeService.saveImageToDirAndReturnPath(img_prize);

        for (String code : promoCodes) {
            Prize prize = new Prize();
            prize.setName_prize(name_prize);
            prize.setPromo_cod(code);
            prize.setImage_path(path);

            prizeService.add(prize);
        }




        model.addAttribute("message", "prize added");


        return "admin/add_prize";
    }




}


