package com.enigma.shopeymart.Dto.Response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderDetailResponse {

    private String orderDetailId;
    private ProductResponse product;
    private  Integer quantity ;

}
