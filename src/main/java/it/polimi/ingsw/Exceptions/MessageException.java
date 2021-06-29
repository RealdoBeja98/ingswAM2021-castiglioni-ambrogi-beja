package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;

public abstract class MessageException extends Exception{

    /**
     * Constructor of the class
     */
    public MessageException(String string){
        super(string);
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     */
    public abstract ErrorMessage getErrorMessage();

}
