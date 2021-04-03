package it.polimi.ingsw.Game;
import it.polimi.ingsw.Table.Deck.LeaderCard;
import it.polimi.ingsw.PersonalBoard.PersonalBoard;
import it.polimi.ingsw.Resource;
import it.polimi.ingsw.Table.Market.DifferentStorageException;
import it.polimi.ingsw.Table.Market.Marble;
import java.util.Arrays;
import java.util.List;

public class Player {

    private String nickname;
    private PersonalBoard personalBoard;
    private LeaderCard[] cardsInHand;
    private LeaderCard[] cardsOnTable;
    private Resource[] activeDiscount;
    private boolean inkwell;
    private List<Marble> marbleFromTheMarket;

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
            cardsOnTable[pos-1] = cardsInHand[pos-1];
            cardsInHand[pos-1] = null;
        }
    }

    public void takeResourcesFromTheMarket(RowColumn rowColumn, int pos){
        if (rowColumn == RowColumn.COLUMN){
            if(pos < 1 || pos > 4){
                throw new IndexOutOfBoundsException();
            }
            else{
                marbleFromTheMarket = Arrays.asList(Game.getInstance().getTable().getMarket().chooseColumn(pos-1));
            }
        }
        else if(rowColumn == RowColumn.ROW){
            if(pos < 1 || pos > 3){
                throw new IndexOutOfBoundsException();
            }else{
                marbleFromTheMarket = Arrays.asList(Game.getInstance().getTable().getMarket().chooseRow(pos-1));
            }
        }else{
            throw new NullPointerException();
        }
    }

    public void addResource(LeaderWarehouse where, int pos){
        if(where == LeaderWarehouse.LEADERCARD){
            try {
                marbleFromTheMarket.remove(0).putResource(cardsOnTable[pos-1]);
            } catch (DifferentStorageException e) {
                e.printStackTrace();
            }
        }
        if(where == LeaderWarehouse.WAREHOUSEDEPOTS){
            marbleFromTheMarket.remove(0).putResource(personalBoard.getWarehouseDepots(), pos);
        }
    }




}
