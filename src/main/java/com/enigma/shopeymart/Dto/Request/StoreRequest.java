package com.enigma.shopeymart.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class StoreRequest {
    // Yang di kirim ke controller untuk di taruh di parameter nya
    private String id;
    private String noSiup;
    private String name;
    private String address ;
    private String mobilePhone;
}
