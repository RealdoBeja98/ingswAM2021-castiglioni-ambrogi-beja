package it.polimi.ingsw.Messages;
import it.polimi.ingsw.Game.Game;
import java.io.PrintWriter;
/**
 * Class of the game message
 */
public abstract class GameMessage extends Message{
    /**
     * This method forward the message to console
     * @param game game instance
     * @param forwardMessage forward message
     */
    protected void forward(Game game, ForwardMessage forwardMessage, PrintWriter toExclude){
        String message = forwardMessage.toString();
        for(PrintWriter i : game.getPrintWriterList()){
            if(i != toExclude){
                i.println(message);
            }
        }
    }

    /**
     * This method forwards all the message
     * @param game game instance
     * @param forwardMessage forward message
     */
    protected void forwardAll(Game game, ForwardMessage forwardMessage){
        String message = forwardMessage.toString();
        for(PrintWriter i : game.getPrintWriterList()){
            i.println(message);
        }
    }

}
