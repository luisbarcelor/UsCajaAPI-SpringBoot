package com.uscaja.uscajaapi.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @Column(name = "account_number", nullable = false)
    private int accountNumber;

    @Column(name = "creation_date", nullable = false, length = 10)
    private String creationDate;

    @Column(name = "balance", nullable = false)
    private double balance = 0;

    @ManyToMany
    @JoinTable(
            name = "users_bank_accounts",
            joinColumns = @JoinColumn(name = "bank_account", referencedColumnName = "account_number"),
            inverseJoinColumns = @JoinColumn(name = "user_dni", referencedColumnName = "dni"))
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "sourceAccount")
    private Set<Transaction> transactionsFrom = new HashSet<>();

    @OneToMany(mappedBy = "destinationAccount")
    private Set<Transaction> transactionsTo = new HashSet<>();

    public BankAccount() {}
    public BankAccount(int accountNumber, String creationDate, double balance, Set<User> users, Set<Transaction> transactionsFrom, Set<Transaction> transactionsTo) {
        this.accountNumber = accountNumber;
        this.creationDate = creationDate;
        this.balance = balance;
        this.users = users;
        this.transactionsFrom = transactionsFrom;
        this.transactionsTo = transactionsTo;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Transaction> getTransactionsFrom() {
        return transactionsFrom;
    }

    public void setTransactionsFrom(Set<Transaction> transactionsFrom) {
        this.transactionsFrom = transactionsFrom;
    }

    public Set<Transaction> getTransactionsTo() {
        return transactionsTo;
    }

    public void setTransactionsTo(Set<Transaction> transactionsTo) {
        this.transactionsTo = transactionsTo;
    }
}
