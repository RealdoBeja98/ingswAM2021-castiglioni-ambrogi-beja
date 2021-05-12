package it.polimi.ingsw.Messages.ServiceMessages;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ServiceMessage;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;
import java.io.PrintWriter;

public class ShowMarketMessage extends ServiceMessage {

    public ShowMarketMessage(){
        identifier = "SHOW_MARKET";
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        View w = new Cli();
        w.showMarket();
    }

    @Override
    public String toString(){
        return identifier;
    }
}
