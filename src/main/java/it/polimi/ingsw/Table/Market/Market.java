package it.polimi.ingsw.Table.Market;
import it.polimi.ingsw.Utilities.ImportExport;
import it.polimi.ingsw.Table.Market.Marbles.*;
import java.util.ArrayList;
import java.util.*;

/**
 * This Class contains all the elements related to the market
 */
public class Market {

    private final static int row = 3;
    private final static int column = 4;
    private final Marble[][]  marketTray;
    private Marble extraMarble;

    /**
     * Constructor method of this class
     */
    public Market() {
        marketTray = new Marble[row][column];
        ArrayList<Marble> temp = new ArrayList<>();
        putMarbles(temp);
        Collections.shuffle(temp);
        listToMarket(temp);
    }

    /**
     * This method receives an ArrayList as parameter and fills it with new marbles
     * @param temp: a temporary list
     */
    private void putMarbles(ArrayList<Marble> temp){
        temp.add(new Coin());
        temp.add(new Coin());
        temp.add(new Stone());
        temp.add(new Stone());
        temp.add(new Servant());
        temp.add(new Servant());
        temp.add(new Shield());
        temp.add(new Shield());
        temp.add(new White());
        temp.add(new White());
        temp.add(new White());
        temp.add(new White());
        temp.add(new Faith());
    }

    /**
     * This method receives a shuffled ArrayList as parameter and copies it into the marketTray
     * @param temp: a temporary list
     */
    private void listToMarket(ArrayList<Marble> temp){
        int k = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
               marketTray[i][j] = temp.get(k);
               k++;
            }
        }
        extraMarble = temp.get(k);
    }

    /**
     * Getter of the parameter marketTray
     * @return a copy of the row and the column in the market, of type Marble[][]
     */
    public Marble[][] getMarketTray(){
        Marble[][] result = marketTray.clone();
        for(int i = 0; i < result.length; i++){
            result[i] = result[i].clone();
        }
        return result;
    }

    /**
     * Getter of the parameter extraMarble
     * @return the marble present in the extra slot, of type Marble
     */
    public Marble getExtraMarble(){
        return extraMarble;
    }

    /**
     * This method makes a copy of the chosen row, then shifts it
     * @param n: the number of the chosen row
     * @return the copied row, of type Marble[]
     */
    public Marble[] chooseRow(int n){
        Marble[] marblesInRow = new Marble[column];
        for(int i = 0; i < column; i++){
            marblesInRow[i] = marketTray[n][i];
        }
        shiftRow(n);

        return marblesInRow;
    }

    /**
     * This method makes a copy of the chosen column, then shifts it
     * @param n: the number of the chosen column
     * @return the copied column, of type Marble[]
     */
    public Marble[] chooseColumn(int n){
        Marble[] marblesInColumn = new Marble[row];
        for(int i = 0; i < row; i++){
            marblesInColumn[i] = marketTray[i][n];
        }
        shiftColumn(n);

        return marblesInColumn;
    }

    /**
     * This methods shifts by one position the row, and then modifies the remaining marble
     * @param n: the number of the chosen row
     */
    private void shiftRow(int n){
        Marble extra = marketTray[n][0];
        for(int i = 1; i < column; i++){
            marketTray[n][i-1] = marketTray[n][i];
        }
        marketTray[n][column-1] = extraMarble;
        extraMarble = extra;
    }

    /**
     * This methods shifts by one position the column, and then modifies the remaining marble
     * @param n: the number of the chosen column
     */
    private void shiftColumn(int n){
        Marble extra = marketTray[0][n];
        for(int i = 1; i < row; i++){
            marketTray[i-1][n] = marketTray[i][n];
        }
        marketTray[row-1][n] = extraMarble;
        extraMarble = extra;
    }

    /**
     * This method exports the Market to a String
     * @return a string with all the market data, of type String
     */
    public String export(){
        String result = "";
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                String toAdd = ImportExport.exportResource(marketTray[i][j].getWhatIAm());
                result = new StringBuilder().append(result).append(toAdd).toString();
            }
        }
        String toAdd = ImportExport.exportResource(extraMarble.getWhatIAm());
        result = new StringBuilder().append(result).append(toAdd).toString();
        return result;
    }

    /**
     * Constructor method of this class from the exported string
     * @param importedString: the string to import
     */
    public Market(String importedString){
        marketTray = new Marble[row][column];
        int x = 0;
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                String toAdd = importedString.substring(x, x+1);
                marketTray[i][j] = ImportExport.importMarble(toAdd);
                x++;
            }
        }
        String toAdd = importedString.substring(x, x+1);
        extraMarble = ImportExport.importMarble(toAdd);
    }

}
