package com.uscaja.uscajaapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users_bank_accounts")
public class UserBankAccount {

    @EmbeddedId
    private UserBankAccountId id;

    @ManyToOne
    @MapsId("userDni")
    @JoinColumn(name = "user_dni", referencedColumnName = "dni")
    private User user;

    @ManyToOne
    @MapsId("bankAccountNumber")
    @JoinColumn(name = "bank_account", referencedColumnName = "account_number")
    private BankAccount bankAccount;

    public UserBankAccount() {}

    public UserBankAccount(UserBankAccountId id, User user, BankAccount bankAccount) {
        this.id = id;
        this.user = user;
        this.bankAccount = bankAccount;
    }

    public UserBankAccountId getId() {
        return id;
    }

    public void setId(UserBankAccountId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
