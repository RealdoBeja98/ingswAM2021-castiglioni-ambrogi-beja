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
public class ChoosePlayLeaderCardGameMessage extends GameMessage {

    /**
     * Constructor of class game message
     */
    public ChoosePlayLeaderCardGameMessage(){
        identifier = "CHOOSE_PLAY_LEADER_CARD";
    }
    /**
     * This method represents the sending of a  correct message
     * @param game game instance
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().choosePlayLeaderCard();
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
        } catch (MessageException e) {
            Message.sendMessage(out, e.getErrorMessage());
        }
    }

}
