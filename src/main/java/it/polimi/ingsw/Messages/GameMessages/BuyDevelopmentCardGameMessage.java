package it.polimi.ingsw.Messages.GameMessages;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ConfirmedActionMessage;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.GameMessage;

import java.io.PrintWriter;

public class BuyDevelopmentCardGameMessage extends GameMessage {

    private int x;
    private int y;

    public BuyDevelopmentCardGameMessage(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute(Game game, PrintWriter out) {
        try {
            game.getTurn().buyADevelopmentCard(x, y);
            out.println(new ConfirmedActionMessage());
            System.out.println(this);
            //forward("UPDATE_DEVELOPMENT_CARD "+message[1]+" "+message[2], out);<---FIXME->>
        } catch (ActionNotAllowedException e) {
            out.println(new InvalidPositionErrorMessage());
        } catch (SelectedADevelopmentCardYetException e) {
            out.println(new AlreadySelectedSomethingErrorMessage());
        } catch (NotAbleToBuyThisDevelopmentCardException e) {
            out.println(new NotEnoughResourcesErrorMessage());
        } catch (DrawnFromEmptyDeckException e) {
            out.println(new EmptyDeckErrorMessage());
        } catch (PositionInvalidException e) {
            out.println(new InvalidPositionErrorMessage());
        } catch (NotAbleToPlaceThisDevelopmentCardException e) {
            out.println(new InvalidSelectionErrorMessage());
        }
    }

    @Override
    public String toString(){
        return "BUY_DEVELOPMENT_CARD";
    }

}
