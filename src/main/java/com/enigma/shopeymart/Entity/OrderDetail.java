package com.enigma.shopeymart.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_order_detail")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order ;

    @ManyToOne
    @JoinColumn(name = "product_price_id")
    private ProductPrice productPrice ;



}
