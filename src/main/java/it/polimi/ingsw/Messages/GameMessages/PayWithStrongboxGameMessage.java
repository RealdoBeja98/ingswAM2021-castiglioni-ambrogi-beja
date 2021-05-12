package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.ForwardMessages.PayedWithStrongboxForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.UpdateObtainedMultipleResourceForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public class PayWithStrongboxGameMessage extends GameMessage {

    private Resource resource;

    public PayWithStrongboxGameMessage(Resource resource){
        identifier = "PAY_WITH_STRONGBOX";
        this.resource = resource;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            String currentPlayer = game.getTurn().getCurrentPlayer().getNickname();
            int faith = game.getTurn().getCurrentPlayer().obtainingResourcesAfterPaying(Resource.FAITH);
            int coin = game.getTurn().getCurrentPlayer().obtainingResourcesAfterPaying(Resource.COIN);
            int servant = game.getTurn().getCurrentPlayer().obtainingResourcesAfterPaying(Resource.SERVANT);
            int shield = game.getTurn().getCurrentPlayer().obtainingResourcesAfterPaying(Resource.SHIELD);
            int stone = game.getTurn().getCurrentPlayer().obtainingResourcesAfterPaying(Resource.STONE);
            game.getTurn().payWithStrongBox(resource);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
            forwardAll(game, new UpdateObtainedMultipleResourceForwardMessage(currentPlayer, faith, coin, servant, shield, stone));
            forwardAll(game, new PayedWithStrongboxForwardMessage(currentPlayer, resource));
        } catch (WrongPaymentException e) {
            Message.sendMessage(out, new WrongResourceErrorMessage());
        } catch (NegativeResourceException e) {
            Message.sendMessage(out, new MissingResourceErrorMessage());
        } catch (NotAResourceForStrongBoxException e) {
            Message.sendMessage(out, new NotStrongboxErrorMessage());
        } catch (NoResourceToPayException e) {
            Message.sendMessage(out, new NoResourcePErrorMessage());
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        } catch (GameEndedException e) {
            Message.sendMessage(out, new GameEndedErrorMessage());
        }
    }

}
