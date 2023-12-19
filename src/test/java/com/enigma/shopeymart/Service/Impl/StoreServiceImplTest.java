package com.enigma.shopeymart.Service.Impl;

import com.enigma.shopeymart.Dto.Request.StoreRequest;
import com.enigma.shopeymart.Dto.Response.StoreResponse;
import com.enigma.shopeymart.Entity.Store;
import com.enigma.shopeymart.Repositori.StoreRepositori;
import com.enigma.shopeymart.Service.StoreService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class StoreServiceImplTest {


    // Todo Membuat Mock
    //Mock StoreResponse
    private final StoreRepositori storeRepositori = mock(StoreRepositori.class);
    //Create StoreServiceNya Instance
    private final StoreService storeService = new StoreServiceImpl(storeRepositori);

    @Test
    void itShouldReturnStoreWhenCreateNewStore() {

        // Instansiasi Store Request
        StoreRequest dummyStoreRequest = new StoreRequest();

        dummyStoreRequest.setId("111");
        dummyStoreRequest.setName("test");
        dummyStoreRequest.setAddress("makasar");

        StoreResponse dummyStoreResponse = storeService.createStores(dummyStoreRequest);

        verify(storeRepositori).save(any(Store.class));
        assertEquals(dummyStoreRequest.getName(), dummyStoreResponse.getStoreName());
        assertEquals(dummyStoreRequest.getAddress(), dummyStoreResponse.getAddress());
//        assertEquals(dummyStoreRequest.getId(),dummyStoreResponse.getId());
    }

    @Test
    void itShouldGetAllDataStoreWhenCallGetAll() {

        //Todo -> buat Class List
        List<Store> dummyStore = new ArrayList<>();

        dummyStore.add(new Store("1", "123", "toko cina", "kaliders", "0896878788"));
        dummyStore.add(new Store("2", "2323", "toko batak", "ciawi", "089756567878"));

        //Todo -> FindAll
        when(storeRepositori.findAll()).thenReturn(dummyStore);

        // Todo -> Ambil  data dari service
        List<StoreResponse> retriveStore = storeService.getAllStores();

        // todo -> Cek Apakah jumlah nya sama ?
        assertEquals(dummyStore.size(), retriveStore.size());

        // Todo -> di Cek dengan cara Looping karena Tipe data List
        for (int i = 0; i < dummyStore.size(); i++) {
            assertEquals(dummyStore.get(i).getName(), retriveStore.get(i).getStoreName());
            System.out.println(dummyStore);
        }
    }

    @Test
    void itShouldGetDataStoreOneWhenGetByIdStore() {
        // Todo -> Id
        String storeId = "1";

        // Todo -> Instansiasi , agar d store punya data
        Store store = new Store("1", "22", "Jaya selalu", "depok", "089767676");


        // Todo -> Query method dari Store
        when(storeRepositori.findById(storeId)).thenReturn(Optional.of(store));


        // Todo -> Get By Id dari service
        StoreResponse storeResponse = storeService.getByIdStores(storeId);

        // Todo -> query di method Impl
        verify(storeRepositori).findById(storeId);

        // Todo -> Bandingkan Data
        assertNotNull(storeResponse);
        assertEquals(storeId, storeResponse.getId());
        assertEquals("Jaya selalu", storeResponse.getStoreName());


    }

    @Test
    void itShouldUpdateDataStoreOneWhenGetByIdStore(){


        //Todo -> Data Existing
        Store storeExisting = new Store();
        storeExisting.setId("1");
        storeExisting.setName("arifs");


        // Todo -> Data yang mau di ambil
        // Untuk update
        StoreRequest dummyStoreRequest = new StoreRequest();
        dummyStoreRequest.setId("2");
//        dummyStoreRequest.setNoSiup("23");
        dummyStoreRequest.setName("mamam");



        // Todo -> cari Id di Data Existing
        when(storeRepositori.findById(dummyStoreRequest.getId())).thenReturn(Optional.of(storeExisting));

        // Todo -> Save data yang di sudah d update
        StoreResponse dummyStoreResponse = storeService.updateStores(dummyStoreRequest);
        verify(storeRepositori).save(any(Store.class));

        if (storeExisting.getId().equals(dummyStoreResponse.getId())){
            // Todo -> Save di Service

            assertEquals(dummyStoreRequest.getId() ,dummyStoreResponse.getId());
            assertEquals(dummyStoreRequest.getName() , dummyStoreResponse.getStoreName());
        }




    }

}
