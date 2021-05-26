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
public class PayedWithWarehouseDepotsForwardMessage extends ForwardMessage {

    private String nickname;
    private int pos;

    /**
     * Constructor of the class
     * @param nickname name of the player
     * @param pos posisiton coordinate
     */
    public PayedWithWarehouseDepotsForwardMessage(String nickname, int pos){
        identifier = "PAYED_WITH_WAREHOUSE_DEPOTS";
        this.nickname = nickname;
        this.pos = pos;
    }

    /**
     * This method removes resouce from warehouse depots
     * @param game game instanse
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().removeResourceWarehouseDepots(nickname, pos);
        View w = View.get();
        w.showPBCurrent();
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier + " " + nickname + " " + pos;
    }

}
