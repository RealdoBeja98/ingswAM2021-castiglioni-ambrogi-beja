package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public class SelectDefaultProductionPowerGameMessage extends GameMessage {

    public SelectDefaultProductionPowerGameMessage(){
        identifier = "SELECT_DEFAULT_PRODUCTION_POWER";
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().selectDefaultProductionPower();
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        }
    }

}
