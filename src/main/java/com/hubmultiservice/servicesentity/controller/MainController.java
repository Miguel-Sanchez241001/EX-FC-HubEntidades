package com.hubmultiservice.servicesentity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestController
@RequestMapping("/")
public class MainController {
    // Manejar solicitudes a la ruta raíz "/"
    @GetMapping
    public String index() {
        return "Estás accediendo a un servicio.";
    }
      // Manejar cualquier otra ruta no mapeada
    @ResponseStatus(HttpStatus.NOT_FOUND)
    
    @RequestMapping("error")
    public <ResourceNotFoundException> ResponseEntity<String>  error( ) {
        return ResponseEntity.status( HttpStatus.NOT_FOUND).body("{\"error\": \"+ex.getMessage()+\"}");
     }
}
