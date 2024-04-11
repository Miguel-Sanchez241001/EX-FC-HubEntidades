package com.hubmultiservice.servicesentity.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trama1015Casillas {
/**
 *  				 06 TO15-CODCAS    PIC 9(04).                        46    4
                     06 TO15-DESCAS    PIC X(15).                        50   15
                     06 TO15-TMSGCAS   PIC X(04).                        65    4
                     06 TO15-DMSGCAS   PIC X(100).                       69  100
 */
	
 private String CODCAS;
 private String DESCAS;
 private String TMSGCAS;
 private String DMSGCAS;
}
