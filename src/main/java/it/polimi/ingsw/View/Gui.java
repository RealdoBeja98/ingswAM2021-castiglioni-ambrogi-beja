package it.polimi.ingsw.View;
import it.polimi.ingsw.Enums.FavorTiles;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Game.PlayerGame;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Mains.GuiThread;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import it.polimi.ingsw.Table.Decks.Leader.LeaderCard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Popup;


import java.util.ArrayList;

public class Gui extends View{

    private final Canvas canvas;

    public Gui(Canvas canvas){
        this.canvas = canvas;
    }

    @Override
    public void showStartingLC() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ArrayList<PlayerGame.PlayerPlayer> players = ClientMain.getPlayerGame().getPlayers();
        for(PlayerGame.PlayerPlayer n : players){
            if(n.getNickname().equals(ClientMain.getClientNick())){
                LeaderCard[] cardsInHand = n.getCardsInHandFirst();
                for (int i = 0; i < cardsInHand.length; i++) {
                    String name = cardsInHand[i].export();
                    String fullName = "LeaderCard/" + name + ".png";
                    drawCards(gc, i*170+1000, 600, fullName, 150, 230);
                }
                GuiThread.setCardPrinted();
            }
        }

    }

    @Override
    public void showMarket() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                Resource marble = ClientMain.getPlayerGame().getMarket().getMarketTray()[i][j].getWhatIAm();
                String fullName = selectMarble(marble);
                drawLittleSquare(gc, j * 80, i * 80 + 690, fullName, 80, 80);
            }
        }
        Resource extraMarble = ClientMain.getPlayerGame().getMarket().getExtraMarble().getWhatIAm();
        String fullName = selectMarble(extraMarble);
        drawLittleSquare(gc, 450, 690, fullName, 80, 80);
    }

    @Override
    public void showDevCard() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                String name;
                try{
                    name = ClientMain.getPlayerGame().getDevelopmentDeck().visualize()[j][i].export();
                } catch (NullPointerException e){
                    name = "nullDevCard";
                }
                String fullName = "DevelopmentCards/" + name + ".png";
                drawCards(gc, i * 150, j * 230, fullName, 150, 230);
            }
        }
    }

    @Override
    public void showPersonalBoard() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int navigatorBoard = 0;
        ArrayList<PlayerGame.PlayerPlayer> players = ClientMain.getPlayerGame().getPlayers();
        for(PlayerGame.PlayerPlayer n : players){
            if(n.getNickname().equals(ClientMain.getClientNick())){
                drawBoard(n, gc, 720, 0);
            } else {
                if(navigatorBoard == 0){
                    drawBoard(n, gc, 1360, 0);
                }
                if(navigatorBoard == 1){
                    drawBoard(n, gc, 720, 490);
                }
                if(navigatorBoard == 2){
                    drawBoard(n, gc, 1360, 490);
                }
                navigatorBoard++;
            }
        }

    }

    @Override
    public void showPBCurrent() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int navigatorBoard = 0;

        ArrayList<PlayerGame.PlayerPlayer> players = ClientMain.getPlayerGame().getPlayers();
        for(PlayerGame.PlayerPlayer n : players){
            if(n.getNickname().equals(ClientMain.getClientNick())){
                if(n.getNickname().equals(ClientMain.getCurrentP())){
                    drawBoard(n, gc, 720, 0);
                }
            } else {
                if(navigatorBoard == 0){
                    if(n.getNickname().equals(ClientMain.getCurrentP())){
                        drawBoard(n, gc, 1360, 0);
                    }
                }
                if(navigatorBoard == 1){
                    if(n.getNickname().equals(ClientMain.getCurrentP())){
                        drawBoard(n, gc, 720, 490);
                    }
                }
                if(navigatorBoard == 2){
                    if(n.getNickname().equals(ClientMain.getCurrentP())){
                        drawBoard(n, gc, 1360, 490);
                    }
                }
                navigatorBoard++;
            }
        }
    }

    private void drawCards(GraphicsContext gc, int x, int y, String name, int l1, int l2) {
        Image img = new Image(name);
        gc.drawImage(img, x, y, l1, l2);
    }

    private String selectMarble(Resource marble) {
        String name= "NullMarble";
        if(marble == null){
            name = "NullMarble";
        } else {
            switch (marble){
                case COIN: name = "CoinMarble";
                    break;
                case FAITH: name = "FaithMarble";
                    break;
                case SERVANT: name = "ServantMarble";
                    break;
                case SHIELD: name = "ShieldMarble";
                    break;
                case STONE: name = "StoneMarble";
                    break;
                case WHITE: name = "WhiteMarble";
                    break;
            }
        }
        return "Marbles/" + name + ".png";
    }

    private String selectResource(Resource resource) {
        String name;
        if(resource == null){
            name = "NullResource";
        } else {
            switch (resource){
                case COIN: name = "coin";
                    break;
                case SERVANT: name = "servant";
                    break;
                case SHIELD: name = "shield";
                    break;
                case STONE: name = "stone";
                    break;
                default: name = "NullResource";
            }
        }
        return "Resources/" + name + ".png";
    }

    private void drawLittleSquare(GraphicsContext gc, int x, int y, String name, int l1, int l2) {
        Image img = new Image(name);
        gc.drawImage(img, x, y, l1, l2);
    }

    private void drawBoard(PlayerGame.PlayerPlayer n, GraphicsContext gc, int x, int y){
        Image pb = new Image("Misc/PersonalBoard.png");
        gc.drawImage(pb, x, y, 560, 490);
        String nickname = n.getNickname();
        gc.fillText(nickname, x, y+20);
        int faithNumber = n.getFaithTrack().getFaithMarker();
        drawFaith(gc, x, y, faithNumber);
        FavorTiles[] favorTiles = n.getFaithTrack().getFavorTiles();
        drawFavorTiles(gc, x, y, favorTiles);
        drawWarehouseDepots(n, gc, x, y);
        drawActiveCards(n, gc, x, y);
        drawDiscardedCards(n, gc, x, y);
        drawCardsOnTable(n, gc, x, y);
        drawCardsInHand(n, gc, x, y);
        drawStrongbox(n, gc, x, y);
    }


    private void drawFaith(GraphicsContext gc, int x, int y, int faithNumber){
        Image faith = new Image("Misc/FaithMarker.png");
        if(faithNumber == 0){
            gc.drawImage(faith, x+25, y+100, 17, 24);
        }if(faithNumber == 1){
            gc.drawImage(faith, x+55, y+100, 17, 24);
        }if(faithNumber == 2){
            gc.drawImage(faith, x+80, y+100, 17, 24);
        }if(faithNumber == 3){
            gc.drawImage(faith, x+80, y+66, 17, 24);
        }if(faithNumber == 4){
            gc.drawImage(faith, x+80, y+32, 17, 24);
        }if(faithNumber == 5){
            gc.drawImage(faith, x+107, y+32, 17, 24);
        }if(faithNumber == 6){
            gc.drawImage(faith, x+135, y+32, 17, 24);
        }if(faithNumber == 7){
            gc.drawImage(faith, x+163, y+32, 17, 24);
        }if(faithNumber == 8){
            gc.drawImage(faith, x+190, y+32, 17, 24);
        }if(faithNumber == 9){
            gc.drawImage(faith, x+217, y+32, 17, 24);
        }if(faithNumber == 10){
            gc.drawImage(faith, x+217, y+66, 17, 24);
        }if(faithNumber == 11){
            gc.drawImage(faith, x+217, y+100, 17, 24);
        }if(faithNumber == 12){
            gc.drawImage(faith, x+244, y+100, 17, 24);
        }if(faithNumber == 13){
            gc.drawImage(faith, x+272, y+100, 17, 24);
        }if(faithNumber == 14){
            gc.drawImage(faith, x+300, y+100, 17, 24);
        }if(faithNumber == 15){
            gc.drawImage(faith, x+327, y+100, 17, 24);
        }if(faithNumber == 16){
            gc.drawImage(faith, x+354, y+100, 17, 24);
        }if(faithNumber == 17){
            gc.drawImage(faith, x+354, y+66, 17, 24);
        }if(faithNumber == 18){
            gc.drawImage(faith, x+354, y+32, 17, 24);
        }if(faithNumber == 19){
            gc.drawImage(faith, x+381, y+32, 17, 24);
        }if(faithNumber == 20){
            gc.drawImage(faith, x+409, y+32, 17, 24);
        }if(faithNumber == 21){
            gc.drawImage(faith, x+438, y+32, 17, 24);
        }if(faithNumber == 22){
            gc.drawImage(faith, x+464, y+32, 17, 24);
        }if(faithNumber == 23){
            gc.drawImage(faith, x+491, y+32, 17, 24);
        }if(faithNumber == 24){
            gc.drawImage(faith, x+518, y+32, 17, 24);
        }
    }

    private void drawFavorTiles(GraphicsContext gc, int x, int y, FavorTiles[] favorTiles){
        for(int i = 0; i < 3; i++){
            String name = "FavourTile" + (i+1) + "D";
            switch (favorTiles[i]){
                case COVERED: name = "FavourTile" + (i+1) + "x";
                    break;
                case TURNED: name = "FavourTile" + (i+1);
                    break;
                case DITCH: name = "FavourTile" + (i+1) + "D";
                    break;
            }
            String fullName = "FavourTile/" + name + ".png";
            int xx = 0;
            int yy = 0;
            switch (i){
                case 0:
                    xx = 142;
                    yy = 72;
                    break;
                case 1:
                    xx = 278;
                    yy = 40;
                    break;
                case 2:
                    xx = 445;
                    yy = 77;
                    break;
                default: break;
            }
            Image image = new Image(fullName);
            gc.drawImage(image, x+xx, y+yy, 33, 40);
        }
    }

    private void drawWarehouseDepots(PlayerGame.PlayerPlayer n, GraphicsContext gc, int x, int y){
        Resource[] resource = n.getWarehouseDepots().getResource();
        if(resource[0] != null){
            String fullName = selectResource(resource[0]);
            drawLittleSquare(gc, x+65, y+220, fullName, 17, 17);
        }if(resource[1] != null){
            String fullName = selectResource(resource[1]);
            drawLittleSquare(gc, x+55, y+258, fullName, 17, 17);
        }if(resource[2] != null){
            String fullName = selectResource(resource[2]);
            drawLittleSquare(gc, x+74, y+258, fullName, 17, 17);
        }if(resource[3] != null){
            String fullName = selectResource(resource[3]);
            drawLittleSquare(gc, x+45, y+304, fullName, 17, 17);
        }if(resource[4] != null){
            String fullName = selectResource(resource[4]);
            drawLittleSquare(gc, x+64, y+304, fullName, 17, 17);
        }if(resource[5] != null){
            String fullName = selectResource(resource[5]);
            drawLittleSquare(gc, x+85, y+304, fullName, 17, 17);
        }
    }

    private void drawActiveCards(PlayerGame.PlayerPlayer n, GraphicsContext gc, int x, int y) {
        DevelopmentCard[][] visualize = n.getSlotsDevelopmentCards().getSlot();
        if(visualize[2][0] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[2][0].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 220, y+270, fullName, 90, 140);
        }if(visualize[2][1] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[2][1].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 325, y+270, fullName, 90, 140);
        }if(visualize[2][2] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[2][2].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 440, y+270, fullName, 90, 140);
        }if(visualize[1][0] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[1][0].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 220, y+240, fullName, 90, 140);
        }if(visualize[1][1] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[1][1].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 325, y+240, fullName, 90, 140);
        }if(visualize[1][2] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[1][2].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 440, y+240, fullName, 90, 140);
        }if(visualize[0][0] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[2][0].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 220, y+210, fullName, 90, 140);
        }if(visualize[0][1] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[2][1].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 325, y+210, fullName, 90, 140);
        }if(visualize[0][2] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[2][2].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 440, y+210, fullName, 90, 140);
        }
    }

    private void drawDiscardedCards(PlayerGame.PlayerPlayer n, GraphicsContext gc, int x, int y){
        String name = "NullLeaderCard";
        String fullName = "LeaderCard/" + name + ".png";
        drawCards(gc, x+ 560, y+ 0, fullName, 70, 110);
        drawCards(gc, x+ 560, y+ 110, "Labels/DiscardedCard.png", 70, 25);
        drawCards(gc, x+ 560, y+ 245, fullName, 70, 110);
        drawCards(gc, x+ 560, y+ 355, "Labels/DiscardedCard.png", 70, 25);
    }

    private void drawCardsOnTable(PlayerGame.PlayerPlayer n, GraphicsContext gc, int x, int y){
        LeaderCard[] cardsOnTable = n.getCardsOnTable();
        if(cardsOnTable[0] != null){
            String name = cardsOnTable[0].export();
            String fullName = "LeaderCard/" + name + ".png";
            drawCards(gc, x+ 560, y+ 0, fullName, 70, 110);
            drawCards(gc, x+ 560, y+ 110, "Labels/CardInPlay.png", 70, 25);
        }
        if(cardsOnTable[1] != null){
            String name = cardsOnTable[1].export();
            String fullName = "LeaderCard/" + name + ".png";
            drawCards(gc, x+ 560, y+ 245, fullName, 70, 110);
            drawCards(gc, x+ 560, y+ 355, "Labels/CardInPlay.png", 70, 25);
        }
    }

    private void drawCardsInHand(PlayerGame.PlayerPlayer n, GraphicsContext gc, int x, int y){
        LeaderCard[] cardsInHand = n.getCardsInHand();
        if(cardsInHand[0] != null){
            if(n.getNickname().equals(ClientMain.getClientNick())){
                String name = cardsInHand[0].export();
                String fullName = "LeaderCard/" + name + ".png";
                drawCards(gc, x+ 560, y+ 0, fullName, 70, 110);
                drawCards(gc, x+ 560, y+ 110, "Labels/CardInHand.png", 70, 25);
            } else {
                drawCards(gc, x+ 560, y+ 110, "Labels/Invisible.png", 70, 25);
            }
        }
        if(cardsInHand[1] != null){
            if(n.getNickname().equals(ClientMain.getClientNick())){
                String name = cardsInHand[1].export();
                String fullName = "LeaderCard/" + name + ".png";
                drawCards(gc, x+ 560, y+ 245, fullName, 70, 110);
                drawCards(gc, x+ 560, y+ 355, "Labels/CardInHand.png", 70, 25);
            } else {
                drawCards(gc, x+ 560, y+ 355, "Labels/Invisible.png", 70, 25);
            }
        }
    }

    private void drawStrongbox(PlayerGame.PlayerPlayer n, GraphicsContext gc, int x, int y){
        int coin = n.getStrongBox().getCoin();
        int stone = n.getStrongBox().getStone();
        int servant = n.getStrongBox().getServant();
        int shield = n.getStrongBox().getShield();
        drawLittleSquare(gc, x+15, y+350, selectResource(Resource.COIN), 20, 20);
        drawNumber(gc, x+35, y+350, coin);
        drawLittleSquare(gc, x+15, y+380, selectResource(Resource.STONE), 20, 20);
        drawNumber(gc, x+35, y+380, stone);
        drawLittleSquare(gc, x+15, y+410, selectResource(Resource.SERVANT), 20, 20);
        drawNumber(gc, x+35, y+410, servant);
        drawLittleSquare(gc, x+15, y+440, selectResource(Resource.SHIELD), 20, 20);
        drawNumber(gc, x+35, y+440, shield);
    }

    private void drawNumber(GraphicsContext gc, int x, int y, int number){
        int c2 = number % 10;
        int c1 = number / 10;
        drawLittleSquare(gc, x+0, y+0, "Numbers/" + c1 + ".png", 20, 20);
        drawLittleSquare(gc, x+20, y+0, "Numbers/" + c2 + ".png", 20, 20);
    }

}