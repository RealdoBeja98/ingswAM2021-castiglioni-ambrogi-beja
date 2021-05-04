package it.polimi.ingsw.Messages;

import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Table.Decks.Token.ActionToken;

import java.io.PrintWriter;

public class UpdateSoloActionTokenMessage extends Message{

    private ActionToken actionToken;

    public UpdateSoloActionTokenMessage(ActionToken actionToken){
        identifier = "UPDATE_SOLO_ACTION_TOKEN";
        this.actionToken = actionToken;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        //<--FIXME--> implement method
    }

    @Override
    public String toString(){
        return "UPDATE_SOLO_ACTION_TOKEN "+actionToken;
    }

}
