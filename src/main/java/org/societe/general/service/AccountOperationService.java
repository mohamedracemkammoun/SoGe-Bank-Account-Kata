package org.societe.general.service;

import org.societe.general.exception.NotAvailableFundsException;
import org.societe.general.model.BankAccount;
import org.societe.general.model.Transaction;
import org.societe.general.model.TransactionType;

public class AccountOperationService {

    public void deposit(BankAccount account, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        account.setBalance(account.getBalance() + amount);
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, amount, account.getBalance());
        account.addTransaction(transaction);
    }

    public void withdraw(BankAccount account, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        double oldBalance = account.getBalance();
        if (amount > oldBalance) {
            throw new NotAvailableFundsException("Insufficient funds");
        }
        account.setBalance(oldBalance - amount);
        Transaction transaction = new Transaction(TransactionType.WITHDRAWAL, amount, account.getBalance());
        account.addTransaction(transaction);
    }
}
