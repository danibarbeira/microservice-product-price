package com.inditex.starter;

import com.inditex.application.request.ProductPriceRequest;
import com.inditex.application.response.ProductPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class ProductPriceServiceApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
    private String url;
    private URI uri;

    @BeforeEach
    void init() throws URISyntaxException {
        url = "http://localhost:".concat(String.valueOf(port)).concat("/product/price");
        uri = new URI(url);
    }

    @AfterEach
    void clearUp() {
        url = null;
        uri = null;
    }

    @Test
    void contextLoads() {

    }

    @Test
    void givenProductNotExists_whenProductPriceIsCalculated_then404IsReceived() {
        log.info("Checking response status code 404 with non existent product");
        ProductPriceRequest productPriceRequest = new ProductPriceRequest(LocalDateTime.parse("2020-06-14-00.00.00", formatter), 35456L, 15);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<ProductPriceRequest> request = new HttpEntity<>(productPriceRequest, headers);

        ResponseEntity<String> response = this.restTemplate.postForEntity(uri, request, String.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
        Assertions.assertEquals("Price for product at given date not found!", response.getBody());
    }

    @Test
    void givenEmptyBody_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson() {
        log.info("Checking response content type on request with empty body");
        String expectedMimeType = "application/problem+json";
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<ProductPriceRequest> request = new HttpEntity<>(null, headers);

        ResponseEntity<ProductPriceResponse> response = this.restTemplate.postForEntity(uri, request, ProductPriceResponse.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
        Assertions.assertEquals(expectedMimeType, Objects.requireNonNull(response.getHeaders().getContentType()).toString());
    }

    @Test
    void givenProductExistsAndHasPrice_whenProductPriceIsCalculatedAtFirstDate_then200AndPriceIsReceived() {
        log.info("Checking response status code 200 and price of the product on date 2020-06-14-10.00.00");
        BigDecimal expectedPrice = BigDecimal.valueOf(35.50);
        LocalDateTime queryDate = LocalDateTime.parse("2020-06-14-10.00.00", formatter);
        ProductPriceRequest productPriceRequest = new ProductPriceRequest(queryDate, 35455L, 1);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<ProductPriceRequest> request = new HttpEntity<>(productPriceRequest, headers);

        ResponseEntity<ProductPriceResponse> response = this.restTemplate.postForEntity(uri, request, ProductPriceResponse.class);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        Assertions.assertEquals(0, expectedPrice.compareTo(Objects.requireNonNull(response.getBody()).getPrice()));
        Assertions.assertEquals(35455L, response.getBody().getProductId());
        Assertions.assertEquals(1, response.getBody().getBrandId());
        Assertions.assertEquals(1, response.getBody().getPriceList());
        Assertions.assertTrue(queryDate.isAfter(response.getBody().getStartDate()));
        Assertions.assertTrue(queryDate.isBefore(response.getBody().getEndDate()));
    }

    @Test
    void givenProductExistsAndHasPrice_whenProductPriceIsCalculatedAtSecondDate_then200AndPriceIsReceived() {
        log.info("Checking response status code 200 and price of the product on date 2020-06-14-16.00.00");
        BigDecimal expectedPrice = BigDecimal.valueOf(25.45);
        LocalDateTime queryDate = LocalDateTime.parse("2020-06-14-16.00.00", formatter);
        ProductPriceRequest productPriceRequest = new ProductPriceRequest(queryDate, 35455L, 1);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<ProductPriceRequest> request = new HttpEntity<>(productPriceRequest, headers);

        ResponseEntity<ProductPriceResponse> response = this.restTemplate.postForEntity(uri, request, ProductPriceResponse.class);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        Assertions.assertEquals(0, expectedPrice.compareTo(Objects.requireNonNull(response.getBody()).getPrice()));
        Assertions.assertEquals(35455L, response.getBody().getProductId());
        Assertions.assertEquals(1, response.getBody().getBrandId());
        Assertions.assertEquals(2, response.getBody().getPriceList());
        Assertions.assertTrue(queryDate.isAfter(response.getBody().getStartDate()));
        Assertions.assertTrue(queryDate.isBefore(response.getBody().getEndDate()));
    }

    @Test
    void givenProductExistsAndHasPrice_whenProductPriceIsCalculatedAtThirdDate_then200AndPriceIsReceived() {
        log.info("Checking response status code 200 and price of the product on date 2020-06-14-21.00.00");
        BigDecimal expectedPrice = BigDecimal.valueOf(35.50);
        LocalDateTime queryDate = LocalDateTime.parse("2020-06-14-21.00.00", formatter);
        ProductPriceRequest productPriceRequest = new ProductPriceRequest(queryDate, 35455L, 1);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<ProductPriceRequest> request = new HttpEntity<>(productPriceRequest, headers);

        ResponseEntity<ProductPriceResponse> response = this.restTemplate.postForEntity(uri, request, ProductPriceResponse.class);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        Assertions.assertEquals(0, expectedPrice.compareTo(Objects.requireNonNull(response.getBody()).getPrice()));
        Assertions.assertEquals(35455L, response.getBody().getProductId());
        Assertions.assertEquals(1, response.getBody().getBrandId());
        Assertions.assertEquals(1, response.getBody().getPriceList());
        Assertions.assertTrue(queryDate.isAfter(response.getBody().getStartDate()));
        Assertions.assertTrue(queryDate.isBefore(response.getBody().getEndDate()));
    }

    @Test
    void givenProductExistsAndHasPrice_whenProductPriceIsCalculatedAtFourthDate_then200AndPriceIsReceived() {
        log.info("Checking response status code 200 and price of the product on date 2020-06-15-10.00.00");
        BigDecimal expectedPrice = BigDecimal.valueOf(30.50);
        LocalDateTime queryDate = LocalDateTime.parse("2020-06-15-10.00.00", formatter);
        ProductPriceRequest productPriceRequest = new ProductPriceRequest(queryDate, 35455L, 1);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<ProductPriceRequest> request = new HttpEntity<>(productPriceRequest, headers);

        ResponseEntity<ProductPriceResponse> response = this.restTemplate.postForEntity(uri, request, ProductPriceResponse.class);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        Assertions.assertEquals(0, expectedPrice.compareTo(Objects.requireNonNull(response.getBody()).getPrice()));
        Assertions.assertEquals(35455L, response.getBody().getProductId());
        Assertions.assertEquals(1, response.getBody().getBrandId());
        Assertions.assertEquals(3, response.getBody().getPriceList());
        Assertions.assertTrue(queryDate.isAfter(response.getBody().getStartDate()));
        Assertions.assertTrue(queryDate.isBefore(response.getBody().getEndDate()));
    }

    @Test
    void givenProductExistsAndHasPrice_whenProductPriceIsCalculatedAtFifthDate_then200AndPriceIsReceived() {
        log.info("Checking response status code 200 and price of the product on date 2020-06-16-21.00.00 ");
        BigDecimal expectedPrice = BigDecimal.valueOf(38.95);
        LocalDateTime queryDate = LocalDateTime.parse("2020-06-16-21.00.00", formatter);
        ProductPriceRequest productPriceRequest = new ProductPriceRequest(queryDate, 35455L, 1);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<ProductPriceRequest> request = new HttpEntity<>(productPriceRequest, headers);

        ResponseEntity<ProductPriceResponse> response = this.restTemplate.postForEntity(uri, request, ProductPriceResponse.class);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        Assertions.assertEquals(0, expectedPrice.compareTo(Objects.requireNonNull(response.getBody()).getPrice()));
        Assertions.assertEquals(35455L, response.getBody().getProductId());
        Assertions.assertEquals(1, response.getBody().getBrandId());
        Assertions.assertEquals(4, response.getBody().getPriceList());
        Assertions.assertTrue(queryDate.isAfter(response.getBody().getStartDate()));
        Assertions.assertTrue(queryDate.isBefore(response.getBody().getEndDate()));
    }

}
