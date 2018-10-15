package com.sky.hiring.test.shoppingcart.service;

import com.sky.hiring.test.shoppingcart.model.Item;
import com.sky.hiring.test.shoppingcart.model.Promotion;
import com.sky.hiring.test.shoppingcart.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.DoubleAdder;

@Service
public class ShoppingCartService {

    @Autowired
    PromotionService promotoinService;

    ShoppingCart shoppingCart =new ShoppingCart();

    public void addItem(Item item){
        shoppingCart.addItems(item);
    }

    public void addItem(Item item, int itemCount){
        for (int i = 0; i < itemCount ; i++) {
            shoppingCart.addItems(item);
        }
    }

    public void removeItem(Item item){
        shoppingCart.removeItems(item);
    }

    public void clearCart(){
        shoppingCart.clearCart();
    }

    public void displayItems(){
        shoppingCart.getAllItems().forEach(item -> {

            System.out.println("Item Name:"+item.getName()+"    Item Type:"+item.getType()+"    Item Price:"+item.getPrice());
        });
     }

    /**
     * get total price of all items without any promotions
     * @return price
     */
    public double getTotalPrice(){
        DoubleAdder totalPrice = new DoubleAdder();
         List<Item> shoppingItems =  shoppingCart.getAllItems();

        shoppingItems.forEach(item -> {
                totalPrice.add(item.getPrice());
        });
        return totalPrice.doubleValue();
    }

    /*
     * Adjusts the price of items based on the promotions.
     * For discount promotions the prices of items are adjusted as per the applicable discount on items under
     * discount
     *
     * For BuyMore save more promotions, once the number of items qualifies promotion one of the item price is
     * set to 0
     */
    public  void applyPromotions(){
        List<Item> shoppingItems = shoppingCart.getAllItems();
        AtomicInteger promotionsItemsCount=new AtomicInteger();

        shoppingItems.forEach(item -> {

            List<Promotion> promotions = promotoinService.findAll();

            promotions.forEach(promotion -> {
                if(promotion.getPromotionType().equalsIgnoreCase("type")){
                    if(item.getType().equalsIgnoreCase(promotion.getItemType())){
                        System.out.println("Applying Promotion by type for - "+item.getName());
                        double discountedPrice = item.getPrice() - (item.getPrice() / 100) * promotion.getDiscount();
                        item.setPrice(discountedPrice);
                    }
                }else if(promotion.getPromotionType().equalsIgnoreCase("name")){

                    if(item.getName().equalsIgnoreCase(promotion.getItemName())){
                        System.out.println("Applying Promotion by Name - "+item.getName());
                        if (promotionsItemsCount.get() == 2) {
                            promotionsItemsCount.set(0);
                            item.setPrice(0);
                        } else {
                            promotionsItemsCount.incrementAndGet();
                        }
                    }
                }else{
                    // Implement other promotion types in future
                }
            });
        });
    }

}
