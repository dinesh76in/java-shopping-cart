package com.sky.hiring.test.shoppingcart.service;

import com.sky.hiring.test.shoppingcart.model.Promotion;
import com.sky.hiring.test.shoppingcart.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionService {
    @Autowired
    PromotionRepository promotionRepository;


    public void addPromotion(Promotion promotion){
        promotionRepository.save(promotion);
    }

    public void deleteAll(){
        promotionRepository.deleteAll();
    }

    public List<Promotion> findByItemName(String itemName){
        return promotionRepository.findByItemName(itemName);
    }

    public List<Promotion> findByItemType(String itemType){
        return promotionRepository.findByItemType(itemType);
    }


    public void findByItemType(){
        promotionRepository.deleteAll();
    }

    public List<Promotion> findAll(){
        return promotionRepository.findAll();
    }
}
