package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.User;

public interface UserService {
    public void create();
    public User get();
    public User findUserById(Long id);
    public User findUserByUserName(String userName);
}
