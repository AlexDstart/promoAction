package com.example.promoaction.controller;

import com.example.promoaction.entity.Prize;
import com.example.promoaction.repository.PrizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/promo-admin-panel")
public class InfoController {


    @GetMapping("/conditions-action")
    public String conditionsAction(Model model) {
        List<String> rules = new ArrayList<>();
        rules.add("Акция проводится с 1 июня по 30 июня 2024 года.");
        rules.add("В акции могут участвовать все пользователи, зарегистрированные на сайте.");
        rules.add("Для участия необходимо заполнить форму на странице акции и загрузить фотографию с чеком.");
        rules.add("Каждый участник может участвовать в акции только один раз.");
        model.addAttribute("rules", rules);
        return "user/conditions-action";
    }

    @GetMapping("/winners")
    public String allPrizeGet() {
        return "admin/winners";
    }

    @GetMapping("/user-panel")
    public String userPanel() {
        return "user/user_panel";
    }

    @GetMapping("/promo-rules")
    public ResponseEntity<Resource> promoRules() {
        try {
            // Путь к файлу PDF
            String filePath = "C:\\Users\\AlexD\\IdeaProjects\\promoAction\\src\\main\\resources\\static\\teeest.pdf";
            Path path = Paths.get(filePath);
            Resource resource = new FileSystemResource(path);

            if (!resource.exists()) {
                throw new RuntimeException("File not found: " + filePath);
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=teeest.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (Exception e) {
            // Логирование ошибки
            e.printStackTrace();
            // Возвращаем ошибку 404, если файл не найден
            return ResponseEntity.notFound().build();
        }
    }
}
