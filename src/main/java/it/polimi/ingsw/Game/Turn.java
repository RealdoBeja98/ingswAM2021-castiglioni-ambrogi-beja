package it.polimi.ingsw.Game;

import it.polimi.ingsw.Enums.*;
import it.polimi.ingsw.Exceptions.*;

public class Turn {
    private Player currentPlayer;
    private boolean actionLeaderDone = false;
    private InWhichStatePlayer inWhichStatePlayer = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1;

    public Turn(){
        for(Player i : Game.getInstance().getPlayers()){
            if(i.isInkwell()){
                currentPlayer = i;
            }
        }
    }

    /**
     *This method lets the player to go in the next step of discarding a leader card by controlling in
     * which steps he is currently in and before. Taking in consideration
     * that one leader move can be done only one time before or after a normal action.
     *
     * @throws ActionNotAllowedException if other steps are different from
     * CHOSE_ACTION_LEADER_OR_NOT1 and CHOSE_ACTION_LEADER_OR_NOT2 and if a leader action
     * is done before can cannot do it for a second time
     */
    public void chooseDiscardLeaderCard() throws NoLeaderCardToDiscardException, ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1 &&
                inWhichStatePlayer != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2){
            throw new ActionNotAllowedException();
        }
        if(inWhichStatePlayer == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2 &&
                actionLeaderDone == true){
            throw new ActionNotAllowedException();
        }
        if(currentPlayer.countLeaderCardInHand() == 0){
            throw new NoLeaderCardToDiscardException();
        }
        actionLeaderDone = true;
        if(inWhichStatePlayer == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1) {
            inWhichStatePlayer = InWhichStatePlayer.DISCARD_LEADER_CARD1;
        }
        if(inWhichStatePlayer == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2) {
            inWhichStatePlayer = InWhichStatePlayer.DISCARD_LEADER_CARD2;
        }
    }

    /**
     *This method lets the player to go in the next step of choosing a leader card to play by controlling in
     * which steps he is currently in and before. Taking in consideration
     * that one leader move can be done only one time before or after a normal action.
     *
     * @throws ActionNotAllowedException if other steps are different from
     * CHOSE_ACTION_LEADER_OR_NOT1 and CHOSE_ACTION_LEADER_OR_NOT2 and if a leader action
     *is done before cannot do it for a second time
     */
    public void choosePlayLeaderCard() throws ActionNotAllowedException, NoLeaderCardToPlayException {
        if(inWhichStatePlayer != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1 &&
                inWhichStatePlayer != InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2){
            throw new ActionNotAllowedException();
        }
        if(inWhichStatePlayer == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2 &&
                actionLeaderDone == true){
            throw new ActionNotAllowedException();
        }
        if(currentPlayer.canYouPlayAtLeastALeaderCard() == false){
            throw new NoLeaderCardToPlayException();
        }
        actionLeaderDone = true;
        if(inWhichStatePlayer == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1) {
            inWhichStatePlayer = InWhichStatePlayer.PLAY_LEADER_CARD1;
        }
        if(inWhichStatePlayer == InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2) {
            inWhichStatePlayer = InWhichStatePlayer.PLAY_LEADER_CARD2;
        }
    }

    /**
     *This method lets the player to discard one leader card from his hand.Also keeping
     * track of hic actions, by controlling if he has done the action leader before or after a normal move.
     * If the game is played by one person
     * he will have the ability to choose an action token.
     *
     * @param pos: number 1 or 2 to determinate the position of the leader card in hand to discard
     * @throws ActionNotAllowedException if other steps are different from
     * CHOSE_ACTION_LEADER_OR_NOT1 and CHOSE_ACTION_LEADER_OR_NOT2
     */
    public void discardLeaderCard(int pos) throws YetDiscardedThisLeaderCardException, ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.DISCARD_LEADER_CARD1 &&
                inWhichStatePlayer != InWhichStatePlayer.DISCARD_LEADER_CARD2){
            throw new ActionNotAllowedException();
        }
        currentPlayer.discardLeaderCard(pos);
        if(inWhichStatePlayer == InWhichStatePlayer.DISCARD_LEADER_CARD1){
            inWhichStatePlayer = InWhichStatePlayer.SELECT_NORMAL_ACTION;
        }
        if(inWhichStatePlayer == InWhichStatePlayer.DISCARD_LEADER_CARD2){
            if(Game.getInstance().getPlayers().size() == 1){
                inWhichStatePlayer = InWhichStatePlayer.DRAW_SOLO_ACTION_TOKEN;
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
     *
     * @param pos: number 1 or 2 to determinate the position of the leader card in hand to play
     * @throws ActionNotAllowedException if other steps are different from
     * PLAY_LEADER_CARD1 AND PLAY_LEADER_CARD2
     */

    public void playLeaderCard(int pos) throws NotSatisfiedRequirementsForThisLeaderCardException, ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.PLAY_LEADER_CARD1 &&
            inWhichStatePlayer != InWhichStatePlayer.PLAY_LEADER_CARD2){
            throw new ActionNotAllowedException();
        }
        currentPlayer.playLeaderCard(pos);
        if(currentPlayer.somethingToPay() == false){
            if(inWhichStatePlayer == InWhichStatePlayer.DISCARD_LEADER_CARD1){
                inWhichStatePlayer = InWhichStatePlayer.SELECT_NORMAL_ACTION;
            }
            if(inWhichStatePlayer == InWhichStatePlayer.DISCARD_LEADER_CARD2){
                if(Game.getInstance().getPlayers().size() == 1){
                    inWhichStatePlayer = InWhichStatePlayer.DRAW_SOLO_ACTION_TOKEN;
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
     *
     * @throws ActionNotAllowedException if other steps are different from
     * SELECT_NORMAL_ACTION, AND BY CONTROLLING THE POSSIBILITIES IF THE PLAYER CAN AFFORD A MOVE
     */
    public void selectNormalAction(NormalAction selectedNormalAction) throws ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.SELECT_NORMAL_ACTION){
            throw new ActionNotAllowedException();
        }
        switch (selectedNormalAction){
            case TAKE_RESOURCES_FROM_THE_MARKET:
                inWhichStatePlayer = InWhichStatePlayer.TAKE_RESOURCES_FROM_THE_MARKET;
            case BUY_DEVELOPMENT_CARD:
                if(currentPlayer.canYouBuyALeaderCard()){
                    inWhichStatePlayer = InWhichStatePlayer.BUY_DEVELOPMENT_CARD;
                } else {
                    throw new ActionNotAllowedException();
                }
            case ACTIVATE_PRODUCTION:
                if(currentPlayer.canYouActivateAPowerProduction()){
                    inWhichStatePlayer = InWhichStatePlayer.ACTIVATE_PRODUCTION;
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
     *
     * @param rowColumn: to choose a row or a column of the market
     * @param pos: to choose the position of the row or of the column
     * @throws ActionNotAllowedException if action is different from
     * TAKE_RESOURCES_FROM_THE_MARKET
     */
    public void takeResourcesFromTheMarket(RowColumn rowColumn, int pos) throws ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.TAKE_RESOURCES_FROM_THE_MARKET){
            throw new ActionNotAllowedException();
        }
        currentPlayer.takeResourcesFromTheMarket(rowColumn, pos);
    }

    /**
     * This method let player to add a resource, witch he took from the market, to the WarehouseDepots or into
     * an ExtraStorageLeaderCard or even to discard it letting other players to advance in them faith track
     *
     * @param pos: to choose the position of the WarehouseDepots or of the LeaderCard in hand; it's unuseful in case of discarding the resource
     * @throws ActionNotAllowedException if action is different from
     * TAKE_RESOURCES_FROM_THE_MARKET
     *
     */
    public void addResource(LeaderWarehouse where, int pos) throws NoResourceToAddException, DifferentStorageException, OccupiedSlotExtraStorageLeaderCardException, PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException, UnexpectedWhiteMarbleException, UnexpectedFaithMarbleException, ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.TAKE_RESOURCES_FROM_THE_MARKET){
            throw new ActionNotAllowedException();
        }
        currentPlayer.addResource(where, pos);
        if(currentPlayer.resourceToAdd() == false){
            inWhichStatePlayer = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2;
        }
    }

    /**
     * This method is to be called when the player is going to add a white marble:
     * this marble is changed with another marble using a WhiteMarbleLeaderCard
     *
     * @param pos: to choose the position of the WhiteMarbleLeaderCard on the table
     * @throws ActionNotAllowedException if action is different from
     * TAKE_RESOURCES_FROM_THE_MARKET
     */
    public void changeWhiteMarbleWith(int pos) throws ClassCastException, NoWhiteMarbleException, ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.TAKE_RESOURCES_FROM_THE_MARKET){
            throw new ActionNotAllowedException();
        }
        currentPlayer.changeWhiteMarbleWith(pos);
    }

/**
 * This method lets you to take a Development card
 * First choose the development card and than pay for it.
 *
 * @param pos: to choose the position of the DevelopmentCard on slotdevelopmentcard
*/
public void obtainDevelopmentCard(int pos) throws NoDevelopmentCardToObtainException, PositionInvalidException{
        currentPlayer.obtainDevelopmentCard(pos);
        if(currentPlayer.somethingToPay() == false){
            endPayment();
        }
    }

    public void endPayment(){
        if(currentPlayer.developmentCardToObtain()){
            return;
        }
        if(currentPlayer.genericResourcesToObtain()){
            return;
        }
        if(inWhichStatePlayer == InWhichStatePlayer.PLAY_LEADER_CARD1){
            inWhichStatePlayer = InWhichStatePlayer.SELECT_NORMAL_ACTION;
        }
        if(inWhichStatePlayer == InWhichStatePlayer.BUY_DEVELOPMENT_CARD){
            inWhichStatePlayer = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2;
        }
        if(inWhichStatePlayer == InWhichStatePlayer.ACTIVATE_PRODUCTION){
            inWhichStatePlayer = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT2;
        }
        if(inWhichStatePlayer == InWhichStatePlayer.PLAY_LEADER_CARD2){
            if(Game.getInstance().getPlayers().size() == 1){
                inWhichStatePlayer = InWhichStatePlayer.DRAW_SOLO_ACTION_TOKEN;
            } else {
                endTurn();
            }
        }
    }

    /**
     * This method let to pay a resource from the list payingResources using the StrongBox, but befor checking
     * if you are in the right states
     * @param pay: to choose the type of resource from the Strongbox you are using to pay
     * @throws ActionNotAllowedException if you are in the other states
     */
    public void payWithStrongBox(Resource pay) throws WrongPaymentException, NotEnoughResourcesException, NegativeResourceException, NotAResourceForStrongBoxException, NoResourceToPayException, ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.PLAY_LEADER_CARD1 &&
            inWhichStatePlayer != InWhichStatePlayer.PLAY_LEADER_CARD2 &&
            inWhichStatePlayer != InWhichStatePlayer.BUY_DEVELOPMENT_CARD &&
            inWhichStatePlayer != InWhichStatePlayer.ACTIVATE_PRODUCTION){
            throw new ActionNotAllowedException();
        }
        currentPlayer.payWithStrongBox(pay);
        if(currentPlayer.somethingToPay() == false){
            endPayment();
        }
    }

    /**
     * This method lets you pay a resource with a resource located in
     * the warehousedepots. When action is chosen it cheks if
     * resource are of the same type or not.It checks if the player is in the right turn
     * to pay with the resources in the ware house depots.
     *
     * @param pos:Location of the resource to pay from werehousedepots
     * @throws ActionNotAllowedException if you are in the other states
     */
    public void payWithWarehouseDepots(int pos) throws WrongPaymentException, YetEmptySlotException, NoResourceToPayException, ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.PLAY_LEADER_CARD1 &&
                inWhichStatePlayer != InWhichStatePlayer.PLAY_LEADER_CARD2 &&
                inWhichStatePlayer != InWhichStatePlayer.BUY_DEVELOPMENT_CARD){
            throw new ActionNotAllowedException();
        }
        currentPlayer.payWithWarehouseDepots(pos);
        if(currentPlayer.somethingToPay() == false){
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
     * @throws ActionNotAllowedException if the player is in different turn than he should be.
     */
    public void payWithExtraStorageLeaderCard(int pos) throws NotAnExtraStorageLeaderCardException, WrongPaymentException, EmptySlotExtraStorageLeaderCardException, NoResourceToPayException, ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.PLAY_LEADER_CARD1 &&
                inWhichStatePlayer != InWhichStatePlayer.PLAY_LEADER_CARD2 &&
                inWhichStatePlayer != InWhichStatePlayer.BUY_DEVELOPMENT_CARD){
            throw new ActionNotAllowedException();
        }
        currentPlayer.payWithExtraStorageLeaderCard(pos);
        if(currentPlayer.somethingToPay() == false){
            endPayment();
        }
    }

    /**
     * This method let you select or deselect a production power from a DevelopmentCard
     * and by checking the turns of the player if he is allwed to do this method or not
     * @param pos: to choose the position of the DevelopmentCard
     * @throws ActionNotAllowedException if the player is in different turn than he should be.
     */
    public void selectProductionDevelopmentCard(int pos) throws ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.ACTIVATE_PRODUCTION){
            throw new ActionNotAllowedException();
        }
        currentPlayer.selectProductionDevelopmentCard(pos);
    }

    /**
     * This method let you select or deselect a production power from a ProductionPowerLeaderCard
     * and by checking the turns of the player if he is allwed to do this method or not
     *
     * @param pos: to choose the position of the ProductionPowerLeaderCard
     * @throws ActionNotAllowedException if the player is in different turn than he should be.
     */
    public void selectProductionPowerLeaderCard(int pos) throws NoProductionLeaderCardException, ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.ACTIVATE_PRODUCTION){
            throw new ActionNotAllowedException();
        }
        currentPlayer.selectProductionPowerLeaderCard(pos);
    }

    /**
     * This method let you select or deselect the default production power
     * and by checking the turns of the player if he is allowed to do this method or not
     * @throws ActionNotAllowedException if the player is in different turn than he should be.
     */
    public void selectDefaultProductionPower() throws ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.ACTIVATE_PRODUCTION){
            throw new ActionNotAllowedException();
        }
        currentPlayer.selectDefaultProductionPower();
    }

    /**
     * This method lets you to choose a specific power production
     * at your choice
     * @param resource it takes a resource ball with a generic power
     * @throws NoGenericResourceToObtainException there ane not any generic resource to obtain
     */
    public void obtainGenericResource(Resource resource) throws NoGenericResourceToObtainException, NotAResourceForStrongBoxException{
        currentPlayer.obtainGenericResource(resource);
        if(currentPlayer.somethingToPay() == false){
            endPayment();
        }
    }

    /**
     * This method let you start the payment of the selected production power
     * @throws ActionNotAllowedException if the player is in different turn than he should be.
     */
    public void startPayment() throws NotEnoughResourcesException, YouHaveNotSelectedAnyProductionException, ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.ACTIVATE_PRODUCTION){
            throw new ActionNotAllowedException();
        }
        if(currentPlayer.somethingToPay() == false){
            endPayment();
        }
    }

    public void drawSoloActionToken() throws ActionNotAllowedException {
        if(inWhichStatePlayer != InWhichStatePlayer.DRAW_SOLO_ACTION_TOKEN){
            throw new ActionNotAllowedException();
        }
        currentPlayer.drawSoloActionToken();
    }

    /**
     * This method let player to select a slot of the WarehouseDepots
     * useful for moveResourcesInWarehouseDepots, moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard and moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard
     * the selected slot is saved in var selectedWarehouseDepotsSlot of type int
     * @param pos: to choose the position of the slot of the WarehouseDepots
     */
    public void selectAWarehouseDepotsSlot(int pos) throws PositionInvalidException{
        currentPlayer.selectAWarehouseDepotsSlot(pos);
    }

    /**
     * This method let player to move up to two resources in the WarehouseDepots
     * the first position was selected using selectAWarehouseDepotsSlot and saved in var selectedWarehouseDepotsSlot of type int
     * @param pos2: to choose the second position of the slot of the WarehouseDepots
     */
    public void moveResourcesInWarehouseDepots(int pos2) throws NotAdmittedMovementException{
        currentPlayer.moveResourcesInWarehouseDepots(pos2);
    }

    /**
     * This method let player to move a resource from the warehouseDepots to an ExtraStorageLeaderCard
     * the first position of the start slot of the warehouseDepots was selected using selectAWarehouseDepotsSlot and saved in var selectedWarehouseDepotsSlot of type int
     * @param pos: to choose the number 1 or 2 to determinate the position of the ExtraStorageLeaderCard as destination
     */
    public void moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard(int pos) throws PositionInvalidException, NotAnExtraStorageLeaderCardException, YetEmptySlotException, OccupiedSlotExtraStorageLeaderCardException, DifferentStorageException{
        currentPlayer.moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard(pos);
    }

    /**
     * This method let player to move a resource from an ExtraStorageLeaderCard to the selected slot of the warehouseDepots
     * the arrive position of the slot of the warehouseDepots was selected using selectAWarehouseDepotsSlot and saved in var selectedWarehouseDepotsSlot of type int
     * @param pos: to choose the number 1 or 2 to determinate the position of the ExtraStorageLeaderCard as start
     */
    public void moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard(int pos) throws PositionInvalidException, NotAnExtraStorageLeaderCardException, PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException, EmptySlotExtraStorageLeaderCardException{
        currentPlayer.moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard(pos);
    }

    /**
     *This method signalize end of a player turn, by ending an action leader and restarting to
     * a new chose action leader. At the end of the methods it cheks por the next player
     */
    private void endTurn(){
        actionLeaderDone = false;
        inWhichStatePlayer = InWhichStatePlayer.CHOSE_ACTION_LEADER_OR_NOT1;
        boolean found = false;
        for(Player i : Game.getInstance().getPlayers()){
            if(found == true){
                currentPlayer = i;
                return;
            }
            if(i == currentPlayer){
                found = true;
            }
        }
        currentPlayer = Game.getInstance().getPlayers().get(0);
    }
    
}
