package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.NoLeaderCardToPlayException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoCardsPlayErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public class ChoosePlayLeaderCardGameMessage extends GameMessage {

    public ChoosePlayLeaderCardGameMessage(){
        identifier = "CHOOSE_PLAY_LEADER_CARD";
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().choosePlayLeaderCard();
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
        } catch (NoLeaderCardToPlayException e) {
            Message.sendMessage(out, new NoCardsPlayErrorMessage());
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        }
    }

}
