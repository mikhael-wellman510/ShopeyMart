package com.enigma.shopeymart.Service;

import com.enigma.shopeymart.Dto.Request.StoreRequest;
import com.enigma.shopeymart.Dto.Response.StoreResponse;
import com.enigma.shopeymart.Entity.Store;

import java.util.List;

public interface StoreService {
//    Store create(Store store);
//    Store getById(String id);
//    List<Store> getAll();
//    Store update(Store store);
//    void delete(String id);
    StoreResponse createStores(StoreRequest storeRequest);

    StoreResponse getByIdStores(String id);

    List<StoreResponse> getAllStores();

    StoreResponse updateStores(StoreRequest storeRequest);

    void deleteStore(String id);

}
