package it.polimi.ingsw.Messages;
import it.polimi.ingsw.Enums.LeaderWarehouse;
import it.polimi.ingsw.Enums.NormalAction;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.ForwardMessages.*;
import it.polimi.ingsw.Messages.GameMessages.*;
import it.polimi.ingsw.Messages.ServiceMessages.*;
import it.polimi.ingsw.Table.Decks.Token.ActionToken;
import it.polimi.ingsw.Utilities.IntegerFromString;

import java.io.PrintWriter;

/**
 * Class of the  message
 */
public abstract class Message {

    protected String identifier;

    public String getIdentifier() {
        return identifier;
    }

    public abstract void execute(Game game, PrintWriter out);

    /**
     * This method check the length of the message
     * @param message message
     * @param n integer
     */
    private static void checkLength(String[] message, int n) {
        if(message.length != n){
            throw new IllegalArgumentException();
        }
    }

    /**
     * this method return a short message from a string
     * @param string: is the string representing the message
     * @return the message; of type Message
     */
    private static Message fromStringShortMessages(String string){
        switch(string){
            case "ERROR_NO_CARDS_DISCARD": return new NoCardDiscardErrorMessage();
            case "ERROR_PLAYER_EXISTENCE": return new PlayerExistenceErrorMessage();
            case "ERROR_NOT_YOUR_TURN": return new NotYourTurnErrorMessage();
            case "ERROR_INVALID_ACTION": return new InvalidActionErrorMessage();
            case "ERROR_ALREADY_DISCARDED_POSITION": return new AlreadyDiscardedPositionErrorMessage();
            case "ERROR_GAME_ENDED": return new GameEndedErrorMessage();
            case "ERROR_INVALID_POSITION": return new InvalidPositionErrorMessage();
            case "ERROR_TYPO": return new TypoErrorMessage();
            case "ERROR_NO_CARDS_PLAY": return new NoCardsPlayErrorMessage();
            case "ERROR_REQUIREMENTS": return new RequirementsErrorMessage();
            case "ERROR_INVALID_ENUM": return new InvalidEnumErrorMessage();
            case "ERROR_NO_RESOURCE_A": return new NoResourceAErrorMessage();
            case "ERROR_DIFFERENT_STORAGE_TYPE": return new DifferentStorageTypeErrorMessage();
            case "ERROR_OCCUPIED_SLOT_LC": return new OccupiedSlotLCErrorMessage();
            case "ERROR_OCCUPIED_SLOT_WD": return new OccupiedSlotWDErrorMessage();
            case "ERROR_RESOURCE_ALREADY_PRESENT_OTHER_SHELF": return new ResourceAlreadyPresentOtherShelfErrorMessage();
            case "ERROR_DIFFERENT_RESOURCE_ALREADY_PRESENT": return new DifferentResourceAlreadyPresentErrorMessage();
            case "ERROR_WHITE_MARBLE": return new WhiteMarbleErrorMessage();
            case "ERROR_FAITH_MARBLE": return new FaithMarbleErrorMessage();
            case "ERROR_NO_WHITE_MARBLE": return new NoWhiteMarbleErrorMessage();
            case "ERROR_ALREADY_SELECTED_SOMETHING": return new AlreadySelectedSomethingErrorMessage();
            case "ERROR_NOT_ENOUGH_RESOURCES": return new NotEnoughResourcesErrorMessage();
            case "ERROR_EMPTY_DECK": return new EmptyDeckErrorMessage();
            case "ERROR_INVALID_SELECTION": return new InvalidSelectionErrorMessage();
            case "ERROR_WRONG_RESOURCE": return new WrongResourceErrorMessage();
            case "ERROR_MISSING_RESOURCE": return new MissingResourceErrorMessage();
            case "ERROR_NOT_STRONGBOX": return new NotStrongboxErrorMessage();
            case "ERROR_NO_RESOURCE_P": return new NoResourcePErrorMessage();
            case "ERROR_ALREADY_EMPTY": return new AlreadyEmptyErrorMessage();
            case "ERROR_NOT_ES": return new NotEsErrorMessage();
            case "ERROR_EMPTY_SLOT_ES": return new EmptySlotEsErrorMessage();
            case "ERROR_NO_CARD_OBTAINABLE": return new NoCardObtainableErrorMessage();
            case "ERROR_NO_DEVELOPMENT_CARD": return new NoDevelopmentCardErrorMessage();
            case "ERROR_NO_PLC": return new NoPLCErrorMessage();
            case "ERROR_GENERIC_RESOURCE": return new GenericResourceErrorMessage();
            case "ERROR_NOT_ENOUGH_R": return new NotEnoughRErrorMessage();
            case "ERROR_NO_SELECTED_POWERS": return new NoSelectedPowersErrorMessage();
            case "ERROR_INVALID_MOVEMENT": return new InvalidMovementErrorMessage();
            case "ERROR_NAME_TAKEN": return new NameTakenErrorMessage();
            case "ERROR_GAME_STARTED": return new GameStartedErrorMessage();
            case "ERROR_GAME_DONT_EXIST": return new GameDontExistErrorMessage();
            case "SHOW_MARKET": return new ShowMarketMessage();
            case "SHOW_CARD_MARKET": return new ShowCardMarketMessage();
            case "SHOW_CURRENT_BOARD": return new ShowCurrentBoardMessage();
            case "ConfirmedActionMessage": return new ConfirmedActionMessage();
            case "TRANSITION_MESSAGE": return new TransitionForwardMessage();
        }
        return null;
    }

    /**
     * this method return a long message from a string
     * @param message: is the string representing the message
     * @return the message; of type Message
     */
    private static Message fromStringLongMessages(String[] message){
        switch (message[0]){
            case "UPDATE_MARKET":
                checkLength(message, 4);
                return new UpdateMarketForwardMessage(message[1], RowColumn.valueOf(message[2]), IntegerFromString.f(message[3]));
            case "UPDATE_DEVELOPMENT_CARD":
                checkLength(message, 3);
                return new UpdateDevelopmentCardForwardMessage(IntegerFromString.f(message[1]), IntegerFromString.f(message[2]));
            case "UPDATE_SOLO_ACTION_TOKEN":
                checkLength(message, 2);
                return new UpdateSoloActionTokenMessage(ActionToken.valueOf(message[1]));
            case "GAME_START":
                checkLength(message, 2);
                return new GameStartServiceMessage(message[1]);
            case "GAME_RECONNECTING":
                checkLength(message, 2);
                return new GameReconnectingServiceMessage(message[1]);
            case "SET_CURRENT_PLAYER":
                checkLength(message, 2);
                return new CurrentPlayerMessage(message[1]);
            case "ADVANCE_FAITH_TRACK":
                checkLength(message, 2);
                return new AdvanceFaithTrackForwardMessage(message[1]);
            case "DISCARDED_LEADER_CARD":
                checkLength(message, 3);
                return new DiscardedLeaderCardForwardMessage(message[1], IntegerFromString.f(message[2]));
            case "PLAYED_LEADER_CARD":
                checkLength(message, 3);
                return new PlayedLeaderCardForwardMessage(message[1], IntegerFromString.f(message[2]));
            case "ADDED_RESOURCE_TO":
                checkLength(message, 5);
                return new AddedResourceToForwardMessage(message[1], LeaderWarehouse.valueOf(message[2]), Resource.valueOf(message[3]), IntegerFromString.f(message[4]));
            case "PLACED_DEVELOPMENT_CARD":
                checkLength(message, 4);
                return new PlacedDevelopmentCardForwardMessage(message[1], IntegerFromString.f(message[2]), message[3]);
            case "UPDATE_MULTIPLE_RESOURCES":
                checkLength(message, 7);
                return new UpdateObtainedMultipleResourceForwardMessage(message[1], IntegerFromString.f(message[2]), IntegerFromString.f(message[3]), IntegerFromString.f(message[4]), IntegerFromString.f(message[5]), IntegerFromString.f(message[6]));
            case "OBTAINED_GENERIC_RESOURCE":
                checkLength(message, 3);
                return new ObtainedGenericResourceForwardMessage(message[1], Resource.valueOf(message[2]));
            case "PAYED_WITH_EXTRA_STORAGE_LEADER_CARD":
                checkLength(message, 3);
                return new PayedWithExtraStorageLeaderCardForwardMessage(message[1], IntegerFromString.f(message[2]));
            case "PAYED_WITH_STRONGBOX":
                checkLength(message, 3);
                return new PayedWithStrongboxForwardMessage(message[1], Resource.valueOf(message[2]));
            case "PAYED_WITH_WAREHOUSE_DEPOTS":
                checkLength(message, 3);
                return new PayedWithWarehouseDepotsForwardMessage(message[1], IntegerFromString.f(message[2]));
            case "MOVED_RESOURCES_IN_WAREHOUSE_DEPOTS":
                checkLength(message, 4);
                return new MovedResourcesInWarehouseDepotsForwardMessage(message[1], IntegerFromString.f(message[2]), IntegerFromString.f(message[3]));
            case "MOVED_RESOURCES_WAREHOUSE_TO_ES_LC":
                checkLength(message, 4);
                return new MovedResourcesWarehouseToESLCForwardMessage(message[1], IntegerFromString.f(message[2]), IntegerFromString.f(message[3]));
            case "MOVED_RESOURCE_ES_LC_TO_WAREHOUSE":
                checkLength(message, 4);
                return new MovedResourceESLCToWarehouseForwardMessage(message[1], IntegerFromString.f(message[2]), IntegerFromString.f(message[3]));
            case "SELECTED_TWO_CARDS_TO_KEEP":
                checkLength(message, 4);
                return new SelectedTwoCardsToKeepForwardMessage(message[1], IntegerFromString.f(message[2]), IntegerFromString.f(message[3]));
            case "CHOOSE_DISCARD_LEADER_CARD":
                checkLength(message, 1);
                return new ChooseDiscardLeaderCardGameMessage();
            case "DISCARD_LEADER_CARD":
                checkLength(message, 2);
                return new DiscardLeaderCardGameMessage(IntegerFromString.f(message[1]));
            case "CHOOSE_PLAY_LEADER_CARD":
                checkLength(message, 1);
                return new ChoosePlayLeaderCardGameMessage();
            case "PLAY_LEADER_CARD":
                checkLength(message, 2);
                return new PlayLeaderCardGameMessage(IntegerFromString.f(message[1]));
            case "CHOOSE_NO_ACTION_LEADER_CARD":
                checkLength(message, 1);
                return new ChooseNoActionLeaderCardGameMessage();
            case "END_TURN":
                checkLength(message, 1);
                return new EndTurnGameMessage();
            case "SELECT_NORMAL_ACTION":
                checkLength(message, 2);
                return new SelectNormalActionGameMessage(NormalAction.valueOf(message[1]));
            case "TAKE_RESOURCES_FROM_THE_MARKET":
                checkLength(message, 3);
                return new TakeResourcesFromTheMarketGameMessage(RowColumn.valueOf(message[1]), IntegerFromString.f(message[2]));
            case "ADD_RESOURCE_TO":
                boolean weAre = false;
                try {
                    checkLength(message, 1);
                } catch (IllegalArgumentException e){
                    weAre = true;
                }
                if(weAre == false){
                    throw new IllegalArgumentException();
                }
                if(message[1].equals("DISCARD")){
                    checkLength(message, 2);
                    return new AddResourceToGameMessage(LeaderWarehouse.DISCARD, 0);
                } else {
                    checkLength(message, 3);
                    return new AddResourceToGameMessage(LeaderWarehouse.valueOf(message[1]), IntegerFromString.f(message[2]));
                }
            case "CHANGE_WHITE_MARBLE_WITH":
                checkLength(message, 2);
                return new ChangeWhiteMarbleWithGameMessage(IntegerFromString.f(message[1]));
            case "BUY_DEVELOPMENT_CARD":
                checkLength(message, 3);
                return new BuyDevelopmentCardGameMessage(IntegerFromString.f(message[1]), IntegerFromString.f(message[2]));
            case "PAY_WITH_STRONGBOX":
                checkLength(message, 2);
                return new PayWithStrongboxGameMessage(Resource.valueOf(message[1]));
            case "PAY_WITH_WAREHOUSE_DEPOTS":
                checkLength(message, 2);
                return new PayWithWarehouseDepotsGameMessage(IntegerFromString.f(message[1]));
            case "PAY_WITH_EXTRA_STORAGE_LEADER_CARD":
                checkLength(message, 2);
                return new PayWithExtraStorageLeaderCardGameMessage(IntegerFromString.f(message[1]));
            case "PLACE_DEVELOPMENT_CARD":
                checkLength(message, 2);
                return new PlaceDevelopmentCardGameMessage(IntegerFromString.f(message[1]));
            case "SELECT_PRODUCTION_DEVELOPMENT_CARD":
                checkLength(message, 2);
                return new SelectProductionDevelopmentCardGameMessage(IntegerFromString.f(message[1]));
            case "SELECT_PRODUCTION_POWER_LEADER_CARD":
                checkLength(message, 2);
                return new SelectProductionPowerLeaderCardGameMessage(IntegerFromString.f(message[1]));
            case "SELECT_DEFAULT_PRODUCTION_POWER":
                checkLength(message, 1);
                return new SelectDefaultProductionPowerGameMessage();
            case "OBTAIN_GENERIC_RESOURCE":
                checkLength(message, 2);
                return new ObtainGenericResourceGameMessage(Resource.valueOf(message[1]));
            case "START_PAYMENT":
                checkLength(message, 1);
                return new StartPaymentGameMessage();
            case "DRAW_SOLO_ACTION_TOKEN":
                checkLength(message, 1);
                return new DrawSoloActionTokenGameMessage();
            case "SELECT_A_WAREHOUSE_DEPOTS_SLOT":
                checkLength(message, 2);
                return new SelectAWarehouseDepotsSlotGameMessage(IntegerFromString.f(message[1]));
            case "MOVE_RESOURCES_IN_WAREHOUSE_DEPOTS":
                checkLength(message, 2);
                return new MoveResourcesInWarehouseDepotsGameMessage(IntegerFromString.f(message[1]));
            case "MOVE_RESOURCES_WAREHOUSE_TO_ES_LC":
                checkLength(message, 2);
                return new MoveResourcesWarehouseToESLCGameMessage(IntegerFromString.f(message[1]));
            case "MOVE_RESOURCE_ES_LC_TO_WAREHOUSE":
                checkLength(message, 2);
                return new MoveResourceESLCToWEarehouseGameMessage(IntegerFromString.f(message[1]));
            case "SELECT_TWO_CARDS_TO_KEEP":
                checkLength(message, 3);
                return new SelectTwoCardsToKeepGameMessage(IntegerFromString.f(message[1]), IntegerFromString.f(message[2]));
        }
        return null;
    }

    /**
     * This method catches the arriving message and makes tho controlling part and in the end
     * sends to socket the proper message
     * @param string string message
     */
    public static Message fromString(String string){
        Message shortMessage = fromStringShortMessages(string);
        if(shortMessage != null){
            return shortMessage;
        }
        String[] message = string.split(" ");
        try {
            Message longMessage = fromStringLongMessages(message);
            if(longMessage != null){
                return longMessage;
            }
        } catch (IllegalArgumentException e){
            return new ToErrorTypoGameMessage();
        }
        return new ToErrorTypoGameMessage(string);
    }

    /**
     * This method sends the message to the destination socket
     * @param dest destination
     * @param message message
     */
    public static void sendMessage(PrintWriter dest, Message message){
        dest.println(message.identifier);
    }

    /**
     * This methods cheks for a particular message
     * @param other
     */
    public boolean isEqual(Message other){
        if(this.identifier.equals(other.identifier)){
            return  true;
        }
        return false;
    }

    /**
     * This method represents what message to print in console
     */
    @Override
    public String toString(){
        return identifier;
    }

}
