package com.inditex.application.response;

import com.inditex.domain.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductPriceResponse(Long productId, Integer brandId, Integer priceList, LocalDateTime startDate,
                                   LocalDateTime endDate, BigDecimal price) {
    public static ProductPriceResponse fromDomain(Product product) {
        return new ProductPriceResponse(product.getProductId(), product.getBrandId(), product.getPriceList(), product.getStartDate(), product.getEndDate(), product.getPrice());
    }
}
