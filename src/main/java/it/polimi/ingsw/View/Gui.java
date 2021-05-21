package it.polimi.ingsw.View;

import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Game.PlayerGame;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Mains.GuiThread;
import it.polimi.ingsw.Table.Decks.Leader.LeaderCard;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Gui extends View{

    private Canvas canvas;

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
                String name = "NullMarble";
                Resource marble = ClientMain.getPlayerGame().getMarket().getMarketTray()[i][j].getWhatIAm();
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
                String fullName = "Marbles/" + name + ".png";
                drawMarble(gc, j * 80, i * 80 + 690, fullName);
            }
        }
        String name = "NullMarble";
        Resource extraMarble = ClientMain.getPlayerGame().getMarket().getExtraMarble().getWhatIAm();
        if(extraMarble == null){
            name = "NullMarble";
        } else {
            switch (extraMarble) {
                case COIN:
                    name = "CoinMarble";
                    break;
                case FAITH:
                    name = "FaithMarble";
                    break;
                case SERVANT:
                    name = "ServantMarble";
                    break;
                case SHIELD:
                    name = "ShieldMarble";
                    break;
                case STONE:
                    name = "StoneMarble";
                    break;
                case WHITE:
                    name = "WhiteMarble";
                    break;
            }
        }
        String fullName = "Marbles/" + name + ".png";
        drawMarble(gc, 450, 690, fullName);
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
                drawCards(gc, i * 150, j * 230, fullName);
            }
        }
    }

    @Override
    public void showPersonalBoard() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        int navigatorBoard = 0;
        ArrayList<PlayerGame.PlayerPlayer> players = ClientMain.getPlayerGame().getPlayers();
        for(PlayerGame.PlayerPlayer n : players){
            String nickname = n.getNickname();
            if(nickname.equals(ClientMain.getClientNick())){
                gc.fillText(nickname, 700, 20);

            } else {
                if(navigatorBoard == 0){
                    gc.fillText(nickname, 1260, 20);
                }
                if(navigatorBoard == 1){
                    gc.fillText(nickname, 700, 510);
                }
                if(navigatorBoard == 2){
                    gc.fillText(nickname, 1260, 510);
                }
                navigatorBoard++;
            }
        }

    }

    @Override
    public void showPBCurrent() {

    }

    private void drawCards(GraphicsContext gc, int x, int y, String name) {
        Image img = new Image(name);
        gc.drawImage(img, x, y, 150, 230);
    }

    private void drawMarble(GraphicsContext gc, int x, int y, String name) {
        Image img = new Image(name);
        gc.drawImage(img, x, y, 80, 80);
    }

}
