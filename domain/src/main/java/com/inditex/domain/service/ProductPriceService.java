package com.inditex.domain.service;

import com.inditex.domain.exceptions.ProductPriceNotFoundException;
import com.inditex.domain.model.ProductPrice;

import java.time.LocalDateTime;

public interface ProductPriceService {
    ProductPrice findPriceForProductAtDate(Long productId, Integer brandId, LocalDateTime queryDate) throws ProductPriceNotFoundException;
}
