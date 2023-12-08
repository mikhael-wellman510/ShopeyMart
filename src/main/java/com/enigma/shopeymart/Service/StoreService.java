package com.enigma.shopeymart.Service;

import com.enigma.shopeymart.Entity.Store;

import java.util.List;

public interface StoreService {
    Store create(Store store);
    Store getById(String id);
    List<Store> getAll();
    Store update(Store store);
    void delete(String id);


}
