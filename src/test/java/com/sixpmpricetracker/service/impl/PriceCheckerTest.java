//package com.sixpmpricetracker.service.impl;
//
//import com.sixpmpricetracker.model.Product;
//import com.sixpmpricetracker.parser.ProductParser;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//
//import static com.sixpmpricetracker.service.impl.PriceChecker.PriceChangeAction.DOWN;
//import static com.sixpmpricetracker.service.impl.PriceChecker.PriceChangeAction.UP;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class PriceCheckerTest {
//
//    private static final String PRODUCT_URL = "productUrl";
//    private static final String PRODUCT_NAME = "productName";
//    private static final String PRODUCT_BRAND = "productBrand";
//    private static final double FIRST_PRICE = 22.22;
//    private static final double SECOND_PRICE = 32.22;
//    private static final String IMAGE_URL = "imageUrl";
//    private static final LocalDateTime LOCAL_DATE_TIME_NOW = LocalDateTime.now();
//
//    @Mock
//    private ProductParser productParser;
//    @Mock
//    private PriceChangeProcessor priceChangeProcessor;
//    @InjectMocks
//    private PriceChecker priceChecker;
//
//    @Test
//    void checkPriceGoesUp() {
//        Product productFromDb = buildProduct(FIRST_PRICE);
//        Product productFromSite = buildProduct(SECOND_PRICE);
//        when(productParser.parse(PRODUCT_URL)).thenReturn(productFromSite);
//
//        priceChecker.checkPrice(productFromDb);
//
//        verify(productParser).parse(PRODUCT_URL);
//        verify(priceChangeProcessor).processPriceChange(productFromDb, productFromSite, FIRST_PRICE, UP.name());
//        verify(priceChangeProcessor, never()).processPriceChange(productFromDb, productFromSite, FIRST_PRICE, DOWN.name());
//    }
//
//    @Test
//    void checkPriceGoesDown() {
//        Product productFromDb = buildProduct(SECOND_PRICE);
//        Product productFromSite = buildProduct(FIRST_PRICE);
//        when(productParser.parse(PRODUCT_URL)).thenReturn(productFromSite);
//
//        priceChecker.checkPrice(productFromDb);
//
//        verify(productParser).parse(PRODUCT_URL);
//        verify(priceChangeProcessor).processPriceChange(productFromDb, productFromSite, SECOND_PRICE, DOWN.name());
//        verify(priceChangeProcessor, never()).processPriceChange(productFromDb, productFromSite, SECOND_PRICE, UP.name());
//    }
//
//    private Product buildProduct(double price) {
//        return Product.builder()
//                .name(PRODUCT_BRAND + " - " + PRODUCT_NAME)
//                .originPrice(price)
//                .imageUrl(IMAGE_URL)
//                .url(PRODUCT_URL)
//                .tracked(true)
//                .trackedFrom(LOCAL_DATE_TIME_NOW)
//                .build();
//    }
//
//}
