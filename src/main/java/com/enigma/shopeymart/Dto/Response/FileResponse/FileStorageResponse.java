package com.enigma.shopeymart.Dto.Response.FileResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)

public class FileStorageResponse {
    private String fileName;
    private LocalDateTime dateTime;
}
