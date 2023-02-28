package com.uscaja.uscajaapi.services;

import com.uscaja.uscajaapi.models.BankAccount;
import com.uscaja.uscajaapi.models.Message;
import com.uscaja.uscajaapi.models.Transaction;
import com.uscaja.uscajaapi.models.User;
import com.uscaja.uscajaapi.repositories.BankAccountRepository;
import com.uscaja.uscajaapi.repositories.TransactionRepository;
import com.uscaja.uscajaapi.repositories.UserRepository;
import com.uscaja.uscajaapi.utils.DateGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BankAccountRepository bankRepo;

    public Iterable<Transaction> listTransactions() {
        return transactionRepo.findAll();
    }

    public Message createTransaction(Transaction transaction) {
        Message response = new Message();

        try {
            boolean isOwner = false;
            User involvedUser = transaction.getInvolvedUser();
            BankAccount sourceBankAccount = transaction.getSourceAccount();

            User dbInvolvedUser = findUserByDni(involvedUser.getDni());
            Optional<BankAccount> optionalValue = findBankAcountByNumber(sourceBankAccount.getAccountNumber());
            BankAccount dbSourceBankAccount;

            if (optionalValue.isPresent()) {
                dbSourceBankAccount = optionalValue.get();

                for (User owner: dbSourceBankAccount.getUsers()) {
                    if (owner.getDni().equals(dbInvolvedUser.getDni())) {
                        isOwner = true;
                        break;
                    }
                }
            }

            if (isOwner) {
                transaction.setDate(DateGenerator.currentDate());
                transactionRepo.save(transaction);
                response.setMessage("Transaction created");
            } else {
                response.setMessage("The user specified is not the owner of the account");
            }

        } catch (Exception e) {
            response.setMessage("It was not possible to register the transaction");
        }

        return response;
    }


    //External repos
    private User findUserByDni(String dni) {
        List<User> userList = userRepo.findUserByDni(dni);
        return userList.get(0);
    }
    public Optional<BankAccount> findBankAcountByNumber(int account) {
        return bankRepo.findById(account);
    }
}
