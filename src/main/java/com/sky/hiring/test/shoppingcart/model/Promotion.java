package com.sky.hiring.test.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    public Promotion(String promotionName, String promotionType, String itemType, String itemName, double discount) {
        this.promotionName = promotionName;
        this.promotionType = promotionType;
        this.itemType = itemType;
        this.itemName = itemName;
        this.discount = discount;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    private String promotionName;
    private String promotionType;
    private String itemType;
    private String itemName;
    private double discount;


    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    public Promotion() {
      super();
    }



}
