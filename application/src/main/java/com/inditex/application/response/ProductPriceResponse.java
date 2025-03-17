package com.inditex.application.response;

import com.inditex.domain.model.ProductPrice;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ProductPriceResponse extends RepresentationModel<ProductPriceResponse> {

    private final Long productId;
    private final Integer brandId;
    private final Integer priceList;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final BigDecimal price;

    public static ProductPriceResponse fromDomain(@NotNull ProductPrice product) {
        return new ProductPriceResponse(product.getProductId(), product.getBrandId(), product.getPriceList(), product.getStartDate(), product.getEndDate(), product.getPrice());
    }
}
