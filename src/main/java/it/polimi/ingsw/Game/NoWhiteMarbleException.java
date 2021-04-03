package it.polimi.ingsw.Game;

public class NoWhiteMarbleException extends Exception {
    public NoWhiteMarbleException(){
        super("This isn't a white marble!");
    }
}
