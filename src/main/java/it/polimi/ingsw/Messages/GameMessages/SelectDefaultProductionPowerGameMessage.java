package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class SelectDefaultProductionPowerGameMessage extends GameMessage {

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().selectDefaultProductionPower();
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
        } catch (ActionNotAllowedException e) {
            out.println(new InvalidActionErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "SELECT_DEFAULT_PRODUCTION_POWER";
    }

}
