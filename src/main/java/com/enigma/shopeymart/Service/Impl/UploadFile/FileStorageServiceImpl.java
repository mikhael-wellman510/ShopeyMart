package com.enigma.shopeymart.Service.Impl.UploadFile;

import com.enigma.shopeymart.Dto.Response.FileResponse.FileStorageResponse;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Service
public class FileStorageServiceImpl {
    private final Path fileStorageLocation = Paths.get("/home/user/BATCH14/JAVA SPRING BOOT/Java Spring Boot Shopee/shopeymart/src/main/java/com/enigma/shopeymart/File");

    public FileStorageServiceImpl(){
        try {
             Files.createDirectories(this.fileStorageLocation);
        }catch(Exception e){
            throw new RuntimeException("could not create the  directory where the upload file to storage");

        }
    }


    // Setingan nya di Aplication properties UNTUK UKURAN
    public FileStorageResponse storageFile(MultipartFile file){
        String mimiType = file.getContentType();
        // Validasi harus image
        System.out.println(file.getResource());

        //Todo -> ini untuk handle file , supaya tidak semua file bisamasuk
        if (mimiType == null|| (!mimiType.startsWith("image/"))){
            throw new RuntimeException("Invalid upload, Only upload image");
        }
        try {
            Path targertLocation = this.fileStorageLocation.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(),targertLocation , StandardCopyOption.REPLACE_EXISTING);

            return FileStorageResponse.builder()
                    .fileName(file.getOriginalFilename())
                    .dateTime(LocalDateTime.now())
                    .build();
        }catch (IOException e){
            throw new RuntimeException("Could not Store " + file.getOriginalFilename() + " please check again" + e);
        }
    }

    public Resource downloadFile(String nameFile) throws FileNotFoundException{
        try {

            Path targetLocation = this.fileStorageLocation.resolve(nameFile).normalize();
            Resource resource = new UrlResource(targetLocation.toUri());

            if (resource.exists()){
                return resource;
            }else {
                throw  new FileNotFoundException("file Not Found" + nameFile);
            }

        }catch (MalformedURLException e){
            throw new FileNotFoundException("File not found " + nameFile);
        }
    }
}
