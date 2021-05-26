package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class WhiteMarbleErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public WhiteMarbleErrorMessage(){
        identifier = "ERROR_WHITE_MARBLE";
    }

    @Override
    public String toString(){
        return "Error: unexpected white marble!";
    }

}
