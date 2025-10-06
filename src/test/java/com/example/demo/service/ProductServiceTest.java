package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product(null, "Test Product", 10.0, 5);
        Product saved = new Product(1L, "Test Product", 10.0, 5);
        when(productRepository.save(any(Product.class))).thenReturn(saved);
        Product result = productService.saveProduct(product);
        assertEquals(saved.getId(), result.getId());
        assertEquals(saved.getName(), result.getName());
    }

    @Test
    public void testGetAllProducts() {
        Product p1 = new Product(1L, "A", 1.0, 1);
        Product p2 = new Product(2L, "B", 2.0, 2);
        when(productRepository.findAll()).thenReturn(Arrays.asList(p1, p2));
        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
    }

    @Test
    public void testGetProductById() {
        Product p = new Product(1L, "A", 1.0, 1);
        when(productRepository.findById(1L)).thenReturn(Optional.of(p));
        Optional<Product> result = productService.getProductById(1L);
        assertTrue(result.isPresent());
        assertEquals("A", result.get().getName());
    }

    @Test
    public void testUpdateProduct() {
        Product existing = new Product(1L, "A", 1.0, 1);
        Product update = new Product(1L, "B", 2.0, 2);
        when(productRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(productRepository.save(any(Product.class))).thenReturn(update);
        Product result = productService.updateProduct(1L, update);
        assertEquals("B", result.getName());
        assertEquals(2.0, result.getPrice());
    }

    @Test
    public void testDeleteProduct() {
        doNothing().when(productRepository).deleteById(1L);
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
}
