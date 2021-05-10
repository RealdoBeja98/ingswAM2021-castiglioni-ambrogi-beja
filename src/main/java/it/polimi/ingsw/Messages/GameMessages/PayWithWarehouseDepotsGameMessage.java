package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.ForwardMessages.PayedWithWarehouseDepotsForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.UpdateObtainedMultipleResourceForwardMessage;
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
            int faith = game.getTurn().getCurrentPlayer().obtainingResourcesAfterPaying(Resource.FAITH);
            int coin = game.getTurn().getCurrentPlayer().obtainingResourcesAfterPaying(Resource.COIN);
            int servant = game.getTurn().getCurrentPlayer().obtainingResourcesAfterPaying(Resource.SERVANT);
            int shield = game.getTurn().getCurrentPlayer().obtainingResourcesAfterPaying(Resource.SHIELD);
            int stone = game.getTurn().getCurrentPlayer().obtainingResourcesAfterPaying(Resource.STONE);
            game.getTurn().payWithWarehouseDepots(position);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
            forwardAll(game, new UpdateObtainedMultipleResourceForwardMessage(game.getTurn().getCurrentPlayer().getNickname(), faith, coin, servant, shield, stone));
            forwardAll(game, new PayedWithWarehouseDepotsForwardMessage(game.getTurn().getCurrentPlayer().getNickname(), position));
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
