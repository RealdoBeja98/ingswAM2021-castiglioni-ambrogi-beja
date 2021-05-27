package it.polimi.ingsw.Exceptions;


/**
 * Class of a created exception
 */
public class NameAlreadyRegisteredException extends Exception{

    /**
     * Constructor of the class
     */
    public NameAlreadyRegisteredException(){
        super("This name is already registered!");
    }
}
