package com.enigma.shopeymart.Controller;

import com.enigma.shopeymart.Dto.Request.ProductRequest;
import com.enigma.shopeymart.Dto.Response.CommonResponse;
import com.enigma.shopeymart.Dto.Response.ProductResponse;
import com.enigma.shopeymart.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController ;


    @Mock
    private ProductService productService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createProduct() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("Oreo");
        productRequest.setPrice(100L);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId("1");
        productResponse.setProductName("Oreo");
        productResponse.setPrice(100L);


        when(productService.createProductAndProductPrice(productRequest)).thenReturn(productResponse);

        ResponseEntity<?> responseEntity = productController.createProduct(productRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        CommonResponse<ProductResponse>  commonResponse = (CommonResponse<ProductResponse>) responseEntity.getBody() ;

        assertEquals(HttpStatus.CREATED.value(),commonResponse.getStatusCode());
        assertEquals("Successfully crated new Product" ,commonResponse.getMessage());


    }
}