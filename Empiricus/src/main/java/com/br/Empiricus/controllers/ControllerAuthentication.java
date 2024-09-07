package com.br.Empiricus.controllers;

import com.br.Empiricus.services.interfaces.ServiceAuthentication;
import com.br.Empiricus.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class ControllerAuthentication {

    @Autowired
    private ServiceAuthentication service;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> authentication(String cpf, String password){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(cpf,
                            password)
            );
        } catch (Exception ex) {
            //throw new AuthenticationException("Invalid credentials");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(cpf);
        String jwtToken = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(jwtToken);
    }
}
