package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ErrorMessages.TypoErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class ToErrorTypoGameMessage extends GameMessage {

    @Override
    public void execute(Game game, PrintWriter out) {
        out.println(new TypoErrorMessage());
    }

    @Override
    public String toString(){
        return "To ERROR_TYPO";
    }

}
