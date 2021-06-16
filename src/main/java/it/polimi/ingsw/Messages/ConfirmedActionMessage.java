package it.polimi.ingsw.Messages;

import it.polimi.ingsw.Game.Game;
import java.io.PrintWriter;

/**
 * Class of confirmed action message
 */
public class ConfirmedActionMessage extends Message{
    /**
     * Constructor of class
     */
    public ConfirmedActionMessage(){
        identifier = "CONFIRMED_ACTION";
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        System.out.println(this);
    }

}
