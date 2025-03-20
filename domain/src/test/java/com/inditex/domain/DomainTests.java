package com.inditex.domain;

import com.inditex.domain.exceptions.ProductPriceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class DomainTest {

    @Test
    void givenNewProductPriceNotFoundException_whenCreated_thenMessageIsEquals() {
        log.info("Checking ProductPriceResponse creation");
        Assertions.assertEquals("Product Price not found!", new ProductPriceNotFoundException("Product Price not found!").getLocalizedMessage());
    }
}
