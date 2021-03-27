package it.polimi.ingsw.PersonalBoard;

import it.polimi.ingsw.PersonalBoard.Warehouse.PositionAlreadyOccupiedException;

public class SlotsDevelopmentCards {
    private DevelopmentCard[][]  slot = new DevelopmentCard[3][3];

    private SlotsDevelopmentCards(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                slot[i][j] = null;
            }
        }
    }

    public void addDevelopmentCard(int pos, DevelopmentCard card) throws PositionAlreadyOccupiedException {
        levelOfCard = checkLevelOfDevelopCard(card);
        if (pos > 3) {
             throw new IndexOutOfBoundsException();
         }
         else{
             placeCard(levelOfCards, card);
             }
    }

    private void placeCard(int levelOfCard, int pos, DevelopmentCard card) throws PositionAlreadyOccupiedException {
        if (pos == 0) {
            if (slot[pos][0] == null && levelOfCard == 0) {
                occupySlot(pos, levelOfCard, card);
            } else if (slot[pos][1] == null && levelOfCard == 1) {
                occupySlot(pos, levelOfCard, card);
            } else if (slot[pos][2] == null && levelOfCard == 2) {
                occupySlot(pos, levelOfCard, card);
            } else {
                throw new PositionAlreadyOccupiedException();
            }
        } else if (pos == 1) {
            if (slot[pos][0] == null && levelOfCard == 0) {
                occupySlot(pos, levelOfCard, card);
            } else if (slot[pos][1] == null && levelOfCard == 1) {
                occupySlot(pos, levelOfCard, card);
            } else if (slot[pos][2] == null && levelOfCard == 2) {
                occupySlot(pos, levelOfCard, card);
            } else {
                throw new PositionAlreadyOccupiedException();
            }
        } else if (pos == 3) {
            if (slot[pos][0] == null && levelOfCard == 0) {
                occupySlot(pos, levelOfCard, card);
            } else if (slot[pos][1] == null && levelOfCard == 1) {
                occupySlot(pos, levelOfCard, card);
            } else if (slot[pos][2] == null && levelOfCard == 2) {
                occupySlot(pos, levelOfCard, card);
            } else {
                throw new PositionAlreadyOccupiedException();
            }
        }
    }
    private int checkLevelOfDevelopCard(DevelopmentCard card){
          return card.getLevel;
    }

    private void occupySlot (int pos, int levelOfCard, DevelopmentCard card){
          slot[pos][levelOfCard] = card;
    }
}
