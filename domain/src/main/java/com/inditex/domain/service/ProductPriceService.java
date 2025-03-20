package com.inditex.domain.service;

import com.inditex.domain.exceptions.ProductPriceNotFoundException;
import com.inditex.domain.model.ProductPrice;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface ProductPriceService {
    Mono<ProductPrice> findPriceForProductAtDate(Long productId, Integer brandId, LocalDateTime queryDate) throws ProductPriceNotFoundException;
}
