package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.MessageException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class SelectProductionPowerLeaderCardGameMessage extends GameMessage {

    private int leaderCardPosition;

    /**
     * This method print the message after you have completed the specific turn
     * @param leaderCardPosition integer
     */
    public SelectProductionPowerLeaderCardGameMessage(int leaderCardPosition){
        identifier = "SELECT_PRODUCTION_POWER_LEADER_CARD";
        this.leaderCardPosition = leaderCardPosition;
    }

    /**
     * This method represents the sending of a  correct message
     * @param game: game instance
     * @param out: sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().selectProductionPowerLeaderCard(leaderCardPosition);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
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
