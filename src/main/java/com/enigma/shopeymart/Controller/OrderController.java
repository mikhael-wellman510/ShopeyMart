package com.enigma.shopeymart.Controller;


import com.enigma.shopeymart.Dto.Request.OrderRequest;
import com.enigma.shopeymart.Dto.Response.CommonResponse;
import com.enigma.shopeymart.Dto.Response.OrderResponse;
import com.enigma.shopeymart.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    private ResponseEntity<?> createNewOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = orderService.createNewOrder(orderRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<OrderResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Sukses Created Data")
                        .data(orderResponse)
                        .build()
                );
    }

}
