package com.uscaja.uscajaapi.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uscaja.uscajaapi.models.Message;
import com.uscaja.uscajaapi.models.User;
import com.uscaja.uscajaapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/list")
    public Iterable<User> listAllUsers(){
        return service.listUsers();
    }

    @PostMapping("/add")
    public Message addUser(@RequestBody User user) {
        String response = service.addUser(user);
        return new Message(response);
    }

    @DeleteMapping("/delete/{dni}")
    public Message deleteUser(@PathVariable String dni) {
        return service.deleteUser(dni);
    }

    @PutMapping("/update")
    public Message updateUser(@RequestBody User updatedUser) {
        return service.updateUser(updatedUser);
    }
}
