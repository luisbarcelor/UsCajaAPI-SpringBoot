package com.uscaja.uscajaapi.repositories;

import com.uscaja.uscajaapi.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}

