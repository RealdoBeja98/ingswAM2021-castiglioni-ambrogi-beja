package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.IndexOutOfWarehouseDepotsException;
import it.polimi.ingsw.Exceptions.NotAdmittedMovementException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidMovementErrorMessage;
import it.polimi.ingsw.Messages.ForwardMessages.MovedResourcesInWarehouseDepotsForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class MoveResourcesInWarehouseDepotsGameMessage extends GameMessage {

    private int position;

    /**
     * Constructor of class game message
     * @param position integer
     */
    public MoveResourcesInWarehouseDepotsGameMessage(int position){
        identifier = "MOVE_RESOURCES_IN_WAREHOUSE_DEPOTS";
        this.position = position;
    }

    /**
     * This method represents the sending of a  correct message
     * @param game game instance
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            String currentPlayer = game.getTurn().getCurrentPlayer().getNickname();
            int otherPosition = game.getTurn().getCurrentPlayer().getSelectedWarehouseDepotsSlot();
            game.getTurn().moveResourcesInWarehouseDepots(position);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
            forwardAll(game, new MovedResourcesInWarehouseDepotsForwardMessage(currentPlayer, position, otherPosition));
        } catch (NotAdmittedMovementException e) {
            Message.sendMessage(out, new InvalidMovementErrorMessage());
        } catch (IndexOutOfWarehouseDepotsException e){
            Message.sendMessage(out, new InvalidMovementErrorMessage());
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        }
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier + " " + position;
    }

}
