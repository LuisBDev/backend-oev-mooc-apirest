package com.unmsm.oevbackend.service.impl;

import com.unmsm.oevbackend.dto.request.ConferenceRequestDTO;
import com.unmsm.oevbackend.dto.response.ConferenceResponseDTO;
import com.unmsm.oevbackend.exception.AppException;
import com.unmsm.oevbackend.exception.UserNotFoundException;
import com.unmsm.oevbackend.mapper.ConferenceMapper;
import com.unmsm.oevbackend.model.Conference;
import com.unmsm.oevbackend.model.Course;
import com.unmsm.oevbackend.model.User;
import com.unmsm.oevbackend.model.enums.Role;
import com.unmsm.oevbackend.repository.IConferenceRepository;
import com.unmsm.oevbackend.repository.IUserRepository;
import com.unmsm.oevbackend.service.interfaces.IConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConferenceServiceImpl implements IConferenceService {

    private final IConferenceRepository conferenceRepository;

    private final ConferenceMapper conferenceMapper;

    private final IUserRepository userRepository;

    @Override
    public List<ConferenceResponseDTO> findAllConferences() {
        List<Conference> allConferences = conferenceRepository.findAll();
        return conferenceMapper.entityListToDTOList(allConferences);
    }

    @Override
    public List<ConferenceResponseDTO> findAllConferencesByUserId(Long userId) {
        List<Conference> allConferences = conferenceRepository.findConferencesPublishedByUserId(userId);
        return conferenceMapper.entityListToDTOList(allConferences);
    }

    @Override
    public ConferenceResponseDTO findConferenceById(Long id) {
        Optional<Conference> conference = conferenceRepository.findById(id);
        if (conference.isEmpty()) {
            throw new AppException("Conference with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return conferenceMapper.entityToDTO(conference.get());
    }

    @Override
    public void deleteConferenceById(Long id) {
        conferenceRepository.deleteById(id);
    }

    @Override
    public ConferenceResponseDTO createConference(Long userId, ConferenceRequestDTO conferenceRequestDTO) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id " + userId + " not found", HttpStatus.NOT_FOUND);
        }

        User userEntity = user.get();

        if (!userEntity.getRole().equals(Role.ADMINISTRATIVE)) {
            throw new AppException("User with id " + userId + " is not allowed to create courses", HttpStatus.FORBIDDEN);
        }

        Conference conference = conferenceMapper.requestDTOToEntity(conferenceRequestDTO);
        conference.setUser(userEntity);
        conference.setCreationDate(LocalDateTime.now());
        conference.setTotalStudents(0);
        //TODO: Agregar campos automáticos al crear un curso

        conferenceRepository.save(conference);
        return conferenceMapper.entityToDTO(conference);
    }
}
