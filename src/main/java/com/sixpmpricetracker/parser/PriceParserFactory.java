package com.sixpmpricetracker.parser;

import com.sixpmpricetracker.parser.impl.ModivoProductParserImpl;
import com.sixpmpricetracker.parser.impl.NotinoParserImpl;
import com.sixpmpricetracker.parser.impl.SixPmParserImp;
import com.sixpmpricetracker.parser.impl.VictoriasSecretParserImpl;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PriceParserFactory {
    private final Map<Shops, ProductParser> shopsToProductParserMap;

    public PriceParserFactory(SixPmParserImp sixPmParserImp,
                              ModivoProductParserImpl modivoProductParser,
                              VictoriasSecretParserImpl victoriasSecretParser,
                              NotinoParserImpl notinoParser) {
        shopsToProductParserMap = Map.of(
                Shops.SIX_PM, sixPmParserImp,
                Shops.MODIVO, modivoProductParser,
                Shops.VICTORIASSICRET, victoriasSecretParser,
                Shops.NOTINO, notinoParser
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
        }else if (productUrl.contains(Shops.VICTORIASSICRET.getValue())) {
            return PriceParserFactory.Shops.VICTORIASSICRET;
        }else if (productUrl.contains(Shops.NOTINO.getValue())) {
            return PriceParserFactory.Shops.NOTINO;
        } else {
            throw new IllegalArgumentException("Unknown shop to parse");
        }
    }

    private enum Shops {
        SIX_PM("6pm"), MODIVO("modivo"), VICTORIASSICRET("victoriassecret"), NOTINO("notino");

        private final String name;

        Shops(String value) {
            this.name = value;
        }

        public String getValue() {
            return name;
        }

    }
}
