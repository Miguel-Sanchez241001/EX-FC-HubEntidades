package com.hubmultiservice.servicesentity.models.error;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private String path;
}
