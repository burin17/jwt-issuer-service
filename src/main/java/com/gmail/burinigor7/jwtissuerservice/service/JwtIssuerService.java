package com.gmail.burinigor7.jwtissuerservice.service;

import com.gmail.burinigor7.jwtissuerservice.dto.CredentialsRequest;
import com.gmail.burinigor7.jwtissuerservice.dto.FetchedCredentials;
import com.gmail.burinigor7.jwtissuerservice.exception.UnauthorisedException;
import com.gmail.burinigor7.jwtissuerservice.inner_service_api.CrudServiceApiInterface;
import com.gmail.burinigor7.jwtissuerservice.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtIssuerService {
    private final JwtTokenProvider jwtTokenProvider;
    private final CrudServiceApiInterface crudServiceApiInterface;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public JwtIssuerService(JwtTokenProvider jwtTokenProvider,
                            CrudServiceApiInterface crudServiceApiInterface,
                            PasswordEncoder passwordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.crudServiceApiInterface = crudServiceApiInterface;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(CredentialsRequest credentialsRequest) {
        FetchedCredentials fetchedCredentials =
                crudServiceApiInterface.getUserByUsername(credentialsRequest.getUsername());
        validateUsername(fetchedCredentials);
        validatePassword(credentialsRequest, fetchedCredentials);
        return jwtTokenProvider.createToken(fetchedCredentials.getUsername());
    }

    private void validateUsername(FetchedCredentials credentials) {
        if (credentials == null) {
            throw new UnauthorisedException("User with given username doesn't exists.");
        }
    }

    private void validatePassword(CredentialsRequest credentialsRequest, FetchedCredentials fetchedCredentials) {
        if (passwordEncoder.matches(credentialsRequest.getPassword(), fetchedCredentials.getEncryptedPassword())) {
            throw new UnauthorisedException("Incorrect password");
        }
    }
}
