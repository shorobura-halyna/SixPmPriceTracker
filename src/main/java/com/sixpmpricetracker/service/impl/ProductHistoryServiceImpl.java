package com.sixpmpricetracker.service.impl;

import com.sixpmpricetracker.dto.ProductHistoryDto;
import com.sixpmpricetracker.mapper.ProductHistoryMapper;
import com.sixpmpricetracker.repository.ProductHistoryRepository;
import com.sixpmpricetracker.service.ProductHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductHistoryServiceImpl implements ProductHistoryService {
    private final ProductHistoryRepository productHistoryRepository;

    @Override
    public List<ProductHistoryDto> generateProductHistory() {
        return productHistoryRepository.findAll().stream()
                .map(ProductHistoryMapper.INSTANCE::productHistoryToProductHistoryDto)
                .collect(Collectors.toList());
    }
}
