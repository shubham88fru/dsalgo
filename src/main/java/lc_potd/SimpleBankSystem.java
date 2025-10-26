package lc_potd;

//@link - https://leetcode.com/problems/simple-bank-system/?
public class SimpleBankSystem {
    private int n;
    private long[] balance;

    public SimpleBankSystem(long[] balance) {
        this.n = balance.length;
        this.balance = balance;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (!validAccount(account1) || !validAccount(account2)) return false;

        if (money <= balance[account1-1]) {
            balance[account2-1] += money;
            balance[account1-1] -= money;

            return true;
        }

        return false;
    }

    public boolean deposit(int account, long money) {
        if (!validAccount(account)) return false;

        balance[account-1] += money;

        return true;
    }

    public boolean withdraw(int account, long money) {
        if (!validAccount(account)) return false;

        if (money <= balance[account-1]) {
            balance[account-1] -= money;

            return true;
        }

        return false;
    }

    private boolean validAccount(int accountNum) {
        if (accountNum > n) return false;

        return true;
    }
}
