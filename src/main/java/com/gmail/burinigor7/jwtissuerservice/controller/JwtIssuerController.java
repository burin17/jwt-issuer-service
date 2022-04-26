package com.gmail.burinigor7.jwtissuerservice.controller;

import com.gmail.burinigor7.jwtissuerservice.dto.CredentialsRequest;
import com.gmail.burinigor7.jwtissuerservice.service.JwtIssuerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class JwtIssuerController {
    private final JwtIssuerService jwtIssuerService;

    @Autowired
    public JwtIssuerController(JwtIssuerService jwtIssuerService) {
        this.jwtIssuerService = jwtIssuerService;
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> login(@RequestBody CredentialsRequest credentialsDTO) {
        String token = jwtIssuerService.login(credentialsDTO);
        return ResponseEntity.ok().body(Map.of("jwt", token));
    }
}
