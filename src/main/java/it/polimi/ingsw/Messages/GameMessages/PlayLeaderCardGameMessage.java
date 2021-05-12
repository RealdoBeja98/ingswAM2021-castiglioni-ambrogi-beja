package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.GameEndedException;
import it.polimi.ingsw.Exceptions.NotSatisfiedRequirementsForThisLeaderCardException;
import it.polimi.ingsw.Exceptions.PositionInvalidException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GameEndedErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidPositionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.RequirementsErrorMessage;
import it.polimi.ingsw.Messages.ForwardMessages.DiscardedLeaderCardForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.PlayedLeaderCardForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public class PlayLeaderCardGameMessage extends GameMessage {

    private int leaderCardToPlay;

    public PlayLeaderCardGameMessage(int leaderCardToPlay){
        identifier = "PLAY_LEADER_CARD";
        this.leaderCardToPlay = leaderCardToPlay;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            String currentPlayer = game.getTurn().getCurrentPlayer().getNickname();
            game.getTurn().playLeaderCard(leaderCardToPlay);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
            forwardAll(game, new PlayedLeaderCardForwardMessage(currentPlayer, leaderCardToPlay));
        } catch (NotSatisfiedRequirementsForThisLeaderCardException e) {
            Message.sendMessage(out, new RequirementsErrorMessage());
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        } catch (GameEndedException e) {
            Message.sendMessage(out, new GameEndedErrorMessage());
        } catch (PositionInvalidException e) {
            Message.sendMessage(out, new InvalidPositionErrorMessage());
        }
    }

}
