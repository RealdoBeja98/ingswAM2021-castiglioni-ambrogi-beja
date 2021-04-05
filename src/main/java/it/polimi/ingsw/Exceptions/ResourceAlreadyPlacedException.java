package it.polimi.ingsw.Exceptions;

public class ResourceAlreadyPlacedException extends Exception {

    public ResourceAlreadyPlacedException(){
        super("This resource is already present in another shelf");
    }

}
