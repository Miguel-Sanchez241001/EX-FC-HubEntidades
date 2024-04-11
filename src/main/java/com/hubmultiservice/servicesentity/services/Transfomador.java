package com.hubmultiservice.servicesentity.services;

import com.hubmultiservice.servicesentity.models.TramaResponseOutput;

public interface Transfomador {
    
    public TramaResponseOutput ConsultaOUT (String output);
    public TramaResponseOutput PagoOUT (String output);
    public TramaResponseOutput ExtornoOUT (String output);

}
