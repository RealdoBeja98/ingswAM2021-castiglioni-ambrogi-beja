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
public class AdvanceFaithTrackForwardMessage extends ForwardMessage {

    String excludedPlayer;

    /**
     * Constructor of the class
     * @param excludedPlayer exluded player
     */
    public AdvanceFaithTrackForwardMessage(String excludedPlayer){
        identifier = "ADVANCE_FAITH_TRACK";
        this.excludedPlayer = excludedPlayer;
    }

    /**
     * This method lets the faithtrack to forward in one in personal board
     * @param game instance game
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().allOtherPlayersGoOnFaithTrack(excludedPlayer);
        View w = View.get();
        w.showPersonalBoard();
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier + " " + excludedPlayer;
    }

}
