package com.enigma.shopeymart.Service;

import com.enigma.shopeymart.Dto.Request.CustomerRequest;
import com.enigma.shopeymart.Dto.Request.StoreRequest;
import com.enigma.shopeymart.Dto.Response.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customerRequest);
    CustomerResponse getByIdCustomer(String id);

    List<CustomerResponse> getAllCustomer();

    CustomerResponse updateCustomer(CustomerRequest customerRequest);

    void deleteCustomer(String id);
}
