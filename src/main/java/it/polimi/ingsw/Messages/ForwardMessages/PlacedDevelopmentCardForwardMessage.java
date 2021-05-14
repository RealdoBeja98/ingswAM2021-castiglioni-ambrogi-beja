package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;

public class PlacedDevelopmentCardForwardMessage extends ForwardMessage {

    private String nickname;
    private int position;
    private String developmentCard;

    public PlacedDevelopmentCardForwardMessage(String nickname, int position, String developmentCard){
        identifier = "PLACED_DEVELOPMENT_CARD";
        this.position = position;
        this.developmentCard = developmentCard;
        this.nickname = nickname;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().addDevelopmentCard(nickname, position, developmentCard);
        View w = new Cli();
        w.showPBCurrent();
    }

    @Override
    public String toString(){
        return identifier + " " + nickname + " " + position + " " + developmentCard;
    }

}