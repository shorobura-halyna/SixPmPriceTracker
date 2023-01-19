package com.sixpmpricetracker.parser.impl;

import com.sixpmpricetracker.model.Product;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ProductParserImplTest {

    private static final String PRODUCT_URL = "productUrl";
    private static final String PRODUCT_NAME = "productName";
    private static final String PRODUCT_BRAND = "productBrand";
    private static final String PRODUCT_PRICE = "22.22";
    private static final String IMAGE_URL = "imageUrl";
    private static final LocalDateTime LOCAL_DATE_TIME_NOW = LocalDateTime.now();
    // command + shift + U -> upper/lower case
    // command + option + C -> move variable to constant
    // shift + fn + F6 -> rename
    // command + option + M -> extract to method

//    private final ProductParserImpl productParser = new ProductParserImpl();

    @SneakyThrows
    @Test
    void shouldParseProductFromProductUrl() {
        Product expectedProduct = buildProduct();

        Connection mockedConnection = mock(Connection.class);

        try (MockedStatic<Jsoup> mockedJsoup = mockStatic(Jsoup.class);
             MockedStatic<LocalDateTime> mockedLocalDateTime = mockStatic(LocalDateTime.class)) {
            mockedJsoup.when(() -> Jsoup.connect(PRODUCT_URL)).thenReturn(mockedConnection);
            mockedLocalDateTime.when(LocalDateTime::now).thenReturn(LOCAL_DATE_TIME_NOW);

            mockJsoupDocumentParameters(mockedConnection);

//            Product actualProduct = productParser.parse(PRODUCT_URL);

//            assertThat(actualProduct).isEqualTo(expectedProduct);
        }
    }

    private void mockJsoupDocumentParameters(Connection mockedConnection) throws IOException {
        Elements productNameElements = mock(Elements.class);
        Elements productBrandElements = mock(Elements.class);
        Elements productPriceElements = mock(Elements.class);
        Elements productImageUrlElements = mock(Elements.class);
        Document mockedDocument = mock(Document.class);

        when(mockedConnection.get()).thenReturn(mockedDocument);
        when(mockedDocument.select("span.kp-z")).thenReturn(productNameElements);
        when(mockedDocument.select("span[itemprop=\"name\"]")).thenReturn(productBrandElements);
        when(mockedDocument.select("span[aria-hidden]")).thenReturn(productPriceElements);
        when(mockedDocument.select("button.gV-z > picture > img")).thenReturn(productImageUrlElements);

        when(productNameElements.text()).thenReturn(PRODUCT_NAME);
        when(productBrandElements.text()).thenReturn(PRODUCT_BRAND);
        when(productPriceElements.text()).thenReturn("$" + PRODUCT_PRICE);
        when(productImageUrlElements.attr("src")).thenReturn(IMAGE_URL);
    }

    private Product buildProduct() {
        return Product.builder()
                .name(PRODUCT_BRAND + " - " + PRODUCT_NAME)
                .originPrice(22.22)
                .imageUrl(IMAGE_URL)
                .url(PRODUCT_URL)
                .tracked(true)
                .trackedFrom(LOCAL_DATE_TIME_NOW)
                .build();
    }

}
