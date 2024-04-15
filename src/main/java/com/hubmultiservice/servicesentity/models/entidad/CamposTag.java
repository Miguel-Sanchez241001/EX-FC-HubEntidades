package com.hubmultiservice.servicesentity.models.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Entity
@Table(name = "f04_campos")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CamposTag {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f04_camposId",nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)  // El id se incluye en las respuestas, no en las solicitudes
    private Integer id;
    
// Este campo es para recibir el ID de la entidad en el JSON
@Column(name = "f03_plantillaId", insertable = false, updatable = false)
private Integer plantillaId;

    @Column(name = "f04_etiqueta",nullable = false)
    private String etiqueta;

    @Column(name = "f04_valor",nullable = false)
    private String valor;



    @ManyToOne
    @JoinColumn(name = "f03_plantillaId")
    @JsonIgnore
    private Plantilla plantilla;

}
