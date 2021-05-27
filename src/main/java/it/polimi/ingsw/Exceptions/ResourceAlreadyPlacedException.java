package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class ResourceAlreadyPlacedException extends Exception {

    /**
     * Constructor of the class
     */
    public ResourceAlreadyPlacedException(){
        super("This resource is already present in another shelf");
    }

}
