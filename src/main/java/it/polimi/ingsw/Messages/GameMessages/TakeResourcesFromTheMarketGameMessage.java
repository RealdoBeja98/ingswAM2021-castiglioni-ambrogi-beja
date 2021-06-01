package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Exceptions.MessageException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ForwardMessages.UpdateMarketForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class TakeResourcesFromTheMarketGameMessage extends GameMessage {

    private RowColumn rowColumn;
    private int place;

    /**
     * Constructor of class game message
     * @param rowColumn: row or column
     * @param place: position
     */
    public TakeResourcesFromTheMarketGameMessage(RowColumn rowColumn, int place){
        identifier = "TAKE_RESOURCES_FROM_THE_MARKET";
        this.rowColumn = rowColumn;
        this.place = place;
    }

    /**
     * This method is to send other players messages with resources just took
     * @param game: game instance
     * @param out: sends message to socket
     */
    private void sendMarblesFromTheMarket(Game game, PrintWriter out){
        int size = game.getTurn().getCurrentPlayer().getMarblesFromTheMarket().size();
        for (int i = 0; i < size; i++) {
            out.println(game.getTurn().getCurrentPlayer().getMarblesFromTheMarket().get(i));
        }
    }

    /**
     * This method represents the sending of a  correct message
     * @param game: game instance
     * @param out: sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            String currentPlayer = game.getTurn().getCurrentPlayer().getNickname();
            game.getTurn().takeResourcesFromTheMarket(rowColumn, place);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(this);
            sendMarblesFromTheMarket(game, out);
            forwardAll(game, new UpdateMarketForwardMessage(currentPlayer, rowColumn, place));
        } catch (MessageException e) {
            Message.sendMessage(out, e.getErrorMessage());
        }
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier + " " + rowColumn + " " + place;
    }

}
