package com.inditex.infrastructure.outbound.persistence.repositories;

import com.inditex.infrastructure.outbound.persistence.entities.ProductPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ProductPriceJpaRepository extends JpaRepository<ProductPriceEntity, Long> {

    @Query("SELECT p FROM ProductPriceEntity p WHERE p.productId=?1 and p.brandId=?2 and ?3 BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC LIMIT 1")
    ProductPriceEntity findProductPriceAtDate(Long productId, Integer brandId, LocalDateTime queryDate);
}
