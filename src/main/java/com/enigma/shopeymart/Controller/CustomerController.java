package com.enigma.shopeymart.Controller;

import com.enigma.shopeymart.Constant.AppPath;
import com.enigma.shopeymart.Dto.Request.CustomerRequest;
import com.enigma.shopeymart.Dto.Response.CustomerResponse;
import com.enigma.shopeymart.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.CUSTOMER)
public class CustomerController {

    // Ini untuk akses ke Service , ambil method nya
    private final CustomerService customerService;


    @PostMapping(value = "/createCustomer")
    public CustomerResponse createCustomer(@RequestBody CustomerRequest customerRequest){
        return customerService.createCustomer(customerRequest);
    }

    @GetMapping(value = "/getCustomerById/{id}")
    public CustomerResponse getCustomerById(@PathVariable String id){

        return customerService.getByIdCustomer(id);
    }

    @GetMapping(value = "/getAllCustomers")
   public List<CustomerResponse> getAllCustomer(){
        return customerService.getAllCustomer();
   }

   @PutMapping(value = "/updateCustomers")
   public CustomerResponse updateCustomers(@RequestBody CustomerRequest customerRequest){
        return customerService.updateCustomer(customerRequest);
   }

   @DeleteMapping(value = "/deleteCustomers/{id}")
   public void deleteCustomers(@PathVariable String id){
        customerService.deleteCustomer(id);
   }


}
