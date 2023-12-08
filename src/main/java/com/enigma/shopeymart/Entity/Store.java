package com.enigma.shopeymart.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name="m_store")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name= "no_siup" ,unique = true ,nullable = false , length = 30) // Harus Unique
    private String noSiup;

    @Column(name = "name" ,nullable = false , length = 100) // Tidak boleh kosong
    private String name;

    @Column(name = "address" ,nullable = false ,length = 100) // Tidak boleh Kosong
    private String address ;

    @Column(name = "mobile_phone" ,unique = true , nullable = false, length = 30)
    private String mobilePhone;




}
