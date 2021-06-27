package it.polimi.ingsw.Messages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Mains.LocalMain;
import it.polimi.ingsw.View.View;

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
        if(LocalMain.getIsLocal()){
            View.printMessage(this);
        } else {
            System.out.println(this);
        }
    }

}
