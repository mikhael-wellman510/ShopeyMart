package com.enigma.shopeymart.Repositori;

import com.enigma.shopeymart.Entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepositori extends JpaRepository<ProductPrice,String> {
}
