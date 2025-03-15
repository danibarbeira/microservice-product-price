package com.inditex.input.request;

import java.time.LocalDateTime;

public record ProductPriceRequest(LocalDateTime requestDateTime, Long productId, Integer brandId) {
}
