package com.sixpmpricetracker.service.impl;

import com.sixpmpricetracker.model.Product;
import com.sixpmpricetracker.model.ProductHistory;
import com.sixpmpricetracker.repository.ProductHistoryRepository;
import com.sixpmpricetracker.repository.ProductRepository;
import com.sixpmpricetracker.telegram.SixPmPriceTrackerBot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceChangeProcessor {
    private final SixPmPriceTrackerBot sixPmPriceTrackerBot;
    private final ProductHistoryRepository productHistoryRepository;
    private final ProductRepository productRepository;

    public void processPriceChange(Product productFromDb, Product productFromSite, Double productOldPrice, String action) {
        sixPmPriceTrackerBot.sendPriceChangedMassage(action, productFromDb, productFromSite);
        productFromDb.setOriginPrice(productFromSite.getOriginPrice());
        productRepository.save(productFromDb);
        ProductHistory productHistory = ProductHistory.builder()
                .name(productFromDb.getName())
                .oldPrise(productOldPrice)
                .newPrise(productFromSite.getOriginPrice())
                .oldDate(productFromDb.getTrackedFrom())
                .newDate(LocalDateTime.now())
                .build();
        productHistoryRepository.save(productHistory);
    }
}
