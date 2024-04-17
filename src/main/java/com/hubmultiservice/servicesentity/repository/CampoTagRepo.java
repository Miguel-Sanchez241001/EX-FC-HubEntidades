package com.hubmultiservice.servicesentity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hubmultiservice.servicesentity.models.entidad.CamposTag;
import com.hubmultiservice.servicesentity.models.entidad.Plantilla;

import java.util.List;


@Repository
public interface CampoTagRepo extends JpaRepository<CamposTag,Integer> {


    List<CamposTag> findByPlantilla(Plantilla plantilla);
    
}
