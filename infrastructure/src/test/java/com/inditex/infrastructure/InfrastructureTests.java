package com.inditex.infrastructure;

import com.inditex.application.service.ProductPriceUseCase;
import com.inditex.domain.exceptions.ProductPriceNotFoundException;
import com.inditex.infrastructure.configuration.ModelMapperBean;
import com.inditex.infrastructure.handlers.GlobalExceptionHandler;
import com.inditex.infrastructure.outbound.persistence.repositories.ProductPriceJpaRepository;
import com.inditex.infrastructure.outbound.persistence.repositories.ProductPriceRepositoryAdapter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
@Slf4j
class InfrastructureTests {

    @Mock
    private ProductPriceJpaRepository productPriceJpaRepository;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
    private static ModelMapperBean modelMapperBean = new ModelMapperBean();
    private static ProductPriceUseCase productPriceUseCase;
    private static GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    private static ResponseEntity<Object> responseEntity;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(modelMapperBean);
        Assertions.assertNotNull(productPriceJpaRepository);
        Assertions.assertNotNull(globalExceptionHandler);
    }

    @Test
    void givenNewMapperBean_whenMethodIsCalledToObtain_theResultIsNotNull() {
        Assertions.assertNotNull(modelMapperBean.productPriceMapperBean());
    }

    @Test
    void givenNonExistentProduct_whenMethodIsCalledToFindPrice_thenExceptionIsThrown() {
        log.info("Checking ProductPriceNotFoundException is thrown");
        productPriceUseCase = new ProductPriceUseCase(new ProductPriceRepositoryAdapter(productPriceJpaRepository, modelMapperBean.productPriceMapperBean()));
        try {
            productPriceUseCase.findPriceForProductAtDate(54L, 2, LocalDateTime.parse("2020-06-14-00.00.00", formatter));
        } catch (ProductPriceNotFoundException e) {
            Assertions.assertEquals("Price for product at given date not found!", e.getLocalizedMessage());
        }
    }

    @Test
    void givenNewProductPriceNotFoundException_whenExceptionIsHandled_ResponseIsNotNull() {
        responseEntity = globalExceptionHandler.handleProductNotFoundException(new ProductPriceNotFoundException("Product not found!"), null);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals("Product not found!", responseEntity.getBody());
        responseEntity = null;
    }

    @Test
    void givenNewNullPointerException_whenExceptionIsHandled_ResponseIsNotNull() {
        responseEntity = globalExceptionHandler.handleNullPointerException(new NullPointerException("Null Pointer!"), null);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals("Null Pointer!", responseEntity.getBody());
        responseEntity = null;
    }

    @AfterAll
    static void clearUp() {
        modelMapperBean = null;
        productPriceUseCase = null;
        formatter = null;
        globalExceptionHandler = null;
        responseEntity = null;
    }


}
