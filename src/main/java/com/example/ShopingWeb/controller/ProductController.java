package com.example.ShopingWeb.controller;

import java.io.File;
import java.io.IOException;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.ShopingWeb.entity.Product;
import com.example.ShopingWeb.repository.ProductRepository;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveProduct(@ModelAttribute Product product,
            @RequestParam("imageFile") MultipartFile file) throws IOException {

        // Agar product edit ho raha hai
        if (product.getId() != null) {
            Product existingProduct = productRepository.findById(product.getId()).orElseThrow();

            // Agar new image upload nahi hui
            if (file.isEmpty()) {
                product.setImagePath(existingProduct.getImagePath());
            }
        }

        // Agar new image upload hui
        if (!file.isEmpty()) {

            String uploadDir = System.getProperty("user.dir") + "/uploads/";

            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            String fileName = file.getOriginalFilename();
            String filePath = uploadDir + fileName;

            file.transferTo(new File(filePath));

            product.setImagePath("uploads/" + fileName);
        }

        productRepository.save(product);

        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow();
        model.addAttribute("product", product);
        return "product-form";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
}
