package com.enigma.shopeymart.Repositori;

import com.enigma.shopeymart.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StoreRepositori extends JpaRepository<Store,String> {
    // Query method jika native
}
