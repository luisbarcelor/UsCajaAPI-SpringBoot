package com.uscaja.uscajaapi.controllers;

import com.uscaja.uscajaapi.models.Message;
import com.uscaja.uscajaapi.models.User;
import com.uscaja.uscajaapi.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;

    @PostMapping("/sign-up")
    public Message addUser(@RequestBody User user) {
        String response = service.addUser(user);
        return new Message(response);
    }
}
