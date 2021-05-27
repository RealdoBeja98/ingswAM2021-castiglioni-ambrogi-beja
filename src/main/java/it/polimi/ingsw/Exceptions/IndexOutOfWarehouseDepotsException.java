package it.polimi.ingsw.Exceptions;

/**
 * Class of a created exception
 */
public class IndexOutOfWarehouseDepotsException extends Exception{

    /**
     * Constructor of the class
     */
    public IndexOutOfWarehouseDepotsException(){
        super("Index out of bounds of the WarehouseDepots!");
    }
}
