package com.enigma.shopeymart.Service;

import com.enigma.shopeymart.Dto.Request.OrderRequest;
import com.enigma.shopeymart.Dto.Response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createNewOrder(OrderRequest orderRequest);

    OrderResponse getOrderById(String id);

    List<OrderResponse> getAllOrder();

}
