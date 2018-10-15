package com.sky.hiring.test.shoppingcart.service;

import com.sky.hiring.test.shoppingcart.model.Item;
import com.sky.hiring.test.shoppingcart.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;


    public void addItem(Item item){
        itemRepository.save(item);
    }

    public Item findByName(String itemName){

        List<Item> items = itemRepository.findByItemName(itemName);

        if(items.size()==1)
            return items.get(0);
            return null;
    }

    public void deleteAll(){
        itemRepository.deleteAll();
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }


}
