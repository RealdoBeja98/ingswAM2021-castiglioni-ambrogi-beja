package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public class PayWithWarehouseDepotsGameMessage extends GameMessage {

    private int position;

    public PayWithWarehouseDepotsGameMessage(int position){
        identifier = "PAY_WITH_WAREHOUSE_DEPOTS";
        this.position = position;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().payWithWarehouseDepots(position);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
        } catch (WrongPaymentException e) {
            Message.sendMessage(out, new WrongResourceErrorMessage());
        } catch (EmptySlotYetException e) {
            Message.sendMessage(out, new AlreadyEmptyErrorMessage());
        } catch (NoResourceToPayException e) {
            Message.sendMessage(out, new NoResourcePErrorMessage());
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        } catch (GameEndedException e) {
            Message.sendMessage(out, new GameEndedErrorMessage());
        }
    }

}
