package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;

import java.io.PrintWriter;

public class MovedResourcesInWarehouseDepotsForwardMessage extends ForwardMessage {

    private String nickname;
    private int position;
    private int otherPosition;

    public MovedResourcesInWarehouseDepotsForwardMessage(String nickname, int position, int otherPosition){
        identifier = "MOVED_RESOURCES_IN_WAREHOUSE_DEPOTS";
        this.nickname = nickname;
        this.position = position;
        this.otherPosition = otherPosition;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().moveResourceWarehouseDepots(nickname, position, otherPosition);
    }

    @Override
    public String toString(){
        return identifier + " " + nickname + " " + position + " " + otherPosition;
    }

}
