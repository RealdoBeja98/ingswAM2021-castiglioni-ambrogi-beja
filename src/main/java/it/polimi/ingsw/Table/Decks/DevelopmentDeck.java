package it.polimi.ingsw.Table.Decks;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Exceptions.DrawnFromEmptyDeckException;
import it.polimi.ingsw.Exceptions.IndexOutOfDevelopmentDeckException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
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
     * this method tell if there is enough a type finished
     * @return if there is enough a type finished
     */
    public boolean allCardOfATypeFinished(){
        int[][] decksTrack = numbersOfCardsLeft();
        for(int j = 0; j < 4; j++){
            boolean result = true;
            for(int i = 0; i < 3; i++){
                if(decksTrack[i][j] > 0){
                    result = false;
                }
            }
            if(result){
                return true;
            }
        }
        return false;
    }

    /**
     * This method draws the card on top of the selected deck
     * if all the cards of that type are gone and we are in the case of solo-game, calls the end of the game
     * @param x: the chosen row
     * @param y: the chosen column
     * @return the selected card, of type DevelopmentCard
     * @throws DrawnFromEmptyDeckException if the deck selected is empty
     * @throws IndexOutOfDevelopmentDeckException if you are out of bound of DevelopmentDeck
     */
    public DevelopmentCard draw(int x, int y) throws DrawnFromEmptyDeckException, IndexOutOfDevelopmentDeckException {
        if(x < 1 || x > 3 || y < 1 || y > 4){
            throw new IndexOutOfDevelopmentDeckException();
        }
        int z = numbersOfCardsLeft()[x-1][y-1];
        if(z == 0){
            throw new DrawnFromEmptyDeckException();
        }
        DevelopmentCard drawnCard = visualize()[x-1][y-1];
        deck[x - 1][y - 1][4 - z] = null;
        if(Game.get(gameIndex) != null && Game.get(gameIndex).getNumberOfPlayer() == 1){
            if(allCardOfATypeFinished()){
                Game.get(gameIndex).endGame();
            }
        }
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
        }
        else{
            Game.get(gameIndex).endGame();
        }
    }

    /**
     * This method fills all the decks with cards
     */
    private void addCard(){

        ArrayList<DevelopmentCard> developmentCardsToAdd = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/resources/DevelopmentCardsList.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray json = (JSONArray) obj;
            for(Object i : json) {

                JSONArray tempCost = (JSONArray) ((JSONObject) i).get("cost");
                int sizeCost = tempCost.size();
                Resource[] cost = new Resource[sizeCost];
                for(int ii = 0; ii < sizeCost; ii++){
                    cost[ii] = Resource.valueOf((String)tempCost.get(ii));
                }

                JSONArray tempCostNumber = (JSONArray) ((JSONObject) i).get("costNumber");
                int sizeCostNumber = tempCostNumber.size();
                int[] costNumber = new int[sizeCostNumber];
                for(int ii = 0; ii < sizeCostNumber; ii++){
                    costNumber[ii] = (int)((Long)tempCostNumber.get(ii)).longValue();
                }

                Type type = Type.valueOf((String)((JSONObject) i).get("type"));

                int level = (int)((Long) ((JSONObject) i).get("level")).longValue();

                JSONArray tempRequirements = (JSONArray) ((JSONObject) i).get("requirements");
                int sizeRequirements = tempRequirements.size();
                Resource[] requirements = new Resource[sizeRequirements];
                for(int ii = 0; ii < sizeRequirements; ii++){
                    requirements[ii] = Resource.valueOf((String)tempRequirements.get(ii));
                }

                JSONArray tempCostRequirements = (JSONArray) ((JSONObject) i).get("costRequirements");
                int sizeCostRequirements = tempCostRequirements.size();
                int[] costRequirements = new int[sizeCostRequirements];
                for(int ii = 0; ii < sizeCostRequirements; ii++){
                    costRequirements[ii] = (int)((Long)tempCostRequirements.get(ii)).longValue();
                }

                JSONArray tempProducts = (JSONArray) ((JSONObject) i).get("products");
                int sizeProducts = tempProducts.size();
                Resource[] products = new Resource[sizeProducts];
                for(int ii = 0; ii < sizeProducts; ii++){
                    products[ii] = Resource.valueOf((String)tempProducts.get(ii));
                }

                JSONArray tempCostProducts = (JSONArray) ((JSONObject) i).get("costProducts");
                int sizeCostProducts = tempCostProducts.size();
                int[] costProducts = new int[sizeCostProducts];
                for(int ii = 0; ii < sizeCostProducts; ii++){
                    costProducts[ii] = (int)((Long)tempCostProducts.get(ii)).longValue();
                }

                int victoryPoints = (int)((Long) ((JSONObject) i).get("victoryPoints")).longValue();

                DevelopmentCard developmentCard = new DevelopmentCard(cost, costNumber, type, level, requirements, costRequirements, products, costProducts, victoryPoints);

                developmentCardsToAdd.add(developmentCard);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        int x = 2;
        while(x >= 0){
            ArrayList<DevelopmentCard> green = new ArrayList<>();
            ArrayList<DevelopmentCard> purple = new ArrayList<>();
            ArrayList<DevelopmentCard> blue = new ArrayList<>();
            ArrayList<DevelopmentCard> yellow = new ArrayList<>();
            for(int i = 0; i < 16; i++){
                DevelopmentCard developmentCard = developmentCardsToAdd.remove(0);
                switch (developmentCard.getType()){
                    case GREEN:
                        green.add(developmentCard);
                        break;
                    case BLUE:
                        blue.add(developmentCard);
                        break;
                    case YELLOW:
                        yellow.add(developmentCard);
                        break;
                    case PURPLE:
                        purple.add(developmentCard);
                        break;
                    default:
                        break;
                }
            }
            Collections.shuffle(green);
            Collections.shuffle(blue);
            Collections.shuffle(yellow);
            Collections.shuffle(purple);
            for(int ii = 0; ii < 4; ii++){
                deck[x][0][ii] = green.get(ii);
            }
            for(int ii = 0; ii < 4; ii++){
                deck[x][1][ii] = blue.get(ii);
            }
            for(int ii = 0; ii < 4; ii++){
                deck[x][2][ii] = yellow.get(ii);
            }
            for(int ii = 0; ii < 4; ii++){
                deck[x][3][ii] = purple.get(ii);
            }
            x = x - 1;
        }
    }

    /**
     * This method exports the DevelopmentDeck to a String
     * @return a string with all the deck data, of type String
     */
    public String export(){
        String result = "";
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                for(int k = 0; k < 4; k++){
                    result = new StringBuilder().append(result).append(deck[i][j][k].export() + "/").toString();
                }
            }
        }
        result = new StringBuilder().append(result).append(gameIndex).toString();
        return result;
    }

    /**
     * Constructor method of this class from the exported string
     * @param importedString: the string to import
     */
    public DevelopmentDeck(String importedString){
        String[] strings = importedString.split("/");
        deck = new DevelopmentCard[3][4][4];
        int x = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                for(int k = 0; k < 4; k++){
                    deck[i][j][k] = new DevelopmentCard(strings[x]);
                    x++;
                }
            }
        }
        gameIndex = Integer.parseInt(strings[x]);
    }

}
