package com.example.promoaction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/promo-admin-panel")
public class AdminController {
    @GetMapping("/admin-panel")
    public String adminPanelGet() {
        return "admin/admin_panel";
    }



}
