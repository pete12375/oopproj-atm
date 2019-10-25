public class CurrentAccount extends Account {
    private double overdrawnLimit = 10000; // current account overdrawn limit
    private double availableOverdrawnBalance = overdrawnLimit; // available overdrawn balance for the account

    // CurrentAccount constructor initializes attributes
    public CurrentAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
    }

    // returns overdrawnLimit of the current account
    public double getCurrentAccount() {
        return overdrawnLimit;
    }

    // set overdrawnLimit of the current account
    public void setCurrentAccount(int limit) {
        overdrawnLimit = limit;
    }

    // return available overdrawn balance of the current account
    public double getAvailableOverdrawnBalance() {
        return availableOverdrawnBalance;
    }

    // debit available overdrawn balance of the current account
    public void debitAvailableOverdrawnBalance(double amount) {
        availableOverdrawnBalance -= amount;
    }
}
