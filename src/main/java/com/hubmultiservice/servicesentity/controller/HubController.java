package com.hubmultiservice.servicesentity.controller;

import org.springframework.web.bind.annotation.RestController;

import com.hubmultiservice.servicesentity.models.TramaReq;

 import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
 
@RestController
public class HubController {
 
    
    
    @PostMapping("/hubentidades")
    public TramaReq home(@RequestBody TramaReq entity) {
        //TODO: process POST request
        
        return entity;
    }
    

}
