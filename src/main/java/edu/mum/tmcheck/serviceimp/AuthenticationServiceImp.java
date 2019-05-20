package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.User;
import edu.mum.tmcheck.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImp implements AuthenticationService {
    @Autowired
    UserServiceImp userServiceImp;

    @Override
    public User getAuthenticatedUserByUsername(String username) throws AuthenticationCredentialsNotFoundException {
        User user = userServiceImp.findUserByUserName(username);

        if (user == null)
            throw new AuthenticationCredentialsNotFoundException("Not Authenticated");

        return user;
    }
}
