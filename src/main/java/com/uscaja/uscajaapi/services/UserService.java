package com.uscaja.uscajaapi.services;

import com.uscaja.uscajaapi.models.Message;
import com.uscaja.uscajaapi.models.User;
import com.uscaja.uscajaapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public String addUser(User user) {
        userRepo.save(user);
        return "User has been saved successfully";
    }

    public Iterable<User> listUsers() {
        return userRepo.findAll();
    }

    public Message updateUser(User updatedUser) {
        Message response = new Message();
        try {
            User dbUser = findUserByDni(updatedUser.getDni());
            dbUser.setName(updatedUser.getName());
            dbUser.setLastName(updatedUser.getLastName());
            dbUser.setBirthYear(updatedUser.getBirthYear());
            dbUser.setAddress(updatedUser.getAddress());
            dbUser.setPhone(updatedUser.getPhone());
            dbUser.setEmail(updatedUser.getEmail());
            userRepo.save(dbUser);

            response.setMessage("The user has been updated");
        } catch (IndexOutOfBoundsException ie) {
            response.setMessage("The user requested for the update does not exist");
        }

        return response;
    }

    public Message deleteUser(String dni) {
        userRepo.deleteById(dni);
        return new Message("The user has been deleted");
    }

    private User findUserByDni(String dni) {
        List<User> userList = userRepo.findUserByDni(dni);
        return userList.get(0);
    }
}
