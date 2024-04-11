package com.hubmultiservice.servicesentity.models.entidad;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hubmultiservice.servicesentity.models.entidad.enums.ServiceType;

import lombok.*;

@Entity
@Table(name = "f01_entidad")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Entidad {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f01_entidadId",nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)  // El id se incluye en las respuestas, no en las solicitudes
    private Integer id;

    @Column(name = "f01_nombre",nullable = false)
    private String nombre;

 
    @Enumerated(EnumType.STRING)
    @Column(name = "f01_tipo",nullable = false)
    private ServiceType type;



    @OneToMany(mappedBy = "entidad")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) 
    private Set<Interfaces> interfaces;
  

}
