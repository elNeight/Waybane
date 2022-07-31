package com.example.waybane.services;

import com.example.waybane.models.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    boolean save(User user);

}
