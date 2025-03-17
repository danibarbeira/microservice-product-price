package com.inditex.application.inbound.api;

import com.inditex.application.request.ProductPriceRequest;
import com.inditex.application.response.ProductPriceResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;

public interface ProductPriceController {
    ResponseEntity<ProductPriceResponse> obtainProductPrice(@Valid @NotNull ProductPriceRequest productPriceRequest);
}
