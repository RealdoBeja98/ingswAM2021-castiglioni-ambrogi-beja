package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.NotAdmittedMovementException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidMovementErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public class MoveResourcesInWarehouseDepotsGameMessage extends GameMessage {

    private int position;

    public MoveResourcesInWarehouseDepotsGameMessage(int position){
        identifier = "MOVE_RESOURCES_IN_WAREHOUSE_DEPOTS";
        this.position = position;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().moveResourcesInWarehouseDepots(position);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
        } catch (NotAdmittedMovementException e) {
            Message.sendMessage(out, new InvalidMovementErrorMessage());
        }
    }

}
