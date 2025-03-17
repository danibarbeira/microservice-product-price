package com.inditex.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductPrice {

    private Long productId;
    private Integer brandId;
    private BigDecimal price;
    private Integer priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;
    private String currency;
}
