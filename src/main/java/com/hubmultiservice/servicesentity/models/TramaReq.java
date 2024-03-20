package com.hubmultiservice.servicesentity.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TramaReq {
    @JsonProperty("multiserviceRequest")
    private Request resquest;
}
