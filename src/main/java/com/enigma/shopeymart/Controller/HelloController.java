package com.enigma.shopeymart.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping(value = "/hello") //http://localhost:8080/hello
    public String hello(){
        return "<h1 style=\"color: #FF5733;\"> hello World <h1>";
    }

    @GetMapping(value = "/hello/v1")//http://localhost:8080/hello/v1
    public String[] getHobies(){
        return new String[]{"makan", "tidur"};
    }

    //mengakses http request berdasarkan parameter nya
    @GetMapping("/search{key}") //http://localhost:8080/search?key=mikhael
    public String  getRequestParam(@RequestParam String key){
        return key ;
    }

    @GetMapping("/data/{id}") // http://localhost:8080/data/2
    public String getDataById(@PathVariable String id){
        return "Data " +id ;
    }


}
