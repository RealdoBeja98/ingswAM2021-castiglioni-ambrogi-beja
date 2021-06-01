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
public class MovedResourcesInWarehouseDepotsForwardMessage extends ForwardMessage {

    private String nickname;
    private int position;
    private int otherPosition;

    /**
     * Constructor of the class
     * @param nickname name of the player
     * @param position position coordinate
     * @param otherPosition final destination
     */
    public MovedResourcesInWarehouseDepotsForwardMessage(String nickname, int position, int otherPosition){
        identifier = "MOVED_RESOURCES_IN_WAREHOUSE_DEPOTS";
        this.nickname = nickname;
        this.position = position;
        this.otherPosition = otherPosition;
    }

    /**
     * This method moves resource to warehouse depots
     * @param game game instance
     * @param out send message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().moveResourceWarehouseDepots(nickname, position, otherPosition);
        View w = View.get();
        w.showPBCurrent();
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier + " " + nickname + " " + position + " " + otherPosition;
    }

}
