package it.polimi.ingsw.Exceptions;

public class GameAlreadyStartedException extends Exception {
    public GameAlreadyStartedException(){
        super(" This game is already started");
    }
}
