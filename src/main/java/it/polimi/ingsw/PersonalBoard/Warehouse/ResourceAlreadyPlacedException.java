package it.polimi.ingsw.PersonalBoard.Warehouse;

public class ResourceAlreadyPlacedException extends Exception {
    public ResourceAlreadyPlacedException(){
        super("This resource is already present in another shelf");
    }
}
