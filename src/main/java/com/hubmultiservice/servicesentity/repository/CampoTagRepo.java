package com.hubmultiservice.servicesentity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hubmultiservice.servicesentity.models.entidad.CamposTag;

@Repository
public interface CampoTagRepo extends JpaRepository<CamposTag,Integer> {
    
}
