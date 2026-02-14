package com.example.ShopingWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ShopingWeb.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
