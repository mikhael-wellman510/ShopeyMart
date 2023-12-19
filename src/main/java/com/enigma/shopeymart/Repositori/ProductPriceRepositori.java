package com.enigma.shopeymart.Repositori;

import com.enigma.shopeymart.Entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


// jpa s
@Repository
public interface ProductPriceRepositori extends JpaRepository<ProductPrice,String> {

    Optional<ProductPrice> findByProduct_IdAndIsActive(String productId ,Boolean active);


}
