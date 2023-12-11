package com.enigma.shopeymart.Service.Impl;

import com.enigma.shopeymart.Dto.Response.ProductResponse;
import com.enigma.shopeymart.Entity.ProductPrice;

import com.enigma.shopeymart.Repositori.ProductPriceRepositori;
import com.enigma.shopeymart.Service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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



}
