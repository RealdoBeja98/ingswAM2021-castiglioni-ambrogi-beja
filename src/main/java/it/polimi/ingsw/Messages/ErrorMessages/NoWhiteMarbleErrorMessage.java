package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class NoWhiteMarbleErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public NoWhiteMarbleErrorMessage(){
        identifier = "ERROR_NO_WHITE_MARBLE";
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return "Error: you aren't going to change a white marble or you haven't selected a WhiteMarbleLeaderCard!";
    }

}
