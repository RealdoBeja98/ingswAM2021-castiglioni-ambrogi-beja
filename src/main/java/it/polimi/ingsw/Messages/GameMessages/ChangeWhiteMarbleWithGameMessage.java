package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class ChangeWhiteMarbleWithGameMessage extends GameMessage {

    private int leaderCardPosition;

    /**
     * Constructor of class game message
     * @param leaderCardPosition integer
     */
    public ChangeWhiteMarbleWithGameMessage(int leaderCardPosition){
        identifier = "CHANGE_WHITE_MARBLE_WITH";
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
            game.getTurn().changeWhiteMarbleWith(leaderCardPosition);
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
