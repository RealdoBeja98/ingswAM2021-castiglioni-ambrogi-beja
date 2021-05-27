package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class DifferentResourceInThisShelfException extends Exception {

    /**
     * Constructor of the class
     */
    public DifferentResourceInThisShelfException(){
        super("There is another resource type in this shelf");
    }

}
