package com.unmsm.oevbackend.dto.request;

import com.unmsm.oevbackend.model.enums.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDTO {

    private String name;

    private String paternalSurname;

    private String maternalSurname;

    private String email;

    private String password;

    private String phone;

    private Role role;


}
