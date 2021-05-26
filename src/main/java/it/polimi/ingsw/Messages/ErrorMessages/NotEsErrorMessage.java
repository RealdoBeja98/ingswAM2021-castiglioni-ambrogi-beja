package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class NotEsErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public NotEsErrorMessage(){
        identifier = "ERROR_NOT_ES";
    }

    @Override
    public String toString(){
        return "Error: this isn't an ExtraStorageLeaderCard!";
    }

}
