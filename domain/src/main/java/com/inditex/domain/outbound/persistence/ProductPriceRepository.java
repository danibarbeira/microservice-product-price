package com.inditex.domain.outbound.persistence;

import com.inditex.domain.model.ProductPrice;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductPriceRepository {
    Optional<ProductPrice> findPriceForProductAtDate(Long productId, Integer brandId, LocalDateTime queryDate);
}
