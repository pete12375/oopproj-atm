public class CurrentAccount extends Account {
    private double overdrawnLimit = 10000;
    private double availableOverdrawnBalance = overdrawnLimit;

    public CurrentAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
    }

    public double getCurrentAccount() {
        return overdrawnLimit;
    }

    public void setCurrentAccount(int limit) {
        overdrawnLimit = limit;
    }

    public double getAvailableOverdrawnBalance() {
        return availableOverdrawnBalance;
    }

    public void debitAvailableOverdrawnBalance(double amount) {
        availableOverdrawnBalance -= amount;
    }
}
