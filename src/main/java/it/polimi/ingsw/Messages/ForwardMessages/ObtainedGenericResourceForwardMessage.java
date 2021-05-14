package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;

import java.io.PrintWriter;

public class ObtainedGenericResourceForwardMessage extends ForwardMessage {

    private String nickname;
    private Resource resource;

    public ObtainedGenericResourceForwardMessage(String nickname, Resource resource){
        identifier = "OBTAINED_GENERIC_RESOURCE";
        this.nickname = nickname;
        this.resource = resource;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().addStrongBox(nickname, resource, 1);
    }

    @Override
    public String toString(){
        return identifier + " " + nickname + " " + resource;
    }

}