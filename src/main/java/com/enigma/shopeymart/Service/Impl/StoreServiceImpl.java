package com.enigma.shopeymart.Service.Impl;

import com.enigma.shopeymart.Dto.Request.StoreRequest;
import com.enigma.shopeymart.Dto.Response.StoreResponse;
import com.enigma.shopeymart.Entity.Store;
import com.enigma.shopeymart.Repositori.StoreRepositori;
import com.enigma.shopeymart.Service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
// ini pakai method ORM
public class StoreServiceImpl implements StoreService {

    //Ini harus pakai final
    private final StoreRepositori storeRepositori;


//    @Override
//    public Store create(Store store) {
//        return storeRepositori.save(store);
//    }

//    @Override
//    public Store getById(String id) {
//        return storeRepositori.findById(id).orElse(null);
//    }

//    @Override
//    public List<Store> getAll() {
//        return storeRepositori.findAll();
//    }

//    @Override
//    public Store update(Store store) {
        // Ambil Id nya
//        Store currentStoreId = getById(store.getId());
//        System.out.println(currentStoreId);
//        if (currentStoreId != null){
//            return storeRepositori.save(store);
//        }

//        return null;
//    }

//    @Override
//    public void delete(String id) {
//        storeRepositori.deleteById(id);
//    }

    @Override
    public StoreResponse createStores(StoreRequest storeRequest) {
        Store store = Store.builder()
                .name(storeRequest.getName())
                .noSiup(storeRequest.getNoSiup())
                .address(storeRequest.getAddress())
                .mobilePhone(storeRequest.getMobilePhone())
                .build();
        storeRepositori.save(store);
        return StoreResponse.builder() // ini bisa di pilih untuk d tampilkan di postman
                .storeName(store.getName())
                .noSiup(storeRequest.getNoSiup())
                .address(storeRequest.getAddress())
                .phone(store.getMobilePhone())
                .build();
    }

    @Override
    public StoreResponse getByIdStores(String id) {


        // Ini agar mendapatkan data dari controller
        Store store = storeRepositori.findById(id).orElse(null);

        // Ini untuk tampilkan d postman response nya
        return StoreResponse.builder()
                .id(store.getId())
                .storeName(store.getName())
                .noSiup(store.getNoSiup())
                .address(store.getAddress())
                .phone(store.getMobilePhone())
                .build();
    }


    @Override
    public List<StoreResponse> getAllStores() {

        // Logic untuk get Data ke database di ambil dri model
        // lalu nnti di pindahkan ke response
        List<Store> stores = storeRepositori.findAll();

        // Kenapa pakai ini . contoh rumus stream , untuk keluarin list object
//        List<Stasiuns> hasil = stasiun.stream().collect(Collectors.toList());
//        for (Stasiuns s : hasil){
//            System.out.println(s);
//        }

        // Pindahkan dari stores ke StoresResponses
        List<StoreResponse> storeResponses = stores.stream()
                .map(store -> StoreResponse.builder()
                        .id(store.getId())
                        .storeName(store.getName())
                        .noSiup(store.getNoSiup())
                        .address(store.getAddress())
                        .phone(store.getMobilePhone())
                        .build())
                .collect(Collectors.toList());

        // Colector to list untuk collect semua object
        // return ini kirim ke Controller
        return storeResponses ;
    }

//    /    @Override
//    public Store update(Store store) {
    // Ambil Id nya
//        Store currentStoreId = getById(store.getId());
//        System.out.println(currentStoreId);
//        if (currentStoreId != null){
//            return storeRepositori.save(store);
//        }

//        return null;
//    }

    @Override
    public StoreResponse updateStores(StoreRequest storeRequest) {
        // Belum dapat cara atasi error kalo semisal id nya ga sama

        // Minta hasil dari req body nya (storeRequest.getId
        System.out.println("hasil nya : " + getByIdStores(storeRequest.getId()));

        StoreResponse hasil = getByIdStores(storeRequest.getId());

            if (hasil != null){
                Store store = Store.builder()
                        // Id nya harus ikut supaya tidak create baru
                        .id(hasil.getId())
                        .name(storeRequest.getName())
                        .noSiup(storeRequest.getNoSiup())
                        .address(storeRequest.getAddress())
                        .mobilePhone(storeRequest.getMobilePhone())
                        .build();
                storeRepositori.save(store);
                return StoreResponse.builder() // ini bisa di pilih untuk d tampilkan di postman
                        .storeName(store.getName())
                        .noSiup(storeRequest.getNoSiup())
                        .address(storeRequest.getAddress())
                        .phone(store.getMobilePhone())
                        .build();
            }else {
                return null;

            }
    }

    //    @Override
//    public void delete(String id) {
//        storeRepositori.deleteById(id);
//    }


    @Override
    // parameter nya isi Id yg di tulis di postman
    public void deleteStore(String id) {
        System.out.println( getByIdStores(id));

        // Minta hasil id dari yg d tulis d controller model untuk hapus Ke id yg d tuju
        StoreResponse storeId = getByIdStores(id);

        System.out.println(storeId);
        storeRepositori.deleteById(storeId.getId());
    }
}
