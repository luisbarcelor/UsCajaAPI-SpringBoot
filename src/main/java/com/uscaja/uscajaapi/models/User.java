package com.uscaja.uscajaapi.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@JsonIgnoreProperties("bankAccounts")
public class User {

    @Id
    @Column(name = "dni", nullable = false, length = 9)
    private String dni;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "last_name", nullable = false, length = 40)
    private String lastName;

    @Column(name = "phone", nullable = false, length = 9)
    private String phone;

    @Column(name = "birthyear", nullable = false, length = 4)
    private String birthYear;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private Set<BankAccount> bankAccounts = new HashSet<>();

    public User() {}

    public User(String dni, String name, String lastName, String phone, String birthYear, String address, String email, Set<BankAccount> bankAccounts) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.birthYear = birthYear;
        this.address = address;
        this.email = email;
        this.bankAccounts = bankAccounts;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
