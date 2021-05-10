package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;

import java.io.PrintWriter;

public class DiscardedLeaderCardForwardMessage extends ForwardMessage {

    private String nickname;
    private int pos;

    public DiscardedLeaderCardForwardMessage(String nickname, int pos){
        identifier = "DISCARDED_LEADER_CARD";
        this.nickname = nickname;
        this.pos = pos;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().discardLeaderCard(nickname, pos);
    }

    @Override
    public String toString(){
        return identifier + " " + nickname + " " + pos;
    }

}
