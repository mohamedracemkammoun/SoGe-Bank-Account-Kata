package org.societe.general.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.societe.general.exception.NotAvailableFundsException;
import org.societe.general.model.AccountId;
import org.societe.general.model.BankAccount;
import org.societe.general.model.Transaction;
import org.societe.general.model.TransactionType;

import java.util.List;
import java.util.UUID;

class AccountOperationServiceTest {
    private BankAccount account;
    private AccountId accountId = new AccountId(UUID.randomUUID());
    private AccountOperationService transactionService = new AccountOperationService();

    @BeforeEach
    public void setUp() {
        account = new BankAccount(accountId);
    }

    @Test
    void depositShouldIncreaseBalance() {
        final double TRANSACTION_AMOUNT = 100;
        transactionService.deposit(account,TRANSACTION_AMOUNT);

        Assertions.assertEquals(TRANSACTION_AMOUNT, account.getBalance());
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        final double FIRST_TRANSACTION_AMOUNT = 100;
        final double SECOND_TRANSACTION_AMOUNT = 70.3;

        transactionService.deposit(account,FIRST_TRANSACTION_AMOUNT);
        transactionService.withdraw(account,SECOND_TRANSACTION_AMOUNT);

        Assertions.assertEquals(FIRST_TRANSACTION_AMOUNT - SECOND_TRANSACTION_AMOUNT, account.getBalance());
    }

    @Test
    void withdrawWithNegativeValueThrowException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            transactionService.deposit(account,-50);
        });
    }

    @Test
    void depositWithNegativeValueThrowException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            transactionService.withdraw(account,-50);
        });
    }

    @Test
    void withdrawInsufficientFundsThrowException() {
        final double TRANSACTION_AMOUNT = 20;
        transactionService.deposit(account, TRANSACTION_AMOUNT);

        Assertions.assertThrows(NotAvailableFundsException.class, () -> {
            transactionService.withdraw(account,TRANSACTION_AMOUNT + 30);
        });
    }

    @Test
    void getTransactionsShouldReturnTransactionHistory() {
        final double FIRST_TRANSACTION_AMOUNT = 100;
        final double SECOND_TRANSACTION_AMOUNT = 60.5;
        final double THIRD_TRANSACTION_AMOUNT = 50;

        transactionService.deposit(account, FIRST_TRANSACTION_AMOUNT);
        transactionService.withdraw(account, SECOND_TRANSACTION_AMOUNT);
        transactionService.deposit(account, THIRD_TRANSACTION_AMOUNT);

        List<Transaction> transactions = account.getTransactions();

        Assertions.assertEquals(3, transactions.size());
        Assertions.assertEquals(TransactionType.DEPOSIT, transactions.get(0).getType());
        Assertions.assertEquals(SECOND_TRANSACTION_AMOUNT, transactions.get(1).getAmount());
        Assertions.assertEquals(transactions.get(1).getBalance() + THIRD_TRANSACTION_AMOUNT, transactions.get(2).getBalance());
    }

}