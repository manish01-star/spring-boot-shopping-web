package com.example.ShopingWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ShopingWeb.entity.CartItem;
import com.example.ShopingWeb.entity.Product;
import com.example.ShopingWeb.entity.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Get all cart items for a user
    List<CartItem> findByUser(User user);
     CartItem findByUserAndProduct(User user, Product product);
}
