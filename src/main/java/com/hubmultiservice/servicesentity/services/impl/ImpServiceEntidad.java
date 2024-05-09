package com.hubmultiservice.servicesentity.services.impl;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.hubmultiservice.servicesentity.models.entidad.CamposTag;
import com.hubmultiservice.servicesentity.models.entidad.Entidad;
import com.hubmultiservice.servicesentity.models.entidad.Interfaces;
import com.hubmultiservice.servicesentity.models.entidad.Plantilla;
import com.hubmultiservice.servicesentity.models.response.ResponseFront;
import com.hubmultiservice.servicesentity.repository.CampoTagRepo;
import com.hubmultiservice.servicesentity.repository.EntidadRepo;
import com.hubmultiservice.servicesentity.repository.InterfazRepo;
import com.hubmultiservice.servicesentity.repository.PlantillaRepo;
import com.hubmultiservice.servicesentity.services.ServicesEntidad;
import com.hubmultiservice.servicesentity.utils.Constantes;

@Service
@Transactional
public class ImpServiceEntidad implements ServicesEntidad {





    @Autowired
    private EntidadRepo entidadRepository;
    @Autowired
    private InterfazRepo interfazRepo;
    @Autowired
    private PlantillaRepo plantillaRepo;

    @Autowired
     private CampoTagRepo campoTagRepo;





    @Override
    public ResponseFront saveEntidad(Entidad entidad) {
        ResponseFront respuest = ResponseFront
        .builder()
        .data(entidadRepository.save(entidad))
        .build();
        if (respuest.getData()!= null) {
            respuest.setCodigo( Constantes.COG_EXITO);
            respuest.setMensaje(Constantes.MSJ_EXITO);
        }
        return respuest;
    }

    @Override
    public ResponseFront updateEntidad(Entidad entidad) {
        ResponseFront respuest = ResponseFront
        .builder()
        .data(entidadRepository.save(entidad))
        .build();
        if (respuest.getData()!= null) {
            respuest.setCodigo( Constantes.COG_EXITO);
            respuest.setMensaje(Constantes.MSJ_EXITO);
        }
        return respuest;
    }



    @Override
    public ResponseFront saveInterfaz(Interfaces interfaces) {
        Entidad entidad =  entidadRepository.findById(interfaces.getEntidadId()).get();
        interfaces.setEntidad(entidad);
        ResponseFront respuest = ResponseFront
        .builder()
        .data(interfazRepo.save(interfaces))
        .build();
        if (respuest.getData()!= null) {
            respuest.setCodigo( Constantes.COG_EXITO);
            respuest.setMensaje(Constantes.MSJ_EXITO);
        }
        return respuest ;
    }

    @Override
    public ResponseFront updateInterfaz(Interfaces interfaces) {
        Entidad entidad =  entidadRepository.findById(interfaces.getEntidadId()).get();
        interfaces.setEntidad(entidad);
        ResponseFront respuest = ResponseFront
        .builder()
        .data(interfazRepo.save(interfaces))
        .build();
        if (respuest.getData()!= null) {
            respuest.setCodigo( Constantes.COG_EXITO);
            respuest.setMensaje(Constantes.MSJ_EXITO);
        }
        return respuest ;
    }

    @Override
    public ResponseFront savePlantilla(Plantilla plantilla) {
        Interfaces interfaz =  interfazRepo.findById(plantilla.getInterfazId()).get();
        plantilla.setInterfaces(interfaz);
        plantilla.setContenido(Base64Utils.decodeFromString(plantilla.getContenidoSTR()));
        Plantilla plantillaGuarda =   plantillaRepo.save(plantilla);
        ResponseFront respuest = ResponseFront
        .builder()
        .data(plantillaGuarda)
        .build();
        if (respuest.getData()!= null) {
            respuest.setCodigo( Constantes.COG_EXITO);
            respuest.setMensaje(Constantes.MSJ_EXITO);
        }
        return respuest ;
    }

    @Override
    public ResponseFront updatePlantilla(Plantilla plantilla) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePlantilla'");
    }

    @Override
    public ResponseFront saveCampo(CamposTag camposTag) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveCampo'");
    }

    @Override
    public ResponseFront updateCampo(CamposTag camposTag) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCampo'");
    }

    @Override
    public ResponseFront showEntidades() {
        ResponseFront respuest = ResponseFront
        .builder()
        .data(entidadRepository.findAll())
        .build();
        if (respuest.getData()!= null) {
            respuest.setCodigo( Constantes.COG_EXITO);
            respuest.setMensaje(Constantes.MSJ_EXITO);
        }
        return respuest;
    }
    
}
