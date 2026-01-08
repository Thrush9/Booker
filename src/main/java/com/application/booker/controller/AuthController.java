package com.application.booker.controller;

import com.application.booker.entity.Users;
import com.application.booker.jwtSecurity.JwtUtil;
import com.application.booker.model.AuthRequestDTO;
import com.application.booker.model.JWTResponse;
import com.application.booker.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // autowired from JWTAuthConfig
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthRequestDTO authRequest) {
        try {
            // verify Credentials
            Optional<Users> searchUser = userService.findUserByUsername(authRequest.getUsername());
            if (searchUser.isEmpty()) {
                return new ResponseEntity<String>("Error: Invalid Username!", HttpStatus.BAD_REQUEST);
            }
            if(!passwordEncoder.matches(authRequest.getPassword(),searchUser.get().getPassword())){
                return new ResponseEntity<String>("Error: Invalid Password!", HttpStatus.BAD_REQUEST);
            }

            //Authentication
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //JWT generation
            String jwtToken = jwtUtil.generateToken(authRequest.getUsername());
            Users authenticatedUser = searchUser.get();
            JWTResponse jwtResponse = new JWTResponse(authenticatedUser.getUsername(),authenticatedUser.getEmail(), jwtToken);
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e); 
        }

    }
}
