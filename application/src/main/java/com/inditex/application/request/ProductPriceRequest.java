package com.inditex.application.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ProductPriceRequest(@NotNull @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss") LocalDateTime requestDateTime,
                                  @NotNull Long productId,
                                  @NotNull Integer brandId) {
}
