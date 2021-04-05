package it.polimi.ingsw.Game;

public class NoProductionLeaderCardException extends Exception {
    NoProductionLeaderCardException(){
        super("Expected ProductionLeaderCard but not selected it");
    }
}
