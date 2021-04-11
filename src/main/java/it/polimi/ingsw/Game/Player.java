package it.polimi.ingsw.Game;
import it.polimi.ingsw.Enums.LeaderWarehouse;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Table.Decks.*;
import it.polimi.ingsw.PersonalBoard.PersonalBoard;

import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import it.polimi.ingsw.Table.Decks.Leader.ExtraStorageLeaderCard;
import it.polimi.ingsw.Table.Decks.Leader.LeaderCard;
import it.polimi.ingsw.Table.Decks.Leader.ProductionPowerLeaderCard;
import it.polimi.ingsw.Table.Decks.Leader.WhiteMarbleLeaderCard;
import it.polimi.ingsw.Table.Market.Marbles.Faith;
import it.polimi.ingsw.Table.Market.Marbles.Marble;
import it.polimi.ingsw.Table.Market.Marbles.White;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {//<-- FIXME finish me-->

    private String nickname;
    private PersonalBoard personalBoard;
    private LeaderCard[] cardsInHand;
    private LeaderCard[] cardsOnTable;
    private Resource[] activeDiscount;
    private boolean inkwell;
    private List<Marble> marblesFromTheMarket = new ArrayList<>();
    private ArrayList<Production> selectedProduction = new ArrayList<>();
    private boolean selectedDefaultProductionPower = false;
    private ArrayList<Resource> payingResources = new ArrayList<>();
    private int obtainedGeneric = 0;

    public Player(String nickname){
        this.nickname = nickname;
        personalBoard = new PersonalBoard();
        cardsInHand = Game.getInstance().getTable().getLeaderDeck().draw();
        cardsOnTable = new LeaderCard[2];
        activeDiscount = new Resource[2];
        inkwell = false;
    }

    public void setInkwell(){
        inkwell = true;
    }

    public PersonalBoard getPersonalBoard(){
        return personalBoard;
    }

    public String getNickname() {
        return nickname;
    }

    public LeaderCard[] getCardsInHand() {
        return cardsInHand;
    }

    public LeaderCard[] getCardsOnTable() {
        return cardsOnTable;
    }

    public Resource[] getActiveDiscount() {
        return activeDiscount;
    }

    public boolean isInkwell() {
        return inkwell;
    }

    public void discardLeaderCard(int pos){
        if(pos < 1 || pos > 2){
            throw new IndexOutOfBoundsException();
        }
        else{
            cardsInHand[pos-1] = null;
            personalBoard.getFaithTrack().goOn(1);
        }
    }

    public void playLeaderCard(int pos){
        if(pos < 1 || pos > 2){
            throw new IndexOutOfBoundsException();
        }
        else{
            switch(cardsInHand[pos-1].getWhatIAm()) {
                case DISCOUNT:
                    //<!-- FIXME finish this method-->
                    break;
            }
            cardsOnTable[pos-1] = cardsInHand[pos-1];
            cardsInHand[pos-1] = null;
        }
    }

    public void takeResourcesFromTheMarket(RowColumn rowColumn, int pos){
        List<Marble> obtainedMarbles;
        if (rowColumn == RowColumn.COLUMN){
            if(pos < 1 || pos > 4){
                throw new IndexOutOfBoundsException();
            }
            else{
                obtainedMarbles = Arrays.asList(Game.getInstance().getTable().getMarket().chooseColumn(pos-1));
            }
        }
        else if(rowColumn == RowColumn.ROW){
            if(pos < 1 || pos > 3){
                throw new IndexOutOfBoundsException();
            }else{
                obtainedMarbles = Arrays.asList(Game.getInstance().getTable().getMarket().chooseRow(pos-1));
            }
        }else{
            throw new NullPointerException();
        }
        for(Marble i : obtainedMarbles){
            if(i instanceof Faith){
                i.putResource(personalBoard.getFaithTrack());
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
            marblesFromTheMarket.get(0).putResource(cardsOnTable[pos-1]);
        }
        if(where == LeaderWarehouse.WAREHOUSEDEPOTS){
            marblesFromTheMarket.get(0).putResource(personalBoard.getWarehouseDepots(), pos);
        }
        marblesFromTheMarket.remove(0);
    }


    private int numberOfWhiteMarbleLeaderCard(){
        int result = 0;
        for(LeaderCard i : cardsOnTable){
            if(i != null && i instanceof WhiteMarbleLeaderCard){
                result += 1;
            }
        }
        return result;
    }

    private WhiteMarbleLeaderCard getWhiteMarbleLeaderCard() throws NoWhiteMarbleLeaderCardException {
        for(LeaderCard i : cardsOnTable){
            if(i != null && i instanceof WhiteMarbleLeaderCard){
                return (WhiteMarbleLeaderCard)i;
            }
        }
        throw new NoWhiteMarbleLeaderCardException();
    }

    public void changeWhiteMarbleWith(int pos) throws ClassCastException, NoWhiteMarbleException {
        if(pos <= 0 || pos > 2){
            throw new IndexOutOfBoundsException();
        }
        else{
            WhiteMarbleLeaderCard selected = (WhiteMarbleLeaderCard)cardsInHand[pos];
            if(marblesFromTheMarket.get(0) instanceof White){
                marblesFromTheMarket.set(0, selected.getWhiteMarble());
            }
            else{
                throw new NoWhiteMarbleException();
            }
        }
    }

    public void selectProductionDevelopmentCard(int pos){
        if(pos < 1 || pos > 3){
            throw new IndexOutOfBoundsException();
        }
        DevelopmentCard selected = personalBoard.getSlotsDevelopmentCards().getActiveCards()[pos - 1];
        if(selected == null){
            throw new NullPointerException();
        }
        if(selectedProduction.contains(selected)){
            selectedProduction.remove(selected);
        } else {
            selectedProduction.add(selected);
        }
    }

    public void selectProductionPowerLeaderCard(int pos) throws NoProductionLeaderCardException {
        if(pos <= 1 || pos > 2){
            throw new IndexOutOfBoundsException();
        }
        LeaderCard selected = cardsOnTable[pos];
        if(!(selected instanceof ProductionPowerLeaderCard)){
            throw new NoProductionLeaderCardException();
        }
        if(selected == null){
            throw new NullPointerException();
        }
        if(selectedProduction.contains(selected)){
            selectedProduction.remove(selected);
        } else {
            selectedProduction.add((Production)selected);
        }
    }

    public void selectDefaultProductionPower(){
        selectedDefaultProductionPower = !selectedDefaultProductionPower;
    }

    public void startPayment() throws NotEnoughResourcesException {
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
        int coin = personalBoard.getStrongBox().getCoin();
        int servant = personalBoard.getStrongBox().getServant();
        int shield = personalBoard.getStrongBox().getShield();
        int stone = personalBoard.getStrongBox().getStone();
        Resource[] warehouseDepots = personalBoard.getWarehouseDepots().getResource();
        for (Resource i : warehouseDepots) {
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
        for (LeaderCard i : cardsOnTable) {
            if (i != null && i instanceof ExtraStorageLeaderCard) {
                switch (i.getStorageType()) {
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

    public void payWithStrongBox(Resource pay) throws WrongPaymentException, NotEnoughResourcesException, NegativeResourceException, NotAResourceForStrongBoxException {
        Resource paying = payingResources.get(0);
        if(paying != Resource.WHITE && paying != pay){
            throw new WrongPaymentException();
        }
        personalBoard.getStrongBox().remove(pay, 1);
        payingResources.remove(0);
        obtainResourcesIfAllResourcesPayed();
    }

    public void payWithWarehouseDepots(int pos) throws WrongPaymentException, YetEmptySlotException {
        Resource paying = payingResources.get(0);
        Resource pay = personalBoard.getWarehouseDepots().getResource()[pos];
        if(paying != Resource.WHITE && paying != pay){
            throw new WrongPaymentException();
        }
        personalBoard.getWarehouseDepots().removeResource(pos);
        payingResources.remove(0);
        obtainResourcesIfAllResourcesPayed();
    }

    public void payWithExtraStorageLeaderCard(int pos) throws NotAnExtraStorageLeaderCardException, WrongPaymentException, EmptySlotExtraStorageLeaderCardException {
        if(pos <= 1 || pos > 2){
            throw new IndexOutOfBoundsException();
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

}
