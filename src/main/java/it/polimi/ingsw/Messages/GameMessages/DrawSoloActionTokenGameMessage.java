package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Table.Decks.Token.ActionToken;

import java.io.PrintWriter;

public class DrawSoloActionTokenGameMessage extends GameMessage {

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            ActionToken actionToken = game.getTurn().drawSoloActionToken();
            //out.println("UPDATE_SOLO_ACTION_TOKEN "+actionToken);<--FIXME-->
            System.out.println(this);
        } catch (ActionNotAllowedException e) {
            out.println(new InvalidActionErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "DRAW_SOLO_ACTION_TOKEN";
    }

}
