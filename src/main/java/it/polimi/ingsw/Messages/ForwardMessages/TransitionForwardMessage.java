package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;
/**
 * Class of a forward message
 */
public class TransitionForwardMessage extends ForwardMessage {

    /**
     * Constructor of the class
     */
    public TransitionForwardMessage(){
        identifier = "TRANSITION_MESSAGE";
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        View w = View.get();
        w.showMarket();
        w.showDevCard();
        w.showPersonalBoard();
    }

    @Override
    public String toString(){
        return identifier;
    }

}
