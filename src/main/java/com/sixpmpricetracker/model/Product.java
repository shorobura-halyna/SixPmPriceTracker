package com.sixpmpricetracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double originPrice;
    @Column(length = 1000)
    private String url;
    private String imageUrl;
    private LocalDateTime trackedFrom;
    private Long chatId;
    private boolean tracked;
}
