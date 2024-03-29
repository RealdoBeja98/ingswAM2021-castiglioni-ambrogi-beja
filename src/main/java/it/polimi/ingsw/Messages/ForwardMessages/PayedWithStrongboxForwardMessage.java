package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;
import java.io.PrintWriter;

/**
 * Class of a forward message
 */
public class PayedWithStrongboxForwardMessage extends ForwardMessage {

    private String nickname;
    private Resource resource;

    /**
     * Constructor of the class
     * @param nickname name of the player
     * @param resource instance of resource
     */
    public PayedWithStrongboxForwardMessage(String nickname, Resource resource){
        identifier = "PAYED_WITH_STRONGBOX";
        this.nickname = nickname;
        this.resource = resource;
    }

    /**
     * This method removes resource from stongbox
     * @param game game instance
     * @param out sends message in socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().removeStrongBox(nickname, resource, 1);
        View w = View.get();
        w.showPBCurrent();
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier + " " + nickname + " " + resource;
    }

}
