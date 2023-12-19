package com.enigma.shopeymart.Service.Impl;

import com.enigma.shopeymart.Dto.Response.ProductResponse;
import com.enigma.shopeymart.Entity.ProductPrice;

import com.enigma.shopeymart.Repositori.ProductPriceRepositori;
import com.enigma.shopeymart.Service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {


    private final ProductPriceRepositori productPriceRespositori;

    @Override
    public ProductPrice create(ProductPrice productPrice) {

        // ini karena udh di builder di product service
        return productPriceRespositori.save(productPrice);
    }

    @Override
    public ProductPrice getById(String id) {


        return productPriceRespositori.findById(id).orElse(null);
    }

    @Override
    public ProductPrice findProductPriceIsActive(String productId, Boolean active) {
        return productPriceRespositori.findByProduct_IdAndIsActive(productId,active).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"product Not Found"));

    }

    @Override
    public List<ProductPrice> getAllProductPrice() {


        return productPriceRespositori.findAll();
    }
}
