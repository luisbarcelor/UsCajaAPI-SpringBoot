package com.uscaja.uscajaapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_code", nullable = false)
    private int transactionCode;

    @Column(name = "transaction_type", nullable = false, length = 20)
    private String transactionType;

    @Column(name = "date", nullable = false, length = 10)
    private String date;

    @Column(name = "amount", nullable = false)
    private float amount;

    @ManyToOne
    @JoinColumn(name = "source_account", referencedColumnName = "account_number")
    private BankAccount sourceAccount;

    @ManyToOne
    @JoinColumn(name = "destination_account", referencedColumnName = "account_number")
    private BankAccount destinationAccount;

    @ManyToOne
    @JoinColumn(name = "involved_user", referencedColumnName = "dni")
    private User involvedUser;

    public Transaction() {}
    public Transaction(int transactionCode, String transactionType, String date, float amount, BankAccount sourceAccount, BankAccount destinationAccount, User involvedUser) {
        this.transactionCode = transactionCode;
        this.transactionType = transactionType;
        this.date = date;
        this.amount = amount;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.involvedUser = involvedUser;
    }

    public int getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(int transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public BankAccount getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(BankAccount sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public BankAccount getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(BankAccount destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public User getInvolvedUser() {
        return involvedUser;
    }

    public void setInvolvedUser(User involvedUser) {
        this.involvedUser = involvedUser;
    }
}
