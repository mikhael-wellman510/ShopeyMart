package com.enigma.shopeymart.Controller;


import com.enigma.shopeymart.Constant.AppPath;
import com.enigma.shopeymart.Dto.Request.ProductRequest;
import com.enigma.shopeymart.Dto.Response.CommonResponse;
import com.enigma.shopeymart.Dto.Response.ProductResponse;
import com.enigma.shopeymart.Entity.Product;
import com.enigma.shopeymart.Entity.ProductPrice;
import com.enigma.shopeymart.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.PRODUCT)
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "/tes")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest){
       // ini hasil yg di kirim
        ProductResponse productResponse = productService.createProductAndProductPrice(productRequest);

        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<ProductResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully crated new Product")
                        .data(productResponse)
                        .build()) ;

    }

    @GetMapping(value = "/getAllProducts")
    public List<ProductResponse> getAll(){
        return productService.getAllProduct();
    }

    @GetMapping(value = "/getProductById/{id}")
    public ResponseEntity<?> getProdukById(@PathVariable String id){
       ProductResponse productResponse = productService.getByIdProduct(id);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<ProductResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Succesfull getById")
                        .data(productResponse)
                        .build()
                );
    }
}
