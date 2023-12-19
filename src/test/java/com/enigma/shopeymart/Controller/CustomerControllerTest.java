package com.enigma.shopeymart.Controller;

import com.enigma.shopeymart.Dto.Request.CustomerRequest;
import com.enigma.shopeymart.Dto.Response.CustomerResponse;
import com.enigma.shopeymart.Service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


// Todo -> Controller tidak pakai springbootTes
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

@BeforeEach
    public void  setUp(){
    MockitoAnnotations.initMocks(this);
    }
    @Test
    void createCustomer() {
        CustomerRequest customerRequest = new CustomerRequest();
        CustomerResponse customerResponse = new CustomerResponse();

        //Todo -> Cek Behavior(Service nya) dan theReturn = Hasil balikan nya
        when(customerService.createCustomer(customerRequest)).thenReturn(customerResponse);

        //Todo -> Ambil Return Controller nya
        CustomerResponse actualResponse = customerController.createCustomer(customerRequest);

        assertEquals(customerResponse , actualResponse);
    }

    @Test
    void getCustomerById() {
        String customerId = "1";
        CustomerResponse expectedResponse = new CustomerResponse();


        when(customerService.getByIdCustomer(customerId)).thenReturn(expectedResponse);

        CustomerResponse actualResponse = customerController.getCustomerById(customerId);

        assertEquals(expectedResponse,actualResponse);

    }

    @Test
    void getAllCustomer() {

        //Todo -> buat Expected Response
        List<CustomerResponse> expectedResponse = new ArrayList<>();

        //Todo -> Ambil Service
        when(customerService.getAllCustomer()).thenReturn(expectedResponse);

        //Todo -> Buat Actual Response
        List<CustomerResponse> actualResponse = customerController.getAllCustomer();

        //Todo -> Bandingkan
        assertEquals(expectedResponse,actualResponse);

    }

    @Test
    void updateCustomers() {
        CustomerResponse expectedResponse = new CustomerResponse();
        CustomerRequest customerRequest = new CustomerRequest();

        when(customerService.updateCustomer(customerRequest)).thenReturn(expectedResponse);

        CustomerResponse actualResponse = customerController.updateCustomers(customerRequest);

        assertEquals(expectedResponse,actualResponse);

    }

    @Test
    void deleteCustomers() {
        String custmerId = "1";

        // Todo -> Panggil controller nya
        customerController.deleteCustomers(custmerId);

        verify(customerService,times(1)).deleteCustomer(custmerId);
    }
}