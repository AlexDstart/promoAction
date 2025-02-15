package com.example.promoaction.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name_winner;
    private String email_winner;
    private String number_winner;
    private Boolean status_winner ;
    private String promo_cod;
    private String name_prize;
    private String image_path;

    public Prize(String name_winner, String promo_cod, String image_path) {
        this.name_winner = name_winner;
        this.promo_cod = promo_cod;
        this.image_path = image_path;
    }
}
