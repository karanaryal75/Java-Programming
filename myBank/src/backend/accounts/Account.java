package backend.accounts;

import backend.accounts.exceptions.InsufficientFunds;

import java.sql.SQLOutput;
import java.util.Arrays;

/**
 * The most important idea at any bank is the accounts of the members.
 * Thus this class contains a lot of logic to do all the transactions
 * performed at a bank.
 * An account is given a variable amount of interest
 * each week based on the average balance of the account
 * over the past 7 days.
 */
public class Account {
    private AccountId accountId;
    private double balance;
    private double[] balanceHistory;
    private double loanBalance;

    public Account(AccountId accountId,
                   double balance) {
        this.accountId = accountId;
        this.balance = balance;
        this.balanceHistory = new double[7];
        Arrays.fill(balanceHistory, balance);
        this.loanBalance = 0;
    }

    public AccountId getAccountId() { return accountId;}

    public double balance() {
        return balance;
    }

    public double[] balanceHistory() {
        return balanceHistory;
    }

    public double loanBalance() {
        return loanBalance;
    }

    /**
     * Withdraw the given amount from the account.
     * Throws an exception if the account does not
     * have sufficient funds.
     * @param amount Amount to withdraw
     * @throws InsufficientFunds
     */
    // TODO: Fill this in with the above logic
    public void withdraw(double amount) throws InsufficientFunds {
        try {
            if (balance  >= amount)
            {
                balance = balance - amount;
            }
        }
        catch(Exception e){
            System.out.println("The account doesn't have sufficient fund!");
        }
    }

    /**
     * Deposit the given amount into the account
     * @param amount Amount to deposit
     */
    // TODO: Fill this in with the above logic
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Add the given amount to the loan balance
     * @param amount Amount to add
     */
    // TODO: Fill this in with the above logic
    public void addLoan(double amount) {
        loanBalance = amount + loanBalance;
    }

    /**
     * Subtract the given amount from the loan balance.
     * @param amount Amount to pay
     */
    // TODO: Fill this in with the above logic
    public void payLoan(double amount) {
        loanBalance = loanBalance - amount;
    }

    /**
     * Give interest to the loan balance equal to the given rate.
     * The formula for giving interest is:
     * loan balance = (loan balance) * rate
     * @param rate Rate of interest accruing on loan. Given as decimal
     *             between 0 and 1.
     */
    // TODO: Fill this in with the above logic
    public void payLoanInterest(double rate) {
        loanBalance = loanBalance * rate;
    }

    /**
     * Update the history with a new amount. The balanceHistory only holds
     * the last 7 account balances. Thus you must remove the oldest balance
     * and add the current balance into the array.
     */
    // TODO: Fill this in with the above logic
    public void updateHistory() {
        for (int i = balanceHistory.length - 2; i >= 0; i--)
        {
            balanceHistory[i+1] = balanceHistory[i];
        }
    }

    /**
     * Add interest income to this account equal to:
     * balance = balance +
     *   (average balance over the past 7 days) * interestRate
     */
    // TODO: Fill this in with the above logic
    public void postInterest(double interestRate) {
        int total = 0;
        for(int i = 0; i < (balanceHistory.length - 1); i ++)
        {
            total += balanceHistory[i];
        }
        int average = total / 7;
        balance = balance + average * interestRate ;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", balanceHistory=" + Arrays.toString(balanceHistory) +
                ", loanBalance=" + loanBalance +
                '}';
    }
}
