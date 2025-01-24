package com.unmsm.oevbackend.controller;

import com.unmsm.oevbackend.dto.request.UserLoginRequestDTO;
import com.unmsm.oevbackend.dto.request.UserRegisterRequestDTO;
import com.unmsm.oevbackend.dto.response.AuthUserResponseDTO;
import com.unmsm.oevbackend.dto.response.UserResponseDTO;
import com.unmsm.oevbackend.service.interfaces.IAuthService;
import com.unmsm.oevbackend.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthUserResponseDTO> login(@Valid @RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        return ResponseEntity.ok(authService.login(userLoginRequestDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        return ResponseEntity.ok(authService.register(userRegisterRequestDTO));
    }


}
