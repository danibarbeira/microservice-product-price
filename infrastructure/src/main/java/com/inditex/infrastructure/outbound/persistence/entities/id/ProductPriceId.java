package com.inditex.infrastructure.outbound.persistence.entities.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPriceId implements Serializable {

    private Long productId;

    private Integer brandId;

    private Integer priceList;

}
