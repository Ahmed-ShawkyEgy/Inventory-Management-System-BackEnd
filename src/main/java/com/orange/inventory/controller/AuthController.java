package com.orange.inventory.controller;

import java.time.Instant;
import java.util.Collections;

import javax.validation.Valid;

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

import com.orange.inventory.exception.AppException;
import com.orange.inventory.model.Role;
import com.orange.inventory.model.RoleName;
import com.orange.inventory.model.User;
import com.orange.inventory.payload.ApiResponse;
import com.orange.inventory.payload.JwtAuthenticationResponse;
import com.orange.inventory.payload.LoginRequest;
import com.orange.inventory.payload.SignUpRequest;
import com.orange.inventory.repository.RoleRepository;
import com.orange.inventory.repository.UserRepository;
import com.orange.inventory.security.JwtTokenProvider;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    	String email = loginRequest.getEmail();
    	String password = loginRequest.getPassword();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
        System.out.println("User "+loginRequest.getEmail() + " has logged in at "+ Instant.now());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        System.out.println("Token : "+jwt);
        
        User user = userRepository.findByEmail(email).get();
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt,user.getId(),user.getRoles()));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getEmail(), signUpRequest.getPassword(),signUpRequest.getFirst_name(),
                signUpRequest.getLast_name());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        userRepository.save(user);

        System.out.println("User "+user.getEmail() + " registered at "+Instant.now());
        
        return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully"));
    }
}