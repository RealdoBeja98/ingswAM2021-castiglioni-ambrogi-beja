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
public class PlacedDevelopmentCardForwardMessage extends ForwardMessage {

    private String nickname;
    private int position;
    private String developmentCard;

    /**
     * Constructor of the class
     * @param nickname name of the player
     * @param position position coordinate
     * @param developmentCard developmentcard
     */
    public PlacedDevelopmentCardForwardMessage(String nickname, int position, String developmentCard){
        identifier = "PLACED_DEVELOPMENT_CARD";
        this.position = position;
        this.developmentCard = developmentCard;
        this.nickname = nickname;
    }

    /**
     * This method places a development card
     * @param game game instance
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().addDevelopmentCard(nickname, position, developmentCard);
        View w = View.get();
        w.showPBCurrent();
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier + " " + nickname + " " + position + " " + developmentCard;
    }

}
