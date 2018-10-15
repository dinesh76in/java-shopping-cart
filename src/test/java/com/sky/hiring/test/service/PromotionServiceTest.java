package com.sky.hiring.test.service;

import com.sky.hiring.test.shoppingcart.ShoppingCartApp;
import com.sky.hiring.test.shoppingcart.model.Promotion;
import com.sky.hiring.test.shoppingcart.service.PromotionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingCartApp.class)
public class PromotionServiceTest {

    @Autowired
    private PromotionService promotoinService;

    @Before
    public void setUp() throws Exception {

        promotoinService.addPromotion(new Promotion("first promotion","name","Audio","Headphones",10));
        promotoinService.addPromotion(new Promotion("second promotion","type","Food","Chicken Soup",15));
        }

    @After
    public final void tearDown() {
        promotoinService.deleteAll();
    }

    @Test
    public void findAllPromotionsTest() {
        List<Promotion> promotions = promotoinService.findAll();
        assertEquals(2, promotions.size());
    }

    @Test
    public void findByItemNameTest() {
        List<Promotion> promotions = promotoinService.findByItemName("Chicken Soup");
        assertEquals(1, promotions.size());
    }

    @Test
    public void findByItemTypeTest() {
        List<Promotion> promotions = promotoinService.findByItemType("Food");
        assertEquals(1, promotions.size());
    }

}
