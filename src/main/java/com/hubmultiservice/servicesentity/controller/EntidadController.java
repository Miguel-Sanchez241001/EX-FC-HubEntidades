package com.hubmultiservice.servicesentity.controller;

import org.springframework.web.bind.annotation.RestController;

import com.hubmultiservice.servicesentity.models.entidad.CamposTag;
import com.hubmultiservice.servicesentity.models.entidad.Entidad;
import com.hubmultiservice.servicesentity.models.entidad.Interfaces;
import com.hubmultiservice.servicesentity.models.entidad.Plantilla;
import com.hubmultiservice.servicesentity.models.entidad.enums.DocType;
import com.hubmultiservice.servicesentity.repository.EntidadRepo;
import com.hubmultiservice.servicesentity.repository.InterfazRepo;
import com.hubmultiservice.servicesentity.repository.PlantillaRepo;
import com.hubmultiservice.servicesentity.utils.LectorJsonDocTag;
import com.hubmultiservice.servicesentity.utils.LectorXmlDocTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("entidad")
public class EntidadController {
    

    @Autowired
    private EntidadRepo entidadRepository;
    @Autowired
    private InterfazRepo interfazRepo;
    @Autowired
    private PlantillaRepo plantillaRepo;


    private static final Logger logger = LoggerFactory.getLogger(EntidadController.class);




    @PostMapping("/create")
    public Entidad entidadSaveUdpate(@RequestBody Entidad entidad) {
         
        return entidadRepository.save(entidad);
    }
    
    @PostMapping("/interfaz")
    public ResponseEntity<Interfaces> interfazSaveUdpate(@Valid  @RequestBody Interfaces interfaces) {
       Entidad entidad =  entidadRepository.findById(interfaces.getEntidadId()).get();
       interfaces.setEntidad(entidad);
       return  ResponseEntity.ok( interfazRepo.save(interfaces));
   
    }

    // @PostMapping("/plantilla")
    // public  ResponseEntity<Plantilla>   plantillaSaveUdpate(@RequestBody Plantilla plantilla) {
    //     Interfaces interfaz =  interfazRepo.findById(plantilla.getInterfazId()).get();
    //     plantilla.setInterfaces(interfaz);
    //     plantilla.setContenido(Base64Utils.decodeFromString(plantilla.getContenidoSTR()));










    //     return ResponseEntity.status(HttpStatus.CREATED).body(plantillaRepo.save(plantilla)); 
    // }


    @PostMapping("/plantilla")
    public  ResponseEntity<List<String>>   plantillaSaveUdpate(@RequestBody Plantilla plantilla) throws Exception {
        Interfaces interfaz =  interfazRepo.findById(plantilla.getInterfazId()).get();
        plantilla.setInterfaces(interfaz);
        plantilla.setContenido(Base64Utils.decodeFromString(plantilla.getContenidoSTR()));

        List<String> camposEncontrados =  new ArrayList<>();


        Plantilla plantillaGuarda =   plantillaRepo.save(plantilla);

       // logger.info("Guardada " + plantillaGuarda.toString());
        if (plantillaGuarda.getDocType().equals(DocType.XML)) {
            camposEncontrados =  new LectorXmlDocTag().processXML(plantillaGuarda.getContenido()) ;
        }
        if (plantillaGuarda.getDocType().equals(DocType.JSON)) {
            camposEncontrados = new LectorJsonDocTag().readKeys(plantillaGuarda.getContenido());
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(camposEncontrados); 
    }
}
