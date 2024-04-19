package com.hubmultiservice.servicesentity.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString   
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseFront {
    
        private Object data;
        private String mensaje;
        private String codigo; 
    }
