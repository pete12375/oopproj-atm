public class CurrentAccount extends Account {
    private double overdrawnLimit = -10000;

    public CurrentAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
    }

    public double getCurrentAccount() {
        return overdrawnLimit;
    }

    public void setCurrentAccount(int limit) {
        overdrawnLimit = limit;
    }

    public void checkLimitOfTotalBalance() {
        if (getTotalBalance() < overdrawnLimit)
            System.out.println("You cannot exceed the overdrawn limit which is HK$10,000.");
    }
}
