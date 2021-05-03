package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class PayWithExtraStorageLeaderCardGameMessage extends GameMessage {

    private int leaderCardPosition;

    public PayWithExtraStorageLeaderCardGameMessage(int leaderCardPosition){
        this.leaderCardPosition = leaderCardPosition;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().payWithExtraStorageLeaderCard(leaderCardPosition);
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
        } catch (NotAnExtraStorageLeaderCardException e) {
            out.println(new NotEsErrorMessage());
        } catch (WrongPaymentException e) {
            out.println(new WrongResourceErrorMessage());
        } catch (EmptySlotExtraStorageLeaderCardException e) {
            out.println(new EmptySlotEsErrorMessage());
        } catch (NoResourceToPayException e) {
            out.println(new NoResourcePErrorMessage());
        } catch (ActionNotAllowedException e) {
            out.println(new InvalidActionErrorMessage());
        } catch (PositionInvalidException e) {
            out.println(new InvalidPositionErrorMessage());
        } catch (GameEndedException e) {
            out.println(new GameEndedErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "PAY_WITH_EXTRA_STORAGE_LEADER_CARD";
    }

}
