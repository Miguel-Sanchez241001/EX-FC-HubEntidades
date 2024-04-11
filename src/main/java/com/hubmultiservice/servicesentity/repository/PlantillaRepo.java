package com.hubmultiservice.servicesentity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hubmultiservice.servicesentity.models.entidad.Plantilla;

@Repository
public interface PlantillaRepo extends JpaRepository<Plantilla,Integer>{
    
}
