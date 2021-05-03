package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.GameEndedException;
import it.polimi.ingsw.Exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.Exceptions.YouHaveNotSelectedAnyProductionException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GameEndedErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoSelectedPowersErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NotEnoughRErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class StartPaymentGameMessage extends GameMessage {

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().startPayment();
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
        } catch (NotEnoughResourcesException e) {
            out.println(new NotEnoughRErrorMessage());
        } catch (YouHaveNotSelectedAnyProductionException e) {
            out.println(new NoSelectedPowersErrorMessage());
        } catch (ActionNotAllowedException e) {
            out.println(new InvalidActionErrorMessage());
        } catch (GameEndedException e) {
            out.println(new GameEndedErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "START_PAYMENT";
    }

}
