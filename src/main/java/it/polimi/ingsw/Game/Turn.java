package it.polimi.ingsw.Game;
import it.polimi.ingsw.Enums.*;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Messages.ForwardMessages.CurrentPlayerMessage;
import it.polimi.ingsw.Messages.ForwardMessages.TransitionForwardMessage;
import it.polimi.ingsw.Table.Decks.Token.ActionToken;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This Class represents the turn
 */
public class Turn {

    private boolean transitionFromFirstTurnToNormalTurnsDone = false;
    private Player currentPlayer;
    private boolean actionLeaderDone1 = false;
    private InWhichStatePlayer currentPlayerState = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1;
    private boolean developmentCardTaken = false;
    private boolean resourcesFromTheMarketTaken = false;
    private boolean gameEnded = false;
    private boolean startedPayment = false;
    private final int gameIndex;

    /**
     * Constructor method of this class
     * @param gameIndex: the index of the Game
     */
    public Turn(int gameIndex){
        this.gameIndex = gameIndex;
        int n = 0;
        for(Player i : Game.get(gameIndex).getPlayers()){
            if(i.isInkwell()){
                currentPlayer = i;
                if (Game.get(gameIndex).getPrintWriterList().size() != 0 && listWithoutNullPointers(Game.get(gameIndex).getPrintWriterList())) {
                    Game.get(gameIndex).getPrintWriterList().get(n).println("You have the inkwell!");
                }
            }
            n++;
        }
    }

    /**
     * This method let, at the beginning to choose two of the four leader card that you draw at the beginning
     * @param card1 the first card you choose
     * @param card2 the second card you choose
     * @throws ActionNotAllowedException if this action isn't allowed in this moment
     * @throws PositionInvalidException if you choose an invalid position
     * @throws GameEndedException if the game is finished and it's time to show final points
     */
    public void selectThoCardsToKeep(int card1, int card2) throws ActionNotAllowedException, PositionInvalidException, GameEndedException {
        currentPlayer.selectThoCardsToKeep(card1, card2);
        endTurn();
    }

    /**
     * This method return if an ArrayList of PrintWriter is a list without null pointers or not
     * @param list an ArrayList of PrintWriter
     * @return if it's a list without null pointers or not
     */
    private boolean listWithoutNullPointers(ArrayList<PrintWriter> list){
        for(PrintWriter i : list){
            if(i == null){
                return false;
            }
        }
        return true;
    }

    /**
     * This method advances the turn if the player refuse to play a leader card
     * @throws ActionNotAllowedException if this action isn't allowed in this moment
     * @throws GameEndedException if the game is finished
     */
    public void chooseNoActionLeaderCard() throws ActionNotAllowedException, GameEndedException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1 &&
                currentPlayerState != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1){
            currentPlayerState = InWhichStatePlayer.SELECT_NORMAL_ACTION;
        } else {
            if(Game.get(gameIndex).getNumberOfPlayer() == 1){
                currentPlayerState = InWhichStatePlayer.DRAW_SOLO_ACTION_TOKEN;
            } else {
                currentPlayerState = InWhichStatePlayer.FINISHING_TURN;
            }
        }
    }

    /**
     * This method advances the turn if the player choose to discard a leader card
     * @throws ActionNotAllowedException if this action isn't allowed in this moment
     * @throws NoLeaderCardToDiscardException if there isn't any LeaderCard in the hand
     */
    public void chooseDiscardLeaderCard() throws NoLeaderCardToDiscardException, ActionNotAllowedException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1 &&
                currentPlayerState != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2 &&
                actionLeaderDone1){
            throw new ActionNotAllowedException();
        }
        if(currentPlayer.countLeaderCardInHand() == 0){
            throw new NoLeaderCardToDiscardException();
        }
        if(currentPlayerState == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1){
            actionLeaderDone1 = true;
        }
        if(currentPlayerState == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1) {
            currentPlayerState = InWhichStatePlayer.DISCARD_LEADER_CARD1;
        } else if(currentPlayerState == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2) {
            currentPlayerState = InWhichStatePlayer.DISCARD_LEADER_CARD2;
        }
    }

    /**
     * This method advances the turn if the player choose to play a leader card
     * @throws ActionNotAllowedException if this action isn't allowed in this moment
     * @throws NoLeaderCardToPlayException if there isn't any LeaderCard in the hand that can be placed
     */
    public void choosePlayLeaderCard() throws ActionNotAllowedException, NoLeaderCardToPlayException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1 &&
                currentPlayerState != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2 &&
                actionLeaderDone1){
            throw new ActionNotAllowedException();
        }
        if(!currentPlayer.canYouPlayAtLeastALeaderCard()){
            throw new NoLeaderCardToPlayException();
        }
        if(currentPlayerState == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1){
            actionLeaderDone1 = true;
        }
        if(currentPlayerState == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1) {
            currentPlayerState = InWhichStatePlayer.PLAY_LEADER_CARD1;
        } else if(currentPlayerState == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2) {
            currentPlayerState = InWhichStatePlayer.PLAY_LEADER_CARD2;
        }
    }

    /**
     * This method lets the player discard one leader card from his hand
     * @param pos: position of the leader card in the hand
     * @throws ActionNotAllowedException if this action isn't allowed in this moment
     * @throws GameEndedException if the game is finished
     * @throws PositionInvalidException if the position isn't 1 or 2
     * @throws AlreadyDiscardedThisLeaderCardException if you try to discard a card from an empty position
     */
    public void discardLeaderCard(int pos) throws AlreadyDiscardedThisLeaderCardException, ActionNotAllowedException, GameEndedException, PositionInvalidException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.DISCARD_LEADER_CARD1 &&
                currentPlayerState != InWhichStatePlayer.DISCARD_LEADER_CARD2){
            throw new ActionNotAllowedException();
        }
        currentPlayer.discardLeaderCard(pos);
        if(currentPlayerState == InWhichStatePlayer.DISCARD_LEADER_CARD1){
            currentPlayerState = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1;
        } else if(currentPlayerState == InWhichStatePlayer.DISCARD_LEADER_CARD2){
            currentPlayerState = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2;
        }
    }

    /**
     * This method lets the player play one leader card from his hand
     * @param pos: position of the leader card in the hand
     * @throws ActionNotAllowedException if this action isn't allowed in this moment
     * @throws GameEndedException if the game is finished
     * @throws PositionInvalidException if the position isn't 1 or 2
     * @throws NotSatisfiedRequirementsForThisLeaderCardException if the requirements to play the card aren't met
     */
    public void playLeaderCard(int pos) throws NotSatisfiedRequirementsForThisLeaderCardException, ActionNotAllowedException, GameEndedException, PositionInvalidException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.PLAY_LEADER_CARD1 &&
            currentPlayerState != InWhichStatePlayer.PLAY_LEADER_CARD2){
            throw new ActionNotAllowedException();
        }
        currentPlayer.playLeaderCard(pos);
        if(currentPlayerState == InWhichStatePlayer.PLAY_LEADER_CARD1){
            currentPlayerState = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1;
        } else if(currentPlayerState == InWhichStatePlayer.PLAY_LEADER_CARD2){
            currentPlayerState = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2;
        }
    }

    /**
     * This method advances the turn if the player choose to do a normal action
     * @throws ActionNotAllowedException if this action isn't allowed in this moment
     * @param selectedNormalAction selected normal action
     */
    public void selectNormalAction(NormalAction selectedNormalAction) throws ActionNotAllowedException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.SELECT_NORMAL_ACTION){
            throw new ActionNotAllowedException();
        }
        switch (selectedNormalAction){
            case TAKE_RESOURCES_FROM_THE_MARKET:
                currentPlayerState = InWhichStatePlayer.TAKE_RESOURCES_FROM_THE_MARKET;
                break;
            case BUY_DEVELOPMENT_CARD:
                if(currentPlayer.canYouBuyADevelopmentCard()){
                    currentPlayerState = InWhichStatePlayer.BUY_DEVELOPMENT_CARD;
                    break;
                } else {
                    throw new ActionNotAllowedException();
                }
            case ACTIVATE_PRODUCTION:
                if(currentPlayer.canYouActivateAPowerProduction()){
                    currentPlayerState = InWhichStatePlayer.ACTIVATE_PRODUCTION;
                    break;
                }
                else{
                    throw new ActionNotAllowedException();
                }
            default: break;
        }
    }

    /**
     * This method lets the player take resources from the market
     * @param rowColumn: indicates a row or a column
     * @param pos: indicates the number of the row or column
     * @throws ActionNotAllowedException if this action isn't allowed in this moment
     * @throws PositionInvalidException if the selected row or column is invalid
     * @throws NullEnumException if there is a null pointer instead of a row or column
     * @throws NoWhiteMarbleLeaderCardException if an unexpectedly it fails to obtain the WhiteMarbleLeaderCard
     */
    public void takeResourcesFromTheMarket(RowColumn rowColumn, int pos) throws ActionNotAllowedException, PositionInvalidException, NullEnumException, NoWhiteMarbleLeaderCardException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.TAKE_RESOURCES_FROM_THE_MARKET){
            throw new ActionNotAllowedException();
        }
        if(resourcesFromTheMarketTaken == true){
            throw new ActionNotAllowedException();
        }
        currentPlayer.takeResourcesFromTheMarket(rowColumn, pos);
        resourcesFromTheMarketTaken = true;
        if(!currentPlayer.resourceToAdd()){
            currentPlayerState = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2;
        }
    }

    /**
     * This method lets the player deposit or discard a resource
     * @param where: indicates a warehouse, a leader card or to discard
     * @param pos: indicates the number of the slot in the storage
     * @throws ActionNotAllowedException if this action isn't allowed in this moment
     * @throws NoResourceToAddException if all the marbles are been placed
     * @throws DifferentStorageException if you select an ExtraStorageLeaderCard of a wrong type
     * @throws OccupiedSlotExtraStorageLeaderCardException if you select an ExtraStorageLeaderCard slot already occupied
     * @throws PositionAlreadyOccupiedException if you select a WarehouseDepots slot already occupied
     * @throws ResourceAlreadyPlacedException if you place in the WarehouseDepots a resource already present on another shelf
     * @throws DifferentResourceInThisShelfException if you place in the WarehouseDepots a different resource from the one already present in the shelf
     * @throws UnexpectedWhiteMarbleException if there is an unexpected white marble
     * @throws UnexpectedFaithMarbleException if there is an unexpected faith marble
     * @throws IndexOutOfWarehouseDepotsException if you are out of bounds of the WarehouseDepots
     */
    public void addResource(LeaderWarehouse where, int pos) throws NoResourceToAddException, DifferentStorageException, OccupiedSlotExtraStorageLeaderCardException, PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException, UnexpectedWhiteMarbleException, UnexpectedFaithMarbleException, ActionNotAllowedException, IndexOutOfWarehouseDepotsException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.TAKE_RESOURCES_FROM_THE_MARKET){
            throw new ActionNotAllowedException();
        }
        currentPlayer.addResource(where, pos);
        if(!currentPlayer.resourceToAdd()){
            currentPlayerState = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2;
        }
    }



    /**
     * This method adds resources for the current player
     * @param where position
     * @throws NoResourceToAddException no resources  to add
     * @throws DifferentStorageException different storage type
     * @throws OccupiedSlotExtraStorageLeaderCardException occupied slot
     * @throws PositionAlreadyOccupiedException if the position isn't 1 or 2
     * @throws ResourceAlreadyPlacedException resource already placed
     * @throws DifferentResourceInThisShelfException different resource already placed here
     * @throws UnexpectedWhiteMarbleException unexpected white marble
     * @throws UnexpectedFaithMarbleException unexpected faith marble
     * @throws ActionNotAllowedException if this action isn't allowed in this momentn
     */
    public void addResource(LeaderWarehouse where) throws NoResourceToAddException, DifferentStorageException, OccupiedSlotExtraStorageLeaderCardException, PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException, UnexpectedWhiteMarbleException, UnexpectedFaithMarbleException, ActionNotAllowedException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.TAKE_RESOURCES_FROM_THE_MARKET){
            throw new ActionNotAllowedException();
        }
        currentPlayer.addResource(where);
        if(!currentPlayer.resourceToAdd()){
            currentPlayerState = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2;
        }
    }

    /**
     * This method lets the player change a white marble with another resource
     * @param pos: the position of a WhiteMarbleLeaderCard on the table
     * @throws ActionNotAllowedException if this action isn't allowed in this moment
     * @throws NoWhiteMarbleLeaderCardException if in the selected position there's not a WhiteMarbleLeaderCard
     * @throws NoWhiteMarbleException if the current marble is not white
     * @throws PositionInvalidException if the position isn't 1 or 2
     */
    public void changeWhiteMarbleWith(int pos) throws NoWhiteMarbleLeaderCardException, NoWhiteMarbleException, ActionNotAllowedException, PositionInvalidException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.TAKE_RESOURCES_FROM_THE_MARKET){
            throw new ActionNotAllowedException();
        }
        currentPlayer.changeWhiteMarbleWith(pos);
    }

    /**
     * This method lets you buy a development card by checking first the resources you have,
     * then looking for the cards on table and then checking if you have enough resources to buy.
     * @param xx rows from 1 to 4
     * @param yy columns from 1 to 3
     * @throws PositionInvalidException if rows coordinate is lower than 1 and column coordination is greater than 3
     * @throws NotAbleToBuyThisDevelopmentCardException when you don't have enough resources to buy a development card
     * @throws NotAbleToPlaceThisDevelopmentCardException when you dont have space in your storage after buying it
     * @throws DrawnFromEmptyDeckException if the deck selected is empty
     * @throws SelectedADevelopmentCardYetException if you have yet selected a DevelopmentCard
     * @throws ActionNotAllowedException if you've bought a DevelopmentCard yet
     * @throws IndexOutOfDevelopmentDeckException if you are out of bound of DevelopmentDeck
     */
    public void buyADevelopmentCard(int xx, int yy) throws ActionNotAllowedException, SelectedADevelopmentCardYetException, NotAbleToBuyThisDevelopmentCardException, DrawnFromEmptyDeckException, PositionInvalidException, NotAbleToPlaceThisDevelopmentCardException, IndexOutOfDevelopmentDeckException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(developmentCardTaken){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.BUY_DEVELOPMENT_CARD){
            throw new ActionNotAllowedException();
        }
        currentPlayer.buyADevelopmentCard(xx, yy);
        developmentCardTaken = true;
    }

    /**
    * This method lets you to take a Development card
    * First choose the development card and than pay for it.
    * @param pos: to choose the position of the DevelopmentCard on SlotDevelopmentCard
    * @throws NoDevelopmentCardToObtainException if the place you chose has no development card,
    * @throws PositionInvalidException if the position you chose does not exists
    * @throws GameEndedException if the game is finished and it's time to show final points
    * @throws ActionNotAllowedException if action is different from BUY_DEVELOPMENT_CARD
    * @throws IndexOutOfSlotDevelopmentCardsException if you are out of bound of slots for development cards
    */
    public void placeDevelopmentCard(int pos) throws NoDevelopmentCardToObtainException, PositionInvalidException, GameEndedException, ActionNotAllowedException, IndexOutOfSlotDevelopmentCardsException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.BUY_DEVELOPMENT_CARD){
            throw new ActionNotAllowedException();
        }
        currentPlayer.placeDevelopmentCard(pos);
        if(!currentPlayer.somethingToPay()){
            endPayment();
        }
    }

    /**
     * This method is to end the payment (of a production or a LeaderCard or a DevelopmentCard)
     * and it verify any state transitions
     * @throws GameEndedException if the game is finished and it's time to show final points
     */
    private void endPayment() throws GameEndedException {
        if(currentPlayer.developmentCardToObtain()){
            return;
        }
        if(currentPlayer.genericResourcesToObtain()){
            return;
        }
        if(currentPlayerState == InWhichStatePlayer.PLAY_LEADER_CARD1){
            currentPlayerState = InWhichStatePlayer.SELECT_NORMAL_ACTION;
        } else if(currentPlayerState == InWhichStatePlayer.BUY_DEVELOPMENT_CARD){
            currentPlayerState = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2;
        } else if(currentPlayerState == InWhichStatePlayer.ACTIVATE_PRODUCTION){
            currentPlayerState = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2;
        } else if(currentPlayerState == InWhichStatePlayer.PLAY_LEADER_CARD2){
            if(Game.get(gameIndex).getNumberOfPlayer() == 1){
                currentPlayerState = InWhichStatePlayer.DRAW_SOLO_ACTION_TOKEN;
            } else {
                currentPlayerState = InWhichStatePlayer.FINISHING_TURN;
            }
        }
    }

    /**
     * This method let to pay a resource from the list payingResources using the StrongBox, but before checking
     * if you are in the right states
     * @param pay: to choose the type of resource from the Strongbox you are using to pay
     * @throws ActionNotAllowedException if you are in the other states
     * @throws GameEndedException if the game is finished and it's time to show final points
     * @throws WrongPaymentException if you are using a different type of resource from the resource requested
     * @throws NegativeResourceException if you are going to pay with a resource ypu dont have in the Strongbox
     * @throws NotAResourceForStrongBoxException if the resource passed is not supposed to be stored in the strong box
     * @throws NoResourceToPayException if you haven't anything to pay
     */
    public void payWithStrongBox(Resource pay) throws WrongPaymentException, NegativeResourceException, NotAResourceForStrongBoxException, NoResourceToPayException, ActionNotAllowedException, GameEndedException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.PLAY_LEADER_CARD1 &&
            currentPlayerState != InWhichStatePlayer.PLAY_LEADER_CARD2 &&
            currentPlayerState != InWhichStatePlayer.BUY_DEVELOPMENT_CARD &&
            currentPlayerState != InWhichStatePlayer.ACTIVATE_PRODUCTION){
            throw new ActionNotAllowedException();
        }
        currentPlayer.payWithStrongBox(pay);
        if(!currentPlayer.somethingToPay()){
            endPayment();
        }
    }

    /**
     * This method lets you pay a resource with a resource located in
     * the warehousedepots. When action is chosen it cheks if
     * resource are of the same type or not.It checks if the player is in the right turn
     * to pay with the resources in the ware house depots.
     * @param pos:Location of the resource to pay from warehousedepots
     * @throws ActionNotAllowedException if you are in the other states
     * @throws GameEndedException if the game is finished and it's time to show final points
     * @throws NoResourceToPayException if you haven't anything to pay
     * @throws WrongPaymentException if the resources to pay are different
     * @throws EmptySlotYetException if the selected slot is empty
     */
    public void payWithWarehouseDepots(int pos) throws WrongPaymentException, EmptySlotYetException, NoResourceToPayException, ActionNotAllowedException, GameEndedException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.PLAY_LEADER_CARD1 &&
                currentPlayerState != InWhichStatePlayer.PLAY_LEADER_CARD2 &&
                currentPlayerState != InWhichStatePlayer.BUY_DEVELOPMENT_CARD &&
                currentPlayerState != InWhichStatePlayer.ACTIVATE_PRODUCTION){
            throw new ActionNotAllowedException();
        }
        currentPlayer.payWithWarehouseDepots(pos);
        if(!currentPlayer.somethingToPay()){
            endPayment();
        }
    }

    /**
     * This method lets you pay with a Leader Card positioned in your table, by checking
     * firstly if you any resources, secondly if the position player chose really exists,
     * thirdly if card chosen is really a leader card, and the last if
     * the cards are the same type. Always controlling first if the player is in the right
     * turn to do che paying process.
     * @param pos: position from 1 to 2 to chose on your table
     * @throws ActionNotAllowedException if you are in the other states
     * @throws GameEndedException if the game is finished and it's time to show final points
     * @throws NotAnExtraStorageLeaderCardException if card chosen is not an Extra Strorage Leader Card
     * @throws WrongPaymentException if cards choese are not the same type of extra storage leader card
     * @throws NoResourceToPayException if you don't have any resources left.
     * @throws PositionInvalidException if the position is lower than 1 e greater than 2
     * @throws EmptySlotExtraStorageLeaderCardException if the operation of removing a resource from an ExtraStorageLeaderCard fails
     */
    public void payWithExtraStorageLeaderCard(int pos) throws NotAnExtraStorageLeaderCardException, WrongPaymentException, EmptySlotExtraStorageLeaderCardException, NoResourceToPayException, ActionNotAllowedException, GameEndedException, PositionInvalidException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.PLAY_LEADER_CARD1 &&
                currentPlayerState != InWhichStatePlayer.PLAY_LEADER_CARD2 &&
                currentPlayerState != InWhichStatePlayer.BUY_DEVELOPMENT_CARD &&
                currentPlayerState != InWhichStatePlayer.ACTIVATE_PRODUCTION){
            throw new ActionNotAllowedException();
        }
        currentPlayer.payWithExtraStorageLeaderCard(pos);
        if(!currentPlayer.somethingToPay()){
            endPayment();
        }
    }

    /**
     * This method let you select or deselect a production power from a DevelopmentCard
     * and by checking the turns of the player if he is allwed to do this method or not
     * @param pos: to choose the position of the DevelopmentCard
     * @throws ActionNotAllowedException if you are in the other states
     * @throws PositionInvalidException if the position isn't 1, 2 or 3
     * @throws NoDevelopmentCardInThisPositionException if there is no DevelopmentCard in the selected position
     */
    public void selectProductionDevelopmentCard(int pos) throws ActionNotAllowedException, PositionInvalidException, NoDevelopmentCardInThisPositionException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.ACTIVATE_PRODUCTION){
            throw new ActionNotAllowedException();
        }
        currentPlayer.selectProductionDevelopmentCard(pos);
    }

    /**
     * This method let you select or deselect a production power from a ProductionPowerLeaderCard
     * and by checking the turns of the player if he is allwed to do this method or not
     * @param pos: to choose the position of the ProductionPowerLeaderCard
     * @throws ActionNotAllowedException if you are in the other states
     * @throws PositionInvalidException if the position isn't 1 or 2
     * @throws NoProductionLeaderCardException if you haven't selected a ProductionPowerLeaderCard
     */
    public void selectProductionPowerLeaderCard(int pos) throws NoProductionLeaderCardException, ActionNotAllowedException, PositionInvalidException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.ACTIVATE_PRODUCTION){
            throw new ActionNotAllowedException();
        }
        currentPlayer.selectProductionPowerLeaderCard(pos);
    }

    /**
     * This method let you select or deselect the default production power
     * and by checking the turns of the player if he is allowed to do this method or not
     * @throws ActionNotAllowedException if you are in the other states
     */
    public void selectDefaultProductionPower() throws ActionNotAllowedException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.ACTIVATE_PRODUCTION){
            throw new ActionNotAllowedException();
        }
        currentPlayer.selectDefaultProductionPower();
    }

    /**
     * This method lets you to choose a specific power production
     * at your choice
     * @param resource it takes a resource ball with a generic power
     * @throws NoGenericResourceToObtainException there ane not any generic resource to obtain
     * @throws GameEndedException if the game is finished and it's time to show final points
     * @throws NotAResourceForStrongBoxException if the resource in input isn't nor COIN, STONE, SERVANT or SHIELD
     * @throws ActionNotAllowedException if you are in the other states
     */
    public void obtainGenericResource(Resource resource) throws NoGenericResourceToObtainException, NotAResourceForStrongBoxException, GameEndedException, ActionNotAllowedException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        currentPlayer.obtainGenericResource(resource);
        if(!currentPlayer.somethingToPay()){
            endPayment();
        }
    }

    /**
     * This method let you start the payment of the selected production power
     * @throws NotEnoughResourcesException if you aren't able to pay all production power
     * @throws YouHaveNotSelectedAnyProductionException if you haven't selected any production power
     * @throws ActionNotAllowedException if you are in the other states
     * @throws GameEndedException if the game is finished and it's time to show final points
     */
    public void startPayment() throws NotEnoughResourcesException, YouHaveNotSelectedAnyProductionException, ActionNotAllowedException, GameEndedException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.ACTIVATE_PRODUCTION){
            throw new ActionNotAllowedException();
        }
        if(startedPayment == true){
            throw new ActionNotAllowedException();
        }
        currentPlayer.startPayment();
        startedPayment = true;
        if(!currentPlayer.somethingToPay()){
            endPayment();
        }
    }

    /**
     * this method is to draw a SoloActionToken and to execute its effect
     * @throws ActionNotAllowedException if you are in the other states
     * @return currentPlayer.drawSoloActionToken() returns the solo action token
     */
    public ActionToken drawSoloActionToken() throws ActionNotAllowedException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.DRAW_SOLO_ACTION_TOKEN){
            throw new ActionNotAllowedException();
        }
        currentPlayerState = InWhichStatePlayer.FINISHING_TURN;
        return currentPlayer.drawSoloActionToken();
    }

    /**
     * This method let player to select a slot of the WarehouseDepots
     * useful for moveResourcesInWarehouseDepots, moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard and moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard
     * the selected slot is saved in var selectedWarehouseDepotsSlot of type int
     * @param pos: to choose the position of the slot of the WarehouseDepots
     * @throws PositionInvalidException if the selected position is invalid
     * @throws ActionNotAllowedException if you are in the other states
     */
    public void selectAWarehouseDepotsSlot(int pos) throws PositionInvalidException, ActionNotAllowedException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        currentPlayer.selectAWarehouseDepotsSlot(pos);
    }

    /**
     * This method let player to move up to two resources in the WarehouseDepots
     * the first position was selected using selectAWarehouseDepotsSlot and saved in var selectedWarehouseDepotsSlot of type int
     * @param pos2: to choose the second position of the slot of the WarehouseDepots
     * @throws NotAdmittedMovementException if the movement is invalid
     * @throws IndexOutOfWarehouseDepotsException if you are out of bounds of the WarehouseDepots
     * @throws ActionNotAllowedException if you are in the other states
     */
    public void moveResourcesInWarehouseDepots(int pos2) throws NotAdmittedMovementException, IndexOutOfWarehouseDepotsException, ActionNotAllowedException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        currentPlayer.moveResourcesInWarehouseDepots(pos2);
    }

    /**
     * This method let player to move a resource from the warehouseDepots to an ExtraStorageLeaderCard
     * the first position of the start slot of the warehouseDepots was selected using selectAWarehouseDepotsSlot and saved in var selectedWarehouseDepotsSlot of type int
     * @param pos: to choose the number 1 or 2 to determinate the position of the ExtraStorageLeaderCard as destination
     * @throws PositionInvalidException if the position of the ExtraStorageLeaderCard isn't 1 or 2
     * @throws NotAnExtraStorageLeaderCardException if the selected LeaderCard isn't an ExtraStorageLeaderCard
     * @throws EmptySlotYetException if the slot of the warehouseDepots was yet empty
     * @throws OccupiedSlotExtraStorageLeaderCardException if all slot of the ExtraStorageLeaderCard are yet occupied
     * @throws DifferentStorageException if the selected resource from warehouseDepots doesn't fit with the admitted resource of the selected ExtraStorageLeaderCard
     * @throws ActionNotAllowedException if you are in the other states
     */
    public void moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard(int pos) throws PositionInvalidException, NotAnExtraStorageLeaderCardException, EmptySlotYetException, OccupiedSlotExtraStorageLeaderCardException, DifferentStorageException, ActionNotAllowedException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        currentPlayer.moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard(pos);
    }

    /**
     * This method let player to move a resource from an ExtraStorageLeaderCard to the selected slot of the warehouseDepots
     * the arrive position of the slot of the warehouseDepots was selected using selectAWarehouseDepotsSlot and saved in var selectedWarehouseDepotsSlot of type int
     * @param pos: to choose the number 1 or 2 to determinate the position of the ExtraStorageLeaderCard as start
     * @throws PositionInvalidException if the position of the ExtraStorageLeaderCard isn't 1 or 2
     * @throws NotAnExtraStorageLeaderCardException if the selected LeaderCard isn't an ExtraStorageLeaderCard
     * @throws PositionAlreadyOccupiedException if the slot of the warehouseDepots was yet occupied
     * @throws ResourceAlreadyPlacedException if the resource passed to the warehouseDepots is yet present on a different shelf
     * @throws DifferentResourceInThisShelfException if there are different resources types already placed in the chosen shelf
     * @throws EmptySlotExtraStorageLeaderCardException if the selected ExtraStorageLeaderCard is yet empty
     * @throws IndexOutOfWarehouseDepotsException if you are out of bounds of the WarehouseDepots
     * @throws ActionNotAllowedException if you are in the other states
     */
    public void moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard(int pos) throws PositionInvalidException, NotAnExtraStorageLeaderCardException, PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException, EmptySlotExtraStorageLeaderCardException, IndexOutOfWarehouseDepotsException, ActionNotAllowedException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        currentPlayer.moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard(pos);
    }

    /**
     * This method is to mark the game as ended
     */
    public void endGame(){
        gameEnded = true;
    }

    /**
     * This method returns to true or false if game has ended
     * @return true or false if game has ended
     */
    public boolean getGameEnded(){
        return gameEnded;
    }

    /**
     * This method sets the command of ending the turn of a player
     * @throws ActionNotAllowedException action not allowed if you in another state of the turn
     * @throws GameEndedException game ended exception
     */
    public void endTurnCommand() throws ActionNotAllowedException, GameEndedException {
        if(currentPlayer.isHimFirstTurn()){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState != InWhichStatePlayer.FINISHING_TURN){
            throw new ActionNotAllowedException();
        }
        endTurn();
    }

    /**
     * This method signalize end of a player turn, by ending an action leader and restarting to
     * a new chose action leader. At the end of the methods it checks por the next player
     * @throws GameEndedException if the game is finished and it's time to show final points
     */
    public void endTurn() throws GameEndedException {
        actionLeaderDone1 = false;
        currentPlayerState = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1;
        developmentCardTaken = false;
        resourcesFromTheMarketTaken = false;
        startedPayment = false;
        boolean found = false;
        int n = 0;
        for(Player i : Game.get(gameIndex).getPlayers()){
            if(found){
                currentPlayer = i;
                finalPartOfEndTurn(n);
                return;
            }
            if(i == currentPlayer){
                found = true;
            }
            n++;
        }
        currentPlayer = Game.get(gameIndex).getPlayers().get(0);
        finalPartOfEndTurn(0);
    }

    /**
     * This is the final part, always executed, of method endTurn
     * @param n is to find the player in the list
     * @throws GameEndedException if the game is finished and it's time to show final points
     */
    private void finalPartOfEndTurn(int n) throws GameEndedException {
        if(currentPlayer.getDisconnected()){
            endTurn();
            return;
        }

        for(PrintWriter pw : Game.get(gameIndex).getPrintWriterList()){
            if(pw != null){
                pw.println(new CurrentPlayerMessage(currentPlayer.getNickname()));
            }
        }
        PrintWriter itSYourTurn = Game.get(gameIndex).getPrintWriterList().get(n);
        if(itSYourTurn != null){
            itSYourTurn.println("It's your turn!");
        }
        if(currentPlayer.isInkwell() && gameEnded){
            Game.get(gameIndex).notifyScore();
            for(PrintWriter ii : Game.get(gameIndex).getPrintWriterList()){
                ii.println("GAME_ENDED");
            }
            System.out.println("Game " + gameIndex + " ended!");
            //throw new GameEndedException(); //commented
        }
        if(!(currentPlayer.isHimFirstTurn()) && !transitionFromFirstTurnToNormalTurnsDone){
            transitionFromFirstTurnToNormalTurnsDone = true;
            for(PrintWriter pw : Game.get(gameIndex).getPrintWriterList()){
                if(pw != null){
                    pw.println(new TransitionForwardMessage());
                }
            }
        }
    }

    /**
     * Getter of the parameter currentPlayer
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

}
