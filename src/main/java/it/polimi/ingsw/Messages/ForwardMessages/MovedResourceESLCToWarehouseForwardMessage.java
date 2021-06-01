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
public class MovedResourceESLCToWarehouseForwardMessage extends ForwardMessage {

    private String nickname;
    private int leaderCardPosition;
    private int warehousePosition;

    /**
     * Constructor of the class
     * @param nickname name of player
     * @param leaderCardPosition position of leader card
     * @param warehousePosition position in warehouse
     */
    public MovedResourceESLCToWarehouseForwardMessage(String nickname, int leaderCardPosition, int warehousePosition){
        identifier = "MOVED_RESOURCE_ES_LC_TO_WAREHOUSE";
        this.nickname = nickname;
        this.leaderCardPosition = leaderCardPosition;
        this.warehousePosition = warehousePosition;
    }

    /**
     * This method moves resources from extra storage leader card to warehouse depots
     * @param game game instance
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard(nickname, leaderCardPosition, warehousePosition);
        View w = View.get();
        w.showPBCurrent();
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier + " " + nickname + " " + leaderCardPosition + " " + warehousePosition;
    }

}
