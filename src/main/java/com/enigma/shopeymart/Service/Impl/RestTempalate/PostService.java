package com.enigma.shopeymart.Service.Impl.RestTempalate;

import com.enigma.shopeymart.Entity.RestTemplate.Posts;
import com.enigma.shopeymart.Repositori.PostsRepositori;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    // Import nya yg SpringBean
    @Value("${api.endpoint.url.post}")
    private String BASE_URL;

    private final RestTemplate restTemplate;
    private final PostsRepositori postsRepositori;

    public ResponseEntity<List<Posts>> getAllPosts(){


       ResponseEntity<Posts[]> apiResponse = restTemplate.getForEntity(BASE_URL,Posts[].class);


        List<Posts>  externalPost = List.of(apiResponse.getBody());

        List<Posts> dbPost = postsRepositori.findAll();
        dbPost.addAll(externalPost);
        return ResponseEntity.ok(dbPost);
    }

    public ResponseEntity<String> getPostById(Long id){

        return responseMethod(restTemplate.getForEntity(BASE_URL+ "/" + id,String.class),"Failed to Load data");
    }

    public  ResponseEntity<String> createPosts(Posts posts){
        postsRepositori.save(posts);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Posts> requestEntity = new HttpEntity<>(posts,headers);
        // Response
        return responseMethod(restTemplate.postForEntity(BASE_URL,requestEntity,String.class),"Failed to Create Data");
    }
    private ResponseEntity<String> responseMethod(ResponseEntity<String> restTemplate,String message){
        ResponseEntity<String> responseEntity = restTemplate;

        if (responseEntity.getStatusCode().is2xxSuccessful()) {

            String responseBody = responseEntity.getBody();
            return ResponseEntity.ok(responseBody);
        }

        return  ResponseEntity.status(responseEntity.getStatusCode()).body(message);
    }
}
