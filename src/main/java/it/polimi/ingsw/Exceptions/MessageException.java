package it.polimi.ingsw.Exceptions;

import it.polimi.ingsw.Messages.ErrorMessage;

/**
 * abstract class of message exception
 */
public abstract class MessageException extends Exception{

    /**
     * Constructor of the class
     * @param string string
     */
    public MessageException(String string){
        super(string);
    }

    /**
     * this method is to get the ErrorMessage associated to this exception
     * @return return
     */

    public abstract ErrorMessage getErrorMessage();

}
