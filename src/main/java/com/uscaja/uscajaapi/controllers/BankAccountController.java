package com.uscaja.uscajaapi.controllers;

import com.uscaja.uscajaapi.models.BankAccount;
import com.uscaja.uscajaapi.models.Message;
import com.uscaja.uscajaapi.models.Transaction;
import com.uscaja.uscajaapi.models.User;
import com.uscaja.uscajaapi.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/bank-accounts")
public class BankAccountController {
    @Autowired
    public BankAccountService bankService;

    @GetMapping("/list")
    public Iterable<BankAccount> listAll() {
        return bankService.listAll();
    }

    @GetMapping("/{account}/owners")
    public Set<User> listAccountOwners(@PathVariable int account) {
        return bankService.listAccountOwners(account);
    }

    @GetMapping("/list/by-user/{dni}")
    public List<BankAccount> listUserSpecificBankAccounts(@PathVariable String dni) {
        return bankService.listBankAccountsByUser(dni);
    }

    @GetMapping("/list/transactions-from")
    public Set<Transaction> listAccountTransactionsFrom(@RequestParam int account) {
        return bankService.listTransactionsFrom(account);
    }

    @GetMapping("/list/transactions-to")
    public Set<Transaction> listAccountTransactionsTo(@RequestParam int account) {
        return bankService.listTransactionsTo(account);
    }

    @PostMapping("/create")
    public Message createBankAccount(@RequestBody User user) {
        return bankService.createBankAccount(new BankAccount(), user);
    }

    @DeleteMapping("/remove-owner/{dni}")
    public Message removeOwner(@PathVariable String dni, @RequestParam(value = "account") int accountNumber) {
        return bankService.deleteUserFromAccount(dni, accountNumber);
    }

    @PostMapping("add-owner/{dni}")
    public Message addOwner(@PathVariable String dni, @RequestParam(value = "account") int accountNumber) {
        return bankService.addUserToAccount(dni, accountNumber);
    }
}
