package com.enigma.shopeymart.Controller.FileUpload;


import com.enigma.shopeymart.Dto.Response.FileResponse.FileStorageResponse;
import com.enigma.shopeymart.Service.Impl.UploadFile.FileStorageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileStorageServiceImpl fileStorageService;

    @PostMapping(value = "/upload" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileStorageResponse uploadFile(@RequestParam("file")MultipartFile file){
       FileStorageResponse fileStorageResponse = fileStorageService.storageFile(file);


        return fileStorageResponse;
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename){
        Resource resource ;

        try {
            resource = fileStorageService.downloadFile(filename);
        }catch (FileNotFoundException e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +resource.getFilename() + "\"" )
                .body(resource);
    }
}
