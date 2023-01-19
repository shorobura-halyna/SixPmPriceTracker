package com.sixpmpricetracker.parser.impl;

import com.sixpmpricetracker.model.Product;
import com.sixpmpricetracker.parser.ProductParser;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SixPmParserImp implements ProductParser {

    @Override
    @SneakyThrows
    public Product parse(String productUrl) {
        Document document = Jsoup.connect(productUrl).get();
        String productName = document.select("span.kp-z").text();
        String productBrand = document.select("span[itemprop=\"name\"]").text();
        String productPrice = document.select("span[aria-hidden]").text().replace("$", "");
        if (productPrice.isEmpty()) {
            throw new IllegalArgumentException("Price should not be empty");
        }
        String imageUrl = document.select("button.gV-z > picture > img").attr("src");
        return Product.builder()
                .name(productBrand + " - " + productName)
                .originPrice(Double.parseDouble(productPrice))
                .imageUrl(imageUrl)
                .url(productUrl)
                .tracked(true)
                .trackedFrom(LocalDateTime.now())
                .build();
    }
}
