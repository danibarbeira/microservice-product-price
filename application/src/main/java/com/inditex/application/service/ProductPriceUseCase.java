package com.inditex.application.service;

import com.inditex.domain.exceptions.ProductPriceNotFoundException;
import com.inditex.domain.model.ProductPrice;
import com.inditex.domain.outbound.persistence.ProductPriceRepository;
import com.inditex.domain.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductPriceUseCase implements ProductPriceService {

    private final ProductPriceRepository productPriceRepository;

    @Override
    public ProductPrice findPriceForProductAtDate(Long productId, Integer brandId, LocalDateTime queryDate) throws ProductPriceNotFoundException {
        return productPriceRepository.findPriceForProductAtDate(productId, brandId, queryDate).orElseThrow(() -> new ProductPriceNotFoundException("Price for product at given date not found!"));
    }
}
