package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;

public class PayedWithExtraStorageLeaderCardForwardMessage extends ForwardMessage {

    private String nickname;
    private int leaderCardPosition;

    public PayedWithExtraStorageLeaderCardForwardMessage(String nickname, int leaderCardPosition){
        identifier = "PAYED_WITH_EXTRA_STORAGE_LEADER_CARD";
        this.nickname = nickname;
        this.leaderCardPosition = leaderCardPosition;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().removeResourceExtraStorageLeaderCard(nickname, leaderCardPosition);
        View w = new Cli();
        w.showPBCurrent();
    }

    @Override
    public String toString(){
        return identifier + " " + nickname + " " + leaderCardPosition;
    }

}
