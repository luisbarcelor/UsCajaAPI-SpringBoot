package com.uscaja.uscajaapi.models;

public class Message {
    private String message;
    private BankAccount object;

    public Message() {}

    public Message(String message) {
        this.message = message;
    }

    public Message(String message, BankAccount object) {
        this.message = message;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BankAccount getObject() {
        return object;
    }

    public void setObject(BankAccount object) {
        this.object = object;
    }
}
