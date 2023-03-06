package com.uscaja.uscajaapi.services;

import com.uscaja.uscajaapi.models.BankAccount;
import com.uscaja.uscajaapi.models.Message;
import com.uscaja.uscajaapi.models.Transaction;
import com.uscaja.uscajaapi.models.User;
import com.uscaja.uscajaapi.repositories.BankAccountRepository;
import com.uscaja.uscajaapi.repositories.UserRepository;
import com.uscaja.uscajaapi.utils.DateGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BankAccountService {
    private int tries = 0;
    @Autowired
    private BankAccountRepository bankRepo;
    @Autowired
    private UserRepository userRepo;

    public Optional<BankAccount> findBankAcountByNumber(int account) {
        return bankRepo.findById(account);
    }
    public Iterable<BankAccount> listAll() {
        return bankRepo.findAll();
    }
    public Message createBankAccount(BankAccount bankAccount, User user) {
        Message response = new Message();
        Random random = new Random();
        int randomNumber = random.nextInt(900000000) + 1000000000;
        User existingUser = findUserByDni(user.getDni());

        bankAccount.setAccountNumber(randomNumber);
        bankAccount.getUsers().add(existingUser);
        bankAccount.setCreationDate(DateGenerator.currentDate());

        if (existingUser == null) {
            response.setObject(null);
            response.setMessage("There was a problem creating the bank account. The user specified does not exist");
        } else {
            try {
                bankRepo.save(bankAccount);
                tries = 0;
            } catch (Exception exception) {
                if (tries < 4) {
                    tries++;
                    createBankAccount(bankAccount, user);
                } else {
                    response.setMessage("There was a problem creating the bank account");
                    response.setObject(null);
                }
            }

            response.setMessage("Bank account created successfully");
            response.setObject(bankAccount);
        }

        return response;
    }
    public List<BankAccount> listBankAccountsByUser(String dni) {
        List<BankAccount> bankAccountList = new ArrayList<>();

        try {
            User user = findUserByDni(dni);
            bankAccountList = bankRepo.findBankAccountsByUsersContaining(user);
        } catch (Exception ignored) {}

        return bankAccountList;
    }

    public Message deleteUserFromAccount(String dni, int account) {
        Message response = new Message();

        if (listAccountOwners(account).size() != 1) {
            try {
                BankAccount dbBankAccount = findBankAcountByNumber(account).get();
                User dbUser = findUserByDni(dni);
                Set<User> users = dbBankAccount.getUsers();
                users.remove(dbUser);

                bankRepo.save(dbBankAccount);
                response.setMessage("User ownership removed from the account");
            } catch (Exception e) {
                response.setMessage("The account number or user provided does not exist");
            }
        } else {
            response.setMessage("A bank account must have at least one owner");
        }

        return response;
    }

    public Message addUserToAccount(String dni, int account) {
        Message response = new Message();

        try {
            BankAccount dbBankAccount = findBankAcountByNumber(account).get();
            User dbUser = findUserByDni(dni);
            Set<User> users = dbBankAccount.getUsers();
            users.add(dbUser);

            bankRepo.save(dbBankAccount);
            response.setMessage("Successfully added user to the account");
        } catch (Exception e) {
            response.setMessage("The account number or user provided do not exist");
        }

        return response;
    }

    public Set<User> listAccountOwners(int account) {
        Set<User> response = new HashSet<>();
        Optional<BankAccount> dbBankAccount = findBankAcountByNumber(account);

        if (dbBankAccount.isPresent()) {
            BankAccount bankAccount = dbBankAccount.get();
            response = bankAccount.getUsers();
        }

        return response;
    }

    public Set<Transaction> listTransactionsFrom(int account) {
        Set<Transaction> transactions = new HashSet<>();
        Optional<BankAccount> optionalValue = findBankAcountByNumber(account);

        if (optionalValue.isPresent()) {
            BankAccount bankAccount = optionalValue.get();
            transactions = bankAccount.getTransactionsFrom();
        }

        return transactions;
    }

    public Set<Transaction> listTransactionsTo(int account) {
        Set<Transaction> transactions = new HashSet<>();
        Optional<BankAccount> optionalValue = findBankAcountByNumber(account);

        if (optionalValue.isPresent()) {
            BankAccount bankAccount = optionalValue.get();
            transactions = bankAccount.getTransactionsTo();
        }

        return transactions;
    }

    //External repos
    private User findUserByDni(String dni) {
        List<User> userList = userRepo.findUserByDni(dni);
        return userList.get(0);
    }
}
