package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ForwardMessages.PayedWithExtraStorageLeaderCardForwardMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class PayWithExtraStorageLeaderCardGameMessage extends PayWithSomethingGameMessage {

    private int leaderCardPosition;

    /**
     * Constructor of class game message
     * @param leaderCardPosition integer
     */
    public PayWithExtraStorageLeaderCardGameMessage(int leaderCardPosition){
        identifier = "PAY_WITH_EXTRA_STORAGE_LEADER_CARD";
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
            super.execute(game, out);
            game.getTurn().payWithExtraStorageLeaderCard(leaderCardPosition);//
            forwardAll(game, new PayedWithExtraStorageLeaderCardForwardMessage(currentPlayer, leaderCardPosition));//
            Message.sendMessage(out, new ConfirmedActionMessage());
            sendNextResourceToPay(game, out);
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
