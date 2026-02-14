package com.example.ShopingWeb.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.ShopingWeb.entity.CartItem;
import com.example.ShopingWeb.entity.Product;
import com.example.ShopingWeb.entity.User;
import com.example.ShopingWeb.repository.CartItemRepository;
import com.example.ShopingWeb.repository.ProductRepository;
import com.example.ShopingWeb.repository.UserRepository;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartController(CartItemRepository cartItemRepository, ProductRepository productRepository,
            UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, Authentication auth) {
        String username = auth.getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        Product product = productRepository.findById(productId).orElseThrow();

        CartItem existingItem = cartItemRepository.findByUserAndProduct(user, product);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            cartItemRepository.save(existingItem);
        } else {
            CartItem cartItem = new CartItem(user, product, 1);
            cartItemRepository.save(cartItem);
        }

        return "redirect:/products";
    }

    @GetMapping
    public String viewCart(Model model, Authentication auth) {
        String username = auth.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        model.addAttribute("cartItems", cartItemRepository.findByUser(user));
        return "cart";
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartItemRepository.deleteById(id);
        return "redirect:/cart";
    }
}
