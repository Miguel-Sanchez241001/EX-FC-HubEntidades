package com.hubmultiservice.servicesentity.controller;

import org.springframework.web.bind.annotation.RestController;

import com.hubmultiservice.servicesentity.models.TramaReq;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
public class HubController {
    @GetMapping("/")
    public String getMethodName(@RequestParam String param) {
        return "Servicio WEB";
    }
    
    
    @PostMapping("/hubentidades")
    public TramaReq home(@RequestBody TramaReq entity) {
        //TODO: process POST request
        
        return entity;
    }
    

}
