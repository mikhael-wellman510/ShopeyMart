package com.enigma.shopeymart.Controller.RestTemplate;


import com.enigma.shopeymart.Entity.RestTemplate.Posts;
import com.enigma.shopeymart.Service.Impl.RestTempalate.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostsController {

private final PostService postService;


@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<List<Posts>> getAllPosts(){
    return postService.getAllPosts();
}

@GetMapping(value = "/{id}")
public ResponseEntity<String> getPostById(@PathVariable Long id){
    return postService.getPostById(id);
}

@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<String> createPosts(@RequestBody Posts posts){
    return postService.createPosts(posts);
}

}
