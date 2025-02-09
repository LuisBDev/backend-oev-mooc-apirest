package com.unmsm.oevbackend.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationResponseDTO {


    private Long id;


    private Long userId;


    private Long conferenceId;

    private String status;


    private LocalDateTime registrationDate;
}
