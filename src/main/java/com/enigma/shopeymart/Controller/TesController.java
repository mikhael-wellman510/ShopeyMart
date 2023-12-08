package com.enigma.shopeymart.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesController {

    @GetMapping("/search/{id}{keyid}") // http://localhost:8080/search/12?keyid=tes
    public String getDatasById(@PathVariable String id , @RequestParam String keyid){
        return "Data " + id + keyid ;
    }
    @GetMapping("/tesGet/{id}/{nama}") //http://localhost:8080/tesGet/murid/Mikhael
    public String getDataByIds(@PathVariable String id, @PathVariable String nama){
        return "Data " + id + " dan nama :  " + nama;
    }

    @GetMapping("/getParams{keys}")//http://localhost:8080/getParams?keys=12
    public String getTesParams(@RequestParam String keys){
        return  "Berhasil mendapatkan params : " + keys;
    }

    @GetMapping("/getAnak{anak}")
    public String getTotalAnak(@RequestParam String anak){
        return  "<p style='background-color : red'>Anda akan punya " + anak + " anak <p>";
    }
}
