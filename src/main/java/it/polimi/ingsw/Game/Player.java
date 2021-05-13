package it.polimi.ingsw.Game;
import it.polimi.ingsw.Enums.*;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Messages.ServiceMessages.CurrentPlayerMessage;
import it.polimi.ingsw.Messages.ServiceMessages.ShowCurrentBoardMessage;
import it.polimi.ingsw.Table.Decks.*;
import it.polimi.ingsw.PersonalBoard.PersonalBoard;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import it.polimi.ingsw.Table.Decks.Leader.*;
import it.polimi.ingsw.Table.Decks.Token.ActionToken;
import it.polimi.ingsw.Table.Market.Marbles.Faith;
import it.polimi.ingsw.Table.Market.Marbles.Marble;
import it.polimi.ingsw.Table.Market.Marbles.White;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This Class represents the player
 */
public class Player {//<--FIXME check javadoc from here-->

    private String nickname;
    private PersonalBoard personalBoard;
    private LeaderCard[] cardsInHand;
    private LeaderCard[] cardsOnTable;
    private boolean inkwell;
    private List<Marble> marblesFromTheMarket = new ArrayList<>();
    private ArrayList<Production> selectedProduction = new ArrayList<>();
    private boolean selectedDefaultProductionPower = false;
    private ArrayList<Resource> payingResources = new ArrayList<>();
    private int obtainedGeneric = 0;
    private DevelopmentCard obtainedDevelopmentCard;
    private int selectedWarehouseDepotsSlot = 0;
    private int gameIndex;

    /**
     * Constructor method of this class
     * @param nickname: the nickname of the player
     * @param gameIndex: the index of the Game
     */
    public Player(String nickname, int gameIndex){
        this.gameIndex = gameIndex;
        this.nickname = nickname;
        personalBoard = new PersonalBoard(gameIndex);
        cardsInHand = Game.get(gameIndex).getTable().getLeaderDeck().draw();
        cardsOnTable = new LeaderCard[2];
        inkwell = false;
    }

    /**
     * This method set the inkwell to this player
     */
    public void setInkwell(){
        inkwell = true;
    }

    /**
     * Getter of the parameter personalBoard
     * @return the personalBoard, of type PersonalBoard
     */
    public PersonalBoard getPersonalBoard(){
        return personalBoard;
    }

    /**
     * Getter of the parameter nickname
     * @return the nickname, of type String
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Getter of the parameter cardsInHand
     * @return the cardsInHand, of type LeaderCard[]
     */
    public LeaderCard[] getCardsInHand() {
        return cardsInHand;
    }

    /**
     * Getter of the parameter cardsOnTable
     * @return the cardsOnTable, of type LeaderCard[]
     */
    public LeaderCard[] getCardsOnTable() {
        return cardsOnTable;
    }

    /**
     * This method return a new array with the resources in discount
     * @return a new array with the resources in discount, of type Resource[]
     */
    public Resource[] getActiveDiscount() {
        Resource[] result = new Resource[2];
        for(int i = 0; i < 2; i++){
            if(cardsOnTable[i] != null && cardsOnTable[i].getWhatIAm() == LeaderCardType.DISCOUNT){
                result[i] = ((DiscountLeaderCard)cardsOnTable[i]).getDiscount();
            }
        }
        return result;
    }

    /**
     * Getter of the parameter inkwell
     * @return the inkwell, of type boolean
     */
    public boolean isInkwell() {
        return inkwell;
    }

    /**
     * This method let player to discard a leader card in his hand advancing on him faith track
     * @param pos: number 1 or 2 to determinate the position of the leader card in hand to discard
     * @throws PositionInvalidException if the position isn't 1 or 2
     * @throws AlreadyDiscardedThisLeaderCardException if you try to discard a card again
     */
    public void discardLeaderCard(int pos) throws AlreadyDiscardedThisLeaderCardException, PositionInvalidException {
        if(pos < 1 || pos > 2){
            throw new PositionInvalidException();
        }
        else{
            if(cardsInHand[pos-1] == null){
                throw new AlreadyDiscardedThisLeaderCardException();
            }
            cardsInHand[pos-1] = null;
            personalBoard.getFaithTrack().goOn(1);
        }
    }

    /**
     * This Class represents the set of resources of the player
     */
    private class TotalResourcesPlayer{

        private final int coin;
        private final int servant;
        private final int shield;
        private final int stone;

        /**
         * Constructor method of this class
         */
        private TotalResourcesPlayer(){
            int coin = personalBoard.getStrongBox().getCoin();
            int servant = personalBoard.getStrongBox().getServant();
            int shield = personalBoard.getStrongBox().getShield();
            int stone = personalBoard.getStrongBox().getStone();
            Resource[] warehouseDepots = personalBoard.getWarehouseDepots().getResource();
            for (Resource i : warehouseDepots) {
                if(i != null){
                    switch (i) {
                        case COIN:
                            coin++;
                            break;
                        case SERVANT:
                            servant++;
                            break;
                        case SHIELD:
                            shield++;
                            break;
                        case STONE:
                            stone++;
                            break;
                        default:
                            break;
                    }
                }
            }
            for (LeaderCard i : cardsOnTable) {
                if (i != null && i instanceof ExtraStorageLeaderCard) {
                    switch (((ExtraStorageLeaderCard) i).getStorageType()) {
                        case COIN:
                            coin += ((ExtraStorageLeaderCard) i).occupiedResources();
                            break;
                        case SERVANT:
                            servant += ((ExtraStorageLeaderCard) i).occupiedResources();
                            break;
                        case SHIELD:
                            shield += ((ExtraStorageLeaderCard) i).occupiedResources();
                            break;
                        case STONE:
                            stone += ((ExtraStorageLeaderCard) i).occupiedResources();
                            break;
                        default:
                            break;
                    }
                }
            }
            this.coin = coin;
            this.servant = servant;
            this.shield = shield;
            this.stone = stone;
        }

    }

    /**
     * This method check if the player has at least 5 resources of the specified type
     * @param resource: type of resource to have
     * @return if the player has at least 5 resources of the specified type or not, of type boolean
     */
    private boolean checkToHaveAtLeastFiveOfThisResource(Resource resource){
        TotalResourcesPlayer totalResourcesPlayer = new TotalResourcesPlayer();
        int coin = totalResourcesPlayer.coin;
        int servant = totalResourcesPlayer.servant;
        int stone = totalResourcesPlayer.stone;
        int shield = totalResourcesPlayer.shield;
        switch (resource){
            case COIN:
                if(coin >= 5){
                    return true;
                } else {
                    return false;
                }
            case SERVANT:
                if(servant >= 5){
                    return true;
                } else {
                    return false;
                }
            case STONE:
                if(stone >= 5){
                    return true;
                } else {
                    return false;
                }
            case SHIELD:
                if(shield >= 5){
                    return true;
                } else {
                    return false;
                }
            default: return false;
        }
    }

    /**
     * This method tell if the player is able to pay at least a leader card in his hand
     * @return if the player the player is able to pay at least a leader card in his hand
     */
    public boolean canYouPlayAtLeastALeaderCard(){
        boolean result = false;
        for(LeaderCard i : cardsInHand){
            if(i != null){
                switch (i.getWhatIAm()){
                    case DISCOUNT:
                        if(personalBoard.getSlotsDevelopmentCards().checkHaveTypes(((DiscountLeaderCard)i).getCostOfLeaderCard())){
                            result = true;
                        }
                        break;
                    case STORAGE:
                        if(checkToHaveAtLeastFiveOfThisResource(((ExtraStorageLeaderCard)i).getCostOfLeaderCard())){
                            result = true;
                        }
                        break;
                    case PRODUCTIONPOWER:
                        if(personalBoard.getSlotsDevelopmentCards().checkHaveTypeAtLevelTwo(((ProductionPowerLeaderCard)i).getCostOfLeaderCard())){
                            result = true;
                        }
                        break;
                    case WHITE:
                        Type[] price = new Type[3];
                        price[0] = ((WhiteMarbleLeaderCard)i).getCostOfLeaderCard()[0];
                        price[1] = price[0];
                        price[2] = ((WhiteMarbleLeaderCard)i).getCostOfLeaderCard()[1];
                        if(personalBoard.getSlotsDevelopmentCards().checkHaveTypes(price)){
                            result = true;
                        }
                        break;
                    default:
                        throw new RuntimeException();
                }
            }
        }
        return result;
    }

    /**
     * This method let the player to put on the table a leader card that was in his hand
     * in case the player select a StorageLeaderCard, the list payingResources is updated with the resources the player has to pay
     * @param pos: number 1 or 2 to determinate the position of the leader card in hand to play
     * @throws PositionInvalidException if the position isn't 1 or 2
     * @throws NotSatisfiedRequirementsForThisLeaderCardException if the player isn't able to play the card
     */
    public void playLeaderCard(int pos) throws NotSatisfiedRequirementsForThisLeaderCardException, PositionInvalidException {
        if(pos < 1 || pos > 2){
            throw new PositionInvalidException();
        }
        else{
            switch(cardsInHand[pos-1].getWhatIAm()) {
                case DISCOUNT:
                    if(!personalBoard.getSlotsDevelopmentCards().checkHaveTypes(((DiscountLeaderCard)cardsInHand[pos-1]).getCostOfLeaderCard())){
                        throw new NotSatisfiedRequirementsForThisLeaderCardException();
                    }
                    break;
                case STORAGE:
                    if(!checkToHaveAtLeastFiveOfThisResource(((ExtraStorageLeaderCard)cardsInHand[pos-1]).getCostOfLeaderCard())){
                        throw new NotSatisfiedRequirementsForThisLeaderCardException();
                    }
                    for(int i = 0; i < 5; i++){
                        payingResources.add(((ExtraStorageLeaderCard)cardsInHand[pos-1]).getCostOfLeaderCard());
                    }
                    break;
                case PRODUCTIONPOWER:
                    if(!personalBoard.getSlotsDevelopmentCards().checkHaveTypeAtLevelTwo(((ProductionPowerLeaderCard)cardsInHand[pos-1]).getCostOfLeaderCard())){
                        throw new NotSatisfiedRequirementsForThisLeaderCardException();
                    }
                    break;
                case WHITE:
                    Type[] price = new Type[3];
                    price[0] = ((WhiteMarbleLeaderCard)cardsInHand[pos-1]).getCostOfLeaderCard()[0];
                    price[1] = price[0];
                    price[2] = ((WhiteMarbleLeaderCard)cardsInHand[pos-1]).getCostOfLeaderCard()[1];
                    if(!personalBoard.getSlotsDevelopmentCards().checkHaveTypes(price)){
                        throw new NotSatisfiedRequirementsForThisLeaderCardException();
                    }
                    break;
                default:
                    throw new RuntimeException();
            }
            cardsOnTable[pos-1] = cardsInHand[pos-1];
            cardsInHand[pos-1] = null;
        }
    }

    /**
     * This method let player to take resources from the market
     * Taken resources are added to the list marblesFromTheMarket
     * Faith marble are removed advancing on the faith track
     * White marble could be removed, substituted or could remain
     * @param rowColumn: to choose a row or a column of the market
     * @param pos: to choose the position of the row or of the column
     * @throws PositionInvalidException if the selected row or column is invalid
     * @throws NullEnumException if you pass a null pointer instead of selecting row or column
     */
    public void takeResourcesFromTheMarket(RowColumn rowColumn, int pos) throws PositionInvalidException, NullEnumException {
        List<Marble> obtainedMarbles;
        if (rowColumn == RowColumn.COLUMN){
            if(pos < 1 || pos > 4){
                throw new PositionInvalidException();
            }
            else{
                obtainedMarbles = Arrays.asList(Game.get(gameIndex).getTable().getMarket().chooseColumn(pos-1));
            }
        }
        else if(rowColumn == RowColumn.ROW){
            if(pos < 1 || pos > 3){
                throw new PositionInvalidException();
            }else{
                obtainedMarbles = Arrays.asList(Game.get(gameIndex).getTable().getMarket().chooseRow(pos-1));
            }
        } else {
            throw new NullEnumException();
        }
        for(Marble i : obtainedMarbles){
            if(i instanceof Faith){
                i.putResource(personalBoard.getFaithTrack());
                for(PrintWriter pw : Game.get(gameIndex).getPrintWriterList()){ //<--FIXME da spostare in player game riga 140 dopo aver messo i printwriter li-->
                    pw.println(new ShowCurrentBoardMessage());
                }
            }
            else if(i instanceof White){
                if(numberOfWhiteMarbleLeaderCard() == 1){
                    try {
                        marblesFromTheMarket.add(getWhiteMarbleLeaderCard().getWhiteMarble());
                    } catch (NoWhiteMarbleLeaderCardException e) {
                        e.printStackTrace();
                    }
                }
                else if(numberOfWhiteMarbleLeaderCard() >= 2){
                    marblesFromTheMarket.add(i);
                }
            }
            else{
                marblesFromTheMarket.add(i);
            }
        }
    }

    /**
     * This method let player to select a slot of the WarehouseDepots
     * useful for moveResourcesInWarehouseDepots, moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard and moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard
     * the selected slot is saved in var selectedWarehouseDepotsSlot of type int
     * @param pos: to choose the position of the slot of the WarehouseDepots
     * @throws PositionInvalidException if the selected position is invalid
     */
    public void selectAWarehouseDepotsSlot(int pos) throws PositionInvalidException {
        if(pos < 0 || pos >= 6){
            throw new PositionInvalidException();
        }
        selectedWarehouseDepotsSlot = pos;
    }

    /**
     * Getter method of param selectedWarehouseDepotsSlot
     * @return the selected warehouse depots slot
     */
    public int getSelectedWarehouseDepotsSlot(){
        return selectedWarehouseDepotsSlot;
    }

    /**
     * This method let player to move up to two resources in the WarehouseDepots
     * the first position was selected using selectAWarehouseDepotsSlot and saved in var selectedWarehouseDepotsSlot of type int
     * @param pos2: to choose the second position of the slot of the WarehouseDepots
     * @throws NotAdmittedMovementException if the movement is invalid
     */
    public void moveResourcesInWarehouseDepots(int pos2) throws NotAdmittedMovementException {
        int pos1 = selectedWarehouseDepotsSlot;
        personalBoard.getWarehouseDepots().moveResource(pos1, pos2);
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
    public void moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard(int pos) throws PositionInvalidException, NotAnExtraStorageLeaderCardException, EmptySlotYetException, OccupiedSlotExtraStorageLeaderCardException, DifferentStorageException {
        if(pos < 1 || pos > 2){
            throw new PositionInvalidException();
        }
        if(cardsOnTable[pos-1] == null || !(cardsOnTable[pos-1] instanceof ExtraStorageLeaderCard)){
            throw new NotAnExtraStorageLeaderCardException();
        }
        Resource movedResource = personalBoard.getWarehouseDepots().getResource()[selectedWarehouseDepotsSlot];
        if(movedResource == null)
        if(movedResource != ((ExtraStorageLeaderCard)cardsOnTable[pos-1]).getStorageType()){
            throw new DifferentStorageException();
        }
        personalBoard.getWarehouseDepots().removeResource(selectedWarehouseDepotsSlot);
        ((ExtraStorageLeaderCard)cardsOnTable[pos-1]).addResource();
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
    public void moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard(int pos) throws PositionInvalidException, NotAnExtraStorageLeaderCardException, PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException, EmptySlotExtraStorageLeaderCardException {
        if(pos < 1 || pos > 2){
            throw new PositionInvalidException();
        }
        if(cardsOnTable[pos-1] == null || !(cardsOnTable[pos-1] instanceof ExtraStorageLeaderCard)){
            throw new NotAnExtraStorageLeaderCardException();
        }
        Resource movedResource = ((ExtraStorageLeaderCard)cardsOnTable[pos-1]).getStorageType();
        if(((ExtraStorageLeaderCard)cardsOnTable[pos-1]).occupiedResources() == 0){
            throw new EmptySlotExtraStorageLeaderCardException();
        }
        personalBoard.getWarehouseDepots().addResource(movedResource, selectedWarehouseDepotsSlot);
        ((ExtraStorageLeaderCard)cardsOnTable[pos-1]).removeResource();
    }

    /**
     * This method tell if there are some marblesFromTheMarket to add or not
     * @return if there are some marblesFromTheMarket to add or not
     */
    public boolean resourceToAdd(){
        if(marblesFromTheMarket.size() == 0){
            return false;
        }
        return true;
    }

    /**
     * This method returns the first marble to add from the market.
     * @return the first marble to add from the market.
     */
    public Marble whichResourceToAdd() throws NoMarbleToAddFromTheMarketException {
        if(marblesFromTheMarket.isEmpty()){
            throw new NoMarbleToAddFromTheMarketException();
        }
        return marblesFromTheMarket.get(0);
    }

    /**
     * This method let player to add a resource, witch he took from the market, to the WarehouseDepots or into
     * an ExtraStorageLeaderCard or even to discard it letting other players to advance in them faith track
     * @param where: to choose the WarehouseDepots or the LeaderCard or even to choose to discard the resource
     * @param pos: to choose the position of the WarehouseDepots or of the LeaderCard in hand; it's unuseful in case of discarding the resource
     * @throws NoResourceToAddException if the list marblesFromTheMarket, in witch there are all the resources to add, is empty
     * @throws DifferentStorageException if you select an ExtraStorageLeaderCard of another type of the resource to add
     * @throws OccupiedSlotExtraStorageLeaderCardException if you select an ExtraStorageLeaderCard yet occupied
     * @throws PositionAlreadyOccupiedException if you select a position yet occupied of the WarehouseDepots
     * @throws ResourceAlreadyPlacedException if you place in WarehouseDepots a type of resource you yet placed in another shielf
     * @throws DifferentResourceInThisShelfException if you place in WarehouseDepots the resource in a shelf where there is yet another resource of another type
     * @throws UnexpectedWhiteMarbleException if it's unexpectly found you are going to place a white marble
     * @throws UnexpectedFaithMarbleException if it's unexpectly found you are going to place a faith marble
     */
    public void addResource(LeaderWarehouse where, int pos) throws NoResourceToAddException, DifferentStorageException, OccupiedSlotExtraStorageLeaderCardException, PositionAlreadyOccupiedException, ResourceAlreadyPlacedException, DifferentResourceInThisShelfException, UnexpectedWhiteMarbleException, UnexpectedFaithMarbleException {
        if(marblesFromTheMarket.size() == 0) {
            throw new NoResourceToAddException();
        }
        if(marblesFromTheMarket.get(0) instanceof White){
            throw new UnexpectedWhiteMarbleException();
        }
        if(marblesFromTheMarket.get(0) instanceof Faith){
            throw new UnexpectedFaithMarbleException();
        }
        if(where == LeaderWarehouse.LEADERCARD){
            marblesFromTheMarket.get(0).putResource((ExtraStorageLeaderCard) cardsOnTable[pos-1]);
        }
        if(where == LeaderWarehouse.WAREHOUSEDEPOTS){
            marblesFromTheMarket.get(0).putResource(personalBoard.getWarehouseDepots(), pos);
        }
        if(where == LeaderWarehouse.DISCARD){
            throw new IllegalArgumentException();
        }
        marblesFromTheMarket.remove(0);
    }

    public void addResource(LeaderWarehouse where) throws NoResourceToAddException, UnexpectedWhiteMarbleException, UnexpectedFaithMarbleException {
        if(marblesFromTheMarket.size() == 0) {
            throw new NoResourceToAddException();
        }
        if(marblesFromTheMarket.get(0) instanceof White){
            throw new UnexpectedWhiteMarbleException();
        }
        if(marblesFromTheMarket.get(0) instanceof Faith){
            throw new UnexpectedFaithMarbleException();
        }
        if(where != LeaderWarehouse.DISCARD){
            throw new IllegalArgumentException();
        }
        else if(Game.get(gameIndex).getNumberOfPlayer() != 1 && Game.get(gameIndex).getPlayers().size() == 1){
            marblesFromTheMarket.remove(0);
        }
        else{
            personalBoard.getFaithTrack().allOtherPlayersGoOn(this);
            marblesFromTheMarket.remove(0);
        }
    }

    /**
     * This method return the number of white WhiteMarbleLeaderCard on the table
     * @return the number of white WhiteMarbleLeaderCard on the table
     */
    private int numberOfWhiteMarbleLeaderCard(){
        int result = 0;
        for(LeaderCard i : cardsOnTable){
            if(i != null && i instanceof WhiteMarbleLeaderCard){
                result += 1;
            }
        }
        return result;
    }

    /**
     * This method return a WhiteMarbleLeaderCard you have on the table
     * this method should be called only when you have exactly 1 WhiteMarbleLeaderCard
     * @return the WhiteMarbleLeaderCard you have on the table
     * @throws NoWhiteMarbleLeaderCardException if you haven't on the table any WhiteMarbleLeaderCard
     */
    private WhiteMarbleLeaderCard getWhiteMarbleLeaderCard() throws NoWhiteMarbleLeaderCardException {
        for(LeaderCard i : cardsOnTable){
            if(i != null && i instanceof WhiteMarbleLeaderCard){
                return (WhiteMarbleLeaderCard)i;
            }
        }
        throw new NoWhiteMarbleLeaderCardException();
    }

    /**
     * This method is to be called when the player is going to add a white marble: this marble is changed with another marble using a WhiteMarbleLeaderCard
     * @param pos: to choose the position of the WhiteMarbleLeaderCard on the table
     * @throws NoWhiteMarbleLeaderCardException if the selected position doesn't contain a LeaderCard
     * @throws NoWhiteMarbleException if you are going to change a Marble witch is not White
     * @throws PositionInvalidException if the position isn't 1 or 2
     */
    public void changeWhiteMarbleWith(int pos) throws NoWhiteMarbleLeaderCardException, NoWhiteMarbleException, PositionInvalidException {
        if(pos <= 0 || pos > 2){
            throw new PositionInvalidException();
        }
        else{
            if(!(cardsOnTable[pos-1] instanceof WhiteMarbleLeaderCard)){
                throw new NoWhiteMarbleLeaderCardException();
            }
            WhiteMarbleLeaderCard selected = (WhiteMarbleLeaderCard)cardsOnTable[pos-1];
            if(selected == null){
                throw new NoWhiteMarbleLeaderCardException();
            }
            if(marblesFromTheMarket.get(0) instanceof White){
                marblesFromTheMarket.set(0, selected.getWhiteMarble());
            }
            else{
                throw new NoWhiteMarbleException();
            }
        }
    }

    /**
     * This method tell if you are able to activate at least 1 power production (default, development or leader)
     * @return if you are able to activate a power production
     */
    public boolean canYouActivateAPowerProduction(){
        TotalResourcesPlayer totalResourcesPlayer = new TotalResourcesPlayer();
        int coin = totalResourcesPlayer.coin;
        int servant = totalResourcesPlayer.servant;
        int stone = totalResourcesPlayer.stone;
        int shield = totalResourcesPlayer.shield;
        int sum = coin + servant + shield + stone;
        if(sum >= 2){
            return true;
        }
        ArrayList<ResourceProduction> toCheckCombination = new ArrayList<>();
        for(DevelopmentCard i : personalBoard.getSlotsDevelopmentCards().getActiveCards()){
            if(i != null){
                toCheckCombination.add(i.resourceProduction());
            }
        }
        for(LeaderCard i : cardsOnTable){
            if(i != null && i instanceof ProductionPowerLeaderCard){
                toCheckCombination.add(((ProductionPowerLeaderCard)i).resourceProduction());
            }
        }
        for(ResourceProduction i : toCheckCombination){
            int tryCoin = coin;
            int tryServant = servant;
            int tryShield = shield;
            int tryStone = stone;
            tryCoin -= i.getRequiredCoin();
            tryServant -= i.getRequiredServant();
            tryShield -= i.getRequiredShield();
            tryStone -= i.getRequiredStone();
            if(tryCoin >= 0 && tryServant >= 0 && tryShield >= 0 && tryStone >= 0){
                int trySum = tryCoin + tryServant + tryShield + tryStone;
                if(trySum >= i.getProductionGeneric()){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method let you select or deselect a production power from a DevelopmentCard
     * @param pos: to choose the position of the DevelopmentCard
     * @throws PositionInvalidException if the position isn't 1, 2 or 3
     * @throws NoDevelopmentCardInThisPositionException if there is no DevelopmentCard in the selected position
     */
    public void selectProductionDevelopmentCard(int pos) throws PositionInvalidException, NoDevelopmentCardInThisPositionException {
        if(pos < 1 || pos > 3){
            throw new PositionInvalidException();
        }
        DevelopmentCard selected = personalBoard.getSlotsDevelopmentCards().getActiveCards()[pos - 1];
        if(selected == null){
            throw new NoDevelopmentCardInThisPositionException();
        }
        if(selectedProduction.contains(selected)){
            selectedProduction.remove(selected);
        } else {
            selectedProduction.add(selected);
        }
    }

    /**
     * This method let you select or deselect a production power from a ProductionPowerLeaderCard
     * @param pos: to choose the position of the ProductionPowerLeaderCard
     * @throws PositionInvalidException if the position isn't 1 or 2
     * @throws NoProductionLeaderCardException if you haven't selected a ProductionPowerLeaderCard
     */
    public void selectProductionPowerLeaderCard(int pos) throws NoProductionLeaderCardException, PositionInvalidException {
        if(pos <= 0 || pos > 2){
            throw new PositionInvalidException();
        }
        LeaderCard selected = cardsOnTable[pos-1];
        if(selected == null){
            throw new NoProductionLeaderCardException();
        }
        if(!(selected instanceof ProductionPowerLeaderCard)){
            throw new NoProductionLeaderCardException();
        }
        if(selectedProduction.contains(selected)){
            selectedProduction.remove(selected);
        } else {
            selectedProduction.add((Production)selected);
        }
    }

    /**
     * This method let you select or deselect the default production power
     */
    public void selectDefaultProductionPower(){
        selectedDefaultProductionPower = !selectedDefaultProductionPower;
    }

    /**
     * This method let you start the payment of the selected production power
     * @throws NotEnoughResourcesException if you aren't able to pay all production power
     * @throws YouHaveNotSelectedAnyProductionException if you haven't selected any production power
     */
    public void startPayment() throws NotEnoughResourcesException, YouHaveNotSelectedAnyProductionException {
        if(selectedProduction.size() == 0 && selectedDefaultProductionPower == false){
            throw new YouHaveNotSelectedAnyProductionException();
        }
        ResourceProduction production = new ResourceProduction(0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0);
        for (Production i : selectedProduction) {
            production = production.sum(i.resourceProduction());
        }
        if (selectedDefaultProductionPower) {
            production = production.sum(new ResourceProduction(0, 0, 0,
                    0, 2, 0, 0, 0,
                    0, 1, 0));
        }
        TotalResourcesPlayer totalResourcesPlayer = new TotalResourcesPlayer();
        int coin = totalResourcesPlayer.coin;
        int servant = totalResourcesPlayer.servant;
        int shield = totalResourcesPlayer.shield;
        int stone = totalResourcesPlayer.stone;
        int advance = 0;
        boolean weAre = true;
        if (coin < production.getRequiredCoin()) {
            weAre = false;
        } else {
            advance += coin - production.getRequiredCoin();
        }
        if (servant < production.getRequiredServant()) {
            weAre = false;
        } else {
            advance += servant - production.getRequiredServant();
        }
        if (shield < production.getRequiredShield()) {
            weAre = false;
        } else {
            advance += shield - production.getRequiredShield();
        }
        if (stone < production.getRequiredStone()) {
            weAre = false;
        } else {
            advance += stone - production.getRequiredStone();
        }
        if (advance < production.getRequiredGeneric()) {
            weAre = false;
        }
        if(weAre == false){
            throw new NotEnoughResourcesException();
        }
        for(int i = 0; i < production.getRequiredCoin(); i++){
            payingResources.add(Resource.COIN);
        }
        for(int i = 0; i < production.getRequiredServant(); i++){
            payingResources.add(Resource.SERVANT);
        }
        for(int i = 0; i < production.getRequiredShield(); i++){
            payingResources.add(Resource.SHIELD);
        }
        for(int i = 0; i < production.getRequiredStone(); i++){
            payingResources.add(Resource.STONE);
        }
        for(int i = 0; i < production.getRequiredGeneric(); i++){
            payingResources.add(Resource.WHITE);
        }
        obtainedGeneric = production.getProductionGeneric();
    }

    /**
     * This method tell if you have something to pay (to pay production power or to pay LeaderCard or to pay development card)
     * @return if you have something to pay
     */
    public boolean somethingToPay(){
        if(payingResources.isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * This method tell show the next resource to pay
     * @return the next resource to pay
     */
    public Resource nextToPay() throws NoResourceToPayException {
        if(payingResources.isEmpty()){
            throw new NoResourceToPayException();
        }
        return payingResources.get(0);
    }

    /**
     * This method let to pay a resource from the list payingResources using the StrongBox
     * @param pay: to choose the type of resource from the Strongbox you are using to pay
     * @throws WrongPaymentException if you are using a different type of resource from the resource requested
     * @throws NegativeResourceException if you are going to pay with a resource ypu dont have in the Strongbox
     * @throws NotAResourceForStrongBoxException if the resource passed is not supposed to be stored in the strong box
     * @throws NoResourceToPayException if you haven't anything to pay
     */
    public void payWithStrongBox(Resource pay) throws WrongPaymentException, NegativeResourceException, NotAResourceForStrongBoxException, NoResourceToPayException {
        if(payingResources.isEmpty()){
            throw new NoResourceToPayException();
        }
        Resource paying = payingResources.get(0);
        if(paying != Resource.WHITE && paying != pay){
            throw new WrongPaymentException();
        }
        personalBoard.getStrongBox().remove(pay, 1);
        payingResources.remove(0);
        obtainResourcesIfAllResourcesPayed();
    }

    /**
     * This method lets you pay a resource with a resource located in
     * the warehousedepots. When action is chosen it cheks if
     * resource are of the same type or not.
     * @param pos:Location of the resource to pay from werehousedepots
     * @throws NoResourceToPayException if you haven't anything to pay
     * @throws WrongPaymentException if the resources to pay are different
     * @throws EmptySlotYetException if the selected slot is empty
     */
    public void payWithWarehouseDepots(int pos) throws WrongPaymentException, EmptySlotYetException, NoResourceToPayException {
        if(payingResources.isEmpty()){
            throw new NoResourceToPayException();
        }
        Resource paying = payingResources.get(0);
        Resource pay = personalBoard.getWarehouseDepots().getResource()[pos];
        if(paying != Resource.WHITE && paying != pay){
            throw new WrongPaymentException();
        }
        personalBoard.getWarehouseDepots().removeResource(pos);
        payingResources.remove(0);
        obtainResourcesIfAllResourcesPayed();
    }

    /**
     * This method lets you pay with a Leader Card positioned in your table, by checking
     * firstly if you any resources, secondly if the position player chose really exists,
     * thirdly if card chosen is really a leader card, and the last if
     * the cards are the same type
     * @param pos: position from 1 to 2 to chose on your table
     * @throws NotAnExtraStorageLeaderCardException if card chosen is not an Extra Strorage Leader Card
     * @throws WrongPaymentException if cards choese are not the same type of extra storage leader card
     * @throws NoResourceToPayException if you don't have any resources left.
     * @throws PositionInvalidException if the position is lower than 1 e greater than 2
     * @throws EmptySlotExtraStorageLeaderCardException if the operation of removing a resource from an ExtraStorageLeaderCard fails
     */
    public void payWithExtraStorageLeaderCard(int pos) throws NotAnExtraStorageLeaderCardException, WrongPaymentException, EmptySlotExtraStorageLeaderCardException, NoResourceToPayException, PositionInvalidException {
        if(payingResources.isEmpty()){
            throw new NoResourceToPayException();
        }
        if(pos <= 1 || pos > 2){
            throw new PositionInvalidException();
        }
        LeaderCard selected = cardsOnTable[pos - 1];
        if(!(selected instanceof ExtraStorageLeaderCard)){
            throw new NotAnExtraStorageLeaderCardException();
        }
        Resource paying = payingResources.get(0);
        Resource pay = ((ExtraStorageLeaderCard)selected).getStorageType();
        if(paying != Resource.WHITE && paying != pay){
            throw new WrongPaymentException();
        }
        ((ExtraStorageLeaderCard)selected).removeResource();
        payingResources.remove(0);
        obtainResourcesIfAllResourcesPayed();
    }

    /**
     * This methods lets you receive your paid resources, but first checking
     * if you really do have enough resources to do the buying process
     */
    private void obtainResourcesIfAllResourcesPayed(){
        if(payingResources.isEmpty()){
            ResourceProduction production = new ResourceProduction(0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0);
            for (Production i : selectedProduction) {
                production = production.sum(i.resourceProduction());
            }
            if (selectedDefaultProductionPower) {
                production = production.sum(new ResourceProduction(0, 0, 0,
                        0, 2, 0, 0, 0,
                        0, 1, 0));
            }
            try {
                personalBoard.getStrongBox().add(Resource.COIN, production.getProductionCoin());
                personalBoard.getStrongBox().add(Resource.SERVANT, production.getProductionServant());
                personalBoard.getStrongBox().add(Resource.SHIELD, production.getProductionShield());
                personalBoard.getStrongBox().add(Resource.STONE, production.getProductionStone());
            } catch (NotAResourceForStrongBoxException e) {
                e.printStackTrace();
            }
            personalBoard.getFaithTrack().goOn(production.getProductionFaith());
            if(obtainedGeneric <= 0){
                selectedProduction = new ArrayList<>();
                selectedDefaultProductionPower = false;
                payingResources = new ArrayList<>();
            }
        }
    }

    /**
     * This method let you know how many resources you are going to obtain after the payment
     * If you are going to pay the last resource to pay, this method will show you how many resource will be added in
     * your strongbox suddenly after the payment; else this method will return 0
     * @param resource the kind of resource of witch you want to know the number
     * @return how many resources of the specified type you are going to obtain after the payment
     */
    public int obtainingResourcesAfterPaying(Resource resource){
        if(payingResources.size() == 1){
            int faith = 0;
            int coin = 0;
            int servant = 0;
            int shield = 0;
            int stone = 0;
            ResourceProduction production = new ResourceProduction(0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0);
            for (Production i : selectedProduction) {
                production = production.sum(i.resourceProduction());
            }
            if (selectedDefaultProductionPower) {
                production = production.sum(new ResourceProduction(0, 0, 0,
                        0, 2, 0, 0, 0,
                        0, 1, 0));
            }
            faith += production.getProductionFaith();
            coin += production.getProductionCoin();
            servant += production.getProductionServant();
            shield += production.getProductionShield();
            stone += production.getProductionStone();
            switch (resource){
                case FAITH: return faith;
                case COIN: return coin;
                case SERVANT: return servant;
                case SHIELD: return shield;
                case STONE: return stone;
                default: return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * This method gives you the generic resources with generic possibilities for each resource
     * @return if you have to obtain a generic resource or not
     */
    public boolean genericResourcesToObtain(){
        if(obtainedGeneric > 0){
            return true;
        }
        return false;
    }

    /**
     * This method lets you to choose a specific power production
     * at your choice
     * @param resource it takes a resource ball with a generic power
     * @throws NoGenericResourceToObtainException there ane not any generic resource to obtain
     * @throws NotAResourceForStrongBoxException if the resource in input isn't nor COIN, STONE, SERVANT or SHIELD
     */
    public void obtainGenericResource(Resource resource) throws NoGenericResourceToObtainException, NotAResourceForStrongBoxException {
        if(obtainedGeneric <= 0){
            throw new NoGenericResourceToObtainException();
        }
        personalBoard.getStrongBox().add(resource, 1);
        obtainedGeneric = obtainedGeneric - 1;
        if(obtainedGeneric <= 0){
            selectedProduction = new ArrayList<>();
            selectedDefaultProductionPower = false;
            payingResources = new ArrayList<>();
        }
    }

    /**
     * This method lets you buy a DevelopmentCard, but first checking your resources from ware house depots.
     * second checks cards on table.
     * third adds all the costs of the resources after discount.
     * @return true or false by all the checkings done as mention before
     */
    public boolean canYouBuyADevelopmentCard(){
        boolean result = false;
        for(DevelopmentCard[] k : Game.get(gameIndex).getTable().getDevelopmentDeck().visualize()){
            for(DevelopmentCard j : k){
                if(j != null){
                    TotalResourcesPlayer totalResourcesPlayer = new TotalResourcesPlayer();
                    int coin = totalResourcesPlayer.coin;
                    int servant = totalResourcesPlayer.servant;
                    int stone = totalResourcesPlayer.stone;
                    int shield = totalResourcesPlayer.shield;
                    Resource[] activeDiscounts = getActiveDiscount();
                    int costCoin = 0;
                    int costServant = 0;
                    int costShield = 0;
                    int costStone = 0;
                    for(int i = 0; i < j.getCost().length; i++){
                        switch (j.getCost()[i]){
                            case COIN: costCoin += j.getCostNumber()[i];
                                break;
                            case SERVANT: costServant += j.getCostNumber()[i];
                                break;
                            case SHIELD: costShield += j.getCostNumber()[i];
                                break;
                            case STONE: costStone += j.getCostNumber()[i];
                                break;
                            default: break;
                        }
                    }
                    for(Resource i : activeDiscounts){
                        if(i != null){
                            switch (i){
                                case COIN: costCoin--;
                                    break;
                                case SERVANT: costServant--;
                                    break;
                                case SHIELD: costShield--;
                                    break;
                                case STONE: costStone--;
                                    break;
                                default: break;
                            }
                        }
                    }
                    if(costCoin < 0){
                        costCoin = 0;
                    }
                    if(costServant < 0){
                        costServant = 0;
                    }
                    if(costShield < 0){
                        costShield = 0;
                    }
                    if(costStone < 0){
                        costStone = 0;
                    }
                    coin -= costCoin;
                    servant -= costServant;
                    shield -= costShield;
                    stone -= costStone;
                    if(!(coin < 0 || servant < 0 || shield < 0 || stone < 0)){
                        result = true;
                    }
                }
            }
        }
        return result;
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
     */
    public void buyADevelopmentCard(int xx, int yy) throws PositionInvalidException, NotAbleToBuyThisDevelopmentCardException, NotAbleToPlaceThisDevelopmentCardException, DrawnFromEmptyDeckException, SelectedADevelopmentCardYetException {
        if(obtainedDevelopmentCard != null){
            throw new SelectedADevelopmentCardYetException();
        }
        int x = xx - 1;
        int y = yy - 1;
        if(x < 0 || x >= 4 || y < 0 || y >= 3){
            throw new PositionInvalidException();
        }
        DevelopmentCard selectedDevelopmentCard = Game.get(gameIndex).getTable().getDevelopmentDeck().visualize()[x][y];
        if(selectedDevelopmentCard == null){
            throw new DrawnFromEmptyDeckException();
        }
        TotalResourcesPlayer totalResourcesPlayer = new TotalResourcesPlayer();
        int coin = totalResourcesPlayer.coin;
        int servant = totalResourcesPlayer.servant;
        int stone = totalResourcesPlayer.stone;
        int shield = totalResourcesPlayer.shield;
        Resource[] activeDiscounts = getActiveDiscount();
        int costCoin = 0;
        int costServant = 0;
        int costShield = 0;
        int costStone = 0;
        for(int i = 0; i < selectedDevelopmentCard.getCost().length; i++){
            switch (selectedDevelopmentCard.getCost()[i]){
                case COIN: costCoin += selectedDevelopmentCard.getCostNumber()[i];
                    break;
                case SERVANT: costServant += selectedDevelopmentCard.getCostNumber()[i];
                    break;
                case SHIELD: costShield += selectedDevelopmentCard.getCostNumber()[i];
                    break;
                case STONE: costStone += selectedDevelopmentCard.getCostNumber()[i];
                    break;
                default: break;
            }
        }
        for(Resource i : activeDiscounts){
            if(i != null){
                switch (i){
                    case COIN: costCoin--;
                        break;
                    case SERVANT: costServant--;
                        break;
                    case SHIELD: costShield--;
                        break;
                    case STONE: costStone--;
                        break;
                    default: break;
                }
            }
        }
        if(costCoin < 0){
            costCoin = 0;
        }
        if(costServant < 0){
            costServant = 0;
        }
        if(costShield < 0){
            costShield = 0;
        }
        if(costStone < 0){
            costStone = 0;
        }
        coin -= costCoin;
        servant -= costServant;
        shield -= costShield;
        stone -= costStone;
        if(coin < 0 || servant < 0 || shield < 0 || stone < 0){
            throw new NotAbleToBuyThisDevelopmentCardException();
        }
        if(!personalBoard.getSlotsDevelopmentCards().checkAbleToAddThisDevelopmentCard(selectedDevelopmentCard)){
            throw new NotAbleToPlaceThisDevelopmentCardException();
        }
        obtainedDevelopmentCard = selectedDevelopmentCard;
        Game.get(gameIndex).getTable().getDevelopmentDeck().draw(xx,yy);
        for(int i = 0; i < costCoin; i++){
            payingResources.add(Resource.COIN);
        }
        for(int i = 0; i < costServant; i++){
            payingResources.add(Resource.SERVANT);
        }
        for(int i = 0; i < costShield; i++){
            payingResources.add(Resource.SHIELD);
        }
        for(int i = 0; i < costStone; i++){
            payingResources.add(Resource.STONE);
        }
    }

    /**
     * this method will come in hand the moment the player buys a development card and needs to
     * place it in a slot for development card, and return yes or no if i a am ready to check in other methds
     * the availability of the slot
     * @return tru or false
     */
    public boolean developmentCardToObtain(){
        if(obtainedDevelopmentCard == null){
            return false;
        }
        return true;
    }

    /**
     * this method return the obtained DevelopmentCard
     * @return the obtained DevelopmentCard
     */
    public DevelopmentCard getObtainedDevelopmentCard(){
        return obtainedDevelopmentCard;
    }

    /**
     * This method lets you to take a Development card
     * First choose the development card and than pay for it.
     * @param pos: to choose the position of the DevelopmentCard on SlotDevelopmentCard
     * @throws NoDevelopmentCardToObtainException if the place you chose has no development card,
     * @throws PositionInvalidException if the position you chose does not exists
     */
    public void placeDevelopmentCard(int pos) throws NoDevelopmentCardToObtainException, PositionInvalidException {
        if(obtainedDevelopmentCard == null){
            throw new NoDevelopmentCardToObtainException();
        }
        personalBoard.getSlotsDevelopmentCards().addDevelopmentCard(pos, obtainedDevelopmentCard);
        obtainedDevelopmentCard = null;
    }

    /**
     * this method configure the Player for a single-player game
     */
    public void isSinglePlayer(){
        personalBoard.createFaithTrackSP();
        Game.get(gameIndex).getTable().createActionTokenDeck();
    }

    /**
     * this method is to draw a SoloActionToken and to execute its effect
     */
    public ActionToken drawSoloActionToken(){
        ActionToken token = Game.get(gameIndex).getTable().getActionTokenDeck().draw();
        if (token.getWhatIAm() == Type.BLACKCROSS1) {
            personalBoard.getLorenzoTrack().goOn(1);
            Game.get(gameIndex).getTable().getActionTokenDeck().shuffle();
        } else if (token.getWhatIAm() == Type.BLACKCROSS2) {
            personalBoard.getLorenzoTrack().goOn(2);
        } else {
            Game.get(gameIndex).getTable().getDevelopmentDeck().discard(token.getWhatIAm());
        }
        return token;
    }

    /**
     * this method counts the number of leader card in the hand of player
     * @return an integer the number of cards in his hand
     */
    public int countLeaderCardInHand(){
        int count = 0;
        for(LeaderCard i : cardsInHand){
            if(i != null){
                count ++;
            }
        }
        return count;
    }

    /**
     * this method return victory points of the player
     * @return victory points of the player
     */
    public int calculateVictoryPoints(){
        int result = 0;
        for(DevelopmentCard[] i : getPersonalBoard().getSlotsDevelopmentCards().getSlot()){
            for(DevelopmentCard j : i){
                if(j != null){
                    result += j.getVictoryPoints();
                }
            }
        }
        result += getPersonalBoard().getFaithTrack().victoryPoints();
        for(LeaderCard i : cardsOnTable){
            if(i != null){
                result += i.getVictoryPoints();
            }
        }
        int numTotResources = 0;
        numTotResources += getPersonalBoard().getStrongBox().getCoin();
        numTotResources += getPersonalBoard().getStrongBox().getServant();
        numTotResources += getPersonalBoard().getStrongBox().getStone();
        numTotResources += getPersonalBoard().getStrongBox().getShield();
        for(Resource i : getPersonalBoard().getWarehouseDepots().getResource()){
            if(i != null){
                numTotResources++;
            }
        }
        for(LeaderCard i : cardsOnTable){
            if(i != null && i.getWhatIAm() == LeaderCardType.STORAGE){
                numTotResources += ((ExtraStorageLeaderCard)i).occupiedResources();
            }
        }
        int rest = numTotResources % 5;
        int dividend = numTotResources - rest;
        int bonusResources = dividend / 5;
        result += bonusResources;
        return result;
    }

    public List<Marble> getMarblesFromTheMarket() {
        return marblesFromTheMarket;
    }

    public String exportForPlayersAndCardsInHand(){
        String s1 = cardsInHand[0].export();
        String s2 = cardsInHand[1].export();
        String s3 = nickname;
        String result = s1 + "/" + s2 + "/" + s3;
        return result;
    }

}
