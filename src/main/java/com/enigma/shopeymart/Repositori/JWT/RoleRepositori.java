package com.enigma.shopeymart.Repositori.JWT;

import com.enigma.shopeymart.Constant.JWT.ERole;
import com.enigma.shopeymart.Entity.JWT.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepositori extends JpaRepository<Role,String> {
    Optional<Role> findByName(ERole name); // name di ambil dri Entity
}
