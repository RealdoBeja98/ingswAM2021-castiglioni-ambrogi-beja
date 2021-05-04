package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.TypoErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public class ToErrorTypoGameMessage extends GameMessage {

    public ToErrorTypoGameMessage(){
        identifier = "TO_ERROR_TYPO";
    }

    public ToErrorTypoGameMessage(String messageString){
        if(messageString == null){
            identifier = "TO_ERROR_TYPO";
        } else {
            identifier = messageString;
        }
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        Message.sendMessage(out, new TypoErrorMessage());
    }


}
