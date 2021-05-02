package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.NoLeaderCardToDiscardException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ErrorMessages.NoCardDiscardErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class ChooseDiscardLeaderCardGameMessage extends GameMessage {

    @Override
    public void execute(Game game, PrintWriter out) {///////////////////////////////////////
        try {
            game.getTurn().chooseDiscardLeaderCard();
            out.println("CONFIRMED_ACTION");
            System.out.println("CHOOSE_DISCARD_LEADER_CARD");
        } catch (NoLeaderCardToDiscardException e) {
            out.println((new NoCardDiscardErrorMessage()).toString());
        } catch (ActionNotAllowedException e) {
            out.println("ERROR_INVALID_ACTION");
        }
    }

    @Override
    public String toString(){
        return "CHOOSE_DISCARD_LEADER_CARD";
    }

}
