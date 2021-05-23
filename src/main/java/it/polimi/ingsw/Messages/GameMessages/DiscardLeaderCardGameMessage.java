package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.AlreadyDiscardedThisLeaderCardException;
import it.polimi.ingsw.Exceptions.GameEndedException;
import it.polimi.ingsw.Exceptions.PositionInvalidException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.ForwardMessages.DiscardedLeaderCardForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.View.Cli;
import it.polimi.ingsw.View.View;

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
            String currentPlayer = game.getTurn().getCurrentPlayer().getNickname();
            game.getTurn().discardLeaderCard(leaderCardToDiscard);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
            forwardAll(game, new DiscardedLeaderCardForwardMessage(currentPlayer, leaderCardToDiscard));
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

    @Override
    public String toString(){
        return identifier + " " + leaderCardToDiscard;
    }

}
