package com.enigma.shopeymart.Service.Impl;

import com.enigma.shopeymart.Dto.Request.OrderRequest;
import com.enigma.shopeymart.Dto.Response.*;
import com.enigma.shopeymart.Entity.Customer;
import com.enigma.shopeymart.Entity.Order;
import com.enigma.shopeymart.Entity.OrderDetail;
import com.enigma.shopeymart.Entity.ProductPrice;
import com.enigma.shopeymart.Repositori.CustomerRepositori;
import com.enigma.shopeymart.Repositori.OrderRepository;
import com.enigma.shopeymart.Service.CustomerService;
import com.enigma.shopeymart.Service.OrderService;
import com.enigma.shopeymart.Service.ProductPriceService;
import com.enigma.shopeymart.Service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductPriceService productPriceService;
    private final CustomerRepositori customerRepositori;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public OrderResponse createNewOrder(OrderRequest orderRequest) {
        // Todo 1 : validate customer (ambil id customer)
        CustomerResponse customerResponse = customerService.getByIdCustomer(orderRequest.getCustomerId());
//        Optional<Customer> customer = customerRepositori.findById(orderRequest.getCustomerId());
        System.out.println("Get id cutomer : " + customerResponse);
        // Todo 2 : Convert OrderDetail (ambil data dari table order detail)
        List<OrderDetail> orderDetails = orderRequest.getOrderDetails().stream().map(orderDetailRequest -> {
            // todo 3 :Validate Product Price (ambil id product_price di  t_order_detail )
            ProductPrice productPrice = productPriceService.getById(orderDetailRequest.getProductPriceId());
            //Masukan ke OrderDetailRequest
            return OrderDetail.builder()
                    // ambil dari produk price
                    .productPrice(productPrice)
                    .quantity(orderDetailRequest.getQuantity())
                    .build();

        }).toList();
        // Todo 4 : Create new order
        // ngeSet Table Order
        Order order = Order.builder()
                //ambil id
                .customer(Customer.builder()
                        .id(customerResponse.getId())
                        .build())
                // Ambil waktu saat ini
                .transdate(LocalDateTime.now())
                .orderDetails(orderDetails)
                .build();

        // create isi order ke db
        orderRepository.saveAndFlush(order);

        List<OrderDetailResponse> orderDetailResponses = order.getOrderDetails().stream().map(orderDetail -> {
            // Todo 5 : Set order from orderDetail after creating new order
            orderDetail.setOrder(order);
            System.out.println(order);

            //Todo 6 : change the stock from the purchase quantity

            // supaya bisa setter getter , set stok dan get stok
            ProductPrice currentProductPrice = orderDetail.getProductPrice();
            System.out.println("current Produk Price : " + currentProductPrice);
            currentProductPrice.setStock(currentProductPrice.getStock() - orderDetail.getQuantity());
            return OrderDetailResponse.builder()
                    .orderDetailId(orderDetail.getId())
                    .quantity(orderDetail.getQuantity())
                    // todo 7 : Convert product to productResponse(productPrice)
                    .product(ProductResponse.builder()
                            .id(currentProductPrice.getProduct().getId())
                            .productName(currentProductPrice.getProduct().getName())
                            .description(currentProductPrice.getProduct().getDescription())
                            .stock(currentProductPrice.getStock())
                            .price(currentProductPrice.getPrice())
                            //Todo 8 : convert store to storeResponse
                            .store(StoreResponse.builder()
                                    .id(currentProductPrice.getStore().getId())
                                    .storeName(currentProductPrice.getStore().getName())
                                    .noSiup(currentProductPrice.getStore().getNoSiup())
                                    .build())
                            .build())
                    .build();

        }).toList();


        // Todo 9 convert customer to customer response

        // todo 10 return orderResponse;

      return OrderResponse.builder()
                .transDate(order.getTransdate())
                .customer(customerResponse)
                .orderDetails(orderDetailResponses)
                .build();


    }

    @Override
    public OrderResponse getOrderById(String id) {
        return null;
    }

    @Override
    public List<OrderResponse> getAllOrder() {
        return null;
    }
}
