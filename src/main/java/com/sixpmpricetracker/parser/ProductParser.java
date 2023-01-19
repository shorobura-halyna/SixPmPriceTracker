package com.sixpmpricetracker.parser;

import com.sixpmpricetracker.model.Product;

public interface ProductParser {
    Product parse(String productUrl);
}
