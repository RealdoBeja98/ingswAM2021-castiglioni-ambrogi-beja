package it.polimi.ingsw.Exceptions;

public class NotAnExtraStorageLeaderCardException extends Exception{
    public NotAnExtraStorageLeaderCardException(){
        super("This isn't an ExtraStorageLeaderCard!");
    }
}
