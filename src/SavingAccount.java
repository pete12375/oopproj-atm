public class SavingAccount extends Account {
    private double InterestRate = 0.001;
    private int CompoundedTimes = 1;

    // SavingAccount constructor
    public SavingAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
        // initialize superclass variables
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
    }

    public void setInterestRate(double newRate) {
        InterestRate = newRate;
    }

    public void setCompoundedTimes(int newTimes) {
        CompoundedTimes = newTimes;
    }

    public double getInterestRate() {return InterestRate;}
    public int getCompoundedTimes() {return CompoundedTimes;}
    public double getAnnualInterest() {
        int ai = 0;
        for (int i = 1; i <= CompoundedTimes; i++)
            ai += (getTotalBalance() * InterestRate);
        return ai;
    }
}
