public class Transfer extends Transaction {
    private int amount; // amount to transfer
    private int toAccount; // account to transfer
    private Keypad keypad; // reference to keypad

    // constant corresponding to menu option to cancel
    private final static int CANCELED = 2;

    // Withdrawal constructor
    public Transfer( int userAccountNumber, Screen atmScreen,
                       BankDatabase atmBankDatabase, Keypad atmKeypad )
    {
        // initialize superclass variables
        super( userAccountNumber, atmScreen, atmBankDatabase );

        // initialize references to keypad and cash dispenser
        keypad = atmKeypad;
    } // end Withdrawal constructor

    public void execute()
    {
        boolean cashTransferred = false; // cash was not transferred yet
        double availableBalance; // amount available for withdrawal

        // get references to bank database and screen
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        // loop until cash is transferred or the user cancels
        do
        {
            // obtain a chosen withdrawal amount from the user
            toAccount = displayMenuOfPayee();
            amount = displayMenuOfAmounts();

            // check whether user chose a withdrawal amount or canceled
            if ( amount != CANCELED )
            {
                // get available balance of account involved
                availableBalance =
                        bankDatabase.getAvailableBalance( getAccountNumber() );

                // check whether the user has enough money in the account
                if ( amount <= availableBalance )
                {
                    // update the account involved to reflect withdrawal
                    bankDatabase.debit( getAccountNumber(), amount );
                    bankDatabase.credit( toAccount, amount );
                    cashTransferred = true; // cash was dispensed

                    screen.displayMessageLine( "\nSuccess" );
                } // end if
                else // not enough money available in user's account
                {
                    screen.displayMessageLine(
                            "\nInsufficient funds in your account." +
                                    "\n\nPlease choose a smaller amount." );
                } // end else
            } // end if
            else // user chose cancel menu option
            {
                screen.displayMessageLine( "\nCanceling transaction..." );
                return; // return to main menu because user canceled
            } // end else
        } while ( !cashTransferred );


    } // end method execute

    private int displayMenuOfPayee() {
        int userChoice = 0; // local variable to store return value

        Screen screen = getScreen(); // get screen reference

        // loop while no valid choice has been made
        while ( userChoice == 0 )
        {
            // display the menu
            screen.displayMessageLine( "\nTransfer Menu:" );
            screen.displayMessageLine( "1 - Continue and enter payee account number" );
            screen.displayMessageLine( "2 - Cancel transaction\n" );

            int input = keypad.getInput(); // get user input through keypad
            BankDatabase bankDatabase = getBankDatabase();

            // determine how to proceed based on the input value
            switch ( input )
            {
                case 1: // custom withdrawal amount
                    do {
                        screen.displayMessage("Enter account no.: ");
                        userChoice = keypad.getInput();
                        if(!bankDatabase.accountExistance(userChoice))
                            screen.displayMessageLine("\nThe account does not exist\n");
                    } while (!bankDatabase.accountExistance(userChoice));
                    break;
                case CANCELED: // the user chose to cancel
                    userChoice = CANCELED; // save user's choice
                    break;
                default: // the user did not enter a value from 1-2
                    screen.displayMessageLine(
                            "\nInvalid selection. Please try again." );
            } // end switch
        } // end while

        return userChoice; // return withdrawal amount or CANCELED
    }

    private int displayMenuOfAmounts()
    {
        int userChoice = 0; // local variable to store return value

        Screen screen = getScreen(); // get screen reference

        // loop while no valid choice has been made
        while ( userChoice == 0 )
        {
            // display the menu
            screen.displayMessageLine( "\nTransfer Menu:" );
            screen.displayMessageLine( "1 - Continue and enter amount" );
            screen.displayMessageLine( "2 - Cancel transaction\n" );

            int input = keypad.getInput(); // get user input through keypad

            // determine how to proceed based on the input value
            switch ( input )
            {
                case 1: // custom withdrawal amount
                    do {
                        screen.displayMessage("Enter amount: ");
                        userChoice = keypad.getInput();
                        if (userChoice <= 0)
                            screen.displayMessage("Invalid value. Please try again.\n\n");
                    } while (userChoice <= 0);
                    break;
                case CANCELED: // the user chose to cancel
                    userChoice = CANCELED; // save user's choice
                    break;
                default: // the user did not enter a value from 1-2
                    screen.displayMessageLine(
                            "\nInvalid selection. Please try again." );
            } // end switch
        } // end while

        return userChoice; // return withdrawal amount or CANCELED
    } // end method displayMenuOfAmounts

}
