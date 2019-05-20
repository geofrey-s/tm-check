package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    UserServiceImp userServiceImp;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userServiceImp.findUserByUserName(userName);

        if(user.equals(null)){
            return null;
        }

        log.info("loadUserByUserName() : {}", userName);


        UserBuilder builder = null;

        builder = org.springframework.security.core.userdetails.User.withUsername(userName);
        builder.password(user.getPassword());
        builder.roles(user.getRole());



        return builder.build();
    }
}
