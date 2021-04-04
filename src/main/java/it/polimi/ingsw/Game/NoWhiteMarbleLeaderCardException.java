package it.polimi.ingsw.Game;

public class NoWhiteMarbleLeaderCardException extends Exception{
    public NoWhiteMarbleLeaderCardException(){
        super("There is no white marble leader card!");
    }
}
