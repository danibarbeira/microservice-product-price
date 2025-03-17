package com.inditex.domain.exceptions;

public class ProductPriceNotFoundException extends RuntimeException {
    public ProductPriceNotFoundException(String message) {
        super(message);
    }
}
