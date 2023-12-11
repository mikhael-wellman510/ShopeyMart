package com.enigma.shopeymart.Repositori;

import com.enigma.shopeymart.Entity.Product;
import com.enigma.shopeymart.Entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositori extends JpaRepository<Product,String> {
}
