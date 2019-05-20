package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.User;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

public interface AuthenticationService {
    public User getAuthenticatedUserByUsername(String username) throws AuthenticationCredentialsNotFoundException;
}
