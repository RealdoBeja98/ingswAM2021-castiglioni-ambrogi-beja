package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.ForwardMessages.UpdateDevelopmentCardForwardMessage;
import it.polimi.ingsw.Messages.GameMessage;
import it.polimi.ingsw.Messages.Message;

import java.io.PrintWriter;

public class BuyDevelopmentCardGameMessage extends GameMessage {

    private int x;
    private int y;

    public BuyDevelopmentCardGameMessage(int x, int y){
        identifier = "BUY_DEVELOPMENT_CARD";
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().buyADevelopmentCard(x, y);
            Message.sendMessage(out, new ConfirmedActionMessage());
            System.out.println(identifier);
            forwardAll(game, new UpdateDevelopmentCardForwardMessage(x, y));
        } catch (ActionNotAllowedException e) {
            Message.sendMessage(out, new InvalidPositionErrorMessage());
        } catch (SelectedADevelopmentCardYetException e) {
            Message.sendMessage(out, new AlreadySelectedSomethingErrorMessage());
        } catch (NotAbleToBuyThisDevelopmentCardException e) {
            Message.sendMessage(out, new NotEnoughResourcesErrorMessage());
        } catch (DrawnFromEmptyDeckException e) {
            Message.sendMessage(out, new EmptyDeckErrorMessage());
        } catch (PositionInvalidException e) {
            Message.sendMessage(out, new InvalidPositionErrorMessage());
        } catch (NotAbleToPlaceThisDevelopmentCardException e) {
            Message.sendMessage(out, new InvalidSelectionErrorMessage());
        } catch (IndexOutOfDevelopmentDeckException e) {
            Message.sendMessage(out, new InvalidPositionErrorMessage());
        }
    }

}
