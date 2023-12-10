package com.enigma.shopeymart.Dto.Response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
// name nya agak beda dengan Request
public class StoreResponse {
    // Hasil dari query
    // Attribute nya  bisa di atur jika ingin di tampilkan di postman , tpi lebih baik dri controller
    private String id;
    private String noSiup;
    private String storeName;
    private String address ;
    private String phone;
}
