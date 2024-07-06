package com.example.workmannager.controller;

import com.example.workmannager.DAO.UserDao;
import com.example.workmannager.model.DTO.AuthenticationRequest;
import com.example.workmannager.model.DTO.AuthenticationResponse;
import com.example.workmannager.services.UserDaoService;
import com.example.workmannager.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;

    private final UserDaoService userDetailsService;

    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<HttpStatus> authenticate(@RequestBody AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final UserDao user = userDetailsService.findUserByEmail(request.getUsername());
        String  token = "";
        if(user != null){
            token = jwtUtils.generateToken(user);
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setJwtKey(token);
            authenticationResponse.setRole(user.getAuthorities().stream().toList().get(0).toString());
            authenticationResponse.setId(user.getId());
            return new ResponseEntity(authenticationResponse, HttpStatus.OK);
        }
        return new ResponseEntity("Não Autorizado", HttpStatus.UNAUTHORIZED);
    }
}
