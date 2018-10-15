package com.sky.hiring.test.shoppingcart.repository;

import com.sky.hiring.test.shoppingcart.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion,Integer> {

    @Query("SELECT p FROM Promotion p where p.itemName = :itemName")
    public List<Promotion> findByItemName(@Param("itemName") String itemName);

    @Query("SELECT p FROM Promotion p where p.itemType = :itemType")
    public List<Promotion> findByItemType(@Param("itemType") String itemType);
}