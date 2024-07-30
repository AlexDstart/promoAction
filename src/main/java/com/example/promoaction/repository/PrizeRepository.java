package com.example.promoaction.repository;

import com.example.promoaction.entity.Prize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrizeRepository extends JpaRepository<Prize,Long> {

    boolean existsByPromoCode(String promoCode);

}
