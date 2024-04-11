package com.hubmultiservice.servicesentity.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TramaResponseOutput {
    
    /**
                 05  TO15-NUMREQ.
                     06 TO15-CODBCO    PIC 9(03).                         1    3
                     06 TO15-NUMSEC    PIC 9(09).                         4    9
                 05  TO15-TIPREQ       PIC 9(04).                        13    4
                 05  TO15-CODEF        PIC 9(03).                        17    3
                 05  TO15-FECREQ       PIC X(22).                        20   22
                 05  TO15-CODMSG       PIC 9(04).                        42    4
                 05  TO15-CASILLAS OCCURS 20 TIMES.
                     06 TO15-CODCAS    PIC 9(04).                        46    4
                     06 TO15-DESCAS    PIC X(15).                        50   15
                     06 TO15-TMSGCAS   PIC X(04).                        65    4
                     06 TO15-DMSGCAS   PIC X(100).                       69  100
                 05  TO15-CASILLAS1 OCCURS 70 TIMES.
                     06 TO15-CODCAS1   PIC 9(04).                      2506    4
                     06 TO15-DESCAS1   PIC X(15).                      2510   15
                 05  TI15-FILLER       PIC X(4165).                    3836 2510
--------------------------------------------------------------------------------
                    LONGITUD TOTAL DEL REGISTRO --                         6,345
 */
    private String codigoRetorno;
    private String desMensaje;
    private String CODBCO;
    private String NUMSEC;
    private String TIPREQ;
    private String CODEF;
    private String FECREQ;
    private String CODMSG;
    private List<Trama1015Casillas> CASILLAS;




}
