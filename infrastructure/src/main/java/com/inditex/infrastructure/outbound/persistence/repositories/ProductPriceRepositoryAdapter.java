package com.inditex.infrastructure.outbound.persistence.repositories;

import com.inditex.domain.model.ProductPrice;
import com.inditex.domain.outbound.persistence.ProductPriceRepository;
import com.inditex.infrastructure.outbound.persistence.entities.ProductPriceEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductPriceRepositoryAdapter implements ProductPriceRepository {

    private final ProductPriceJpaRepository productPriceJpaRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<ProductPrice> findPriceForProductAtDate(Long productId, Integer brandId, LocalDateTime queryDate) {
        ProductPriceEntity productPriceEntity = productPriceJpaRepository.findProductPriceAtDate(productId, brandId, queryDate);
        return Optional.ofNullable(Objects.isNull(productPriceEntity) ? null : modelMapper.map(productPriceEntity, ProductPrice.class));
    }
}
