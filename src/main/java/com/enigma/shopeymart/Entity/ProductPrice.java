package com.enigma.shopeymart.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "m_product_price")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductPrice {

    //id , stock , isActive(boolean), price(long)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id ;

    @Column(name = "stock" , columnDefinition = "int check (stock > 0)" , length = 100)
    private Integer stock;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "price" ,columnDefinition = "bigint check (price > 8)" ,length = 100)
    private Long price;


    @ManyToOne
    @JoinColumn(name="store_id")
    private  Store store;

    @ManyToOne
     @JsonBackReference
    @JoinColumn(name = "product_id")
    private Product product;
}
