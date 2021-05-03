package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.GameEndedException;
import it.polimi.ingsw.Exceptions.NoGenericResourceToObtainException;
import it.polimi.ingsw.Exceptions.NotAResourceForStrongBoxException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GameEndedErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GenericResourceErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NotStrongboxErrorMessage;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class ObtainGenericResourceGameMessage extends GameMessage {

    private Resource resource;

    public ObtainGenericResourceGameMessage(Resource resource){
        this.resource = resource;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().obtainGenericResource(resource);
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
        } catch (NoGenericResourceToObtainException e) {
            out.println(new GenericResourceErrorMessage());
        } catch (NotAResourceForStrongBoxException e) {
            out.println(new NotStrongboxErrorMessage());
        } catch (GameEndedException e) {
            out.println(new GameEndedErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "OBTAIN_GENERIC_RESOURCE";
    }

}
