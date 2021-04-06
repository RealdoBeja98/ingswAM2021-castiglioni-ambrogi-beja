package it.polimi.ingsw.Exceptions;

public class NoProductionLeaderCardException extends Exception {
    public NoProductionLeaderCardException(){
        super("Expected ProductionLeaderCard but not selected it");
    }
}
