package it.polimi.ingsw.Messages.ErrorMessages;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * Class of an error message
 */
public class NoCardsPlayErrorMessage extends ErrorMessage {
    /**
     * constructor of class
     */
    public NoCardsPlayErrorMessage(){
        identifier = "ERROR_NO_CARDS_PLAY";
    }

    @Override
    public String toString(){
        return "Error: you haven't any leader card to play!";
    }

}
