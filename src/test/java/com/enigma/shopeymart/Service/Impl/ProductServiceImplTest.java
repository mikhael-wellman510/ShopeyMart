package com.enigma.shopeymart.Service.Impl;

import com.enigma.shopeymart.Dto.Request.ProductRequest;
import com.enigma.shopeymart.Dto.Request.StoreRequest;
import com.enigma.shopeymart.Dto.Response.ProductResponse;
import com.enigma.shopeymart.Dto.Response.StoreResponse;
import com.enigma.shopeymart.Entity.Product;
import com.enigma.shopeymart.Entity.ProductPrice;
import com.enigma.shopeymart.Repositori.ProductRepositori;
import com.enigma.shopeymart.Service.ProductPriceService;
import com.enigma.shopeymart.Service.ProductService;
import com.enigma.shopeymart.Service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceImplTest {
    private final ProductRepositori productRepositori = mock(ProductRepositori.class);
    private final ProductPriceService productPriceService = mock(ProductPriceService.class);
    private final StoreService storeService = mock(StoreService.class);
    private final ProductService productService = new ProductServiceImpl(productRepositori,storeService,productPriceService);
    private final StoreRequest storeRequest = mock(StoreRequest.class);


    @BeforeEach
    public void setUp(){
        reset(productPriceService,productRepositori,storeService);
    }

    @Test
    void createProductAndProductPrice(){
        //store request, seharusnya store request tapi ambilnya dari response
        StoreResponse dummyStore = new StoreResponse();
        dummyStore.setId("storeId");
        dummyStore.setStoreName("semoga sehat");
        dummyStore.setNoSiup("123456");

        when(storeService.getByIdStores(anyString())).thenReturn(dummyStore);

        //mock product thaht will be save
        Product saveProduct = new Product();

        saveProduct.setName("Oreo");
        saveProduct.setDescription("Hitam nikmat");
        //TODO -> Create Product
        when(productRepositori.saveAndFlush(any(Product.class))).thenReturn(saveProduct);

        // Todo -> Buat Product Price (Product Request)
        ProductRequest dummyRequest = mock(ProductRequest.class);

        // Todo - when
        when(dummyRequest.getStoreId()).thenReturn(StoreResponse.builder().id("storeId").build());
        when(dummyRequest.getProductName()).thenReturn(saveProduct.getName());
        when(dummyRequest.getDescription()).thenReturn(saveProduct.getDescription());
        when(dummyRequest.getPrice()).thenReturn(10000L);
        when(dummyRequest.getStock()).thenReturn(10);

        // Call Method create
        ProductResponse productResponse = productService.createProductAndProductPrice(dummyRequest);

        // Validate response
        assertNotNull(productResponse);
        assertEquals(saveProduct.getName(), productResponse.getProductName());

        //validate that the poduct price was set correct
        assertEquals(dummyRequest.getPrice() ,productResponse.getPrice());
        assertEquals(dummyRequest.getStock() , productResponse.getStock());

        //Validate interaction with store
        assertEquals(dummyStore.getId() ,productResponse.getStore().getId());

        // validate
        verify(storeService).getByIdStores(anyString());
        verify(productRepositori).saveAndFlush(any(Product.class));
        verify(productPriceService).create(any(ProductPrice.class));
    }
}