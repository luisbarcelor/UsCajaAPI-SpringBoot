package com.uscaja.uscajaapi.controllers;

import com.uscaja.uscajaapi.models.Message;
import com.uscaja.uscajaapi.models.User;
import com.uscaja.uscajaapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @GetMapping("/get-user")
    public User getUser(){
        return new User("54788319L", "Luis", "Barcelo", "722116525", "2003",
                "Calle Chapistas 2", "luisicuba03@outlook.com", new HashSet<>());
    }

    @PostMapping("/add-user")
    public Message addUser(@RequestBody User user) {
        String response = service.addUser(user);
        return new Message(response);
    }
}
