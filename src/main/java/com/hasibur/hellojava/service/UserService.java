package com.hasibur.hellojava.service;

import com.hasibur.hellojava.model.User;

import java.util.List;

public interface UserService {

    public User saveUser(User user);
    public User findByEmail(String email);

    public List<User> findAll();
}
