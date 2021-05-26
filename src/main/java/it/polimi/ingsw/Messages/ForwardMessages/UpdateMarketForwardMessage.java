package it.polimi.ingsw.Messages.ForwardMessages;

import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Messages.ForwardMessage;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;
/**
 * Class of a forward message
 */
public class UpdateMarketForwardMessage extends ForwardMessage {

    private String nickname;
    private RowColumn rowColumn;
    private int place;

    /**
     * Constructor of the class
     * @param nickname name of the player
     * @param place position
     * @param rowColumn choose row or column
     */
    public UpdateMarketForwardMessage(String nickname, RowColumn rowColumn, int place){
        identifier = "UPDATE_MARKET";
        this.rowColumn = rowColumn;
        this.place = place;
        this.nickname = nickname;
    }

    /**
     * This method updates the market
     * @param game game instance
     * @param out sends message to the socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        ClientMain.getPlayerGame().updateMarket(nickname, rowColumn, place);
        View w = View.get();
        w.showMarket();
    }

    @Override
    public String toString(){
        return identifier + " " + nickname + " " + rowColumn + " " + place;
    }

}
