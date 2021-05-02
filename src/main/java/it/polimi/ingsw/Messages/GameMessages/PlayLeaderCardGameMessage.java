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
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class PlayLeaderCardGameMessage extends GameMessage {

    private int leaderCardToPlay;

    public PlayLeaderCardGameMessage(int leaderCardToPlay){
        this.leaderCardToPlay = leaderCardToPlay;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().playLeaderCard(leaderCardToPlay);
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
        } catch (NotSatisfiedRequirementsForThisLeaderCardException e) {
            out.println(new RequirementsErrorMessage());
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
        return "PLAY_LEADER_CARD";
    }

}
