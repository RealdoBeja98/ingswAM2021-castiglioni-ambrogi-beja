package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.GameEndedException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GameEndedErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public class ChooseNoActionLeaderCardGameMessage extends GameMessage {

    public ChooseNoActionLeaderCardGameMessage(){
        identifier = "CHOOSE_NO_ACTION_LEADER_CARD";
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().chooseNoActionLeaderCard();
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
        } catch (GameEndedException e) {
            Message.sendMessage(out, new GameEndedErrorMessage());
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        }
    }

}
