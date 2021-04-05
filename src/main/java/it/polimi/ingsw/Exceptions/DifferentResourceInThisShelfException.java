package it.polimi.ingsw.Exceptions;

public class DifferentResourceInThisShelfException extends Exception {

    public DifferentResourceInThisShelfException(){
        super("There is another resource type in this shelf");
    }

}
