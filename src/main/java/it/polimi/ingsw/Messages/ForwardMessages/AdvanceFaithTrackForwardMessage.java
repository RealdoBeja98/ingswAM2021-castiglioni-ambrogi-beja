package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;

import java.io.PrintWriter;

public class AdvanceFaithTrackForwardMessage extends ForwardMessage {

    String excludedPlayer;

    public AdvanceFaithTrackForwardMessage(String excludedPlayer){
        identifier = "ADVANCE_FAITH_TRACK";
        this.excludedPlayer = excludedPlayer;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().allOtherPlayersGoOnFaithTrack(excludedPlayer);
    }

    @Override
    public String toString(){
        return identifier + " " + excludedPlayer;
    }

}
