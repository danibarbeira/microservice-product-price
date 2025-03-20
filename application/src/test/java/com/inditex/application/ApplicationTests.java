package com.inditex.application;

import com.inditex.application.request.ProductPriceRequest;
import com.inditex.application.response.ProductPriceResponse;
import com.inditex.domain.model.ProductPrice;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
class ApplicationTests {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

    @Test
    void contextLoads() {
        Assertions.assertNotNull(formatter);
    }

    @Test
    void givenNewRequest_whenCreated_thenIsNotNullAndValuesAreCorrect() {
        log.info("Checking ProductPriceRequest creation");
        LocalDateTime queryDate = LocalDateTime.parse("2020-06-14-10.00.00", formatter);
        ProductPriceRequest productPriceRequest = new ProductPriceRequest(queryDate, 3544L, 2);
        Assertions.assertEquals(3544L, productPriceRequest.productId());
        Assertions.assertEquals(queryDate, productPriceRequest.requestDateTime());
        Assertions.assertEquals(2, productPriceRequest.brandId());
    }

    @Test
    void givenNewResponse_whenCreated_thenIsNotNull() {
        log.info("Checking ProductPriceResponse creation");
        Assertions.assertNotNull(ProductPriceResponse.fromDomain(new ProductPrice()));
    }

    @AfterAll
    static void clearUp() {
        formatter = null;
    }

}
