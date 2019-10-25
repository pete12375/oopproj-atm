public class SavingAccount extends Account {
    private double interestRate = 0.001;
    private int compoundedTimes = 1;

    // SavingAccount constructor initializes attributes
    public SavingAccount(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
        // initialize superclass variables
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
    }

    // set interest rate of the saving account
    public void setInterestRate(double newRate) {
        interestRate = newRate;
    }

    // set compounded times of interest of the saving account
    public void setCompoundedTimes(int newTimes) {
        compoundedTimes = newTimes;
    }

    // return interest rate of the saving account
    public double getInterestRate() {return interestRate;}

    // return compounded times of interest of the saving account
    public int getCompoundedTimes() {return compoundedTimes;}

    // return current annual interest of the saving account
    public double getAnnualInterest() {
        int ai = 0;
        for (int i = 1; i <= compoundedTimes; i++)
            ai += (getTotalBalance() * interestRate);
        return ai;
    }
}
