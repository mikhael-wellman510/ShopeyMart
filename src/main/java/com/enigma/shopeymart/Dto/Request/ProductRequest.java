package com.enigma.shopeymart.Dto.Request;

import com.enigma.shopeymart.Dto.Response.StoreResponse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)

public class ProductRequest {


    private String id;

    @NotBlank(message = "Product Name is Required")
    private String productName;

    @NotBlank(message = "Description is Required")
    private String description;

    @NotBlank(message = "Price is Required")
    private Long price;

    @NotBlank(message = "Produck stock is Required")
    @Min(value = 0, message = "Stock must be greather than equals 0")
    private Integer Stock;

    @NotBlank(message = "StoreId os Required")
    private StoreResponse storeId;
}
