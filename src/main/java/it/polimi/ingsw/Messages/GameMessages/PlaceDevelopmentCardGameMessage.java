package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.ActionNotAllowedException;
import it.polimi.ingsw.Exceptions.GameEndedException;
import it.polimi.ingsw.Exceptions.NoDevelopmentCardToObtainException;
import it.polimi.ingsw.Exceptions.PositionInvalidException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.GameEndedErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidActionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.InvalidPositionErrorMessage;
import it.polimi.ingsw.Messages.ErrorMessages.NoCardObtainableErrorMessage;
import it.polimi.ingsw.Messages.ForwardMessages.AddedResourceToForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.PlacedDevelopmentCardForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;

import java.io.PrintWriter;

public class PlaceDevelopmentCardGameMessage extends GameMessage {

    private int position;

    public PlaceDevelopmentCardGameMessage(int position){
        identifier = "PLACE_DEVELOPMENT_CARD";
        this.position = position;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            DevelopmentCard obtainedDevelopmentCard = game.getTurn().getCurrentPlayer().getObtainedDevelopmentCard();
            game.getTurn().placeDevelopmentCard(position);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
            forward(game, new PlacedDevelopmentCardForwardMessage(game.getTurn().getCurrentPlayer().getNickname(), position, obtainedDevelopmentCard.export()), out);
        } catch (NoDevelopmentCardToObtainException e) {
            Message.sendMessage(out, new NoCardObtainableErrorMessage());
        } catch (PositionInvalidException e) {
            Message.sendMessage(out, new InvalidPositionErrorMessage());
        } catch (GameEndedException e) {
            Message.sendMessage(out, new GameEndedErrorMessage());
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidActionErrorMessage());
        }
    }

}
