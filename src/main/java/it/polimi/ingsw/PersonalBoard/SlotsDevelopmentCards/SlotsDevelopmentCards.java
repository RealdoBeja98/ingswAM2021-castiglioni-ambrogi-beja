package it.polimi.ingsw.PersonalBoard.SlotsDevelopmentCards;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Exceptions.IndexOutOfSlotDevelopmentCardsException;
import it.polimi.ingsw.Exceptions.PositionInvalidException;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Utilities.MyInt;

/**
 * This Class represents the slots for the development card
 */
public class SlotsDevelopmentCards {

    private static final int unusefulGameIndex = -1;
    private DevelopmentCard[][] slot;
    private DevelopmentCard[] activeCards;
    private final int gameIndex;
    private final int numberOfSlots = 3;
    private final int maxCardsPerSlot = 3;
    private final int maxLevel = 3;

    /**
     * Constructor method of this class
     * @param gameIndex game index int
     */
    public SlotsDevelopmentCards(int gameIndex){
        this.gameIndex = gameIndex;
        activeCards = new DevelopmentCard[numberOfSlots];
        slot = new DevelopmentCard[numberOfSlots][maxCardsPerSlot];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                slot[i][j] = null;
            }
        }
    }

    /**
     * Constructor method of this class without gameIndex
     */
    public SlotsDevelopmentCards(){
        this.gameIndex = unusefulGameIndex;
        activeCards = new DevelopmentCard[numberOfSlots];
        slot = new DevelopmentCard[numberOfSlots][maxCardsPerSlot];
        for(int i = 0; i < numberOfSlots; i++){
            for(int j = 0; j < maxCardsPerSlot; j++){
                slot[i][j] = null;
            }
        }
    }

    /**
     * This method adds a development card into an available slot, then check if is the seventh and in the end update
     * the array of cards on top
     * @param pos: column where the card is going to be added
     * @param card: card that is going to be added
     * @throws PositionInvalidException propagates this exception, generated if the position requested is occupied or
     *                                   there are no card under with lower level in the same position
     * @throws IndexOutOfSlotDevelopmentCardsException if you are out of bound of slots for development cards
     */
    public void addDevelopmentCard(int pos, DevelopmentCard card) throws PositionInvalidException, IndexOutOfSlotDevelopmentCardsException {
        int levelOfCard = card.getLevel();
        if (pos >= 4 || pos < 1) {
            throw new IndexOutOfSlotDevelopmentCardsException();
        } else {
            placeCard(levelOfCard, pos, card);
            seventhSlotOccupied();
            viewActiveCards();
        }
    }

    /**
     * This method counts if there are seven active card and in case trigger the end of the game
     */
    private void seventhSlotOccupied() {
        int count = 0;
        for(int i = 0; i < numberOfSlots; i++){
            for(int j = 0; j < maxCardsPerSlot; j++){
                if(slot[i][j] != null){
                    count++;
                }
            }
        }
        if(count >= 7){
            if(gameIndex != unusefulGameIndex){
                Game.get(gameIndex).endGame();
                if(Game.get(gameIndex).getNumberOfPlayer() == 1){
                    Game.get(gameIndex).endGameImmediately();
                }
            }
        }
    }

    /**
     * This method checks the condition on card placement, and, if all the condition are verified, place the card
     * @param levelOfCard: level of the card that is going to be added
     * @param pos: column where the card is going to be added
     * @param card: card that is going to be added
     * @throws PositionInvalidException if the position requested is occupied or there are no card
     *                                  under with lower level in the same position
     */
    private void placeCard(int levelOfCard, int pos, DevelopmentCard card) throws PositionInvalidException {
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

    /**
     * This method updates the variable activeCards[]
     */
    public void viewActiveCards(){
        int col = 0;
        int rig = 0;
        while(col < numberOfSlots){
            while(rig < maxCardsPerSlot && slot[rig][col] == null){
                rig++;
            }
            if(rig >=3){
                activeCards[col] = null;
            }
            else{
                activeCards[col] = slot[rig][col];
            }
            col++;
            rig = 0;
        }
    }

    /**
     * This method counts how many victory points are given by the placed card
     * @return the number of victory points, of type int
     */
    public int victoryPoints(){
        int victoryPoints = 0;
        for(int i = 0; i < numberOfSlots; i++){
            for(int j = 0; j < maxCardsPerSlot; j++){
                if(slot[i][j] != null){
                    victoryPoints += slot[i][j].getVictoryPoints();
                }
            }
        }
        return victoryPoints;
    }

    /**
     * Getter of the parameter slot
     * @return a copy of the row and the column in slot for the development card, of type DevelopmentCard[][]
     */
    public DevelopmentCard[][] getSlot() {
        SlotsDevelopmentCards copy = copy();
        return copy.slot;
    }

    /**
     * Getter of the parameter activeCards
     * @return a copy of the array of the development card on top, of type DevelopmentCard[]
     */
    public DevelopmentCard[] getActiveCards() {
        SlotsDevelopmentCards copy = copy();
        return copy.activeCards;
    }

    /**
     * This method creates a copy of this class
     * @return a copy, of type SlotsDevelopmentCards
     */
    private SlotsDevelopmentCards copy(){
        SlotsDevelopmentCards copy = new SlotsDevelopmentCards(gameIndex);
        copy.slot = this.slot.clone();
        for(int i = 0; i < copy.slot.length; i++){
            copy.slot[i] = copy.slot[i].clone();
        }
        copy.activeCards = this.activeCards.clone();
        return copy;
    }

    /**
     * This method checks if you are able to add the card before buying it
     * @param developmentCard development card
     * @return true if you can add the development card, of type int
     */
    public boolean checkAbleToAddThisDevelopmentCard(DevelopmentCard developmentCard){
        int level = developmentCard.getLevel();
        int antiLevel = maxLevel - level;
        for(int i = 0; i < numberOfSlots; i++){
            if(slot[antiLevel][i] == null){
                if(level == 1){
                    return true;
                } else {
                    int previousLevel = level - 1;
                    int previousAntiLevel = maxLevel - previousLevel;
                    if(slot[previousAntiLevel][i] != null){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * this method return how much card of every type you have
     * @param green: card of type green
     * @param blue: card of type blue
     * @param yellow: card of type yellow
     * @param purple: card of type purple
     */
    private void typesThatYouHave(MyInt green, MyInt blue, MyInt yellow, MyInt purple){
        for(DevelopmentCard[] i : slot){
            for(DevelopmentCard j : i){
                if(j != null){
                    switch (j.getType()){
                        case GREEN: green.n++;
                            break;
                        case BLUE: blue.n++;
                            break;
                        case YELLOW: yellow.n++;
                            break;
                        case PURPLE: purple.n++;
                            break;
                        default: break;
                    }
                }
            }
        }
    }

    /**
     * This method checks if you have all the required development cards placed
     * @param typesToHave: development card's type required
     * @return a boolean which is true if you you have at least the required development card's type
     */
    public boolean checkHaveTypes(Type[] typesToHave){
        MyInt green = new MyInt();
        MyInt blue = new MyInt();
        MyInt yellow = new MyInt();
        MyInt purple = new MyInt();
        typesThatYouHave(green, blue, yellow, purple);

        for(Type i : typesToHave){
            switch (i){
                case GREEN: green.n--;
                    break;
                case BLUE: blue.n--;
                    break;
                case YELLOW: yellow.n--;
                    break;
                case PURPLE: purple.n--;
                    break;
                default: break;
            }
        }
        return green.n >= 0 && blue.n >= 0 && yellow.n >= 0 && purple.n >= 0;
    }

    /**
     * This method checks the presence of a level 2 card with a certain type
     * @param typeToHave: card type that is going to be check
     * @return true if there is a level 2 card of the desired type, of type boolean
     */
    public boolean checkHaveTypeAtLevelTwo(Type typeToHave){
        for(DevelopmentCard[] i : slot){
            for(DevelopmentCard j : i){
                if(j != null){
                    if(j.getLevel() == 2 && j.getType() == typeToHave){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method exports the SlotsDevelopmentCards to a String
     * @return a string with all the SlotsDevelopmentCards data, of type String
     */
    public String export(){
        StringBuilder result = new StringBuilder();
        for(DevelopmentCard[] groupOfSlot : slot){
            for(DevelopmentCard developmentCard : groupOfSlot){
                String toAdd = " ";
                if(developmentCard != null){
                    toAdd = developmentCard.export();
                }
                result.append(toAdd).append("/");
            }
        }
        return result.toString();
    }

    /**
     * Constructor method of this class from the exported string
     * @param importedString: the string to import
     */
    public SlotsDevelopmentCards(String importedString){
        this.gameIndex = unusefulGameIndex;
        activeCards = new DevelopmentCard[numberOfSlots];
        slot = new DevelopmentCard[numberOfSlots][maxCardsPerSlot];
        String[] strings = importedString.split("/");
        int k = 0;
        for(int i = 0; i < numberOfSlots; i++){
            for(int j = 0; j < maxCardsPerSlot; j++){
                if(strings[k].equals(" ")){
                    slot[i][j] = null;
                } else {
                    slot[i][j] = new DevelopmentCard(strings[k]);
                    k++;
                }
            }
        }
    }

}
