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
public class ShowMarketMessage extends ForwardMessage {

    /**
     * Constructor of the class
     */
    public ShowMarketMessage(){
        identifier = "SHOW_MARKET";
    }

    /**
     * This method shows the morket
     * @param game game instance
     * @param out send s message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        View w = View.get();
        w.showMarket();
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier;
    }

}
