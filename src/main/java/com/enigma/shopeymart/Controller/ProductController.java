package com.enigma.shopeymart.Controller;


import com.enigma.shopeymart.Constant.AppPath;
import com.enigma.shopeymart.Dto.Request.ProductRequest;
import com.enigma.shopeymart.Dto.Response.CommonResponse;
import com.enigma.shopeymart.Dto.Response.PagingResponse;
import com.enigma.shopeymart.Dto.Response.ProductResponse;
import com.enigma.shopeymart.Entity.Product;
import com.enigma.shopeymart.Entity.ProductPrice;
import com.enigma.shopeymart.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.PRODUCT)
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "/create")
    @PreAuthorize("hasRole('ADMIN')") // hanya admin yg bisa akses
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

    @GetMapping(value = "/page")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllProductPage(
            @RequestParam(name = "name" , required = false)String name,
            @RequestParam(name = "price" , required = false)Long maxPrice,
            @RequestParam(name = "page" , required = false ,defaultValue = "0") Integer page,
            @RequestParam(name = "size" , required = false , defaultValue = "5") Integer size
            ){

        // Todo -> Ini Untuk Hasil yang Akan di tampilkan
            Page<ProductResponse> productResponse = productService.getAllByNameOrPrice(name,maxPrice,page,size);
        System.out.println("Controller" + productResponse);

        // Todo -> Ini Untuk Pagging Response
        PagingResponse pagingResponse = PagingResponse.builder()
                .currentPage(page)
                .totalPage(productResponse.getTotalPages())
                .size(size)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Succes Get all Product")
                        .data(productResponse.getContent())
                        .paging(pagingResponse)
                        .build()
                );
    }
}
