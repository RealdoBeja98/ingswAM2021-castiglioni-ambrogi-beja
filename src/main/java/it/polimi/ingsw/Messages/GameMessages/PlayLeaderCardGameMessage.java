package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ForwardMessages.PlayedLeaderCardForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class PlayLeaderCardGameMessage extends GameMessage {

    private int leaderCardToPlay;

    /**
     * Constructor of class game message
     * @param leaderCardToPlay integer
     */
    public PlayLeaderCardGameMessage(int leaderCardToPlay){
        identifier = "PLAY_LEADER_CARD";
        this.leaderCardToPlay = leaderCardToPlay;
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
            game.getTurn().playLeaderCard(leaderCardToPlay);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
            forwardAll(game, new PlayedLeaderCardForwardMessage(currentPlayer, leaderCardToPlay));
        } catch (MessageException e) {
            Message.sendMessage(out, e.getErrorMessage());
        }
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier + " " + leaderCardToPlay;
    }

}
