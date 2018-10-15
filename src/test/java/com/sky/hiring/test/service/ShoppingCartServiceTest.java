package com.sky.hiring.test.service;

import com.sky.hiring.test.shoppingcart.ShoppingCartApp;
import com.sky.hiring.test.shoppingcart.model.Item;
import com.sky.hiring.test.shoppingcart.model.Promotion;
import com.sky.hiring.test.shoppingcart.service.ShoppingCartService;
import com.sky.hiring.test.shoppingcart.service.ItemService;
import com.sky.hiring.test.shoppingcart.service.PromotionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingCartApp.class)
public class ShoppingCartServiceTest {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private PromotionService promotoinService;

    @Autowired
    private ItemService itemService;

    private List<Item> shoppingItems=new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        itemService.deleteAll();
        promotoinService.deleteAll();
    }

    @After
    public final void tearDown() {
        shoppingItems.clear();
    }

    @Test
    public void getTotalPriceTest() {

        shoppingCartService.clearCart();
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("Speakers","power",20));
        shoppingCartService.addItem(new Item("Headphones","power",30));

        double totalPrice = shoppingCartService.getTotalPrice();
        assertEquals(70.0, totalPrice,0);
    }

    @Test
    public void applyPromotionsWithTypeTest_singleItem() {

        shoppingCartService.clearCart();
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("Speakers","power",20));
        shoppingCartService.addItem(new Item("Headphones","power",30));


        promotoinService.addPromotion(new Promotion("discount promotion","type","Audio","",10));

        shoppingCartService.applyPromotions();

        double totalPrice = shoppingCartService.getTotalPrice();
        assertEquals(59.0, totalPrice,0);
    }

    @Test
    public void applyPromotionsWithTypeTest_multipleItems() {

        shoppingCartService.clearCart();
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("Speakers","power",20));
        shoppingCartService.addItem(new Item("Headphones","power",30));


        promotoinService.addPromotion(new Promotion("discount promotion","type","Audio","",10));

        shoppingCartService.applyPromotions();

        double totalPrice = shoppingCartService.getTotalPrice();
        assertEquals(68.0, totalPrice,0);
    }

    @Test
    public void applyPromotionsWithNameTest_NotQualifyingPromotion() {

        shoppingCartService.clearCart();
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("Speakers","power",20));
        shoppingCartService.addItem(new Item("Headphones","power",30));


        promotoinService.addPromotion(new Promotion("3 for the price of 2","name","","AAA Battery",0));

        shoppingCartService.applyPromotions();

        double totalPrice = shoppingCartService.getTotalPrice();
        assertEquals(70.0, totalPrice,0);
    }

    @Test
    public void applyPromotionsWithNameTest_QualifyingPromotion() {

        shoppingCartService.clearCart();
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("Speakers","power",20));
        shoppingCartService.addItem(new Item("Headphones","power",30));


        promotoinService.addPromotion(new Promotion("3 for the price of 2","name","","AAA Battery",0));

        shoppingCartService.applyPromotions();

        double totalPrice = shoppingCartService.getTotalPrice();
        assertEquals(70.0, totalPrice,0);
    }

    @Test
    public void applyPromotionsWithNameTest_QualifyingPromotionEdgeCase1() {

        shoppingCartService.clearCart();
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("Speakers","power",20));
        shoppingCartService.addItem(new Item("Headphones","power",30));


        promotoinService.addPromotion(new Promotion("3 for the price of 2","name","","AAA Battery",0));

        shoppingCartService.applyPromotions();

        double totalPrice = shoppingCartService.getTotalPrice();
        assertEquals(90.0, totalPrice,0);
    }

    @Test
    public void applyPromotionsWithNameTest_QualifyingPromotionEdgeCase2() {

        shoppingCartService.clearCart();
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("AAA Battery","Audio",10));
        shoppingCartService.addItem(new Item("Speakers","power",20));
        shoppingCartService.addItem(new Item("Headphones","power",30));


        promotoinService.addPromotion(new Promotion("3 for the price of 2","name","","AAA Battery",0));

        shoppingCartService.applyPromotions();

        double totalPrice = shoppingCartService.getTotalPrice();
        assertEquals(90.0, totalPrice,0);
    }

    /**
     *  Test case for requirements use case
     */
    @Test
    public void applyAllPromotions() {

        shoppingCartService.clearCart();
        shoppingCartService.addItem(new Item("AAA Battery","Power",0.85));
        shoppingCartService.addItem(new Item("AAA Battery","Power",0.85));
        shoppingCartService.addItem(new Item("AAA Battery","Power",0.85));
        shoppingCartService.addItem(new Item("AAA Battery","Power",0.85));
        shoppingCartService.addItem(new Item("AAA Battery","Power",0.85));
        shoppingCartService.addItem(new Item("Speaker","Audio",85.00));
        shoppingCartService.addItem(new Item("Protein Bars (Box)","Food",25.00));
        shoppingCartService.addItem(new Item("Protein Bars (Box)","Food",25.00));


        promotoinService.addPromotion(new Promotion("3 for the price of 2","name","","AAA Battery",0));
        promotoinService.addPromotion(new Promotion("discount promotion","type","Audio","",30));

        shoppingCartService.applyPromotions();

        double totalPrice = shoppingCartService.getTotalPrice();
        assertEquals(112.9, totalPrice,0);
    }

}
