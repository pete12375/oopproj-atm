public class CheckInterest extends Transaction
{
    // constructor
    public CheckInterest( int userAccountNumber, Screen atmScreen,
                           BankDatabase atmBankDatabase )
    {
        super( userAccountNumber, atmScreen, atmBankDatabase );
    } // end constructor

    // performs the transaction
    public void execute()
    {
        // get references to bank database and screen
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        double interestRate = bankDatabase.dbGetInterestRate( getAccountNumber() );
        double compoundedTimes = bankDatabase.dbGetCompoundedTimes( getAccountNumber() );
        double annualInterest = bankDatabase.dbGetAnnualInterest( getAccountNumber() );

        // display the balance information on the screen
        screen.displayMessage( "\nInterest rate: " );
        System.out.print( interestRate );
        screen.displayMessage( "\nCompounded Times: " );
        System.out.print( compoundedTimes );
        screen.displayMessage( "\nAnnual interest: " );
        System.out.print( annualInterest );
        screen.displayMessageLine( "" );
    } // end method execute
}
