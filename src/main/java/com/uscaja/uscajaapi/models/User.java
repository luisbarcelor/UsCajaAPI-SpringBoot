package com.uscaja.uscajaapi.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name="users",
    uniqueConstraints={
        @UniqueConstraint(columnNames="username"),
        @UniqueConstraint(columnNames="email")
    })
@JsonIgnoreProperties("bankAccounts")
public class User {

    @NotBlank
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

    @Email
    @Size(max = 50)
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private Set<BankAccount> bankAccounts = new HashSet<>();

    @Size(max = 20)
    private String username;
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_dni", referencedColumnName = "dni"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(String dni, String name, String lastName, String phone, String birthYear, String address, String email, Set<BankAccount> bankAccounts, String username, String password, Set<Role> roles) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.birthYear = birthYear;
        this.address = address;
        this.email = email;
        this.bankAccounts = bankAccounts;
        this.username = username;
        this.password = password;
        this.roles = roles;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
