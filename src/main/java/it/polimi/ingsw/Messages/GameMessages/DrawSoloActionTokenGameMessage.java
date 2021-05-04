package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.UpdateSoloActionTokenMessage;
import it.polimi.ingsw.Table.Decks.Token.ActionToken;

import java.io.PrintWriter;

public class DrawSoloActionTokenGameMessage extends GameMessage {

    public DrawSoloActionTokenGameMessage(){
        identifier = "DRAW_SOLO_ACTION_TOKEN";
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            ActionToken actionToken = game.getTurn().drawSoloActionToken();
            out.println(new UpdateSoloActionTokenMessage(actionToken));
            System.out.println(identifier);
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        }
    }

}
