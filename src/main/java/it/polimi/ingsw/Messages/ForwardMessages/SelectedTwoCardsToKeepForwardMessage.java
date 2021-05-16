package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;

public class SelectedTwoCardsToKeepForwardMessage extends ForwardMessage {

    private String nickname;
    private int card1;
    private int card2;

    public SelectedTwoCardsToKeepForwardMessage(String nickname, int card1, int card2){
        identifier = "SELECTED_TWO_CARDS_TO_KEEP";
        this.nickname = nickname;
        this.card1 = card1;
        this.card2 = card2;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().updateTwoCardToKeepSelected(nickname, card1, card2);
    }

    @Override
    public String toString(){
        return identifier + " " + nickname + " " + card1 + " " + card2;
    }

}
