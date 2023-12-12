package com.enigma.shopeymart.Repositori;

import com.enigma.shopeymart.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepositori extends JpaRepository<OrderDetail,String> {
}


