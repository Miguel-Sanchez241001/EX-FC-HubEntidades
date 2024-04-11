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
public class TramaRequestInput {
	/**
	 *                04  TI-TRAMA-1010.
      *          NUMERO DE CASILLAS ENVIADAS
                 05  TI10-NCASILLA     PIC 9(03).                         1    3
                 05  TI10-NUMREQ.
                     06 TI10-CODBCO    PIC 9(03).                         4    3
                     06 TI10-NUMSEC    PIC 9(09).                         7    9
                 05  TI10-TIPREQ       PIC 9(04).                        16    4
                 05  TI10-CODEF        PIC 9(03).                        20    3
                 05  TI10-FECREQ       PIC X(22).                        23   22
                 05  TI10-CODCAN       PIC 9(02).                        45    2
                 05  TI10-NUMCAN       PIC X(15).                        47   15
                 05  TI10-CODFOR       PIC 9(04).                        62    4
                 05  TI10-CASILLAS OCCURS 30 TIMES.
                     06 TI10-CODCAS    PIC 9(04).                        66    4
                     06 TI10-DESCAS    PIC X(15).                        70   15
                 05  TI10-FILLER       PIC X(7365).                     636   70
	 */
	private String NCASILLA;
	private String CODBCO;
	private String NUMSEC;
	private String TIPREQ;
	private String CODEF;
	private String FECREQ;
	private String CODCAN;
	private String NUMCAN;
	private String CODFOR;
	private List<Trama1010Casillas> CASILLAS;





}
