package com.enigma.shopeymart.Repositori.JWT;

import com.enigma.shopeymart.Entity.JWT.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepositori extends JpaRepository<UserCredential,String> {
Optional<UserCredential> findByUsername(String username);
// Optional itu bisa ada atau tidak ....



}
