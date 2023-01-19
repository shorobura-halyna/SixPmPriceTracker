package com.sixpmpricetracker.service;

import com.sixpmpricetracker.dto.ProductHistoryDto;

import java.util.List;

public interface ProductHistoryService {
    List<ProductHistoryDto> generateProductHistory();
}
