package com.inditex.infrastructure.outbound.persistence.repositories;

import com.inditex.domain.model.ProductPrice;
import com.inditex.domain.outbound.persistence.ProductPriceRepository;
import com.inditex.infrastructure.outbound.persistence.entities.ProductPriceEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductPriceRepositoryAdapter implements ProductPriceRepository {

    private final ProductPriceJpaRepository productPriceJpaRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<ProductPrice> findPriceForProductAtDate(Long productId, Integer brandId, LocalDateTime queryDate) {
        List<ProductPriceEntity> productPriceEntities = productPriceJpaRepository.findProductPriceAtDate(productId, brandId, queryDate);
        return Optional.ofNullable(CollectionUtils.isEmpty(productPriceEntities) ? null : modelMapper.map(productPriceEntities.getFirst(), ProductPrice.class));
    }
}
