package it.polimi.ingsw.Exceptions;

public class IndexOutOfWarehouseDepotsException extends Exception{
    public IndexOutOfWarehouseDepotsException(){
        super("Index out of bounds of the WarehouseDepots!");
    }
}
