package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ForwardMessages.MovedResourceESLCToWarehouseForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class MoveResourceESLCToWEarehouseGameMessage extends GameMessage {

    private int leaderCardPosition;

    /**
     * Constructor of class game message
     * @param leaderCardPosition integer
     */
    public MoveResourceESLCToWEarehouseGameMessage(int leaderCardPosition){
        identifier = "MOVE_RESOURCE_ES_LC_TO_WAREHOUSE";
        this.leaderCardPosition = leaderCardPosition;
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
            int warehousePosition = game.getTurn().getCurrentPlayer().getSelectedWarehouseDepotsSlot();
            game.getTurn().moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard(leaderCardPosition);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
            forwardAll(game, new MovedResourceESLCToWarehouseForwardMessage(currentPlayer, leaderCardPosition, warehousePosition));
        } catch (MessageException e) {
            Message.sendMessage(out, e.getErrorMessage());
        }
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier + " " + leaderCardPosition;
    }

}
