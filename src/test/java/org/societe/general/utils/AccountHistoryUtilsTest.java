package org.societe.general.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.societe.general.model.AccountId;
import org.societe.general.service.AccountOperationService;
import org.societe.general.model.BankAccount;

import java.util.UUID;

class AccountHistoryUtilsTest {
    private BankAccount account;
    private AccountId accountId = new AccountId(UUID.randomUUID());
    private AccountOperationService transactionService = new AccountOperationService();

    @BeforeEach
    void setUp() {
        account = new BankAccount(accountId);
    }

    @Test
    void testPrintHistory() {
        transactionService.deposit(account, 100.25);
        transactionService.withdraw(account, 70.36);
        transactionService.deposit(account, 200);
        transactionService.deposit(account, 30.9);
        String history = AccountHistoryUtils.getHistoryOf(account);
        System.out.println(history);
        Assertions.assertEquals(6, history.trim().split("\n").length);
    }
}