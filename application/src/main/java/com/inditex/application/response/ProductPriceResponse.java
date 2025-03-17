package com.inditex.application.response;

import com.inditex.domain.model.ProductPrice;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductPriceResponse(Long productId, Integer brandId, Integer priceList, LocalDateTime startDate,
                                   LocalDateTime endDate, BigDecimal price) {
    public static ProductPriceResponse fromDomain(@NotNull ProductPrice product) {
        return new ProductPriceResponse(product.getProductId(), product.getBrandId(), product.getPriceList(), product.getStartDate(), product.getEndDate(), product.getPrice());
    }
}
