package it.polimi.ingsw.PersonalBoard.SlotsDevelopmentCards;
import it.polimi.ingsw.Deck.DevelopmentCard;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.PersonalBoard.Warehouse.PositionAlreadyOccupiedException;

public class SlotsDevelopmentCards {

    private final DevelopmentCard[][]  slot = new DevelopmentCard[3][3];
    private final DevelopmentCard[] activeCards = new DevelopmentCard[3];

    public SlotsDevelopmentCards(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                slot[i][j] = null;
            }
        }
    }

    public void addDevelopmentCard(int pos, DevelopmentCard card) throws PositionAlreadyOccupiedException {
        int levelOfCard = card.getLevel();

        if (pos >= 4 || pos < 1) {
            throw new IndexOutOfBoundsException();
        } else {
            try {
                placeCard(levelOfCard, pos, card);
                seventhSlotOccupied();
                viewActiveCards();
            } catch (PositionInvalidException e) {
                e.printStackTrace();
                throw new PositionAlreadyOccupiedException();
            }
        }
    }

    private void seventhSlotOccupied() {
        int count = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(slot[i][j] != null){
                    count++;
                }
            }
        }
        if(count >= 7){
                Game.getInstance().endGame();
        }
    }

    private void placeCard(int levelOfCard, int pos, DevelopmentCard card) throws PositionInvalidException {
        if (pos == 1) {
            checkSlot(levelOfCard, pos, card);
        } else if (pos == 2) {
            checkSlot(levelOfCard, pos, card);
        } else if (pos == 3) {
            checkSlot(levelOfCard, pos, card);
        }
    }

    private void checkSlot(int levelOfCard, int pos, DevelopmentCard card) throws PositionInvalidException {
        if (slot[2][pos-1] == null && levelOfCard == 1) {
            slot[2][pos-1] = card;
        } else if (slot[1][pos-1] == null && levelOfCard == 2 && slot[2][pos-1] != null) {
            slot[1][pos-1] = card;
        } else if (slot[0][pos-1] == null && levelOfCard == 3 && slot[1][pos-1] != null) {
            slot[0][pos-1] = card;
        } else {
            throw new PositionInvalidException();
        }
    }

    public void viewActiveCards(){
        int col = 0;
        int rig = 0;
        while(col < 3){
            while(rig < 3 && slot[rig][col] == null){
                rig++;
            }
            if(rig >=3){
                activeCards[col] = null;
                col++;
                rig = 0;
            }
            else{
                activeCards[col] = slot[rig][col];
                col++;
                rig = 0;
            }
        }
    }

    public int victoryPoints(){
        int victoryPoints = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(slot[i][j] != null){
                    victoryPoints += slot[i][j].getVictoryPoints();
                }
            }
        }
        return victoryPoints;
    }

    public DevelopmentCard[][] getSlot() {
        return slot;
    }

    public DevelopmentCard[] getActiveCards() {
        return activeCards;
    }
}
