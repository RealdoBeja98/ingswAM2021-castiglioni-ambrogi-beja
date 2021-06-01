package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.Messages.ServiceMessage;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;
import java.io.PrintWriter;

/**
 * Class of a forward message
 */
public class ShowCardMarketMessage extends ForwardMessage {

    /**
     * Constructor of the class
     */
    public ShowCardMarketMessage(){
        identifier = "SHOW_CARD_MARKET";
    }

    /**
     * This method shows the cards market
     * @param game game instance
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        View w = View.get();
        w.showDevCard();
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier;
    }

}
