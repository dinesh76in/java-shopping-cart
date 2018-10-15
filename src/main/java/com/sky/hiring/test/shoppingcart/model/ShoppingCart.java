package com.sky.hiring.test.shoppingcart.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    List<Item> shoppingItems =new ArrayList<>();

    public void addItems(Item item){
        shoppingItems.add(item);
    }

    public void removeItems(Item item){
        shoppingItems.remove(item);
    }

    public void clearCart(){
        shoppingItems.clear();
    }

    public List<Item> getAllItems(){
        return shoppingItems;
    }

    public void displayShoppingCartContents(){

        shoppingItems.forEach(item -> {
            System.out.println("ItemName : " + item.getName() + " Price : " + item.getPrice());
        });
    }


}
