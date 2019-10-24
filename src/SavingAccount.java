public class SavingAccount extends Account {
    private double interestRate = 0.001;
    private int compoundedTimes = 1;

    // SavingAccount constructor
    public SavingAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
        // initialize superclass variables
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
    }

    public void setInterestRate(double newRate) {
        interestRate = newRate;
    }

    public void setCompoundedTimes(int newTimes) {
        compoundedTimes = newTimes;
    }

    public double getInterestRate() {return interestRate;}

    public int getCompoundedTimes() {return compoundedTimes;}

    public double getAnnualInterest() {
        int ai = 0;
        for (int i = 1; i <= compoundedTimes; i++)
            ai += (getTotalBalance() * interestRate);
        return ai;
    }
}
