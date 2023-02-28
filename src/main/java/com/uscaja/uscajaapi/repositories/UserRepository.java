package com.uscaja.uscajaapi.repositories;

import com.uscaja.uscajaapi.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    List<User> findUserByDni(String dni);
}

