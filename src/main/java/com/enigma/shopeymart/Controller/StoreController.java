package com.enigma.shopeymart.Controller;

import com.enigma.shopeymart.Constant.AppPath;
import com.enigma.shopeymart.Dto.Request.StoreRequest;
import com.enigma.shopeymart.Dto.Response.StoreResponse;
import com.enigma.shopeymart.Entity.Store;
import com.enigma.shopeymart.Service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.STORE)
public class StoreController {
    // wajib di bawa
    private final StoreService storeService;

    // Post
//    @PostMapping(value = "/createStore")
//    public Store createStore(@RequestBody Store store){
//        return storeService.create(store);
//    }

    // Get By Id
//    @GetMapping(value = "/getByIdStore/{id}")
//    public Store getByIdStore(@PathVariable String id){
//        return storeService.getById(id);
//    }

    //GetAll
//    @GetMapping(value = "/getStoreAll")
//    public List<Store> getAllStore(){
//        return storeService.getAll();
//    }

    //Update (Pakai request Body)
//    @PutMapping(value = "/updateStore")
//    public Store updateStore(@RequestBody Store store){
//        return storeService.update(store);
//
//    }

//    @DeleteMapping(value = "/deleteStore/{id}")
//    public void delete(@PathVariable String id){
//         storeService.delete(id);
//    }

    @PostMapping(value = "/v1")
    @PreAuthorize("hasRole('ADMIN')") // hanya admin yg bisa akses
    public StoreResponse createStores(@RequestBody StoreRequest storeRequest){
        return storeService.createStores(storeRequest);
    }

    @GetMapping(value = "/getByIdStores/{id}")
    public StoreResponse getById(@PathVariable String id){

        return storeService.getByIdStores(id);
    }

    @GetMapping(value = "/getAllStores")
    public List<StoreResponse> getAll(){
        return storeService.getAllStores();
    }



    @PutMapping(value = "/updateStores")
    public StoreResponse updateStores(@RequestBody StoreRequest storeRequest){
        return storeService.updateStores(storeRequest);
    }

    //    @DeleteMapping(value = "/deleteStore/{id}")
//    public void delete(@PathVariable String id){
//         storeService.delete(id);
//    }

    @DeleteMapping(value = "/deleteStores/{id}")
    public void deleteStore(@PathVariable String id){
        storeService.deleteStore(id);
    }

}
