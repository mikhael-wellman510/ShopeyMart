package com.enigma.shopeymart.Service;

import com.enigma.shopeymart.Dto.Request.ProductRequest;
import com.enigma.shopeymart.Dto.Response.ProductResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse getByIdProduct(String id);

    List<ProductResponse> getAllProduct();

    ProductResponse updateProduct(ProductRequest productRequest);

    void deleteProduct(String id);

    ProductResponse createProductAndProductPrice(ProductRequest productRequest);

    Page<ProductResponse> getAllByNameOrPrice(String name , Long maxPrice , Integer page, Integer size);



}
