package com.uscaja.uscajaapi.repositories;

import com.uscaja.uscajaapi.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}

