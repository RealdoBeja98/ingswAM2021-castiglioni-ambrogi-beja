package it.polimi.ingsw.Messages.ForwardMessages;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.Messages.ServiceMessage;
import java.io.PrintWriter;

public class CurrentPlayerMessage extends ForwardMessage {

    private String currentP;

    public CurrentPlayerMessage(String currentP){
        this.currentP = currentP;
        identifier = "SET_CURRENT_PLAYER";
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.setCurrentP(currentP);
    }

    @Override
    public String toString(){
        return identifier + " " + currentP;
    }
}
