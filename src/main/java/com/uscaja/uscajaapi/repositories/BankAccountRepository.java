package com.uscaja.uscajaapi.repositories;

import com.uscaja.uscajaapi.models.BankAccount;
import com.uscaja.uscajaapi.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {
    List<BankAccount> findBankAccountsByUsersContaining(User user);
}

