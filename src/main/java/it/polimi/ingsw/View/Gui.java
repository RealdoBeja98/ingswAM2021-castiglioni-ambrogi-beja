package it.polimi.ingsw.View;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Game.PlayerGame;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


import java.util.ArrayList;

public class Gui extends View{

    private final Canvas canvas;

    public Gui(Canvas canvas){
        this.canvas = canvas;
    }

    @Override
    public void showStartingLC() {

    }

    @Override
    public void showMarket() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                Resource marble = ClientMain.getPlayerGame().getMarket().getMarketTray()[i][j].getWhatIAm();
                String fullName = selectMarble(marble);
                drawMarble(gc, j * 80, i * 80 + 690, fullName, 80, 80);
            }
        }
        Resource extraMarble = ClientMain.getPlayerGame().getMarket().getExtraMarble().getWhatIAm();
        String fullName = selectMarble(extraMarble);
        drawMarble(gc, 450, 690, fullName, 80, 80);
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
                drawBoard(n, gc, 700, 0);
            } else {
                if(navigatorBoard == 0){
                    drawBoard(n, gc, 1260, 0);
                }
                if(navigatorBoard == 1){
                    drawBoard(n, gc, 700, 490);
                }
                if(navigatorBoard == 2){
                    drawBoard(n, gc, 1260, 490);
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
                    drawBoard(n, gc, 700, 0);
                }
            } else {
                if(navigatorBoard == 0){
                    if(n.getNickname().equals(ClientMain.getCurrentP())){
                        drawBoard(n, gc, 1260, 0);
                    }
                }
                if(navigatorBoard == 1){
                    if(n.getNickname().equals(ClientMain.getCurrentP())){
                        drawBoard(n, gc, 700, 490);
                    }
                }
                if(navigatorBoard == 2){
                    if(n.getNickname().equals(ClientMain.getCurrentP())){
                        drawBoard(n, gc, 1260, 490);
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

    private void drawMarble(GraphicsContext gc, int x, int y, String name, int l1, int l2) {
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
        drawWarehouseDepots(n, gc, x, y);
        drawActiveCards(n, gc, x, y);
    }


    private void drawFaith(GraphicsContext gc, int x, int y, int faithNumber){ //<--FIXME da sistemare posizione e dimensione delle cose da quà in giù-->
        Image faith = new Image("Misc/FaithMarker.png");
        if(faithNumber == 0){
            gc.drawImage(faith, x+25, y+75, 20, 30);
        }if(faithNumber == 1){
            gc.drawImage(faith, x+45, y+75, 20, 30);
        }if(faithNumber == 2){
            gc.drawImage(faith, x+85, y+75, 20, 30);
        }if(faithNumber == 3){
            gc.drawImage(faith, x+85, y+40, 20, 30);
        }if(faithNumber == 4){
            gc.drawImage(faith, x+85, y+10, 20, 30);
        }if(faithNumber == 5){
            gc.drawImage(faith, x+95, y+10, 20, 30);
        }if(faithNumber == 6){
            gc.drawImage(faith, x+115, y+10, 20, 30);
        }if(faithNumber == 7){
            gc.drawImage(faith, x+135, y+10, 20, 30);
        }if(faithNumber == 8){
            gc.drawImage(faith, x+155, y+10, 20, 30);
        }if(faithNumber == 9){
            gc.drawImage(faith, x+175, y+10, 20, 30);
        }if(faithNumber == 10){
            gc.drawImage(faith, x+175, y+50, 20, 30);
        }if(faithNumber == 11){
            gc.drawImage(faith, x+175, y+70, 20, 30);
        }if(faithNumber == 12){
            gc.drawImage(faith, x+195, y+70, 20, 30);
        }if(faithNumber == 13){
            gc.drawImage(faith, x+215, y+70, 20, 30);
        }if(faithNumber == 14){
            gc.drawImage(faith, x+235, y+70, 20, 30);
        }if(faithNumber == 15){
            gc.drawImage(faith, x+255, y+70, 20, 30);
        }if(faithNumber == 16){
            gc.drawImage(faith, x+275, y+70, 20, 30);
        }if(faithNumber == 17){
            gc.drawImage(faith, x+275, y+50, 20, 30);
        }if(faithNumber == 18){
            gc.drawImage(faith, x+275, y+10, 20, 30);
        }if(faithNumber == 19){
            gc.drawImage(faith, x+295, y+10, 20, 30);
        }if(faithNumber == 20){
            gc.drawImage(faith, x+315, y+10, 20, 30);
        }if(faithNumber == 21){
            gc.drawImage(faith, x+335, y+10, 20, 30);
        }if(faithNumber == 22){
            gc.drawImage(faith, x+355, y+10, 20, 30);
        }if(faithNumber == 23){
            gc.drawImage(faith, x+375, y+10, 20, 30);
        }if(faithNumber == 24){
            gc.drawImage(faith, x+395, y+10, 20, 30);
        }
    }

    private void drawWarehouseDepots(PlayerGame.PlayerPlayer n, GraphicsContext gc, int x, int y){
        Resource[] resource = n.getWarehouseDepots().getResource();
        if(resource[0] != null){
            String fullName = selectMarble(resource[0]);
            drawMarble(gc, x+70, y+220, fullName, 20, 20);
        }if(resource[1] != null){
            String fullName = selectMarble(resource[1]);
            drawMarble(gc, x+60, y+265, fullName, 20, 20);
        }if(resource[2] != null){
            String fullName = selectMarble(resource[2]);
            drawMarble(gc, x+80, y+265, fullName, 20, 20);
        }if(resource[3] != null){
            String fullName = selectMarble(resource[3]);
            drawMarble(gc, x+20, y+300, fullName, 20, 20);
        }if(resource[4] != null){
            String fullName = selectMarble(resource[4]);
            drawMarble(gc, x+55, y+300, fullName, 20, 20);
        }if(resource[5] != null){
            String fullName = selectMarble(resource[5]);
            drawMarble(gc, x+90, y+300, fullName, 20, 20);
        }
    }

    private void drawActiveCards(PlayerGame.PlayerPlayer n, GraphicsContext gc, int x, int y) {
        DevelopmentCard[][] visualize = n.getSlotsDevelopmentCards().getSlot();
        if(visualize[0][0] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[0][0].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 300, y+400, fullName, 70, 110);
        }if(visualize[0][1] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[0][1].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 300, y+400, fullName, 70, 110);
        }if(visualize[0][2] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[0][2].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 300, y+400, fullName, 70, 110);
        }if(visualize[1][0] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[1][0].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 300, y+400, fullName, 70, 110);
        }if(visualize[1][1] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[1][1].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 300, y+400, fullName, 70, 110);
        }if(visualize[1][2] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[1][2].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 300, y+400, fullName, 70, 110);
        }if(visualize[2][0] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[2][0].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 300, y+400, fullName, 70, 110);
        }if(visualize[2][1] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[2][1].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 300, y+400, fullName, 70, 110);
        }if(visualize[2][2] != null){
            String name = n.getSlotsDevelopmentCards().getSlot()[2][2].export();
            String fullName = "DevelopmentCards/" + name + ".png";
            drawCards(gc, x+ 300, y+400, fullName, 70, 110);
        }
    }
}
