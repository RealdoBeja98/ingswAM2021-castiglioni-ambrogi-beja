package it.polimi.ingsw.Table.Decks;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Enums.Type;
import it.polimi.ingsw.Exceptions.DrawnFromEmptyDeckException;
import it.polimi.ingsw.Exceptions.IndexOutOfDevelopmentDeckException;
import it.polimi.ingsw.Game.Game;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import it.polimi.ingsw.Utilities.ObtainIntValueFromLong;
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

    private static final int maxLevels = 3;
    private static final int line = 4;
    private static final int deep = 4;
    private static final int greenNum = 0;
    private static final int blueNum = 1;
    private static final int yellowNum = 2;
    private static final int purpleNum = 3;
    private final DevelopmentCard[][][] deck;
    private final int gameIndex;

    /**
     * Constructor method of this class
     */
    public DevelopmentDeck(int gameIndex){
        this.gameIndex = gameIndex;
        deck = new DevelopmentCard[maxLevels][line][deep];
        addCard();
    }

    /**
     * This method visualizes the card on top of each deck
     * @return a matrix of the top card, of type DevelopmentCard[][]
     */
    public DevelopmentCard[][] visualize(){
        DevelopmentCard[][] view = new DevelopmentCard[maxLevels][line];
        for(int i = 0; i < maxLevels; i++){
            for(int j = 0; j < line; j++){
                for(int z = 0; z < deep; z++){
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
        int[][] cardsLeft = new int[maxLevels][line];
        for(int i = 0; i < maxLevels; i++){
            for(int j = 0; j < line; j++){
                cardsLeft[i][j] = 0;
                for(int z = 0; z < deep; z++){
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
        for(int j = 0; j < line; j++){
            boolean result = true;
            for(int i = 0; i < maxLevels; i++){
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
        if(x < 1 || x > maxLevels || y < 1 || y > line){
            throw new IndexOutOfDevelopmentDeckException();
        }
        int z = numbersOfCardsLeft()[x-1][y-1];
        if(z == 0){
            throw new DrawnFromEmptyDeckException();
        }
        DevelopmentCard drawnCard = visualize()[x-1][y-1];
        deck[x - 1][y - 1][deep - z] = null;
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
            case GREEN: numCol = greenNum;
                break;
            case BLUE: numCol = blueNum;
                break;
            case YELLOW: numCol = yellowNum;
                break;
            case PURPLE: numCol = purpleNum;
                break;
            default: throw new RuntimeException();
        }
        for(int i = 0; i < 2; i++){
            discardFromColumn(numCol);
        }
    }

    /**
     * This method removes a card from the selected column, if all the cards of that type are gone, calls the end of the game
     * @param column: is the column associated to the action token drawn
     */
    private void discardFromColumn(int column) {
        int[][] decksTrack = numbersOfCardsLeft();
        int row = 2;
        for(int i = 0; i < 3; i++){
            if(decksTrack[row][column] != 0){
                deck[row][column][deep - decksTrack[row][column]] = null;
                return;
            }
            row--;
        }
        Game.get(gameIndex).endGame();
    }

    /**
     * method to add the cost of a DevelopmentCard during DevelopmentDeck creation
     * @param i is a json Object
     * @return the cost, of type Resource[]
     */
    private Resource[] addCost(Object i){
        JSONArray tempCost = (JSONArray) ((JSONObject) i).get("cost");
        int sizeCost = tempCost.size();
        Resource[] cost = new Resource[sizeCost];
        for(int ii = 0; ii < sizeCost; ii++){
            cost[ii] = Resource.valueOf((String)tempCost.get(ii));
        }
        return cost;
    }

    /**
     * method to add the costNumber of a DevelopmentCard during DevelopmentDeck creation
     * @param i is a json Object
     * @return the costNumber, of type int[]
     */
    private int[] addCostNumber(Object i){
        JSONArray tempCostNumber = (JSONArray) ((JSONObject) i).get("costNumber");
        int sizeCostNumber = tempCostNumber.size();
        int[] costNumber = new int[sizeCostNumber];
        for(int ii = 0; ii < sizeCostNumber; ii++){
            costNumber[ii] = (int)((Long)tempCostNumber.get(ii)).longValue();
        }
        return costNumber;
    }

    /**
     * method to add the requirements of a DevelopmentCard during DevelopmentDeck creation
     * @param i is a json Object
     * @return the requirements, of type Resource[]
     */
    private Resource[] addRequirements(Object i){
        JSONArray tempRequirements = (JSONArray) ((JSONObject) i).get("requirements");
        int sizeRequirements = tempRequirements.size();
        Resource[] requirements = new Resource[sizeRequirements];
        for(int ii = 0; ii < sizeRequirements; ii++){
            requirements[ii] = Resource.valueOf((String)tempRequirements.get(ii));
        }
        return requirements;
    }

    /**
     * method to add the costRequirements of a DevelopmentCard during DevelopmentDeck creation
     * @param i is a json Object
     * @return the costRequirements, of type int[]
     */
    private int[] addCostRequirements(Object i){
        JSONArray tempCostRequirements = (JSONArray) ((JSONObject) i).get("costRequirements");
        int sizeCostRequirements = tempCostRequirements.size();
        int[] costRequirements = new int[sizeCostRequirements];
        for(int ii = 0; ii < sizeCostRequirements; ii++){
            costRequirements[ii] = (int)((Long)tempCostRequirements.get(ii)).longValue();
        }
        return costRequirements;
    }

    /**
     * method to add the products of a DevelopmentCard during DevelopmentDeck creation
     * @param i is a json Object
     * @return the products, of type Resource[]
     */
    private Resource[] addProducts(Object i){
        JSONArray tempProducts = (JSONArray) ((JSONObject) i).get("products");
        int sizeProducts = tempProducts.size();
        Resource[] products = new Resource[sizeProducts];
        for(int ii = 0; ii < sizeProducts; ii++){
            products[ii] = Resource.valueOf((String)tempProducts.get(ii));
        }
        return products;
    }

    /**
     * method to add the costProducts of a DevelopmentCard during DevelopmentDeck creation
     * @param i is a json Object
     * @return the costProducts, of type int[]
     */
    private int[] addCostProducts(Object i){
        JSONArray tempCostProducts = (JSONArray) ((JSONObject) i).get("costProducts");
        int sizeCostProducts = tempCostProducts.size();
        int[] costProducts = new int[sizeCostProducts];
        for(int ii = 0; ii < sizeCostProducts; ii++){
            costProducts[ii] = (int)((Long)tempCostProducts.get(ii)).longValue();
        }
        return costProducts;
    }

    /**
     * this method is to obtain a list of the DevelopmentCard to add from the json
     * @return the list of the development cards composing the DevelopmentDeck, of type ArrayList<DevelopmentCard>
     */
    private ArrayList<DevelopmentCard> obtainDevelopmentCardsToAdd(){
        ArrayList<DevelopmentCard> developmentCardsToAdd = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/resources/DevelopmentCardsList.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray json = (JSONArray) obj;
            for(Object i : json) {
                Resource[] cost = addCost(i);
                int[] costNumber = addCostNumber(i);
                Type type = Type.valueOf((String)((JSONObject) i).get("type"));
                int level = ObtainIntValueFromLong.f((Long) ((JSONObject) i).get("level"));
                Resource[] requirements = addRequirements(i);
                int[] costRequirements = addCostRequirements(i);
                Resource[] products = addProducts(i);
                int[] costProducts = addCostProducts(i);
                int victoryPoints = ObtainIntValueFromLong.f((Long) ((JSONObject) i).get("victoryPoints"));
                DevelopmentCard developmentCard = new DevelopmentCard(cost, costNumber, type, level, requirements, costRequirements, products, costProducts, victoryPoints);
                developmentCardsToAdd.add(developmentCard);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return developmentCardsToAdd;
    }

    /**
     * This method fills the DevelopmentDeck
     * @param level: the number of the row
     * @param color: the deck of that color
     * @param column: the number of the column
     */
    private void fillDevelopmentDeck(int level, ArrayList<DevelopmentCard> color, int column){
        for(int ii = 0; ii < deep; ii++){
            deck[level][column][ii] = color.get(ii);
        }
    }

    /**
     * this method shuffle four decks of DevelopmentCard
     * @param green: the first deck
     * @param purple: the second deck
     * @param blue: the third deck
     * @param yellow: the fourth deck
     */
    private void shuffleFourDecks(ArrayList<DevelopmentCard> green, ArrayList<DevelopmentCard> purple, ArrayList<DevelopmentCard> blue, ArrayList<DevelopmentCard> yellow){
        Collections.shuffle(green);
        Collections.shuffle(blue);
        Collections.shuffle(yellow);
        Collections.shuffle(purple);
    }

    /**
     * This method fills all the decks with cards
     */
    private void addCard(){
        ArrayList<DevelopmentCard> developmentCardsToAdd = obtainDevelopmentCardsToAdd();
        int level = DevelopmentDeck.maxLevels -1;
        while(level >= 0){
            ArrayList<DevelopmentCard> green = new ArrayList<>();
            ArrayList<DevelopmentCard> purple = new ArrayList<>();
            ArrayList<DevelopmentCard> blue = new ArrayList<>();
            ArrayList<DevelopmentCard> yellow = new ArrayList<>();
            for(int i = 0; i < line*deep; i++){
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
            shuffleFourDecks(green, purple, blue, yellow);
            fillDevelopmentDeck(level, green, greenNum);
            fillDevelopmentDeck(level, blue, blueNum);
            fillDevelopmentDeck(level, yellow, yellowNum);
            fillDevelopmentDeck(level, purple, purpleNum);
            level = level - 1;
        }
    }

    /**
     * This method exports the DevelopmentDeck to a String
     * @return a string with all the deck data, of type String
     */
    public String export(){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < maxLevels; i++){
            for(int j = 0; j < line; j++){
                for(int k = 0; k < deep; k++){
                    result.append(deck[i][j][k].export()).append("/");
                }
            }
        }
        result = new StringBuilder(String.valueOf(result) + gameIndex);
        return result.toString();
    }

    /**
     * Constructor method of this class from the exported string
     * @param importedString: the string to import
     */
    public DevelopmentDeck(String importedString){
        String[] strings = importedString.split("/");
        deck = new DevelopmentCard[maxLevels][line][deep];
        int x = 0;
        for(int i = 0; i < maxLevels; i++){
            for(int j = 0; j < line; j++){
                for(int k = 0; k < deep; k++){
                    deck[i][j][k] = new DevelopmentCard(strings[x]);
                    x++;
                }
            }
        }
        gameIndex = Integer.parseInt(strings[x]);
    }

}
