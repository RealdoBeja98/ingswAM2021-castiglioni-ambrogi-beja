package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.AlreadyDiscardedThisLeaderCardException;
import it.polimi.ingsw.Exceptions.GameEndedException;
import it.polimi.ingsw.Exceptions.PositionInvalidException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public class DiscardLeaderCardGameMessage extends GameMessage {

    private int leaderCardToDiscard;

    public DiscardLeaderCardGameMessage(int leaderCardToDiscard){
        identifier = "DISCARD_LEADER_CARD";
        this.leaderCardToDiscard = leaderCardToDiscard;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().discardLeaderCard(leaderCardToDiscard);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
        } catch (AlreadyDiscardedThisLeaderCardException e) {
            Message.sendMessage(out, new AlreadyDiscardedPositionErrorMessage());
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        } catch (GameEndedException e) {
            Message.sendMessage(out, new GameEndedErrorMessage());
        } catch (PositionInvalidException e) {
            Message.sendMessage(out, new InvalidPositionErrorMessage());
        }
    }

}
