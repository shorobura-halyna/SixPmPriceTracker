package com.sixpmpricetracker.service.impl;

import com.sixpmpricetracker.model.Product;
import com.sixpmpricetracker.repository.ProductHistoryRepository;
import com.sixpmpricetracker.repository.ProductRepository;
import com.sixpmpricetracker.telegram.SixPmPriceTrackerBot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class PriceChangeProcessorTest {

    @Mock
    private SixPmPriceTrackerBot sixPmPriceTrackerBot;
    @Mock
    private ProductHistoryRepository productHistoryRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private PriceChangeProcessor priceChangeProcessor;

    @Test
    void test() {
        //given
        Product productFromDb = Product.builder()
                .name("Dolce Vita")
                .trackedFrom(LocalDateTime.now())
                .url("https://www.6pm.com/p/dolce-vita-peyton-cafe-stella/product/9821950/color/915642")
                .originPrice(29.60)
                .build();

        Product productFromSite = Product.builder()
                .name("Dolce Vita")
                .trackedFrom(LocalDateTime.now())
                .url("https://www.6pm.com/p/dolce-vita-peyton-cafe-stella/product/9821950/color/915642")
                .originPrice(35.90)
                .build();

        Double productOldPrice = 21.99;
        String action = "UP";

//        Mockito.when(sixPmPriceTrackerBot.sendPriceChangedMassage(action, productFromDb, productFromSite)).thenReturn())


        //when
//        priceChangeProcessor.processPriceChange();

        //then


    }

}
