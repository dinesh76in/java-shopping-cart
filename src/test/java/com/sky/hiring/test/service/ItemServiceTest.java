package com.sky.hiring.test.service;

import com.sky.hiring.test.shoppingcart.ShoppingCartApp;
import com.sky.hiring.test.shoppingcart.model.Item;
import com.sky.hiring.test.shoppingcart.service.ItemService;
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
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Before
    public void setUp() throws Exception {

        itemService.addItem(new Item("AAA Battery","Audio",10));
        itemService.addItem(new Item("Speakers","power",20));
        itemService.addItem(new Item("Headphones","power",30));
    }

    @After
    public final void tearDown() {
        itemService.deleteAll();
    }

    @Test
    public void findAllTest() {
        List<Item> items = itemService.findAll();
        assertEquals(3, items.size());
    }



}
