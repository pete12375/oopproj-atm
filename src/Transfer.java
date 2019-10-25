public class Transfer extends Transaction {
    private double amount; // amount to transfer
    private int toAccount; // account to transfer
    private Keypad keypad; // reference to keypad

    // constant corresponding to menu option to cancel
    private final static int CANCELED = 2;

    // Transfer constructor
    public Transfer( int userAccountNumber, Screen atmScreen,
                       BankDatabase atmBankDatabase, Keypad atmKeypad )
    {
        // initialize superclass variables
        super( userAccountNumber, atmScreen, atmBankDatabase );

        // initialize references to keypad
        keypad = atmKeypad;
    } // end transfer constructor

    public void execute()
    {
        boolean cashTransferred = false; // cash was not transferred yet
        double availableBalance; // amount available for transfer

        // get references to bank database and screen
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        // loop until cash is transferred or the user cancels
        do
        {
            // obtain the account number of payee from the user
            toAccount = displayMenuOfPayee();
            // loop until the account number of payee is exist
            if (toAccount == 0 || toAccount == CANCELED ) break;
            // obtain a chosen transfer amount from the user
            amount = displayMenuOfAmounts();

            // check whether user chose a transfer amount or canceled
            if ( amount != CANCELED )
            {
                // get available balance of account involved
                availableBalance =
                        bankDatabase.getAvailableBalance( getAccountNumber() );

                // check whether the user has enough money in the account
                if ( amount <= availableBalance )
                {
                    // update the account involved to reflect transfer
                    bankDatabase.debit( getAccountNumber(), amount );
                    bankDatabase.credit( toAccount, amount );
                    cashTransferred = true; // cash was transferred

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
                case 1: // get the account number of payee from the user
                    screen.displayMessage("Enter account no.: ");
                    userChoice = keypad.getInput();
                    // check whether the account number is exist
                    if(bankDatabase.accountExistence(userChoice)) {
                        screen.displayMessageLine("\nAccount does not exist. Please try again.");
                        userChoice = 0;
                    }
                    break;
                case CANCELED: // the user chose to cancel
                    userChoice = CANCELED; // save user's choice
                    break;
                default: // the user did not enter a value from 1-2
                    screen.displayMessageLine(
                            "\nInvalid selection. Please try again." );
            } // end switch
        } // end while

        return userChoice; // return payee account number or CANCELED
    }

    private double displayMenuOfAmounts()
    {
        double userChoice = 0; // local variable to store return value

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
                case 1: // get the amount to transfer from the user
                    do {
                        screen.displayMessage("Enter amount: ");
                        userChoice = keypad.getDoubleInput();
                        if (userChoice <= 0) // check whether the input is valid
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

        return userChoice; // return transfer amount or CANCELED
    } // end method displayMenuOfAmounts
}
