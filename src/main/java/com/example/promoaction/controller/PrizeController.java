package com.example.promoaction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Controller
public class PrizeController {

    @GetMapping("/promo-admin-panel/add-prize")
    public String addClassGet(){

        return "redirect:/add_prize.html";
    }

    @GetMapping("/promo-admin-panel/admin_panel")
    public String adminPanelGet(){
        return "redirect:/admin_panel";
    }
    @GetMapping("/promo-admin-panel/all_prize")
    public String allPrizeGet(){
        return "redirect:/all_prize.html";
    }


}


