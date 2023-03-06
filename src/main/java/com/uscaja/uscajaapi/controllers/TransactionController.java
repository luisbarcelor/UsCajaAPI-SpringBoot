package com.uscaja.uscajaapi.controllers;

import com.uscaja.uscajaapi.models.Message;
import com.uscaja.uscajaapi.models.Transaction;
import com.uscaja.uscajaapi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TransactionController {
    @Autowired
    private TransactionService service;
    @GetMapping("/list")
    public Iterable<Transaction> listTransactions() {
        return service.listTransactions();
    }

    @PostMapping("/create")
    public Message createTransaction(@RequestBody Transaction transaction) {
        return service.createTransaction(transaction);
    }
}
