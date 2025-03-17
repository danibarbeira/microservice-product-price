package com.inditex.infrastructure.outbound.persistence.entities;

import com.inditex.infrastructure.outbound.persistence.entities.id.ProductPriceId;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
@IdClass(ProductPriceId.class)
@Data
public class ProductPriceEntity {

    @Id
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Id
    @Column(name = "BRAND_ID")
    private Integer brandId;

    @Id
    @Column(name = "PRICE_LIST")
    private Integer priceList;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "PRIORITY", nullable = false)
    private Integer priority;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

}
