package com.sixpmpricetracker.service.impl;

import com.sixpmpricetracker.parser.PriceParserFactory;
import com.sixpmpricetracker.model.Product;
import com.sixpmpricetracker.parser.ProductParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.sixpmpricetracker.service.impl.PriceChecker.PriceChangeAction.DOWN;
import static com.sixpmpricetracker.service.impl.PriceChecker.PriceChangeAction.UP;

@Service
@RequiredArgsConstructor
public class PriceChecker {
    private final PriceChangeProcessor priceChangeProcessor;
    private final PriceParserFactory priceParserFactory;

    public void checkPrice(Product productFromDb) {
        ProductParser productParser = priceParserFactory.getProductParser(productFromDb.getUrl());
        Product productFromSite = productParser.parse(productFromDb.getUrl());
        Double productOldPrice = productFromDb.getOriginPrice();
        if (productFromDb.getOriginPrice() > productFromSite.getOriginPrice()) {
            priceChangeProcessor.processPriceChange(productFromDb, productFromSite, productOldPrice, DOWN.name());
        } else if (productFromDb.getOriginPrice() < productFromSite.getOriginPrice()) {
            priceChangeProcessor.processPriceChange(productFromDb, productFromSite, productOldPrice, UP.name());
        }
    }

    enum PriceChangeAction {
        UP, DOWN
    }

}
