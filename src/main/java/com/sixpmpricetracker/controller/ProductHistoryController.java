package com.sixpmpricetracker.controller;

import com.sixpmpricetracker.dto.ProductHistoryDto;
import com.sixpmpricetracker.service.ProductHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/history")
@RequiredArgsConstructor
public class ProductHistoryController {
    private final ProductHistoryService productHistoryService;

    @GetMapping
    public List<ProductHistoryDto> showProductHistory() {
        return productHistoryService.generateProductHistory();
    }
}
