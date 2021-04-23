package it.polimi.ingsw.Table.Decks;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Exceptions.DrawnFromEmptyDeckException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This Class represents the decks of development cards
 */
public class DevelopmentDeck{

    private final DevelopmentCard[][][] deck;
    private final int gameIndex;

    /**
     * Constructor method of this class
     */
    public DevelopmentDeck(int gameIndex){
        this.gameIndex = gameIndex;
        deck = new DevelopmentCard[3][4][4];
        addCard();
    }

    /**
     * This method visualizes the card on top of each deck
     * @return a matrix of the top card, of type DevelopmentCard[][]
     */
    public DevelopmentCard[][] visualize(){
        DevelopmentCard[][] view = new DevelopmentCard[3][4];
        for(int i = 0 ; i < 3; i++){
            for(int j = 0; j < 4; j++){
                for(int z = 0; z < 4; z++){
                    if(deck[i][j][z] != null){
                        view[i][j] = deck[i][j][z];
                        break;
                    }
                }
            }
        }
        return view;
    }

    /**
     * This method counts how many cards are left in each deck
     * @return a matrix of number, of type int[][]
     */
    private int[][] numbersOfCardsLeft(){
        int[][] cardsLeft = new int[3][4];
        for(int i = 0 ; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                cardsLeft[i][j] = 0;
            }
        }
        for(int i = 0 ; i < 3; i++){
            for(int j = 0; j < 4; j++){
                for(int z = 0; z < 4; z++){
                    if(deck[i][j][z] != null){
                        cardsLeft[i][j] += 1;
                    }
                }
            }
        }
        return cardsLeft;
    }

    /**
     * This method draws the card on top of the selected deck
     * @param x: the chosen row
     * @param y: the chosen column
     * @return the selected card, of type DevelopmentCard
     * @throws DrawnFromEmptyDeckException if the deck selected is empty
     */
    public DevelopmentCard draw(int x, int y) throws DrawnFromEmptyDeckException {
        if(x < 1 || x > 3 || y < 1 || y > 4){
            throw new IndexOutOfBoundsException();
        }
        int z = numbersOfCardsLeft()[x-1][y-1];
        if(z == 0){
            throw new DrawnFromEmptyDeckException();
        }
        DevelopmentCard drawnCard = visualize()[x-1][y-1];
        deck[x - 1][y - 1][4 - z] = null;
        return drawnCard;
    }

    /**
     * This method discards two cards of the same type as the action token
     * @param type: is the color of the action token drawn
     */
    public void discard(Type type){
        int numCol;
        switch (type){
            case GREEN: numCol = 0;
                break;
            case BLUE: numCol = 1;
                break;
            case YELLOW: numCol = 2;
                break;
            case PURPLE: numCol = 3;
                break;
            default: throw new RuntimeException();
        }
        discardFromColumn(numCol);
        discardFromColumn(numCol);
    }

    /**
     * This method removes a card from the selected column, if all the cards of that type are gone, calls the end of the game
     * @param column: is the column associated to the action token drawn
     */
    private void discardFromColumn(int column) {
        int[][] decksTrack = numbersOfCardsLeft();
        int row = 2;

        if(decksTrack[row][column] != 0){
            deck[row][column][4 - decksTrack[row][column]] = null;
            return;
        }
        else{
            row--;
        }

        if(decksTrack[row][column] != 0){
            deck[row][column][4 - decksTrack[row][column]] = null;
            return;
        }
        else{
            row--;
        }
        if(decksTrack[row][column] != 0){
            deck[row][column][4 - decksTrack[row][column]] = null;
            return;
        }
        else{
            Game.get(gameIndex).endGame();
        }
    }

    /**
     * This method fills all the decks with cards
     */
    private void addCard(){//<--FIXME-->
        ArrayList<DevelopmentCard> deckGreen1 = new ArrayList<>();
        Resource[] card1R = {Resource.SHIELD};
        int[] card1C = {2};
        Resource[] req1 = {Resource.COIN};
        int[] costReq1 = {1};
        Resource[] prod1 = {Resource.FAITH};
        int[] costProd1 = {1};
        deckGreen1.add(new DevelopmentCard(card1R, card1C, Type.GREEN, 1, req1, costReq1, prod1,costProd1, 1));
        Resource[] card2R = {Resource.SHIELD, Resource.SERVANT, Resource.STONE};
        int[] card2C = {1, 1, 1};
        Resource[] req2 = {Resource.STONE};
        int[] costReq2 = {1};
        Resource[] prod2 = {Resource.SERVANT};
        int[] costProd2 = {1};
        deckGreen1.add(new DevelopmentCard(card2R, card2C, Type.GREEN, 1, req2, costReq2, prod2,costProd2, 2));
        Resource[] card3R = {Resource.SHIELD};
        int[] card3C = {3};
        Resource[] req3 = {Resource.SERVANT};
        int[] costReq3 = {2};
        Resource[] prod3 = {Resource.COIN, Resource.SHIELD, Resource.STONE};
        int[] costProd3 = {1, 1, 1};
        deckGreen1.add(new DevelopmentCard(card3R, card3C, Type.GREEN, 1, req3, costReq3, prod3,costProd3, 3));
        Resource[] card4R = {Resource.SHIELD, Resource.COIN};
        int[] card4C = {2, 2};
        Resource[] req4 = {Resource.STONE, Resource.SERVANT};
        int[] costReq4 = {1, 1};
        Resource[] prod4 = {Resource.COIN, Resource.FAITH};
        int[] costProd4 = {2, 1};
        deckGreen1.add(new DevelopmentCard(card4R, card4C, Type.GREEN, 1, req4, costReq4, prod4,costProd4, 4));
        Collections.shuffle(deckGreen1);
        for(int i = 0; i < 4; i++){
            deck[2][0][i] = deckGreen1.get(i);
        }
        //ripeti x 11 v
    }

}
