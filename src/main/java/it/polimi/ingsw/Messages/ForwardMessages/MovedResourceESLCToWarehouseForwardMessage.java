package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;

public class MovedResourceESLCToWarehouseForwardMessage extends ForwardMessage {

    private String nickname;
    private int leaderCardPosition;
    private int warehousePosition;

    public MovedResourceESLCToWarehouseForwardMessage(String nickname, int leaderCardPosition, int warehousePosition){
        identifier = "MOVED_RESOURCE_ES_LC_TO_WAREHOUSE";
        this.nickname = nickname;
        this.leaderCardPosition = leaderCardPosition;
        this.warehousePosition = warehousePosition;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard(nickname, leaderCardPosition, warehousePosition);
        View w = View.get();
        w.showPBCurrent();
    }

    @Override
    public String toString(){
        return identifier + " " + nickname + " " + leaderCardPosition + " " + warehousePosition;
    }

}
