package com.sixpmpricetracker.parser.impl;

import com.sixpmpricetracker.model.Product;
import com.sixpmpricetracker.parser.ProductParser;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ModivoProductParserImpl implements ProductParser {

    @SneakyThrows
    @Override
    public Product parse(String productUrl) {
        Document document = Jsoup.connect(productUrl).get();
        String productName = document.select("h1 > strong.name").text();
        String productBrand = document.select("h1 > strong.brand").text();
        String productWithoutSalePrice = (document.select("div.price").text()).replace("zł", " ").split(" ")[0];
        String productSalePrice = document.select("div.price.is-sale").text().replace(" zł", "");
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
