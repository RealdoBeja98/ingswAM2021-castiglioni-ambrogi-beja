package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;

import java.io.PrintWriter;

public class UpdateDevelopmentCardForwardMessage extends ForwardMessage {

    private int x;
    private int y;

    public UpdateDevelopmentCardForwardMessage(int x, int y){
        identifier = "UPDATE_DEVELOPMENT_CARD";
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().updateDevelopmentDeck(x, y);
    }

    @Override
    public String toString(){
        return "UPDATE_DEVELOPMENT_CARD" + " "+x+" "+y;
    }

}
