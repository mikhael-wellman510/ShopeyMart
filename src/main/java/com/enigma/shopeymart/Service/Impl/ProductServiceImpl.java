package com.enigma.shopeymart.Service.Impl;

import com.enigma.shopeymart.Dto.Request.ProductRequest;
import com.enigma.shopeymart.Dto.Response.ProductResponse;
import com.enigma.shopeymart.Dto.Response.StoreResponse;
import com.enigma.shopeymart.Entity.Customer;
import com.enigma.shopeymart.Entity.Product;
import com.enigma.shopeymart.Entity.ProductPrice;
import com.enigma.shopeymart.Entity.Store;
import com.enigma.shopeymart.Repositori.ProductPriceRepositori;
import com.enigma.shopeymart.Repositori.ProductRepositori;
import com.enigma.shopeymart.Service.ProductPriceService;
import com.enigma.shopeymart.Service.ProductService;
import com.enigma.shopeymart.Service.StoreService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepositori productRepositori;
    private final StoreService storeService;
    private final ProductPriceRepositori productPriceRepositori;

    // ambil objek untuk bawa method ke sini
    private final ProductPriceService productPriceService;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        return null;
    }

    @Override
    public ProductResponse getByIdProduct(String id) {
        Product product = productRepositori.findById(id).orElse(null);

        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getName())
                .description(product.getDescription())
                // kalau mau bawa tabel productPrices
                .productPrice(product.getProductPrices())
                .build();
    }

    @Override
    public List<ProductResponse> getAllProduct() {


    // masukan
        List<Product> products= productRepositori.findAll();

        return products.stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .productName(product.getName())
                        .description(product.getDescription())
                        .productPrice(product.getProductPrices())
                        .build()).toList();
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest) {
        return null;
    }

    @Override
    public void deleteProduct(String id) {


    }

    @Transactional(rollbackOn = Exception.class) // Untuk Rolback jika ada yg gagal
    @Override
    public ProductResponse createProductAndProductPrice(ProductRequest productRequest) {
        // mendapatkan id store
        StoreResponse storeResponse = storeService.getByIdStores(productRequest.getStoreId().getId());
        System.out.println("ini adalah store response : " +storeResponse);

        // *** bikin PRODUK
        Product product = Product.builder()
                .name(productRequest.getProductName())
                .description(productRequest.getDescription())
                .build();

        // Create product
        productRepositori.saveAndFlush(product);

        // *** membuat product price //
        ProductPrice productPrice = ProductPrice.builder()
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .isActive(true)
                // masukan product
                .product(product)
                .store(Store.builder()
                        // Masukan id di body request postman
                        .id(storeResponse.getId())
                        .build())
                .build();

        // Ini di kirim ke productPriceService yg dari body
        productPriceService.create(productPrice);

        // Tampilkan product nya
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getName())
                .description(product.getDescription())
                .price(productPrice.getPrice())
                .stock(productPrice.getStock())
                .store(StoreResponse.builder()
                        .id(storeResponse.getId())
                        .storeName(storeResponse.getStoreName())
                        .noSiup(storeResponse.getNoSiup())
                        .storeName(storeResponse.getStoreName())
                        .address(storeResponse.getAddress())
                        .phone(storeResponse.getPhone())
                        .build())
                .build();
    }
}
