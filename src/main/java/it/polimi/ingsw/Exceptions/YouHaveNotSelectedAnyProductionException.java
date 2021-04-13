package it.polimi.ingsw.Exceptions;

public class YouHaveNotSelectedAnyProductionException extends Exception{
    public YouHaveNotSelectedAnyProductionException(){
        super("You have not selected any production power!");
    }
}
