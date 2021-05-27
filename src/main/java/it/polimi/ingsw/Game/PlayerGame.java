package it.polimi.ingsw.Game;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.RowColumn;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrack;
import it.polimi.ingsw.PersonalBoard.Faith.FaithTrackSP;
import it.polimi.ingsw.PersonalBoard.SlotsDevelopmentCards.SlotsDevelopmentCards;
import it.polimi.ingsw.PersonalBoard.StrongBox.StrongBox;
import it.polimi.ingsw.PersonalBoard.Warehouse.WarehouseDepots;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import it.polimi.ingsw.Table.Decks.DevelopmentDeck;
import it.polimi.ingsw.Table.Decks.Leader.ExtraStorageLeaderCard;
import it.polimi.ingsw.Table.Decks.Leader.LeaderCard;
import it.polimi.ingsw.Table.Market.Marbles.Marble;
import it.polimi.ingsw.Table.Market.Market;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Class of the Player Game
 */
public class PlayerGame {

    public class PlayerPlayer {

        private String nickname;
        private LeaderCard[] cardsInHandFirst;
        private LeaderCard[] cardsInHand;
        private LeaderCard[] cardsOnTable;

        public String getNickname() {
            return nickname;
        }

        public LeaderCard[] getCardsInHandFirst() {
            return cardsInHandFirst;
        }

        public LeaderCard[] getCardsInHand() {
            return cardsInHand;
        }

        public LeaderCard[] getCardsOnTable() {
            return cardsOnTable;
        }

        public FaithTrack getFaithTrack() {
            return faithTrack;
        }

        public WarehouseDepots getWarehouseDepots() {
            return warehouseDepots;
        }

        public StrongBox getStrongBox() {
            return strongBox;
        }

        public SlotsDevelopmentCards getSlotsDevelopmentCards() {
            return slotsDevelopmentCards;
        }

        private FaithTrack faithTrack;
        private WarehouseDepots warehouseDepots;
        private StrongBox strongBox;
        private SlotsDevelopmentCards slotsDevelopmentCards;

    }

    private PlayerPlayer getPlayerPlayerFromNickname(String nickname){
        //System.out.println("Finding: " + nickname);/////////////////
        for(PlayerPlayer i : players){
            if(i.nickname.equals(nickname)){
                return i;
            } else {
                //System.out.println("not this player: " + i.nickname);/////////////////
            }
        }
        return null;
    }

    private static ArrayList<FaithTrack> allFaithTrack = new ArrayList<>();

    public static ArrayList<FaithTrack> getAllFaithTrack(){
        return allFaithTrack;
    }

    public ArrayList<PlayerPlayer> getPlayers() {
        return (ArrayList<PlayerPlayer>) players.clone();
    }

    private ArrayList<PlayerPlayer> players;

    public Market getMarket() {
        return market;
    }

    private Market market;

    public DevelopmentDeck getDevelopmentDeck() {
        return developmentDeck;
    }

    private DevelopmentDeck developmentDeck;

    private FaithTrackSP lorenzoTrack = null;

    public FaithTrackSP getLorenzoTrack(){
        return lorenzoTrack;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    private PrintWriter out;

    public PlayerGame(String market, String developmentDeck, ArrayList<String> playersAndCardsInHand){
        this.market = new Market(market);
        this.developmentDeck = new DevelopmentDeck(developmentDeck);
        this.players = new ArrayList<>();
        for(String player : playersAndCardsInHand){
            String[] strings = player.split("/");
            PlayerPlayer playerPlayer = new PlayerPlayer();
            playerPlayer.nickname = strings[4];
            //System.out.println("Added nickname: " + playerPlayer.nickname);/////////////////////////////////
            playerPlayer.cardsInHandFirst = new LeaderCard[4];
            playerPlayer.cardsInHand = new LeaderCard[2];
            playerPlayer.cardsOnTable = new LeaderCard[2];
            playerPlayer.cardsInHandFirst[0] = LeaderCard.importLeaderCard(strings[0]);
            playerPlayer.cardsInHandFirst[1] = LeaderCard.importLeaderCard(strings[1]);
            playerPlayer.cardsInHandFirst[2] = LeaderCard.importLeaderCard(strings[2]);
            playerPlayer.cardsInHandFirst[3] = LeaderCard.importLeaderCard(strings[3]);
            playerPlayer.faithTrack = new FaithTrack();
            allFaithTrack.add(playerPlayer.faithTrack);
            playerPlayer.warehouseDepots = new WarehouseDepots();
            playerPlayer.strongBox = new StrongBox();
            playerPlayer.slotsDevelopmentCards = new SlotsDevelopmentCards();
            players.add(playerPlayer);
        }

        if(playersAndCardsInHand.size() == 1){
            lorenzoTrack = new FaithTrackSP();
            allFaithTrack.add(lorenzoTrack);
        }
    }

    public void updateTwoCardToKeepSelected(String nickname, int card1, int card2){
        getPlayerPlayerFromNickname(nickname).cardsInHand[0] = getPlayerPlayerFromNickname(nickname).cardsInHandFirst[card1-1];
        getPlayerPlayerFromNickname(nickname).cardsInHand[1] = getPlayerPlayerFromNickname(nickname).cardsInHandFirst[card2-1];
    }

    public void updateMarket(String nickname, RowColumn rowColumn, int n){
        Marble[] obtained = new Marble[0];
        if(rowColumn == RowColumn.ROW){
            obtained = market.chooseRow(n-1);
        }
        if(rowColumn == RowColumn.COLUMN){
            obtained = market.chooseColumn(n-1);
        }
        int numberOfFaithMarbles = 0;
        for(Marble i : obtained){
            if(i.getWhatIAm() == Resource.FAITH){
                numberOfFaithMarbles++;
            }
        }
        if(numberOfFaithMarbles > 0){
            getPlayerPlayerFromNickname(nickname).faithTrack.goOn(numberOfFaithMarbles);
            out.println("NOTIFY_PB_ALL");
        }
    }

    public void updateDevelopmentDeck(int x, int y){
        try {
            developmentDeck.draw(x, y);
        } catch (DrawnFromEmptyDeckException | IndexOutOfDevelopmentDeckException e) {
            e.printStackTrace();
        }
    }

    public void discardDevelopmentDeck(Type type){
        developmentDeck.discard(type);
    }

    public void discardLeaderCard(String nickname, int pos){
        getPlayerPlayerFromNickname(nickname).cardsInHand[pos-1] = null;
        getPlayerPlayerFromNickname(nickname).faithTrack.goOn(1);
    }

    public void playLeaderCard(String nickname, int pos){
        getPlayerPlayerFromNickname(nickname).cardsOnTable[pos-1] = getPlayerPlayerFromNickname(nickname).cardsInHand[pos-1];
        getPlayerPlayerFromNickname(nickname).cardsInHand[pos-1] = null;
    }

    public void addResourceExtraStorageLeaderCard(String nickname, int pos){
        try {
            ((ExtraStorageLeaderCard)getPlayerPlayerFromNickname(nickname).cardsOnTable[pos-1]).addResource();
        } catch (OccupiedSlotExtraStorageLeaderCardException e) {
            e.printStackTrace();
        }
    }

    public void removeResourceExtraStorageLeaderCard(String nickname, int pos){
        try {
            ((ExtraStorageLeaderCard)getPlayerPlayerFromNickname(nickname).cardsOnTable[pos-1]).removeResource();
        } catch (EmptySlotExtraStorageLeaderCardException e) {
            e.printStackTrace();
        }
    }

    public void addResourceWarehouseDepots(String nickname, Resource r, int pos){
        try {
            getPlayerPlayerFromNickname(nickname).warehouseDepots.addResource(r, pos);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException | IndexOutOfWarehouseDepotsException e) {
            e.printStackTrace();
        }
    }

    public void moveResourceWarehouseDepots(String nickname, int startPos, int finalPos){
        try {
            getPlayerPlayerFromNickname(nickname).warehouseDepots.moveResource(startPos, finalPos);
        } catch (NotAdmittedMovementException | IndexOutOfWarehouseDepotsException e) {
            e.printStackTrace();
        }
    }

    public void moveResourcesFromWarehouseDepotsToExtraStorageLeaderCard(String nickname, int leaderCardPosition, int warehousePosition){
        try {
            getPlayerPlayerFromNickname(nickname).warehouseDepots.removeResource(warehousePosition);
        } catch (EmptySlotYetException e) {
            e.printStackTrace();
        }
        try {
            ((ExtraStorageLeaderCard)getPlayerPlayerFromNickname(nickname).cardsOnTable[leaderCardPosition-1]).addResource();
        } catch (OccupiedSlotExtraStorageLeaderCardException e) {
            e.printStackTrace();
        }
    }

    public void moveResourcesToWarehouseDepotsFromExtraStorageLeaderCard(String nickname, int leaderCardPosition, int warehousePosition){
        try {
            ((ExtraStorageLeaderCard)getPlayerPlayerFromNickname(nickname).cardsOnTable[leaderCardPosition-1]).removeResource();
        } catch (EmptySlotExtraStorageLeaderCardException e) {
            e.printStackTrace();
        }
        Resource resource = ((ExtraStorageLeaderCard)getPlayerPlayerFromNickname(nickname).cardsOnTable[leaderCardPosition-1]).getStorageType();
        try {
            getPlayerPlayerFromNickname(nickname).warehouseDepots.addResource(resource, warehousePosition);
        } catch (PositionAlreadyOccupiedException | ResourceAlreadyPlacedException | DifferentResourceInThisShelfException | IndexOutOfWarehouseDepotsException e) {
            e.printStackTrace();
        }
    }

    public void removeResourceWarehouseDepots(String nickname, int pos){
        try {
            getPlayerPlayerFromNickname(nickname).warehouseDepots.removeResource(pos);
        } catch (EmptySlotYetException e) {
            e.printStackTrace();
        }
    }

    public void addStrongBox(String nickname, Resource r, int n){
        try {
            getPlayerPlayerFromNickname(nickname).strongBox.add(r, n);
        } catch (NotAResourceForStrongBoxException e) {
            e.printStackTrace();
        }
    }

    public void removeStrongBox(String nickname, Resource r, int n){
        try {
            getPlayerPlayerFromNickname(nickname).strongBox.remove(r, n);
        } catch (NegativeResourceException | NotAResourceForStrongBoxException e) {
            e.printStackTrace();
        }
    }

    public void addDevelopmentCard(String nickname, int pos, String developmentCard){
        try {
            //System.out.println("Questa è la developmentCard: " + developmentCard);///////////////////////

            getPlayerPlayerFromNickname(nickname).slotsDevelopmentCards.addDevelopmentCard(pos, new DevelopmentCard(developmentCard));
        } catch (PositionInvalidException | IndexOutOfSlotDevelopmentCardsException e) {
            e.printStackTrace();
        }
    }

    public void goOnFaithTrack(String nickname, int n){
        getPlayerPlayerFromNickname(nickname).faithTrack.goOn(n);
    }

    public void allOtherPlayersGoOnFaithTrack(String nicknameOfExcludedPlayer){
        for(PlayerPlayer i : players){
            if(!(i.nickname.equals(nicknameOfExcludedPlayer))){
                i.faithTrack.goOn(1);
            }
        }
        if(lorenzoTrack != null){
            lorenzoTrack.goOn(1);
        }
    }

    public void loernzoGoOn(){
        lorenzoTrack.goOn(1);
    }

    public void quitAPlayer(String nickname){
        //System.out.println("The excluded player is: " + nickname);///////////////////
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).nickname.equals(nickname)){
                players.remove(i);
                return;
            }
        }
    }

}
