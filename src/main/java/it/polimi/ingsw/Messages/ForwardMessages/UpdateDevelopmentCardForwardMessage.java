package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;
/**
 * Class of a forward message
 */
public class UpdateDevelopmentCardForwardMessage extends ForwardMessage {

    private int x;
    private int y;

    /**
     * Constructor of the class
     * @param x coordinate
     * @param y coordinate
     */
    public UpdateDevelopmentCardForwardMessage(int x, int y){
        identifier = "UPDATE_DEVELOPMENT_CARD";
        this.x = x;
        this.y = y;
    }

    /**
     * This method lets you to update the development deck
     * @param game game instance
     * @param out sends message to the socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().updateDevelopmentDeck(x, y);
        View w = View.get();
        w.showDevCard();
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier + " "+x+" "+y;
    }

}
