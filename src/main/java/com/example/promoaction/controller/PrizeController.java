package com.example.promoaction.controller;

import com.example.promoaction.entity.Prize;
import com.example.promoaction.service.PrizeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Controller
@RequestMapping("/promo-admin-panel")
public class PrizeController {
    private PrizeService prizeService;

    public PrizeController(PrizeService prizeService) {
        this.prizeService = prizeService;
    }

    @GetMapping("/add-prize")
    public String addClassGet(){
        return "redirect:/add_prize.html";
    }

    @GetMapping("/admin-panel")
    public String adminPanelGet(){
        return "admin_panel";
    }
    @GetMapping("/winners")
    public String allPrizeGet(){
        return "winners";
    }

    //POST REQUEST
    @PostMapping("/add-prize")
    public String addClassPost(
            @RequestParam String name_prize,
            @RequestParam String promo_code,
            @RequestParam MultipartFile img_prize
    ){
        Prize prize = new Prize();
        prize.setName_prize(name_prize);
        prize.setPromo_cod(promo_code);

        String path = prizeService.saveImageToDirAndReturnPath(img_prize);
        prize.setImage_path(path);

        prizeService.add(prize);



        return "redirect:/add_prize.html";
    }






}


