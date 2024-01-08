package com.enigma.shopeymart.Entity.RestTemplate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "m_posts")
public class Posts {

    @Id
    private Integer id ;

    private String title;

    private String body;

    @Column(name = "userId")
    private String userId;
}
