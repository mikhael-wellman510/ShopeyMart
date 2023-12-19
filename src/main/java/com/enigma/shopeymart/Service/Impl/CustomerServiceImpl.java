package com.enigma.shopeymart.Service.Impl;

import com.enigma.shopeymart.Dto.Request.CustomerRequest;
import com.enigma.shopeymart.Dto.Response.CustomerResponse;
import com.enigma.shopeymart.Dto.Response.StoreResponse;
import com.enigma.shopeymart.Entity.Customer;
import com.enigma.shopeymart.Repositori.CustomerRepositori;
import com.enigma.shopeymart.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepositori customerRepositori;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {

      // Builder ini seperti set , jdi set dari controller, yg d di kirim postman
        Customer customer = Customer.builder()
                .address(customerRequest.getAddress())
                .email(customerRequest.getEmail())
                .mobilePhone(customerRequest.getMobilePhone())
                .name(customerRequest.getName())
                .build();

                customerRepositori.save(customer);

        return CustomerResponse.builder()
                .id(customer.getId())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .mobilePhone(customer.getMobilePhone())
                .name(customer.getName())
                .build();
    }

    @Override
    public CustomerResponse createNewCustomer(Customer request) {
        Customer customer = customerRepositori.saveAndFlush(request);

        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .mobilePhone(customer.getMobilePhone())
                .build();
    }

    @Override
    public CustomerResponse getByIdCustomer(String id) {

        // D masukan ke variabel customer
        // Supaya hasil find nya bisa di tampilkan
        Customer customer = customerRepositori.findById(id).orElse(null);



        // ambil isi cusomer
        return CustomerResponse.builder()
                .id(customer.getId())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .mobilePhone(customer.getMobilePhone())
                .name(customer.getName())
                .build();
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {

        // masukan method dan ambil dari entity
        List<Customer> customer = customerRepositori.findAll();

        System.out.println(customer);

        return customer.stream()
                .map(customers -> CustomerResponse.builder()
                        .id(customers.getId())
                        .address(customers.getAddress())
                        .email(customers.getEmail())
                        .mobilePhone(customers.getMobilePhone())
                        .name(customers.getName())
                        .build())
                .toList();
    }

    @Override
    public CustomerResponse updateCustomer(CustomerRequest customerRequest) {

        // Ambil Id Dari Controller
        CustomerResponse custRespon = getByIdCustomer(customerRequest.getId());
//        Customer customer = customerRepositori.findById(customerRequest.getId()).orElse(null);

        if (custRespon != null){
            // Lalu siap di Update ke db
            Customer customers = Customer.builder()
                    //Id ambil dari cust response
                    .id(custRespon.getId())
                    // selebih nya ambil dari body yg dikirim controller
                    .address(customerRequest.getAddress())
                    .email(customerRequest.getEmail())
                    .mobilePhone(customerRequest.getMobilePhone())
                    .name(customerRequest.getName())
                    .build();

            customerRepositori.save(customers);
            return CustomerResponse.builder()
                    .id(customerRequest.getId())
                    .address(customerRequest.getAddress())
                    .email(customerRequest.getEmail())
                    .mobilePhone(customerRequest.getMobilePhone())
                    .name(customerRequest.getName())
                    .build();
        }


        return null;
    }

    @Override
    public void deleteCustomer(String id) {

        CustomerResponse custIds = getByIdCustomer(id);
        System.out.println(custIds);

        if (custIds != null){
            System.out.println("success delete");
            customerRepositori.deleteById(id);
        }

    }
}
