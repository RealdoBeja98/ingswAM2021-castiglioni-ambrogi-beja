package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;

import java.io.PrintWriter;

public class MovedResourcesWarehouseToESLCForwardMessage extends ForwardMessage {

    private String nickname;
    private int leaderCardPosition;
    private int warehousePosition;

    public MovedResourcesWarehouseToESLCForwardMessage(String nickname, int leaderCardPosition, int warehousePosition){
        identifier = "MOVED_RESOURCES_WAREHOUSE_TO_ES_LC";
        this.nickname = nickname;
        this.leaderCardPosition = leaderCardPosition;
        this.warehousePosition = warehousePosition;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard(nickname, leaderCardPosition, warehousePosition);
    }

    @Override
    public String toString(){
        return identifier + " " + nickname + " " + leaderCardPosition + " " + warehousePosition;
    }

}
