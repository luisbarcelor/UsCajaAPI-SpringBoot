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
    private final String[] typeList = {"Transfer", "Top Up", "Withdrawal"};
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
            BankAccount dbSourceBankAccount = null;

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

                if (!transaction.getTransactionType().equals("Transfer")) {
                    transaction.setDestinationAccount(null);
                }

                if (makeTransaction(transaction, dbSourceBankAccount)) {
                    transactionRepo.save(transaction);
                    response.setMessage("Transaction created");
                } else {
                    response.setMessage("The source account may not have enough funds or the transaction type " +
                            "is incorrect. Only 3 possible types ('Top Up', 'Transfer', 'Withdrawal'). If type is 'Transfer' " +
                            "the accounts have to be different");
                }

            } else {
                response.setMessage("The user specified is not an owner of the account");
            }

        } catch (Exception e) {
            response.setMessage("It was not possible to register the transaction");
        }

        return response;
    }



    private boolean makeTransaction(Transaction transaction, BankAccount sourceBankAccount) {
        boolean isDone = false;
        float amount = transaction.getAmount();

        if (typeList[0].equals(transaction.getTransactionType())) { //Transfer
            int srcAccountNum = sourceBankAccount.getAccountNumber();
            int destAccountNum = transaction.getDestinationAccount().getAccountNumber();

            if (findBankAcountByNumber(destAccountNum).isPresent()) {
                BankAccount destAccount = findBankAcountByNumber(destAccountNum).get();
                destAccount.addAmount(amount);
                boolean isWithdrawn = sourceBankAccount.withdrawAmount(amount);

                if (srcAccountNum != destAccountNum) {
                    bankRepo.save(destAccount);
                    bankRepo.save(sourceBankAccount);
                    isDone = isWithdrawn;
                }
            }
        } else if (typeList[1].equals(transaction.getTransactionType())) { //Top Up
            sourceBankAccount.addAmount(amount);
            bankRepo.save(sourceBankAccount);
            isDone = true;
        } else if (typeList[2].equals(transaction.getTransactionType())) { //Withdrawal
            isDone = sourceBankAccount.withdrawAmount(amount);
        }

        return isDone;
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
