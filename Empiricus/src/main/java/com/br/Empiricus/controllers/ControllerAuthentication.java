package com.br.Empiricus.controllers;

import com.br.Empiricus.domain.DTO.AuthenticationDTO;
import com.br.Empiricus.services.interfaces.ServiceUser;
import com.br.Empiricus.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class ControllerAuthentication {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ServiceUser userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity authentication(@RequestBody @Valid AuthenticationDTO data){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.cpf(),
                        data.password())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(data.cpf());
        String jwtToken = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(jwtToken);
    }
}
