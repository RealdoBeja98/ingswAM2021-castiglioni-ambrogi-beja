package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ErrorMessages.TypoErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

/**
 * This is a class of game message
 */
public class ToErrorTypoGameMessage extends GameMessage {
    /**
     * Constructor of the class game message
     */
    public ToErrorTypoGameMessage(){
        identifier = "TO_ERROR_TYPO";
    }

    /**
     * Constructor of class game message
     * @param messageString string message
     */
    public ToErrorTypoGameMessage(String messageString){
        if(messageString == null){
            identifier = "TO_ERROR_TYPO";
        } else {
            identifier = messageString;
        }
    }

    /**
     * This method represents the sending of a correct message
     * @param game game instance
     * @param out sends message to socket
     */
    @Override
    public void execute(Game game, PrintWriter out) {
        Message.sendMessage(out, new TypoErrorMessage());
    }

}
