package com.unmsm.oevbackend.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequestDTO {

    private Long userId;

    private Long conferenceId;

}
