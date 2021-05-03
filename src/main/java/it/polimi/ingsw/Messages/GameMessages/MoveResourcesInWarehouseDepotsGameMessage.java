package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.NotAdmittedMovementException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidMovementErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class MoveResourcesInWarehouseDepotsGameMessage extends GameMessage {

    private int position;

    public MoveResourcesInWarehouseDepotsGameMessage(int position){
        this.position = position;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().moveResourcesInWarehouseDepots(position);
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
        } catch (NotAdmittedMovementException e) {
            out.println(new InvalidMovementErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "MOVE_RESOURCES_IN_WAREHOUSE_DEPOTS";
    }

}
