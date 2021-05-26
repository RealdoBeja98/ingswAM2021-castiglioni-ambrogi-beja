package it.polimi.ingsw.Messages;

import it.polimi.ingsw.Game.Game;

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
        System.out.println(this);
    }

    public void execute(){
        System.out.println(this);
    }

}
