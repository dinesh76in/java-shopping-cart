package com.sky.hiring.test.shoppingcart;

import com.sky.hiring.test.shoppingcart.model.Item;
import com.sky.hiring.test.shoppingcart.model.Promotion;
import com.sky.hiring.test.shoppingcart.service.ItemService;
import com.sky.hiring.test.shoppingcart.service.PromotionService;
import com.sky.hiring.test.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableAutoConfiguration
public class ShoppingCartApp
{
    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ItemService itemService;

    @Autowired
    PromotionService promotionService;

    public static void main( String[] args )
    {
        SpringApplication.run(ShoppingCartApp.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        printCartItems();
    }

    public void printCartItems(){

        itemService.addItem(new Item("Headphones","Audio",150.0));
        itemService.addItem(new Item("Speakers","Audio",85.0));
        itemService.addItem(new Item("AAA Batteries","Power",0.85));
        itemService.addItem(new Item("Protein Bars (Box)","Food",25));

        promotionService.addPromotion(new Promotion("3 for the price of 2","name","","AAA Battery",0));
        promotionService.addPromotion(new Promotion("discount promotion","type","Audio","",30));


        shoppingCartService.clearCart();

        Item batteries=itemService.findByName("AAA Batteries");
        Item speakers=itemService.findByName("Speakers");
        Item proteinBars=itemService.findByName("Protein Bars (Box)");

        shoppingCartService.addItem(batteries,5);
        shoppingCartService.addItem(speakers,1);
        shoppingCartService.addItem(proteinBars,2);

        shoppingCartService.displayItems();

    }
}
