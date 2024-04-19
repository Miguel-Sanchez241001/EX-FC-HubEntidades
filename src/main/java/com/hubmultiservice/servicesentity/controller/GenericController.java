package com.hubmultiservice.servicesentity.controller;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.RestController;

import com.hubmultiservice.servicesentity.models.TramaRequestInput;
import com.hubmultiservice.servicesentity.models.TramaResponseOutput;
import com.hubmultiservice.servicesentity.services.OperationsEntities;
import com.hubmultiservice.servicesentity.services.Transfomador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;




@RestController
@RequestMapping("/api/v1" )
public class GenericController {
    
    @Autowired
    private OperationsEntities operationsEntities;

    @Autowired
    private Transfomador transfomador;

    @PostConstruct
    void init(){
        System.out.println("INICIO CONTROLLER GENERICO");
    }

    @PostMapping("/consulta")
    public TramaResponseOutput ConsultaEntidad(@RequestBody TramaRequestInput entity) {
        
        String Response  = operationsEntities.Consulta(entity);    
        return  transfomador.ConsultaOUT(Response);
    }
    
    @PostMapping("/pago")
    public TramaResponseOutput PagoEntidad(@RequestBody TramaRequestInput entity) {
        String Response  = operationsEntities.Pago(entity);    
        return  transfomador.PagoOUT(Response);
    }
    
    @PostMapping("/extorno")
    public TramaResponseOutput ExtornoEntidad(@RequestBody TramaRequestInput entity) {
        String Response  = operationsEntities.extorno(entity);    
        return  transfomador.ExtornoOUT(Response);
    }
    
}
