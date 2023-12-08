package com.enigma.shopeymart.Controller;

import com.enigma.shopeymart.Constant.AppPath;
import com.enigma.shopeymart.Entity.Store;
import com.enigma.shopeymart.Service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.STORE)
public class StoreController {
    // wajib di bawa
    private final StoreService storeService;

    // Post
    @PostMapping(value = "/createStore")
    public Store createStore(@RequestBody Store store){
        return storeService.create(store);
    }

    // Get By Id
    @GetMapping(value = "/getByIdStore/{id}")
    public Store getByIdStore(@PathVariable String id){
        return storeService.getById(id);
    }

    //GetAll
    @GetMapping(value = "/getStoreAll")
    public List<Store> getAllStore(){
        return storeService.getAll();
    }

    //Update (Pakai request Body)
    @PutMapping(value = "/updateStore")
    public Store updateStore(@RequestBody Store store){
        return storeService.update(store);

    }

    @DeleteMapping(value = "/deleteStore/{id}")
    public void delete(@PathVariable String id){
         storeService.delete(id);
    }

}
