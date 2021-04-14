package it.polimi.ingsw.Exceptions;

public class GameEndedException extends Exception{
    public GameEndedException(){
        super("The game is ended! It's time to show final points!");
    }
}
