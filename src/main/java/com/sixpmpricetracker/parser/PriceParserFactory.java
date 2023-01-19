package com.sixpmpricetracker.parser;

import com.sixpmpricetracker.parser.impl.ModivoProductParserImpl;
import com.sixpmpricetracker.parser.impl.SixPmParserImp;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PriceParserFactory {
    private final Map<Shops, ProductParser> shopsToProductParserMap;

    public PriceParserFactory(SixPmParserImp sixPmParserImp, ModivoProductParserImpl modivoProductParser) {
        shopsToProductParserMap = Map.of(
                Shops.SIX_PM, sixPmParserImp,
                Shops.MODIVO, modivoProductParser
        );
    }

    public ProductParser getProductParser(String productUrl) {
        Shops shopName = getShopName(productUrl);
        return shopsToProductParserMap.get(shopName);
    }

    private PriceParserFactory.Shops getShopName(String productUrl) {
        if (productUrl.contains(Shops.SIX_PM.getValue())) {
            return PriceParserFactory.Shops.SIX_PM;
        } else if (productUrl.contains(Shops.MODIVO.getValue())) {
            return PriceParserFactory.Shops.MODIVO;
        } else {
            throw new IllegalArgumentException("Unknown shop to parse");
        }
    }

    private enum Shops {
        SIX_PM("6pm"), MODIVO("modivo");

        private final String name;

        Shops(String value) {
            this.name = value;
        }

        public String getValue() {
            return name;
        }

    }
}
