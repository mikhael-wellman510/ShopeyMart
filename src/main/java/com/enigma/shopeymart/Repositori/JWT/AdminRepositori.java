package com.enigma.shopeymart.Repositori.JWT;

import com.enigma.shopeymart.Entity.JWT.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepositori extends JpaRepository<Admin,String> {
}
