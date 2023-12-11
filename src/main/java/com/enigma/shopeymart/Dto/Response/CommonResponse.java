package com.enigma.shopeymart.Dto.Response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)

// Ini buat kasih response di controller
public class CommonResponse<T> {

    private Integer statusCode ;
    private String message;
    private T data;

}
