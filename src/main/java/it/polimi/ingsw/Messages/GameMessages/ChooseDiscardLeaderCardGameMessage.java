package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.NoLeaderCardToDiscardException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoCardDiscardErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public class ChooseDiscardLeaderCardGameMessage extends GameMessage {

    public ChooseDiscardLeaderCardGameMessage(){
        identifier = "CHOOSE_DISCARD_LEADER_CARD";
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().chooseDiscardLeaderCard();
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
        } catch (NoLeaderCardToDiscardException e) {
            Message.sendMessage(out, new NoCardDiscardErrorMessage());
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        }
    }

}
