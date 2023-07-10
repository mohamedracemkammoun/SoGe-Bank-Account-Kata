package org.societe.general.utils;

import org.societe.general.model.Transaction;
import org.societe.general.model.BankAccount;

import java.util.List;

public class AccountHistoryUtils {

    private AccountHistoryUtils() {}

    public static String getHistoryOf(BankAccount account) {
        List<Transaction> transactions = account.getTransactions();
        StringBuilder history = new StringBuilder();
        history.append("History for Account: " + account.getAccountId().getId() + "\n");
        history.append("Date\t\t\tAmount\t\tBalance\t\tOperation\n");
        for (Transaction transaction : transactions) {
            history.append(String.format("%s\t%.2f\t\t%.2f\t\t%s%n",
                    transaction.getDate(), transaction.getAmount(),
                    transaction.getBalance(), transaction.getType()));
        }
        return history.toString();
    }
}
