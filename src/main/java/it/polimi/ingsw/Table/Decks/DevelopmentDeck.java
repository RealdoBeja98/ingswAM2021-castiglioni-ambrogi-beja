package it.polimi.ingsw.Table.Decks;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Exceptions.DrawedFromEmptyDeckException;
import it.polimi.ingsw.Exceptions.PositionInvalidException;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;

public class DevelopmentDeck{

    private DevelopmentCard[][][] deck = new DevelopmentCard[3][4][4];

    public DevelopmentDeck(){

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
            deck[0][0][i] = deckGreen1.get(i);
        }
        //ripeti x 11 v

    }

    private DevelopmentCard upperCard(DevelopmentCard[] singleDeck){
        for(int i = 3; i >= 0; i--){
            if(singleDeck[i] != null){
                return singleDeck[i];
            }
        }
        return null;
    }

    public DevelopmentCard[][] view(){
        DevelopmentCard[][] result = new DevelopmentCard[3][4];
        for(int i = 0 ; i < 3; i++){
            for(int j = 0; j < 4; j++){
                result[i][j] = upperCard(deck[i][j]);
            }
        }
        return result;
    }

    private int cardsLeftSingleDeck(DevelopmentCard[] singleDeck){
        for(int i = 3; i >= 0; i--){
            if(singleDeck[i] != null){
                return i + 1;
            }
        }
        return 0;
    }

    public int[][] cardsLeft(){
        int[][] result = new int[3][4];
        for(int i = 0 ; i < 3; i++){
            for(int j = 0; j < 4; j++){
                result[i][j] = cardsLeftSingleDeck(deck[i][j]);
            }
        }
        return result;
    }

    public DevelopmentCard draw(int x, int y) throws DrawedFromEmptyDeckException, PositionInvalidException {
        if(x < 0 || x >= 4 || y < 0 || y >= 3){
            throw new PositionInvalidException();
        }
        if(cardsLeft()[x][y] == 0){
            throw new DrawedFromEmptyDeckException();
        }
        DevelopmentCard result = upperCard(deck[x][y]);
        deck[x][y][cardsLeft()[x][y]-1] = null;
        return result;
    }

    public boolean[][] isFinished(){
        boolean[][] result = new boolean[3][4];
        int[][] numCardsLeft = cardsLeft();
        for(int i = 0 ; i < 3; i++){
            for(int j = 0; j < 4; j++){
                if(numCardsLeft[i][j] == 0){
                    result[i][j] = true;
                } else {
                    result[i][j] = false;
                }
            }
        }
        return result;
    }

    private void discardFromColoumn(int coloumn) throws DrawedFromEmptyDeckException {
        boolean[][] finishedDecks = isFinished();
        int row = 0;
        boolean found = false;
        while(row < 3 && found == false){
            if(finishedDecks[row][coloumn] == false){
                found = true;
            } else {
                row++;
            }
        }
        if(found == false){
            throw new DrawedFromEmptyDeckException();
        }
        try {
            draw(row, coloumn);
        } catch (DrawedFromEmptyDeckException e) {
            e.printStackTrace();
        } catch (PositionInvalidException e) {
            e.printStackTrace();
        }
    }

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
        try {
            discardFromColoumn(numCol);
            discardFromColoumn(numCol);
        } catch (DrawedFromEmptyDeckException e) {

        }
    }

/*
public class DevelopmentDeck {//<-- FIXME finish me-->
    private DevelopmentCard[][] deckTrayLayer1;
    private DevelopmentCard[][] deckTrayLayer2;
    private DevelopmentCard[][] deckTrayLayer3;
    private DevelopmentCard[][] deckTrayLayer4;

    public DevelopmentDeck(){ something don't work here but i'm too sleepy to find the culprit <!-- FIXME -->
        deckTrayLayer1 = new DevelopmentCard[3][4];
        deckTrayLayer2 = new DevelopmentCard[3][4];
        deckTrayLayer3 = new DevelopmentCard[3][4];
        deckTrayLayer4 = new DevelopmentCard[3][4];
        ArrayList<DevelopmentCard> deckGreen1 = new ArrayList<>();
        Resource[] card1R = {Resource.SHIELD, null, null};
        int[] card1C = {2, Integer.parseInt(null), Integer.parseInt(null)};
        Resource[] req1 = {Resource.COIN, null};
        int[] costReq1 = {1, Integer.parseInt(null)};
        Resource[] prod1 = {Resource.FAITH, null, null};
        int[] costProd1 = {1, Integer.parseInt(null), Integer.parseInt(null)};
        deckGreen1.add(new DevelopmentCard(card1R, card1C, Type.GREEN, 1, req1, costReq1, prod1,costProd1, 1));
        Resource[] card2R = {Resource.SHIELD, Resource.SERVANT, Resource.STONE};
        int[] card2C = {1, 1, 1};
        Resource[] req2 = {Resource.STONE, null};
        int[] costReq2 = {1, Integer.parseInt(null)};
        Resource[] prod2 = {Resource.SERVANT, null, null};
        int[] costProd2 = {1, Integer.parseInt(null), Integer.parseInt(null)};
        deckGreen1.add(new DevelopmentCard(card2R, card2C, Type.GREEN, 1, req2, costReq2, prod2,costProd2, 2));
        Resource[] card3R = {Resource.SHIELD, null, null};
        int[] card3C = {3, Integer.parseInt(null), Integer.parseInt(null)};
        Resource[] req3 = {Resource.SERVANT, null};
        int[] costReq3 = {2, Integer.parseInt(null)};
        Resource[] prod3 = {Resource.COIN, Resource.SHIELD, Resource.STONE};
        int[] costProd3 = {1, 1, 1};
        deckGreen1.add(new DevelopmentCard(card3R, card3C, Type.GREEN, 1, req3, costReq3, prod3,costProd3, 3));
        Resource[] card4R = {Resource.SHIELD, Resource.COIN, null};
        int[] card4C = {2, 2, Integer.parseInt(null)};
        Resource[] req4 = {Resource.STONE, Resource.SERVANT};
        int[] costReq4 = {1, 1};
        Resource[] prod4 = {Resource.COIN, Resource.FAITH, null};
        int[] costProd4 = {2, 1, Integer.parseInt(null)};
        deckGreen1.add(new DevelopmentCard(card4R, card4C, Type.GREEN, 1, req4, costReq4, prod4,costProd4, 4));
        Collections.shuffle(deckGreen1);
        deckTrayLayer1[2][0] = deckGreen1.get(0);
        deckTrayLayer2[2][0] = deckGreen1.get(1);
        deckTrayLayer3[2][0] = deckGreen1.get(2);
        deckTrayLayer4[2][0] = deckGreen1.get(3);
    }*/

}
