package com.sky.hiring.test.shoppingcart.repository;

import com.sky.hiring.test.shoppingcart.model.Item;
import com.sky.hiring.test.shoppingcart.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {

    @Query("SELECT i FROM Item i where i.name = :itemName")
    public List<Item> findByItemName(@Param("itemName") String itemName);

}
