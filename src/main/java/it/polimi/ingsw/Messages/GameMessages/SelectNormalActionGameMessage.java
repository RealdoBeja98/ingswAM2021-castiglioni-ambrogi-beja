package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Enums.NormalAction;
import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class SelectNormalActionGameMessage extends GameMessage {

    private NormalAction normalAction;

    public SelectNormalActionGameMessage(NormalAction normalAction){
        this.normalAction = normalAction;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().selectNormalAction(normalAction);
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
        } catch (ActionNotAllowedException e) {
            out.println(new InvalidActionErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "SELECT_NORMAL_ACTION";
    }

}
