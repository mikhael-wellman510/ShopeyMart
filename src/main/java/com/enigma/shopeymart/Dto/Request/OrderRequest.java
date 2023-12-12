package com.enigma.shopeymart.Dto.Request;

import com.enigma.shopeymart.Entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderRequest {

    private String customerId;
    private List<OrderDetailRequest> orderDetails;

}
