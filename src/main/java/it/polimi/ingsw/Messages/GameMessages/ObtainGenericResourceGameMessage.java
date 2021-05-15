package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.GameEndedException;
import it.polimi.ingsw.Exceptions.NoGenericResourceToObtainException;
import it.polimi.ingsw.Exceptions.NotAResourceForStrongBoxException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GameEndedErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GenericResourceErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NotStrongboxErrorMessage;
import it.polimi.ingsw.Messages.ForwardMessages.ObtainedGenericResourceForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public class ObtainGenericResourceGameMessage extends GameMessage {

    private Resource resource;

    public ObtainGenericResourceGameMessage(Resource resource){
        identifier = "OBTAIN_GENERIC_RESOURCE";
        this.resource = resource;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            String currentPlayer = game.getTurn().getCurrentPlayer().getNickname();
            game.getTurn().obtainGenericResource(resource);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
            forwardAll(game, new ObtainedGenericResourceForwardMessage(currentPlayer, resource));
        } catch (NoGenericResourceToObtainException e) {
            Message.sendMessage(out, new GenericResourceErrorMessage());
        } catch (NotAResourceForStrongBoxException e) {
            Message.sendMessage(out, new NotStrongboxErrorMessage());
        } catch (GameEndedException e) {
            Message.sendMessage(out, new GameEndedErrorMessage());
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        }
    }

}
