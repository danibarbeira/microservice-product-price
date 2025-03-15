package com.inditex.input.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductPriceResponse(Long productId, Integer brandId, Integer priceList, LocalDateTime startDate,
                                   LocalDateTime endDate, BigDecimal price) {
}
