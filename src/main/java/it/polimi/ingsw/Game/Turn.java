package it.polimi.ingsw.Game;

import it.polimi.ingsw.Enums.*;
import it.polimi.ingsw.Exceptions.*;

/**
 * This Class represents the turn (both the turn of a player and the changing turn between players)
 */
public class Turn {

    private Player currentPlayer;
    private boolean actionLeaderDone = false;
    private InWhichStatePlayer currentPlayerState = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1;
    private boolean developmentCardTaken = false;
    private boolean gameEnded = false;
    private boolean viewFinalPoints = false;
    private final int gameIndex;

    /**
     * Constructor method of this class
     * @param gameIndex: the index of the Game
     */
    public Turn(int gameIndex){
        this.gameIndex = gameIndex;
        for(Player i : Game.get(gameIndex).getPlayers()){
            if(i.isInkwell()){
                currentPlayer = i;
            }
        }
    }

    /**
     *This method lets the player to choose about not doing an action LeaderCard
     * @throws ActionNotAllowedException if this action isn't allowed in this moment
     * @throws GameEndedException if the game is finished and it's time to show final points
     */
    public void chooseNoActionLeaderCard() throws ActionNotAllowedException, GameEndedException {
        if(currentPlayerState != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1 &&
                currentPlayerState != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1){
            currentPlayerState = InWhichStatePlayer.SELECT_NORMAL_ACTION;
        } else {
            if(Game.get(gameIndex).getPlayers().size() == 1){
                currentPlayerState = InWhichStatePlayer.DRAW_SOLO_ACTION_TOKEN;
            } else {
                endTurn();
            }
        }
    }

    /**
     *This method lets the player to go in the next step of discarding a leader card by controlling in
     * which steps he is currently in and before. Taking in consideration
     * that one leader move can be done only one time before or after a normal action.
     * @throws ActionNotAllowedException if other steps are different from
     * CHOSE_ACTION_LEADER_OR_NOT1 and CHOSE_ACTION_LEADER_OR_NOT2 and if a leader action
     * is done before can cannot do it for a second time
     * @throws NoLeaderCardToDiscardException if you haven't any LeaderCard to discard
     */
    public void chooseDiscardLeaderCard() throws NoLeaderCardToDiscardException, ActionNotAllowedException {
        if(currentPlayerState != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1 &&
                currentPlayerState != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2 &&
                actionLeaderDone){
            throw new ActionNotAllowedException();
        }
        if(currentPlayer.countLeaderCardInHand() == 0){
            throw new NoLeaderCardToDiscardException();
        }
        actionLeaderDone = true;
        if(currentPlayerState == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1) {
            currentPlayerState = InWhichStatePlayer.DISCARD_LEADER_CARD1;
        } else if(currentPlayerState == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2) {
            currentPlayerState = InWhichStatePlayer.DISCARD_LEADER_CARD2;
        }
    }

    /**
     *This method lets the player to go in the next step of choosing a leader card to play by controlling in
     * which steps he is currently in and before. Taking in consideration
     * that one leader move can be done only one time before or after a normal action.
     * @throws ActionNotAllowedException if other steps are different from
     * CHOSE_ACTION_LEADER_OR_NOT1 and CHOSE_ACTION_LEADER_OR_NOT2 and if a leader action
     * is done before cannot do it for a second time
     * @throws NoLeaderCardToPlayException if you haven't any LeaderCard to play
     */
    public void choosePlayLeaderCard() throws ActionNotAllowedException, NoLeaderCardToPlayException {
        if(currentPlayerState != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1 &&
                currentPlayerState != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2){
            throw new ActionNotAllowedException();
        }
        if(currentPlayerState == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2 &&
                actionLeaderDone){
            throw new ActionNotAllowedException();
        }
        if(!currentPlayer.canYouPlayAtLeastALeaderCard()){
            throw new NoLeaderCardToPlayException();
        }
        actionLeaderDone = true;
        if(currentPlayerState == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1) {
            currentPlayerState = InWhichStatePlayer.PLAY_LEADER_CARD1;
        } else if(currentPlayerState == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2) {
            currentPlayerState = InWhichStatePlayer.PLAY_LEADER_CARD2;
        }
    }

    /**
     *This method lets the player to discard one leader card from his hand.Also keeping
     * track of hic actions, by controlling if he has done the action leader before or after a normal move.
     * If the game is played by one person
     * he will have the ability to choose an action token.
     * @param pos: number 1 or 2 to determinate the position of the leader card in hand to discard
     * @throws ActionNotAllowedException if other steps are different from
     * CHOSE_ACTION_LEADER_OR_NOT1 and CHOSE_ACTION_LEADER_OR_NOT2
     * @throws GameEndedException if the game is finished and it's time to show final points
     * @throws PositionInvalidException if the position isn't 1 or 2
     * @throws AlreadyDiscardedThisLeaderCardException if you try to discard a card again
     */
    public void discardLeaderCard(int pos) throws AlreadyDiscardedThisLeaderCardException, ActionNotAllowedException, GameEndedException, PositionInvalidException {
        if(currentPlayerState != InWhichStatePlayer.DISCARD_LEADER_CARD1 &&
                currentPlayerState != InWhichStatePlayer.DISCARD_LEADER_CARD2){
            throw new ActionNotAllowedException();
        }
        currentPlayer.discardLeaderCard(pos);
        if(currentPlayerState == InWhichStatePlayer.DISCARD_LEADER_CARD1){
            currentPlayerState = InWhichStatePlayer.SELECT_NORMAL_ACTION;
        } else if(currentPlayerState == InWhichStatePlayer.DISCARD_LEADER_CARD2){
            if(Game.get(gameIndex).getPlayers().size() == 1){
                currentPlayerState = InWhichStatePlayer.DRAW_SOLO_ACTION_TOKEN;
            } else {
                endTurn();
            }
        }
    }

    /**
     * This method let the player to put on the table a leader card that was in his hand
     * in case the player select a StorageLeaderCard, the list payingResources is updated with the resources the player has to pay
     * If the game is played by one person
     * he will have the ability to choose an action token.
     * @param pos: number 1 or 2 to determinate the position of the leader card in hand to play
     * @throws ActionNotAllowedException if other steps are different from
     * PLAY_LEADER_CARD1 AND PLAY_LEADER_CARD2
     * @throws GameEndedException if the game is finished and it's time to show final points
     * @throws PositionInvalidException if the position isn't 1 or 2
     * @throws NotSatisfiedRequirementsForThisLeaderCardException if the player isn't able to play the card
     */
    public void playLeaderCard(int pos) throws NotSatisfiedRequirementsForThisLeaderCardException, ActionNotAllowedException, GameEndedException, PositionInvalidException {
        if(currentPlayerState != InWhichStatePlayer.PLAY_LEADER_CARD1 &&
            currentPlayerState != InWhichStatePlayer.PLAY_LEADER_CARD2){
            throw new ActionNotAllowedException();
        }
        currentPlayer.playLeaderCard(pos);
        if(!currentPlayer.somethingToPay()){
            if(currentPlayerState == InWhichStatePlayer.DISCARD_LEADER_CARD1){
                currentPlayerState = InWhichStatePlayer.SELECT_NORMAL_ACTION;
            } else if(currentPlayerState == InWhichStatePlayer.DISCARD_LEADER_CARD2){
                if(Game.get(gameIndex).getPlayers().size() == 1){
                    currentPlayerState = InWhichStatePlayer.DRAW_SOLO_ACTION_TOKEN;
                } else {
                    endTurn();
                }
            }
        }
    }

    /**
     * This method let the player to select a normal action which are
     * take resource from the market, buy development card and controlling if he can really
     * afford to buy one, activate production by controlling if he can can activate a power
     * production
     * @throws ActionNotAllowedException if other steps are different from
     * SELECT_NORMAL_ACTION, AND BY CONTROLLING THE POSSIBILITIES IF THE PLAYER CAN AFFORD A MOVE
     */
    public void selectNormalAction(NormalAction selectedNormalAction) throws ActionNotAllowedException {
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
     * This method let player to take resources from the market
     * Taken resources are added to the list marblesFromTheMarket
     * Faith marble are removed advancing on the faith track
     * White marble could be removed, substituted or could remain
     * @param rowColumn: to choose a row or a column of the market
     * @param pos: to choose the position of the row or of the column
     * @throws ActionNotAllowedException if action is different from
     * TAKE_RESOURCES_FROM_THE_MARKET
     * @throws PositionInvalidException if the selected row or column is invalid
     * @throws NullEnumException if you pass a null pointer instead of selecting row or column
     */
    public void takeResourcesFromTheMarket(RowColumn rowColumn, int pos) throws ActionNotAllowedException, PositionInvalidException, NullEnumException {
        if(currentPlayerState != InWhichStatePlayer.TAKE_RESOURCES_FROM_THE_MARKET){
            throw new ActionNotAllowedException();
        }
        currentPlayer.takeResourcesFromTheMarket(rowColumn, pos);
        if(!currentPlayer.resourceToAdd()){
            currentPlayerState = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2;
        }
    }

    /**
     * This method let player to add a resource, witch he took from the market, to the WarehouseDepots or into
     * an ExtraStorageLeaderCard or even to discard it letting other players to advance in them faith track
     * @param pos: to choose the position of the WarehouseDepots or of the LeaderCard in hand; it's unuseful in case of discarding the resource
     * @throws ActionNotAllowedException if action is different from
     * TAKE_RESOURCES_FROM_THE_MARKET
     * @throws NoResourceToAddException if the list marblesFromTheMarket, in witch there are all the resources to add, is empty
     * @throws DifferentStorageException if you select an ExtraStorageLeaderCard of another type of the resource to add
     * @throws OccupiedSlotExtraStorageLeaderCardException if you select an ExtraStorageLeaderCard yet occupied
     * @throws PositionAlreadyOccupiedException if you select a position yet occupied of the WarehouseDepots
     * @throws ResourceAlreadyPlacedException if you place in WarehouseDepots a type of resource you yet placed in another shielf
     * @throws DifferentResourceInThisShelfException if you place in WarehouseDepots the resource in a shelf where there is yet another resource of another type
     * @throws UnexpectedWhiteMarbleException if it's unexpectly found you are going to place a white marble
     * @throws UnexpectedFaithMarbleException if it's unexpectly found you are going to place a faith marble
     */
    public void addResource(LeaderWarehouse where, int pos) throws NoResourceToAddException, DifferentStorageException, OccupiedSlotExtraStorageLeaderCardException, PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException, UnexpectedWhiteMarbleException, UnexpectedFaithMarbleException, ActionNotAllowedException {
        if(currentPlayerState != InWhichStatePlayer.TAKE_RESOURCES_FROM_THE_MARKET){
            throw new ActionNotAllowedException();
        }
        currentPlayer.addResource(where, pos);
        if(!currentPlayer.resourceToAdd()){
            currentPlayerState = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2;
        }
    }

    /**
     * This method is to be called when the player is going to add a white marble:
     * this marble is changed with another marble using a WhiteMarbleLeaderCard
     * @param pos: to choose the position of the WhiteMarbleLeaderCard on the table
     * @throws ActionNotAllowedException if action is different from
     * TAKE_RESOURCES_FROM_THE_MARKET
     * @throws NoWhiteMarbleLeaderCardException if the selected position doesn't contain a LeaderCard
     * @throws NoWhiteMarbleException if you are going to change a Marble witch is not White
     * @throws PositionInvalidException if the position isn't 1 or 2
     */
    public void changeWhiteMarbleWith(int pos) throws NoWhiteMarbleLeaderCardException, NoWhiteMarbleException, ActionNotAllowedException, PositionInvalidException {
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
     */
    public void buyADevelopmentCard(int xx, int yy) throws ActionNotAllowedException, SelectedADevelopmentCardYetException, NotAbleToBuyThisDevelopmentCardException, DrawnFromEmptyDeckException, PositionInvalidException, NotAbleToPlaceThisDevelopmentCardException {
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
    */
    public void placeDevelopmentCard(int pos) throws NoDevelopmentCardToObtainException, PositionInvalidException, GameEndedException, ActionNotAllowedException {
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
            if(Game.get(gameIndex).getPlayers().size() == 1){
                currentPlayerState = InWhichStatePlayer.DRAW_SOLO_ACTION_TOKEN;
            } else {
                endTurn();
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
     * @param pos:Location of the resource to pay from werehousedepots
     * @throws ActionNotAllowedException if you are in the other states
     * @throws GameEndedException if the game is finished and it's time to show final points
     * @throws NoResourceToPayException if you haven't anything to pay
     * @throws WrongPaymentException if the resources to pay are different
     * @throws EmptySlotYetException if the selected slot is empty
     */
    public void payWithWarehouseDepots(int pos) throws WrongPaymentException, EmptySlotYetException, NoResourceToPayException, ActionNotAllowedException, GameEndedException {
        if(currentPlayerState != InWhichStatePlayer.PLAY_LEADER_CARD1 &&
                currentPlayerState != InWhichStatePlayer.PLAY_LEADER_CARD2 &&
                currentPlayerState != InWhichStatePlayer.BUY_DEVELOPMENT_CARD){
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
        if(currentPlayerState != InWhichStatePlayer.PLAY_LEADER_CARD1 &&
                currentPlayerState != InWhichStatePlayer.PLAY_LEADER_CARD2 &&
                currentPlayerState != InWhichStatePlayer.BUY_DEVELOPMENT_CARD){
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
     */
    public void obtainGenericResource(Resource resource) throws NoGenericResourceToObtainException, NotAResourceForStrongBoxException, GameEndedException {
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
        if(currentPlayerState != InWhichStatePlayer.ACTIVATE_PRODUCTION){
            throw new ActionNotAllowedException();
        }
        if(!currentPlayer.somethingToPay()){
            endPayment();
        }
    }

    /**
     * this method is to draw a SoloActionToken and to execute its effect
     * @throws ActionNotAllowedException if you are in the other states
     */
    public void drawSoloActionToken() throws ActionNotAllowedException {
        if(currentPlayerState != InWhichStatePlayer.DRAW_SOLO_ACTION_TOKEN){
            throw new ActionNotAllowedException();
        }
        currentPlayer.drawSoloActionToken();
    }

    /**
     * This method let player to select a slot of the WarehouseDepots
     * useful for moveResourcesInWarehouseDepots, moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard and moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard
     * the selected slot is saved in var selectedWarehouseDepotsSlot of type int
     * @param pos: to choose the position of the slot of the WarehouseDepots
     * @throws PositionInvalidException if the selected position is invalid
     */
    public void selectAWarehouseDepotsSlot(int pos) throws PositionInvalidException{
        currentPlayer.selectAWarehouseDepotsSlot(pos);
    }

    /**
     * This method let player to move up to two resources in the WarehouseDepots
     * the first position was selected using selectAWarehouseDepotsSlot and saved in var selectedWarehouseDepotsSlot of type int
     * @param pos2: to choose the second position of the slot of the WarehouseDepots
     * @throws NotAdmittedMovementException if the movement is invalid
     */
    public void moveResourcesInWarehouseDepots(int pos2) throws NotAdmittedMovementException{
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
     */
    public void moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard(int pos) throws PositionInvalidException, NotAnExtraStorageLeaderCardException, EmptySlotYetException, OccupiedSlotExtraStorageLeaderCardException, DifferentStorageException{
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
     */
    public void moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard(int pos) throws PositionInvalidException, NotAnExtraStorageLeaderCardException, PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException, EmptySlotExtraStorageLeaderCardException{
        currentPlayer.moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard(pos);
    }

    /**
     * This method is to mark the game as ended
     */
    public void endGame(){
        gameEnded = true;
    }

    /**
     * This method signalize end of a player turn, by ending an action leader and restarting to
     * a new chose action leader. At the end of the methods it cheks por the next player
     * @throws GameEndedException if the game is finished and it's time to show final points
     */
    private void endTurn() throws GameEndedException {
        actionLeaderDone = false;
        currentPlayerState = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1;
        developmentCardTaken = false;
        boolean found = false;
        for(Player i : Game.get(gameIndex).getPlayers()){
            if(found){
                currentPlayer = i;
                return;
            }
            if(i == currentPlayer){
                found = true;
            }
        }
        currentPlayer = Game.get(gameIndex).getPlayers().get(0);
        if(currentPlayer.isInkwell() && gameEnded){
            viewFinalPoints = true;
            throw new GameEndedException();
        }
    }

    /**
     * Getter of the parameter viewFinalPoints
     * @return if it's time to view FinalPoints
     */
    public boolean getViewFinalPoints(){
        return viewFinalPoints;
    }

    /**
     * Getter of the parameter currentPlayer
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

}
