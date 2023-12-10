package com.enigma.shopeymart.Repositori;

import com.enigma.shopeymart.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepositori extends JpaRepository<Customer,String> {

}
