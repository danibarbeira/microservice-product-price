package com.inditex.application.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProductPriceRequest(@NotNull LocalDateTime requestDateTime, @NotNull Long productId,
                                  @NotNull Integer brandId) {
}
