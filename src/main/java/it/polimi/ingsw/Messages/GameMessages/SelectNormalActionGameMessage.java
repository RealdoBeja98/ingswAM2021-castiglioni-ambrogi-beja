package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Enums.NormalAction;
import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ServiceMessages.ShowMarketMessage;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;

import java.io.PrintWriter;

public class SelectNormalActionGameMessage extends GameMessage {

    private NormalAction normalAction;

    public SelectNormalActionGameMessage(NormalAction normalAction){
        identifier = "SELECT_NORMAL_ACTION";
        this.normalAction = normalAction;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().selectNormalAction(normalAction);

            if(normalAction.toString().equals("TAKE_RESOURCES_FROM_THE_MARKET")){
                Message.sendMessage(out, new ShowMarketMessage());
            }
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        }
    }

}
