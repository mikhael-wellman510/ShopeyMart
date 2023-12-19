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
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepositori productRepositori;
    private final StoreService storeService;


    // ambil objek untuk bawa method ke sini
    private final ProductPriceService productPriceService;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        return null;
    }

    @Override
    public ProductResponse getByIdProduct(String id) {
//        Product product = productRepositori.findById(id).orElse(null);
//
//        return ProductResponse.builder()
//                .id(product.getId())
//                .productName(product.getName())
//                .description(product.getDescription())
//                // kalau mau bawa tabel productPrices
//                .productPrice(product.getProductPrices())
//                .build();

        return null;
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


        // Todo -> Mengambil data Store Id
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
                        .build())
                .build();
    }


//    ============================= 000 ================================
    // Paginasi
    @Override
    public Page<ProductResponse> getAllByNameOrPrice(String name, Long maxPrice, Integer page, Integer size) {
        // Specification untuk menentukan  kriteria pencarian , disini criteria pencarian ditandakan dengan root, root yang dimaksud adlah entity product
        Specification<Product> spesification = (root , query ,criteriaBuilder) -> {
            // Join untuk merelasikan produk dan price
            Join<Product ,ProductPrice> productPrice =root.join("productPrices");
           // Predicate dgunakan untuk menggunaakan Like dimana menggunakan kondisi pencarian
            List<Predicate> predicates = new ArrayList<>();

            if (name != null){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")) ,"%" + name.toLowerCase() + "%"));

            }

            if (maxPrice != null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(productPrice.get("price") , maxPrice ));
                System.out.println("tes");
            }

            return  query.where(predicates.toArray(new Predicate[]{})).getRestriction();

        };

        Pageable pageable = PageRequest.of(page,size);
        Page<Product> products = productRepositori.findAll(spesification,pageable);

        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : products.getContent()){
            Optional<ProductPrice> productPrice = product.getProductPrices()
                    .stream()
                   .findFirst();

            if (productPrice.isEmpty()) continue;
            Store store = productPrice.get().getStore();
            productResponses.add(toProductResponse(store,product,productPrice.get()));
        }
        return new PageImpl<>(productResponses,pageable,products.getTotalElements());
    }

    private static ProductResponse toProductResponse(Store store , Product product,ProductPrice productPrice){
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getName())
                .description(product.getDescription())
                .price(productPrice.getPrice())
                .stock(productPrice.getStock())
                .store(StoreResponse.builder()
                        .id(store.getId())
                        .storeName(store.getName())
                        .noSiup(store.getNoSiup())
                        .build())
                .build();
    }
}
