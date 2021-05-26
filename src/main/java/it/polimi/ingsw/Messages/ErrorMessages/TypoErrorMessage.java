package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class TypoErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public TypoErrorMessage(){
        identifier = "ERROR_TYPO";
    }

    @Override
    public String toString(){
        return "Error: syntax error (ERROR_TYPO)!";
    }

}
