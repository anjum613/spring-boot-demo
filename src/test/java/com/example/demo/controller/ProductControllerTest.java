package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// This annotation correctly tells Spring to only configure the web layer
// for the ProductController and not the entire application.
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // This is the most important part of the fix.
    // @MockBean creates a mock of ProductService and adds it to the context,
    // satisfying the dependency for ProductController.
    @MockBean
    private IProductService productService;

    @Test
    void testGetAllProducts() throws Exception {
        // Arrange
    Product p1 = new Product(1L, "Product A", 10.0, 1);
    Product p2 = new Product(2L, "Product B", 20.0, 2);
    when(productService.getAllProducts()).thenReturn(Arrays.asList(p1, p2));

        // Act & Assert
     mockMvc.perform(get("/api/v1/products"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$[0].name").value("Product A"))
         .andExpect(jsonPath("$[1].name").value("Product B"));
    }

    @Test
    void testGetProductById() throws Exception {
        // Arrange
    Product product = new Product(1L, "Test Product", 5.0, 3);
    when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        // Act & Assert
     mockMvc.perform(get("/api/v1/products/1"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    void testCreateProduct() throws Exception {
        // Arrange
    Product newProduct = new Product(null, "New Product", 7.0, 4); // ID is null before saving
    Product savedProduct = new Product(1L, "New Product", 7.0, 4); // ID is set after saving
    when(productService.saveProduct(any(Product.class))).thenReturn(savedProduct);

        // Act & Assert
        // CORRECTED: The URL should be plural "/products" to be consistent with REST standards.
        mockMvc.perform(post("/api/v1/products")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"name\":\"New Product\",\"price\":7.0,\"quantity\":4}"))
               .andExpect(status().isOk()) // Or isCreated() (201) which is more common for POST
               .andExpect(jsonPath("$.id").value(1L))
               .andExpect(jsonPath("$.name").value("New Product"));
    }
}