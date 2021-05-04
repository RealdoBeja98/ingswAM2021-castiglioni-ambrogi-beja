package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

public class TypoErrorMessage extends ErrorMessage {

    public TypoErrorMessage(){
        identifier = "ERROR_TYPO";
    }

    @Override
    public String toString(){
        return "Error: syntax error (ERROR_TYPO)!";
    }

}
