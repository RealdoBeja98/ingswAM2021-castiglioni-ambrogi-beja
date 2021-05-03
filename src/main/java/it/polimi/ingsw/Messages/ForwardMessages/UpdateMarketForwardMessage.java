package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ForwardMessage;

import java.io.PrintWriter;

public class UpdateMarketForwardMessage extends ForwardMessage {

    private RowColumn rowColumn;
    private int place;

    public UpdateMarketForwardMessage(RowColumn rowColumn, int place){
        this.rowColumn = rowColumn;
        this.place = place;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        //<--FIXME implement method-->
    }

    @Override
    public String toString(){
        return "UPDATE_MARKET " + rowColumn + " " + place;
    }

}
