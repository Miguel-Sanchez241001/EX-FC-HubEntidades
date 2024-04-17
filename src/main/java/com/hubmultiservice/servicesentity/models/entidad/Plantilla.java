package com.hubmultiservice.servicesentity.models.entidad;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hubmultiservice.servicesentity.models.entidad.enums.DocType;
import com.hubmultiservice.servicesentity.models.entidad.enums.PlantillaType;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Table(name = "f03_plantilla")
public class Plantilla {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f03_plantillaId",nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)  // El id se incluye en las respuestas, no en las solicitudes
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "f02_interfazId")
    @ToString.Exclude
    private Interfaces interfaces;
    // Este campo es para recibir el ID de la entidad en el JSON
    @Column(name = "f02_interfazId", insertable = false, updatable = false)
    private Integer interfazId;
   
    @Column(name = "f03_descripcion",nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "f03_tipo",nullable = false)
    private PlantillaType plantillaType;

    @Enumerated(EnumType.STRING)
    @Column(name = "f03_doctipo",nullable = false)
    private DocType docType;

    @Column(name = "f03_plantilla",nullable = false)
    @Lob
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)  // El id se incluye en las respuestas, no en las solicitudes
    @ToString.Exclude
    private byte[] contenido;

    @Transient
    @ToString.Exclude
    private String contenidoSTR;

    @OneToMany(mappedBy = "plantilla")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) 
    @ToString.Exclude
    private Set<CamposTag> camposTags;
}
