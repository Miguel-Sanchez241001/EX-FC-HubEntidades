package com.hubmultiservice.servicesentity.services;

 
import com.hubmultiservice.servicesentity.models.entidad.CamposTag;
import com.hubmultiservice.servicesentity.models.entidad.Entidad;
import com.hubmultiservice.servicesentity.models.entidad.Interfaces;
import com.hubmultiservice.servicesentity.models.entidad.Plantilla;
import com.hubmultiservice.servicesentity.models.response.ResponseFront;

 
public interface ServicesEntidad {
    

    public ResponseFront saveEntidad (Entidad entidad);
    public ResponseFront updateEntidad (Entidad entidad);


    public ResponseFront saveInterfaz (Interfaces interfaz);
    public ResponseFront updateInterfaz (Interfaces interfaz);


    public ResponseFront savePlantilla (Plantilla plantilla);
    public ResponseFront updatePlantilla (Plantilla plantilla);

    public ResponseFront saveCampo (CamposTag camposTag);
    public ResponseFront updateCampo (CamposTag camposTag);










}
