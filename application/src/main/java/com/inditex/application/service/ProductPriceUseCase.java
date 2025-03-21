package com.inditex.application.service;

import com.inditex.domain.exceptions.ProductPriceNotFoundException;
import com.inditex.domain.model.ProductPrice;
import com.inditex.domain.outbound.persistence.ProductPriceRepository;
import com.inditex.domain.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductPriceUseCase implements ProductPriceService {

    private final ProductPriceRepository productPriceRepository;

    @Override
    public Mono<ProductPrice> findPriceForProductAtDate(Long productId, Integer brandId, LocalDateTime queryDate) throws ProductPriceNotFoundException {

        Optional<ProductPrice> productPriceOptional = productPriceRepository.findPriceForProductAtDate(productId, brandId, queryDate);

        if (productPriceOptional.isPresent())
            return Mono.just(productPriceOptional.get());
        else
            throw new ProductPriceNotFoundException("Price for product at given date not found!");
    }
}
