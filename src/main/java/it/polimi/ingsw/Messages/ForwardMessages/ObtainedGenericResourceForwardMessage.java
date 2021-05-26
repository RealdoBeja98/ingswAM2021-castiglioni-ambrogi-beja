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
public class ObtainedGenericResourceForwardMessage extends ForwardMessage {

    private String nickname;
    private Resource resource;

    /**
     * Constructor of the class
     * @param nickname name of the player
     * @param resource instance of resource
     */
    public ObtainedGenericResourceForwardMessage(String nickname, Resource resource){
        identifier = "OBTAINED_GENERIC_RESOURCE";
        this.nickname = nickname;
        this.resource = resource;
    }

    /**
     * This method adds resource to strongbox
     * @param game game instance
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().addStrongBox(nickname, resource, 1);
        View w = View.get();
        w.showPBCurrent();
    }

    @Override
    public String toString(){
        return identifier + " " + nickname + " " + resource;
    }

}
