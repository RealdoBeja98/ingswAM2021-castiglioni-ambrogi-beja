package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Enums.NormalAction;
import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ForwardMessages.ShowCardMarketMessage;
import it.polimi.ingsw.Messages.ForwardMessages.ShowMarketMessage;

import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class SelectNormalActionGameMessage extends GameMessage {

    private NormalAction normalAction;

    /**
     * Constructor of class game message
     * @param normalAction instance of normalaction
     */
    public SelectNormalActionGameMessage(NormalAction normalAction){
        identifier = "SELECT_NORMAL_ACTION";
        this.normalAction = normalAction;
    }

    /**
     * This method represents the sending of a  correct message
     * @param game game instance
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().selectNormalAction(normalAction);
            if(normalAction.toString().equals("TAKE_RESOURCES_FROM_THE_MARKET")){
                Message.sendMessage(out, new ShowMarketMessage());
            } else if (normalAction.toString().equals("BUY_DEVELOPMENT_CARD")){
                Message.sendMessage(out, new ShowCardMarketMessage());
            }
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        }
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier + " " + normalAction;
    }

}
