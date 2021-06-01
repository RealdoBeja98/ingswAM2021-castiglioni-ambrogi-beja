package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ForwardMessages.DiscardedLeaderCardForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class DiscardLeaderCardGameMessage extends GameMessage {

    private int leaderCardToDiscard;

    /**
     * Constructor of class game message
     * @param leaderCardToDiscard integer
     */
    public DiscardLeaderCardGameMessage(int leaderCardToDiscard){
        identifier = "DISCARD_LEADER_CARD";
        this.leaderCardToDiscard = leaderCardToDiscard;
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
            game.getTurn().discardLeaderCard(leaderCardToDiscard);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
            forwardAll(game, new DiscardedLeaderCardForwardMessage(currentPlayer, leaderCardToDiscard));
        } catch (MessageException e) {
            Message.sendMessage(out, e.getErrorMessage());
        }
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier + " " + leaderCardToDiscard;
    }

}
