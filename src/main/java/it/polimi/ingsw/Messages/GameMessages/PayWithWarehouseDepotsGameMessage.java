package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class PayWithWarehouseDepotsGameMessage extends GameMessage {

    private int position;

    public PayWithWarehouseDepotsGameMessage(int position){
        this.position = position;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().payWithWarehouseDepots(position);
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
        } catch (WrongPaymentException e) {
            out.println(new WrongResourceErrorMessage());
        } catch (EmptySlotYetException e) {
            out.println(new AlreadyEmptyErrorMessage());
        } catch (NoResourceToPayException e) {
            out.println(new NoResourcePErrorMessage());
        } catch (ActionNotAllowedException e) {
            out.println(new InvalidActionErrorMessage());
        } catch (GameEndedException e) {
            out.println(new GameEndedErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "PAY_WITH_WAREHOUSE_DEPOTS";
    }

}
