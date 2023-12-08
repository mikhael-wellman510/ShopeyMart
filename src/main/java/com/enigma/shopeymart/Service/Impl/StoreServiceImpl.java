package com.enigma.shopeymart.Service.Impl;

import com.enigma.shopeymart.Entity.Store;
import com.enigma.shopeymart.Repositori.StoreRepositori;
import com.enigma.shopeymart.Service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class StoreServiceImpl implements StoreService {

    //Ini harus pakai final
    private final StoreRepositori storeRepositori;


    @Override
    public Store create(Store store) {
        return storeRepositori.save(store);
    }

    @Override
    public Store getById(String id) {
        return storeRepositori.findById(id).orElse(null);
    }

    @Override
    public List<Store> getAll() {
        return storeRepositori.findAll();
    }

    @Override
    public Store update(Store store) {
        // Ambil Id nya
        Store currentStoreId = getById(store.getId());
        if (currentStoreId != null){
            return storeRepositori.save(store);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        storeRepositori.deleteById(id);
    }
}
