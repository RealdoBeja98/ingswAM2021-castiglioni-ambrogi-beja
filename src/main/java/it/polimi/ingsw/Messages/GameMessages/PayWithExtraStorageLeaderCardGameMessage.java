package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public class PayWithExtraStorageLeaderCardGameMessage extends GameMessage {

    private int leaderCardPosition;

    public PayWithExtraStorageLeaderCardGameMessage(int leaderCardPosition){
        identifier = "PAY_WITH_EXTRA_STORAGE_LEADER_CARD";
        this.leaderCardPosition = leaderCardPosition;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().payWithExtraStorageLeaderCard(leaderCardPosition);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
        } catch (NotAnExtraStorageLeaderCardException e) {
            Message.sendMessage(out, new NotEsErrorMessage());
        } catch (WrongPaymentException e) {
            Message.sendMessage(out, new WrongResourceErrorMessage());
        } catch (EmptySlotExtraStorageLeaderCardException e) {
            Message.sendMessage(out, new EmptySlotEsErrorMessage());
        } catch (NoResourceToPayException e) {
            Message.sendMessage(out, new NoResourcePErrorMessage());
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        } catch (PositionInvalidException e) {
            Message.sendMessage(out, new InvalidPositionErrorMessage());
        } catch (GameEndedException e) {
            Message.sendMessage(out, new GameEndedErrorMessage());
        }
    }

}
