package com.stzelas.gr.spring_api.authentication;

import com.stzelas.gr.spring_api.core.exceptions.AppObjectNotAuthorizedException;
import com.stzelas.gr.spring_api.dto.AuthenticationRequestDTO;
import com.stzelas.gr.spring_api.dto.AuthenticationResponseDTO;
import com.stzelas.gr.spring_api.model.User;
import com.stzelas.gr.spring_api.repositories.UserRepository;
import com.stzelas.gr.spring_api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;


    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO dto)
            throws AppObjectNotAuthorizedException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new AppObjectNotAuthorizedException("User", "User not authorized"));

        String token = jwtService.generateToken(authentication.getName());
        return new AuthenticationResponseDTO(user.getFirstname(), user.getLastname(), token);
    }
}