package com.sixpmpricetracker.treaker.impl;

import com.sixpmpricetracker.model.Product;
import com.sixpmpricetracker.repository.ProductRepository;
import com.sixpmpricetracker.service.impl.PriceChecker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductTrackerImplTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private PriceChecker priceChecker;
    @InjectMocks
    private ProductTrackerImpl productTracker;

    @Test
    void shouldCheckProductPrice() {
        Product product1 = new Product(1, "Candance Carryall", 29.99, "url", "url", LocalDateTime.now(), 1L, true);
        Product product2 = new Product(2, "Louie Mini", 33.99, "url", "url", LocalDateTime.now(), 2L, true);
        Product product3 = new Product(3, "Dannin Mini", 19.00, "url", "url", LocalDateTime.now(), 3L, true);
        Product product4 = new Product(4, "Lilah Mini", 132.99, "url", "url", LocalDateTime.now(), 4L, false);
        Product product5 = new Product(5, "Paulson Top-Handle", 45.00, "url", "url", LocalDateTime.now(), 5L, false);
        when(productRepository.findAll()).thenReturn(List.of(product1, product2, product3, product4, product5));

        productTracker.trackProductPrise();

        verify(priceChecker, times(1)).checkPrice(product1);
        verify(priceChecker, times(1)).checkPrice(product2);
        verify(priceChecker, times(1)).checkPrice(product3);
        verify(priceChecker, never()).checkPrice(product4);
        verify(priceChecker, never()).checkPrice(product5);

    }
}
