package com.enigma.shopeymart.Service;

import com.enigma.shopeymart.Dto.Response.ProductResponse;
import com.enigma.shopeymart.Entity.ProductPrice;

import java.util.List;

public interface ProductPriceService {


    ProductPrice create(ProductPrice productPrice);

    ProductPrice getById(String id);

    ProductPrice findProductPriceIsActive(String productId,Boolean active);


}
