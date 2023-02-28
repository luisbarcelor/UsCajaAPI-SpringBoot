package com.uscaja.uscajaapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserBankAccountId implements Serializable {
    @Column(name = "user_dni")
    private String userDni;

    @Column(name = "bank_account")
    private int bankAccountNumber;

    public UserBankAccountId() {}

    public UserBankAccountId(String userDni, int bankAccountNumber) {
        this.userDni = userDni;
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getUserDni() {
        return userDni;
    }

    public void setUserDni(String userDni) {
        this.userDni = userDni;
    }

    public int getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(int bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
}