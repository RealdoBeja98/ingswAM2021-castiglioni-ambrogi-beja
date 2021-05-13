package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.Messages.ServiceMessage;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;

public class ShowCurrentBoardMessage extends ForwardMessage {
    public ShowCurrentBoardMessage(){
        identifier = "SHOW_CURRENT_BOARD";
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        View w = new Cli();
        w.showPBCurrent();
    }

    @Override
    public String toString(){
        return identifier;
    }
}
