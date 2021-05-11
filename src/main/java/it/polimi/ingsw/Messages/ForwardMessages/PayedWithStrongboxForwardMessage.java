package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;

import java.io.PrintWriter;

public class PayedWithStrongboxForwardMessage extends ForwardMessage {

    private String nickname;
    private Resource resource;

    public PayedWithStrongboxForwardMessage(String nickname, Resource resource){
        identifier = "PAYED_WITH_STRONGBOX";
        this.nickname = nickname;
        this.resource = resource;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().removeStrongBox(nickname, resource, 1);
    }

    @Override
    public String toString(){
        return identifier + " " + nickname + " " + resource;
    }

}
