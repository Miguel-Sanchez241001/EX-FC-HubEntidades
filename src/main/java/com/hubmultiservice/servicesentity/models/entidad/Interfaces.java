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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hubmultiservice.servicesentity.models.entidad.enums.MethodType;
import com.hubmultiservice.servicesentity.models.entidad.enums.OperationType;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "f02_interfaces")
public class Interfaces {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f02_interfazId",nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)  // El id se incluye en las respuestas, no en las solicitudes
    private Integer id;

// Este campo es para recibir el ID de la entidad en el JSON
@Column(name = "f01_entidadId", insertable = false, updatable = false)
private Integer entidadId;

    @ManyToOne
    @JoinColumn(name = "f01_entidadId")
    @JsonIgnore
    private Entidad entidad;


    @Enumerated(EnumType.STRING)
    @Column(name = "f02_operacion",nullable = false)
    private OperationType operationType;


    @Enumerated(EnumType.STRING)
    @Column(name = "f02_metodo",nullable = false)
    private MethodType methodType;

     @Column(name = "f02_endpoint",nullable = false)
     @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "La URL debe ser válida y comenzar con http o https")
    private String endpoint;


    @OneToMany(mappedBy = "interfaces")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) 
    private Set<Plantilla> plantillas;
}
