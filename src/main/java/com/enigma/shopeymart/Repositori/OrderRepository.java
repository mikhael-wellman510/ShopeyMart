package com.enigma.shopeymart.Repositori;

import com.enigma.shopeymart.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> , JpaSpecificationExecutor<Order> {

}
