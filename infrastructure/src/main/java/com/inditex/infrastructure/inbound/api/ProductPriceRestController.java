package com.inditex.infrastructure.inbound.api;

import com.inditex.application.inbound.api.ProductPriceController;
import com.inditex.application.request.ProductPriceRequest;
import com.inditex.application.response.ProductPriceResponse;
import com.inditex.domain.service.ProductPriceService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.BasicLinkBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductPriceRestController implements ProductPriceController {

    private final ProductPriceService productPriceService;

    private ProductPriceResponse productPriceResponse = null;

    @PostMapping(value = "/price")
    public Mono<ProductPriceResponse> obtainProductPrice(@RequestBody ProductPriceRequest productPriceRequest) {

        val product = productPriceService.findPriceForProductAtDate(productPriceRequest.productId(), productPriceRequest.brandId(), productPriceRequest.requestDateTime());

        product.subscribe(value -> productPriceResponse = toHateoas(ProductPriceResponse.fromDomain(value)));

        return Mono.just(productPriceResponse);
    }

    private ProductPriceResponse toHateoas(@NotNull ProductPriceResponse productPriceResponse) {

        String baseUri = BasicLinkBuilder.linkToCurrentMapping().toString();

        StringBuilder str = new StringBuilder(baseUri);
        str.append("/product/price/");
        str.append(productPriceResponse.getProductId()).append("_").append(productPriceResponse.getBrandId()).append("_").append(productPriceResponse.getPriceList());

        return productPriceResponse.add(Link.of(str.toString(), IanaLinkRelations.SELF));
    }

}
