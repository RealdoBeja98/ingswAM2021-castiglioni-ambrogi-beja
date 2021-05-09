package it.polimi.ingsw.Messages;
import it.polimi.ingsw.Enums.LeaderWarehouse;
import it.polimi.ingsw.Enums.NormalAction;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Messages.ErrorMessages.*;
import it.polimi.ingsw.Messages.ForwardMessages.AdvanceFaithTrackForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.UpdateDevelopmentCardForwardMessage;
import it.polimi.ingsw.Messages.ForwardMessages.UpdateMarketForwardMessage;
import it.polimi.ingsw.Messages.GameMessages.*;
import it.polimi.ingsw.Messages.ServiceMessages.GameStartServiceMessage;
import it.polimi.ingsw.Table.Decks.Token.ActionToken;
import java.io.PrintWriter;

public abstract class Message {

    protected String identifier;

    public String getIdentifier() {
        return identifier;
    }

    public abstract void execute(Game game, PrintWriter out);

    private static void checkLength(String[] message, int n) {
        if(message.length != n){
            throw new IllegalArgumentException();
        }
    }

    private static int atoi(String str)
    {
        try{
            return Integer.parseInt(str);
        }catch(NumberFormatException ex){
            throw new IllegalArgumentException();
        }
    }

    public static Message fromString(String string){

        if(string.equals("ERROR_NO_CARDS_DISCARD")){
            return new NoCardDiscardErrorMessage();
        }
        if(string.equals("ERROR_NOT_YOUR_TURN")){
            return new NotYourTurnErrorMessage();
        }
        if(string.equals("ERROR_INVALID_ACTION")){
            return new InvalidActionErrorMessage();
        }
        if(string.equals("ERROR_ALREADY_DISCARDED_POSITION")){
            return new AlreadyDiscardedPositionErrorMessage();
        }
        if(string.equals("ERROR_GAME_ENDED")){
            return new GameEndedErrorMessage();
        }
        if(string.equals("ERROR_INVALID_POSITION")){
            return new InvalidPositionErrorMessage();
        }
        if(string.equals("ERROR_TYPO")){
            return new TypoErrorMessage();
        }
        if(string.equals("ERROR_NO_CARDS_PLAY")){
            return new NoCardsPlayErrorMessage();
        }
        if(string.equals("ERROR_REQUIREMENTS")){
            return new RequirementsErrorMessage();
        }
        if(string.equals("ERROR_INVALID_ENUM")){
            return new InvalidEnumErrorMessage();
        }
        if(string.equals("ERROR_NO_RESOURCE_A")){
            return new NoResourceAErrorMessage();
        }
        if(string.equals("ERROR_DIFFERENT_STORAGE_TYPE")){
            return new DifferentStorageTypeErrorMessage();
        }
        if(string.equals("ERROR_OCCUPIED_SLOT_LC")){
            return new OccupiedSlotLCErrorMessage();
        }
        if(string.equals("ERROR_OCCUPIED_SLOT_WD")){
            return new OccupiedSlotWDErrorMessage();
        }
        if(string.equals("ERROR_RESOURCE_ALREADY_PRESENT_OTHER_SHELF")){
            return new ResourceAlreadyPresentOtherShelfErrorMessage();
        }
        if(string.equals("ERROR_DIFFERENT_RESOURCE_ALREADY_PRESENT")){
            return new DifferentResourceAlreadyPresentErrorMessage();
        }
        if(string.equals("ERROR_WHITE_MARBLE")){
            return new WhiteMarbleErrorMessage();
        }
        if(string.equals("ERROR_FAITH_MARBLE")){
            return new FaithMarbleErrorMessage();
        }
        if(string.equals("ERROR_NO_WHITE_MARBLE")){
            return new NoWhiteMarbleErrorMessage();
        }
        if(string.equals("ERROR_ALREADY_SELECTED_SOMETHING")){
            return new AlreadySelectedSomethingErrorMessage();
        }
        if(string.equals("ERROR_NOT_ENOUGH_RESOURCES")){
            return new NotEnoughResourcesErrorMessage();
        }
        if(string.equals("ERROR_EMPTY_DECK")){
            return new EmptyDeckErrorMessage();
        }
        if(string.equals("ERROR_INVALID_SELECTION")){
            return new InvalidSelectionErrorMessage();
        }
        if(string.equals("ERROR_WRONG_RESOURCE")){
            return new WrongResourceErrorMessage();
        }
        if(string.equals("ERROR_MISSING_RESOURCE")){
            return new MissingResourceErrorMessage();
        }
        if(string.equals("ERROR_NOT_STRONGBOX")){
            return new NotStrongboxErrorMessage();
        }
        if(string.equals("ERROR_NO_RESOURCE_P")){
            return new NoResourcePErrorMessage();
        }
        if(string.equals("ERROR_ALREADY_EMPTY")){
            return new AlreadyEmptyErrorMessage();
        }
        if(string.equals("ERROR_NOT_ES")){
            return new NotEsErrorMessage();
        }
        if(string.equals("ERROR_EMPTY_SLOT_ES")){
            return new EmptySlotEsErrorMessage();
        }
        if(string.equals("ERROR_NO_CARD_OBTAINABLE")){
            return new NoCardObtainableErrorMessage();
        }
        if(string.equals("ERROR_NO_DEVELOPMENT_CARD")){
            return new NoDevelopmentCardErrorMessage();
        }
        if(string.equals("ERROR_NO_PLC")){
            return new NoPLCErrorMessage();
        }
        if(string.equals("ERROR_GENERIC_RESOURCE")){
            return new GenericResourceErrorMessage();
        }
        if(string.equals("ERROR_NOT_ENOUGH_R")){
            return new NotEnoughRErrorMessage();
        }
        if(string.equals("ERROR_NO_SELECTED_POWERS")){
            return new NoSelectedPowersErrorMessage();
        }
        if(string.equals("ERROR_INVALID_MOVEMENT")){
            return new InvalidMovementErrorMessage();
        }
        if(string.equals("ERROR_NAME_TAKEN")){
            return new NameTakenErrorMessage();
        }
        if(string.equals("ERROR_GAME_STARTED")){
            return new GameStartedErrorMessage();
        }

        if(string.equals("CONFIRMED_ACTION")){
            return new ConfirmedActionMessage();
        }

        if(string.equals("ADVANCE_FAITH_TRACK")){
            return new AdvanceFaithTrackForwardMessage();
        }

        String[] message = string.split(" ");

        try {
            if (message[0].equals("UPDATE_MARKET")) {
                checkLength(message, 3);
                return new UpdateMarketForwardMessage(RowColumn.valueOf(message[1]), atoi(message[2]));
            } else if (message[0].equals("UPDATE_DEVELOPMENT_CARD")) {
                checkLength(message, 3);
                return new UpdateDevelopmentCardForwardMessage(atoi(message[1]), atoi(message[2]));
            } else

            if (message[0].equals("UPDATE_SOLO_ACTION_TOKEN")) {
                checkLength(message, 2);
                return new UpdateSoloActionTokenMessage(ActionToken.valueof(message[1]));
            } else

            if (message[0].equals("GAME_START")) {
                checkLength(message, 2);
                return new GameStartServiceMessage(message[1]);
            } else

            if (message[0].equals("CHOOSE_DISCARD_LEADER_CARD")) {
                checkLength(message, 1);
                return new ChooseDiscardLeaderCardGameMessage();
            } else if (message[0].equals("DISCARD_LEADER_CARD")) {
                checkLength(message, 2);
                return new DiscardLeaderCardGameMessage(atoi(message[1]));
            } else if (message[0].equals("CHOOSE_PLAY_LEADER_CARD")) {
                checkLength(message, 1);
                return new ChoosePlayLeaderCardGameMessage();
            } else if (message[0].equals("PLAY_LEADER_CARD")) {
                checkLength(message, 2);
                return new PlayLeaderCardGameMessage(atoi(message[1]));
            } else if (message[0].equals("CHOOSE_NO_ACTION_LEADER_CARD")) {
                checkLength(message, 1);
                return new ChooseNoActionLeaderCardGameMessage();
            } else if (message[0].equals("SELECT_NORMAL_ACTION")) {
                checkLength(message, 2);
                return new SelectNormalActionGameMessage(NormalAction.valueOf(message[1]));
            } else if (message[0].equals("TAKE_RESOURCES_FROM_THE_MARKET")) {
                checkLength(message, 3);
                return new TakeResourcesFromTheMarketGameMessage(RowColumn.valueOf(message[1]), atoi(message[2]));
            } else if (message[0].equals("ADD_RESOURCE_TO")) {
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
                    return new AddResourceToGameMessage(LeaderWarehouse.valueOf(message[1]), atoi(message[2]));
                }
            } else if (message[0].equals("CHANGE_WHITE_MARBLE_WITH")) {
                checkLength(message, 2);
                return new ChangeWhiteMarbleWithGameMessage(atoi(message[1]));
            } else if (message[0].equals("BUY_DEVELOPMENT_CARD")) {
                checkLength(message, 3);
                return new BuyDevelopmentCardGameMessage(atoi(message[1]), atoi(message[2]));
            } else if (message[0].equals("PAY_WITH_STRONGBOX")) {
                checkLength(message, 2);
                return new PayWithStrongboxGameMessage(Resource.valueOf(message[1]));
            } else if (message[0].equals("PAY_WITH_WAREHOUSE_DEPOTS")) {
                checkLength(message, 2);
                return new PayWithWarehouseDepotsGameMessage(atoi(message[1]));
            } else if (message[0].equals("PAY_WITH_EXTRA_STORAGE_LEADER_CARD")) {
                checkLength(message, 2);
                return new PayWithExtraStorageLeaderCardGameMessage(atoi(message[1]));
            } else if (message[0].equals("PLACE_DEVELOPMENT_CARD")) {
                checkLength(message, 2);
                return new PlaceDevelopmentCardGameMessage(atoi(message[1]));
            } else if (message[0].equals("SELECT_PRODUCTION_DEVELOPMENT_CARD")) {
                checkLength(message, 2);
                return new SelectProductionDevelopmentCardGameMessage(atoi(message[1]));
            } else if (message[0].equals("SELECT_PRODUCTION_POWER_LEADER_CARD")) {
                checkLength(message, 2);
                return new SelectProductionPowerLeaderCardGameMessage(atoi(message[1]));
            } else if (message[0].equals("SELECT_DEFAULT_PRODUCTION_POWER")) {
                checkLength(message, 1);
                return new SelectDefaultProductionPowerGameMessage();
            } else if (message[0].equals("OBTAIN_GENERIC_RESOURCE")) {
                checkLength(message, 2);
                return new ObtainGenericResourceGameMessage(Resource.valueOf(message[1]));
            } else if (message[0].equals("START_PAYMENT")) {
                checkLength(message, 1);
                return new StartPaymentGameMessage();
            } else if (message[0].equals("DRAW_SOLO_ACTION_TOKEN")) {
                checkLength(message, 1);
                return new DrawSoloActionTokenGameMessage();
            } else if (message[0].equals("SELECT_A_WAREHOUSE_DEPOTS_SLOT")) {
                checkLength(message, 2);
                return new SelectAWarehouseDepotsSlotGameMessage(atoi(message[1]));
            } else if (message[0].equals("MOVE_RESOURCES_IN_WAREHOUSE_DEPOTS")) {
                checkLength(message, 2);
                return new MoveResourcesInWarehouseDepotsGameMessage(atoi(message[1]));
            } else if (message[0].equals("MOVE_RESOURCES_WAREHOUSE_TO_ES_LC")) {
                checkLength(message, 2);
                return new MoveResourcesWarehouseToESLCGameMessage(atoi(message[1]));
            } else if (message[0].equals("MOVE_RESOURCE_ES_LC_TO_WAREHOUSE")) {
                checkLength(message, 2);
                return new MoveResourceESLCToEarehouseGameMessage(atoi(message[1]));
            }
        } catch (IllegalArgumentException e){
            return new ToErrorTypoGameMessage();
        }

        return new ToErrorTypoGameMessage(string);
    }

    public static void sendMessage(PrintWriter dest, Message message){
        dest.println(message.identifier);
    }

    public boolean isEqual(Message other){
        if(this.identifier.equals(other.identifier)){
            return  true;
        }
        return false;
    }

    @Override
    public String toString(){
        return identifier;
    }

}
