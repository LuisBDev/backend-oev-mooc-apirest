package com.unmsm.oevbackend.mapper;

import com.unmsm.oevbackend.dto.response.RegistrationResponseDTO;
import com.unmsm.oevbackend.model.Conference;
import com.unmsm.oevbackend.model.Registration;
import com.unmsm.oevbackend.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-08T21:05:08-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class RegistrationMapperImpl implements RegistrationMapper {

    @Override
    public RegistrationResponseDTO entityToResponseDTO(Registration registration) {
        if ( registration == null ) {
            return null;
        }

        RegistrationResponseDTO.RegistrationResponseDTOBuilder registrationResponseDTO = RegistrationResponseDTO.builder();

        registrationResponseDTO.conferenceId( registrationConferenceId( registration ) );
        registrationResponseDTO.userId( registrationUserId( registration ) );
        registrationResponseDTO.id( registration.getId() );
        registrationResponseDTO.status( registration.getStatus() );
        registrationResponseDTO.registrationDate( registration.getRegistrationDate() );

        return registrationResponseDTO.build();
    }

    @Override
    public List<RegistrationResponseDTO> entityListToResponseDTOList(List<Registration> registrations) {
        if ( registrations == null ) {
            return null;
        }

        List<RegistrationResponseDTO> list = new ArrayList<RegistrationResponseDTO>( registrations.size() );
        for ( Registration registration : registrations ) {
            list.add( entityToResponseDTO( registration ) );
        }

        return list;
    }

    private Long registrationConferenceId(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        Conference conference = registration.getConference();
        if ( conference == null ) {
            return null;
        }
        Long id = conference.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long registrationUserId(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        User user = registration.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
