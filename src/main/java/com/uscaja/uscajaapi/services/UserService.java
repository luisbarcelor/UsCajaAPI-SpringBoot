package com.uscaja.uscajaapi.services;

import com.uscaja.uscajaapi.models.User;
import com.uscaja.uscajaapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserRepository repo;

    public String addUser(User user) {
        repo.save(user);
        return "User has been saved successfully";
    }
}
