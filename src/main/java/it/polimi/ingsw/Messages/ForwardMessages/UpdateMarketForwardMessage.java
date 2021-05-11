package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;

public class UpdateMarketForwardMessage extends ForwardMessage {

    private RowColumn rowColumn;
    private int place;

    public UpdateMarketForwardMessage(RowColumn rowColumn, int place){
        identifier = "UPDATE_MARKET";
        this.rowColumn = rowColumn;
        this.place = place;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().updateMarket(rowColumn, place);
        View w = new Cli();
        w.showMarket();
    }

    @Override
    public String toString(){
        return identifier + " " + rowColumn + " " + place;
    }

}
