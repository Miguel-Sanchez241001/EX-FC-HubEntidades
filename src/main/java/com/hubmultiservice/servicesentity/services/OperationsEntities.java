package com.hubmultiservice.servicesentity.services;

import com.hubmultiservice.servicesentity.models.TramaRequestInput;

public interface OperationsEntities {
    
    
    public String Consulta(TramaRequestInput input);
    public String Pago(TramaRequestInput input);
    public String extorno(TramaRequestInput input);
}
