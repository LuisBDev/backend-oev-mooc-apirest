package com.unmsm.oevbackend.mapper;

import com.unmsm.oevbackend.dto.response.RegistrationResponseDTO;
import com.unmsm.oevbackend.model.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    @Mapping(source = "conference.id", target = "conferenceId")
    @Mapping(source = "user.id", target = "userId")
    RegistrationResponseDTO entityToResponseDTO(Registration registration);

    List<RegistrationResponseDTO> entityListToResponseDTOList(List<Registration> registrations);

}
