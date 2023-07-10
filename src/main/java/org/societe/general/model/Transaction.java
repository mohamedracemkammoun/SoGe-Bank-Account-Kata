package org.societe.general.model;

import java.time.LocalDateTime;

public class Transaction {
    private TransactionType type;
    private LocalDateTime date;
    private double amount;
    private double balance;

    public Transaction(TransactionType type, double amount, double balance) {
        this.type = type;
        this.date = LocalDateTime.now();
        this.amount = amount;
        this.balance = balance;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }
}