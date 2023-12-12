package com.enigma.shopeymart.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="t_order")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "trans_date",nullable = false)
    private LocalDateTime transdate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // one to many ke dari order ke orders details
    @OneToMany(mappedBy = "order" , cascade = CascadeType.PERSIST)
    private List<OrderDetail> orderDetails;

}
