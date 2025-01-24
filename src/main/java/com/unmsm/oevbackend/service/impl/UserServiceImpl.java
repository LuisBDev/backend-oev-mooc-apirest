package com.unmsm.oevbackend.service.impl;

import com.unmsm.oevbackend.dto.request.UpdateUserRequestDTO;
import com.unmsm.oevbackend.dto.response.UserResponseDTO;
import com.unmsm.oevbackend.mapper.UserMapper;
import com.unmsm.oevbackend.model.User;
import com.unmsm.oevbackend.repository.IUserRepository;
import com.unmsm.oevbackend.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<UserResponseDTO> findAllUsers() {
        List<User> findAllUsers = userRepository.findAll();
        return userMapper.entityListToDTOList(findAllUsers);
    }

    @Override
    public UserResponseDTO findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return userMapper.entityToDTO(user.get());
    }

    @Override
    public UserResponseDTO findUserByEmail(String username) {
        return null;
    }

    @Override
    public UserResponseDTO updateUserById(Long id, UpdateUserRequestDTO updateUserRequestDTO) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {

    }
}
