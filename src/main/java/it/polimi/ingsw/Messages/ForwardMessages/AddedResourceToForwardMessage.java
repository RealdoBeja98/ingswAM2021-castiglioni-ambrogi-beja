package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Enums.LeaderWarehouse;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;

import java.io.PrintWriter;

public class AddedResourceToForwardMessage extends ForwardMessage {

    private String nickname;
    private LeaderWarehouse leaderWarehouse;
    private Resource resource;
    private int place;

    public AddedResourceToForwardMessage(String nickname, LeaderWarehouse leaderWarehouse, Resource resource, int place){
        identifier = "ADDED_RESOURCE_TO";
        this.nickname = nickname;
        this.leaderWarehouse = leaderWarehouse;
        this.resource = resource;
        this.place = place;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        switch (leaderWarehouse){
            case WAREHOUSEDEPOTS: ClientMain.getPlayerGame().addResourceWarehouseDepots(nickname, resource, place);
                break;
            case LEADERCARD: ClientMain.getPlayerGame().addResourceExtraStorageLeaderCard(nickname, place);
                break;
            case DISCARD:
                break;
        }
    }

    @Override
    public String toString(){
        return identifier + " " + nickname + " " + leaderWarehouse + " " + resource + " " + place;
    }

}
