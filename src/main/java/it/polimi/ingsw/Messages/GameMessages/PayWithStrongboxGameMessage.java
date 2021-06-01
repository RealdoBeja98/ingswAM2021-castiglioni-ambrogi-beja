package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ForwardMessages.PayedWithStrongboxForwardMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class PayWithStrongboxGameMessage extends PayWithSomethingGameMessage {

    private Resource resource;

    /**
     * Constructor of class game message
     * @param resource instance of resource
     */
    public PayWithStrongboxGameMessage(Resource resource){
        identifier = "PAY_WITH_STRONGBOX";
        this.resource = resource;
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
            game.getTurn().payWithStrongBox(resource);//
            forwardAll(game, new PayedWithStrongboxForwardMessage(currentPlayer, resource));//
        } catch (MessageException e) {
            Message.sendMessage(out, e.getErrorMessage());
        }
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier + " " + resource;
    }

}
