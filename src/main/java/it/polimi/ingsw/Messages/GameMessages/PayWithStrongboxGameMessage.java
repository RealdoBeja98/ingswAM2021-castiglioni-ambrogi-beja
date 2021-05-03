package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class PayWithStrongboxGameMessage extends GameMessage {

    private Resource resource;

    public PayWithStrongboxGameMessage(Resource resource){
        this.resource = resource;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().payWithStrongBox(resource);
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
        } catch (WrongPaymentException e) {
            out.println(new WrongResourceErrorMessage());
        } catch (NegativeResourceException e) {
            out.println(new MissingResourceErrorMessage());
        } catch (NotAResourceForStrongBoxException e) {
            out.println(new NotStrongboxErrorMessage());
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
        return "PAY_WITH_STRONGBOX";
    }

}
