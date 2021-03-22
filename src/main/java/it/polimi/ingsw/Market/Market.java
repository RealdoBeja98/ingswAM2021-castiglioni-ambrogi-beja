package it.polimi.ingsw.Market;

import java.util.ArrayList;
import java.util.*;

public class Market {
    private final Marble[][]  marketTray = new Marble[3][4];
    private Marble extraMarble;



    public Market() {
        ArrayList<Marble> temp = new ArrayList<>(13);
        putMarbles(temp);
        Collections.shuffle(temp);
        listToMarket(temp);
    }

    private void putMarbles(ArrayList<Marble> temp){
        temp.set(0, new Coin());
        temp.set(1, new Coin());
        temp.set(2, new Stone());
        temp.set(3, new Stone());
        temp.set(4, new Servant());
        temp.set(5, new Servant());
        temp.set(6, new Shield());
        temp.set(7, new Shield());
        temp.set(8, new White());
        temp.set(9, new White());
        temp.set(10, new White());
        temp.set(11, new White());
        temp.set(12, new Faith());
    }

    private void listToMarket(ArrayList<Marble> temp){
        int k = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
               marketTray[i][j] = temp.get(k);
               k++;
            }
        }
        extraMarble = temp.get(k);
    }

    public Marble[] chooseRow(int n){
        Marble[] marblesInRow = new Marble[4];
        for(int i = 0; i < 4; i++){
            marblesInRow[i] = marketTray[n][i];
        }
        shiftRow(n);

        return marblesInRow;
    }


    public Marble[] chooseColumn(int n){
        Marble[] marblesInColumn = new Marble[3];
        for(int i = 0; i < 3; i++){
            marblesInColumn[i] = marketTray[i][n];
        }
        shiftColumn(n);

        return marblesInColumn;
    }


    private void shiftRow(int n){
        Marble extra = marketTray[n][0];
        for(int i = 1; i < 4; i++){
            marketTray[n][i-1] = marketTray[n][i];
        }
        marketTray[n][3] = extraMarble;
        extraMarble = extra;
    }

    private void shiftColumn(int n){
        Marble extra = marketTray[0][n];
        for(int i = 1; i < 3; i++){
            marketTray[i-1][n] = marketTray[i][n];
        }
        marketTray[2][n] = extraMarble;
        extraMarble = extra;
    }
}
