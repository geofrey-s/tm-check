package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.User;
import edu.mum.tmcheck.domain.repository.UserRepository;
import edu.mum.tmcheck.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void create() {

    }

    @Override
    public User get() {
        return null;
    }

    @Override
    public User findUserById(Long userid) {
        return userRepository.findById(userid).get();
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findUserByUsername(userName);
    }
}
