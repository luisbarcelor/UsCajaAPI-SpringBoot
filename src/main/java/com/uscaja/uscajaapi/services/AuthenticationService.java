package com.uscaja.uscajaapi.services;

import com.uscaja.uscajaapi.models.User;
import com.uscaja.uscajaapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepo;

    public String addUser(User user) {
        String msg;

        try {
            userRepo.save(user);
            msg = "User has been saved successfully";
        } catch (Exception e) {
            msg = "Invalid data";
        }

        return msg;
    }
}
