package com.sixpmpricetracker.parser.impl;

import com.sixpmpricetracker.model.Product;
import com.sixpmpricetracker.parser.ProductParser;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VictoriasSecretParserImpl implements ProductParser {
    @SneakyThrows
    @Override
    public Product parse(String productUrl) {
        Document document = Jsoup.connect(productUrl).get();

        // Set up Selenium web driver
//        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        System.setProperty("webdriver.chrome.driver", "C:\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Navigate to website
        driver.get(productUrl);

        // Locate the product name element and extract the text
        String productName = driver.findElement(By.cssSelector("#product-name")).getText();
        System.out.println("Product Name: " + productName);

        // Locate the product element and extract the information
        String product = driver.findElement(By.cssSelector("#product-info")).getText();
        System.out.println("Product: " + product);

        // Close the browser
        driver.quit();


//        String productName = document.select("div.sc-furvIG mbRwG prism-layout-flex prism-layout").text();
        String productBrand = document.select("h2 > productFullDetail-collection-1IG").text();
        String productWithoutSalePrice = (document.select("div.prism-price sc-PvhCy hvIMPC").text()).replace("zł", " ").split(" ")[0];
        String productSalePrice = document.select("span.prism-danger-zone").text().replace(" zł", "");
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
