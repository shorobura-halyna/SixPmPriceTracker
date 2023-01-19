package com.sixpmpricetracker.treaker.impl;

import com.sixpmpricetracker.model.Product;
import com.sixpmpricetracker.repository.ProductRepository;
import com.sixpmpricetracker.service.impl.PriceChecker;
import com.sixpmpricetracker.treaker.ProductTracker;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductTrackerImpl implements ProductTracker {
    private final ProductRepository productRepository;
    private final PriceChecker priceChecker;

    @Scheduled(cron = "0 * * * * *")
    @Override
    public void trackProductPrise() {
        productRepository.findAll().stream()
                .filter(Product::isTracked)
                .forEach(priceChecker::checkPrice);
    }
}
