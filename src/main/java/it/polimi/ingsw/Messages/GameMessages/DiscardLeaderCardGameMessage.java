package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.AlreadyDiscardedThisLeaderCardException;
import it.polimi.ingsw.Exceptions.GameEndedException;
import it.polimi.ingsw.Exceptions.PositionInvalidException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class DiscardLeaderCardGameMessage extends GameMessage {

    private int leaderCardToDiscard;

    public DiscardLeaderCardGameMessage(int leaderCardToDiscard){
        this.leaderCardToDiscard = leaderCardToDiscard;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().discardLeaderCard(leaderCardToDiscard);
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
        } catch (AlreadyDiscardedThisLeaderCardException e) {
            out.println(new AlreadyDiscardedPositionErrorMessage());
        } catch (ActionNotAllowedException e) {
            out.println(new InvalidActionErrorMessage());
        } catch (GameEndedException e) {
            out.println(new GameEndedErrorMessage());
        } catch (PositionInvalidException e) {
            out.println(new InvalidPositionErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "DISCARD_LEADER_CARD";
    }

}
