package com.uscaja.uscajaapi.repositories;

import com.uscaja.uscajaapi.models.UserBankAccount;
import com.uscaja.uscajaapi.models.UserBankAccountId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBankAccountRepository extends CrudRepository<UserBankAccount, UserBankAccountId> {
}