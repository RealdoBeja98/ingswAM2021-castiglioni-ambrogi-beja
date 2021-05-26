package it.polimi.ingsw.Messages.ForwardMessages;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.Messages.ServiceMessage;
import java.io.PrintWriter;
/**
 * Class of a forward message
 */
public class CurrentPlayerMessage extends ForwardMessage {

    private String currentP;
    /**
     * Constructor of the class
     * @param currentP current player
     */
    public CurrentPlayerMessage(String currentP){
        this.currentP = currentP;
        identifier = "SET_CURRENT_PLAYER";
    }

    /**
     * This method sets the current player
     * @param game instance game
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.setCurrentP(currentP);
    }

    @Override
    public String toString(){
        return identifier + " " + currentP;
    }
}
