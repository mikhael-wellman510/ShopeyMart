package com.enigma.shopeymart.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PagingResponse {

    private  Integer currentPage;
    private  Integer totalPage;
    private Integer size;


}
