package it.polimi.ingsw.Messages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;

/**
 * Class of the error message
 */
public abstract class ErrorMessage extends Message{

    /**
     * This method executes this error message
     * @param game game instance
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        View.printMessage(this);
    }

    public void execute(){
        System.out.println(this);
    }

}
