package com.unmsm.oevbackend.exception;

import lombok.*;


@Getter
@Setter
@Builder
public class ErrorDTO {

    private String message;

    public ErrorDTO(String message) {
        this.message = message;
    }

}
