package com.sixpmpricetracker.parser.impl;

import com.sixpmpricetracker.model.Product;
import com.sixpmpricetracker.parser.ProductParser;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotinoParserImpl implements ProductParser {
    @SneakyThrows
    @Override
    public Product parse(String productUrl) {
        Document document = Jsoup.connect(productUrl).get();
        String productName = document.select("span.sc-3sotvb-4").text();
        String productBrand = document.select("a.sc-3sotvb-2").text();
        String productWithoutSalePrice = (document.select("span.sc-wpdhab-2.gdVmkM").text()).replace("zł", " ").split(" ")[0];
        String productSalePrice = (document.select("div.sc-h83s98-1").text()).replace("zł", "").split(" ")[2];
        String productPrice = checkProductPrice(productWithoutSalePrice, productSalePrice);
        if (productPrice.isEmpty()) {
            throw new IllegalArgumentException("Price should not be empty");
        }
        String imageUrl = document.select("img._imgr").attr("src");
        return Product.builder()
                .name(productBrand + " - " + productName)
                .originPrice(Double.parseDouble(productPrice))
                .imageUrl(imageUrl)
                .url(productUrl)
                .tracked(true)
                .trackedFrom(LocalDateTime.now())
                .build();
    }

    private String checkProductPrice(String productWithoutSalePrice, String productSalePrice) {
        if (productSalePrice.isEmpty()) {
            return productWithoutSalePrice.replace(",", ".");
        } else {
            return productSalePrice.replace(",", ".");
        }
    }
}
