package org.societe.general.model;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private final AccountId accountId;
    private double balance;
    private List<Transaction> transactions;

    public BankAccount(AccountId accountId) {
        this.accountId = accountId;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double newBalance) {
        balance = newBalance;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}