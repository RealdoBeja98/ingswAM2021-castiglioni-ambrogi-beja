package it.polimi.ingsw.Table.Decks;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import java.util.ArrayList;
import java.util.Collections;

public class DevelopmentDeck {
    private DevelopmentCard[][] deckTrayLayer1;
    private DevelopmentCard[][] deckTrayLayer2;
    private DevelopmentCard[][] deckTrayLayer3;
    private DevelopmentCard[][] deckTrayLayer4;

    /*public DevelopmentDeck(){ something don't work here but i'm too sleepy to find the culprit
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
