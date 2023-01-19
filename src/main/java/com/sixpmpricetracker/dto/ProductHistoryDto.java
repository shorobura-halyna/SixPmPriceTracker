package com.sixpmpricetracker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProductHistoryDto {
    private Integer id;
    private String name;
    private Double oldPrise;
    private Double newPrise;
    private LocalDateTime oldDate;
    private LocalDateTime newDate;
}
