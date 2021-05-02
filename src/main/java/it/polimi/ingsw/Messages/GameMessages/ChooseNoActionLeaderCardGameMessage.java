package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.GameEndedException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GameEndedErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class ChooseNoActionLeaderCardGameMessage extends GameMessage {

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().chooseNoActionLeaderCard();
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
        } catch (GameEndedException e) {
            out.println(new GameEndedErrorMessage());
        } catch (ActionNotAllowedException e) {
            out.println(new InvalidActionErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "CHOOSE_NO_ACTION_LEADER_CARD";
    }

}
