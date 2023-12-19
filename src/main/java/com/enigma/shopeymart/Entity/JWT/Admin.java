package com.enigma.shopeymart.Entity.JWT;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "m_admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name ;
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private  UserCredential userCredential ;
}
