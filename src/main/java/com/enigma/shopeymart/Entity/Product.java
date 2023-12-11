package com.enigma.shopeymart.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="m_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Product {

    // id, Name , Description
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="name", nullable = false , length = 30)
    private String name ;

    @Column(name = "description" , nullable = false , length = 100)
    private String description;

    // masukan relasi
    @OneToMany(mappedBy = "product")// biar tidak di buat table conjungtion
    private List<ProductPrice> productPrices;
}
