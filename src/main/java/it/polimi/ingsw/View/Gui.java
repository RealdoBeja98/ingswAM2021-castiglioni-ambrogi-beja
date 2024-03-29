package it.polimi.ingsw.View;
import it.polimi.ingsw.Enums.FavorTiles;
import it.polimi.ingsw.Enums.Resource;
import it.polimi.ingsw.Game.PlayerGame;
import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Mains.GuiThread;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import it.polimi.ingsw.Table.Decks.Leader.ExtraStorageLeaderCard;
import it.polimi.ingsw.Table.Decks.Leader.LeaderCard;
import it.polimi.ingsw.Table.Decks.Token.ActionToken;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.application.Platform;
import java.util.ArrayList;


/**
 * Class of gui
 */
public class Gui extends View{

    private final Canvas canvas;

    /**
     * Constructor of this class
     * @param canvas canvas
     */
    public Gui(Canvas canvas){
        this.canvas = canvas;
    }

    /**
     * overrides the show starting lc
     */
    @Override
    public void showStartingLC() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                _showStartingLC();
            }
        }     );
    }

    /**
     * this method shows the starting leader card
     */
    private void _showStartingLC() {
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

    /**
     * overrides the method showmarket
     */
    @Override
    public void showMarket() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                _showMarket();
            }
        }     );
    }

    /**
     * this method shows to player the market
     */
    private void _showMarket() {
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

    /**
     * overrides the show development card
     */
    @Override
    public void showDevCard() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                _showDevCard();
            }
        }     );
    }

    /**
     * This method shows the development card to others
     */
    public void _showDevCard() {
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

    /**
     * overrides the show personal board
     */
    @Override
    public void showPersonalBoard() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                _showPersonalBoard();
            }
        }     );
    }

    /**
     * this method shows the personal board
     */
    public void _showPersonalBoard() {
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
        if(ClientMain.getPlayerGame().getLorenzoTrack() != null){
            printLorenzoFaithTrack(gc);
        }
    }

    //<--FIXME--> [sorry prof non è bellissima ma funziona] sitemare costanti e switch

    /**
     *overrides the current personal board
     */
    @Override
    public void showPBCurrent() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                _showPBCurrent();
            }
        }     );
    }

    //<--FIXME--> [sorry prof non è bellissima ma funziona] almeno uno switch; usa un array delle posizioni

    /**
     * this method shows the current personal board
     */
    private void _showPBCurrent() {
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
        if(ClientMain.getPlayerGame().getLorenzoTrack() != null){
            printLorenzoFaithTrack(gc);
        }
    }

    /**
     * this method prints the faith track of lorenza
     * @param gc graphics
     */
    private void printLorenzoFaithTrack(GraphicsContext gc){
        drawLorenzoBoard(gc, 1360, 0);
    }

    /**
     * this method lets you to draw cards
     * @param gc graphics
     * @param x position
     * @param y position
     * @param name name of the card
     * @param l1 leader card 1
     * @param l2 leader card 2
     */
    private void drawCards(GraphicsContext gc, int x, int y, String name, int l1, int l2) {
        Image img = loadImage(name);
        gc.drawImage(img, x, y, l1, l2);
    }

    /**
     * this method lets you to choose the marbles
     * @param marble marble
     * @return string with name of marble chosen
     */
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

    /**
     * This method lets you chose the resource
     * @param resource resource
     * @return string with the name of the resource chosen
     */
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

    /**
     * this methods draws a square
     * @param gc graphics
     * @param x position
     * @param y position
     * @param name name
     * @param l1 position
     * @param l2 position
     */
    public void drawLittleSquare(GraphicsContext gc, int x, int y, String name, int l1, int l2) {
        Image img = loadImage(name);
        gc.drawImage(img, x, y, l1, l2);
    }

    /**
     * This methods creates new images
     * @param name: of the image
     * @return the image, of type Image
     */
    private Image loadImage(String name){
        Image pb = new Image(name);
        if(pb == null){
            System.out.println("cannot find: " + name);
        }
        return pb;
    }

    /**
     * this method draws the board
     * @param n current player
     * @param gc graphics
     * @param x position
     * @param y position
     */
    private void drawBoard(PlayerGame.PlayerPlayer n, GraphicsContext gc, int x, int y){
        Image pb = loadImage("Misc/PersonalBoard.png");
        gc.drawImage(pb, x, y, 560, 490);

        Image imgInk = null;
        if(n.getInkwell()){
            imgInk = loadImage("Misc/Inkwell.png");
        } else {
            imgInk = loadImage("Misc/NoInkwell.png");
        }
        gc.drawImage(imgInk, x, y, 20, 20);

        Image whiteBackGround = loadImage("Misc/WhiteBackground.png");
        gc.drawImage(whiteBackGround, x+20, y, 110, 20);


        String nickname = n.getNickname();
        gc.fillText(nickname, x+20, y+14);
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
        drawActualScoreVictoryPointsString(n, gc, x, y);
    }

    /**
     * this method draws the lorenzo's board
     * @param gc graphics
     * @param x position
     * @param y position
     */
    private void drawLorenzoBoard(GraphicsContext gc, int x, int y){
        Image pb = loadImage("Misc/PersonalBoard.png");
        gc.drawImage(pb, x, y, 560, 490);
        Image noInkwell = loadImage("Misc/NoInkwell.png");
        gc.drawImage(noInkwell, x, y, 20, 20);
        Image whiteBackGround = loadImage("Misc/WhiteBackground.png");
        gc.drawImage(whiteBackGround, x+20, y, 110, 20);
        String nickname = "Lorenzo";
        gc.fillText(nickname, x+20, y+14);
        drawLorenzoFaith(gc, x, y, ClientMain.getPlayerGame().getLorenzoTrack().getFaithMarker());
    }

    /**
     * this method draws the faith
     * @param gc graphics
     * @param x position
     * @param y position
     * @param faithNumber number of faith
     */
    private void drawFaith(GraphicsContext gc, int x, int y, int faithNumber){
        drawFaithMarker("Misc/FaithMarker.png", gc, x, y, faithNumber);
    }

    /**
     * this method draws the lorenzo's faith
     * @param gc graphics
     * @param x position
     * @param y position
     * @param faithNumber number of faith
     */
    private void drawLorenzoFaith(GraphicsContext gc, int x, int y, int faithNumber){
        drawFaithMarker("Misc/BlackCrossToken.png", gc, x, y, faithNumber);
        Image cross = loadImage("Misc/cross.png");
        gc.drawImage(cross, x+10, y+168, 540, 310);
    }

    //<--FIXME--> [sorry prof non è bellissima ma funziona] fare json per caricare questi valori

    /**
     * this method draws the faith marker
     * @param imageName imageName
     * @param gc graphics
     * @param x position
     * @param y position
     * @param faithNumber number
     */
    private void drawFaithMarker(String imageName, GraphicsContext gc, int x, int y, int faithNumber){
        Image faithImage = loadImage(imageName);
        if(faithNumber == 0){
            gc.drawImage(faithImage, x+25, y+100, 17, 24);
        }if(faithNumber == 1){
            gc.drawImage(faithImage, x+55, y+100, 17, 24);
        }if(faithNumber == 2){
            gc.drawImage(faithImage, x+80, y+100, 17, 24);
        }if(faithNumber == 3){
            gc.drawImage(faithImage, x+80, y+66, 17, 24);
        }if(faithNumber == 4){
            gc.drawImage(faithImage, x+80, y+32, 17, 24);
        }if(faithNumber == 5){
            gc.drawImage(faithImage, x+107, y+32, 17, 24);
        }if(faithNumber == 6){
            gc.drawImage(faithImage, x+135, y+32, 17, 24);
        }if(faithNumber == 7){
            gc.drawImage(faithImage, x+163, y+32, 17, 24);
        }if(faithNumber == 8){
            gc.drawImage(faithImage, x+190, y+32, 17, 24);
        }if(faithNumber == 9){
            gc.drawImage(faithImage, x+217, y+32, 17, 24);
        }if(faithNumber == 10){
            gc.drawImage(faithImage, x+217, y+66, 17, 24);
        }if(faithNumber == 11){
            gc.drawImage(faithImage, x+217, y+100, 17, 24);
        }if(faithNumber == 12){
            gc.drawImage(faithImage, x+244, y+100, 17, 24);
        }if(faithNumber == 13){
            gc.drawImage(faithImage, x+272, y+100, 17, 24);
        }if(faithNumber == 14){
            gc.drawImage(faithImage, x+300, y+100, 17, 24);
        }if(faithNumber == 15){
            gc.drawImage(faithImage, x+327, y+100, 17, 24);
        }if(faithNumber == 16){
            gc.drawImage(faithImage, x+354, y+100, 17, 24);
        }if(faithNumber == 17){
            gc.drawImage(faithImage, x+354, y+66, 17, 24);
        }if(faithNumber == 18){
            gc.drawImage(faithImage, x+354, y+32, 17, 24);
        }if(faithNumber == 19){
            gc.drawImage(faithImage, x+381, y+32, 17, 24);
        }if(faithNumber == 20){
            gc.drawImage(faithImage, x+409, y+32, 17, 24);
        }if(faithNumber == 21){
            gc.drawImage(faithImage, x+438, y+32, 17, 24);
        }if(faithNumber == 22){
            gc.drawImage(faithImage, x+464, y+32, 17, 24);
        }if(faithNumber == 23){
            gc.drawImage(faithImage, x+491, y+32, 17, 24);
        }if(faithNumber == 24){
            gc.drawImage(faithImage, x+518, y+32, 17, 24);
        }
    }

    /**
     * this method draws the favor tiles
     * @param gc graphics
     * @param x position
     * @param y position
     * @param favorTiles favor tiles
     */
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
            Image image = loadImage(fullName);
            gc.drawImage(image, x+xx, y+yy, 33, 40);
        }
    }

    //<--FIXME--> [sorry prof non è bellissima ma funziona] fare mappe; fullName è un invariante per le quadre

    /**
     * this method draws the warehouse depots
     * @param n player
     * @param gc graphics
     * @param x position
     * @param y position
     */
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

    //<--FIXME--> [sorry prof non è bellissima ma funziona] fare questo più elegante

    /**
     * this method draws the active cards
     * @param n player
     * @param gc graphics
     * @param x position
     * @param y position
     */
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

    /**
     * this method draws the scarded cards
     * @param n player
     * @param gc graphics
     * @param x position
     * @param y position
     */
    private void drawDiscardedCards(PlayerGame.PlayerPlayer n, GraphicsContext gc, int x, int y){
        String name = "NullLeaderCard";
        String fullName = "LeaderCard/" + name + ".png";
        drawCards(gc, x+ 560, y+ 0, fullName, 70, 110);
        drawCards(gc, x+ 560, y+ 110, "Labels/DiscardedCard.png", 70, 25);
        drawCards(gc, x+ 560, y+ 245, fullName, 70, 110);
        drawCards(gc, x+ 560, y+ 355, "Labels/DiscardedCard.png", 70, 25);
    }

    /**
     * this method draws the ards on table
     * @param n player
     * @param gc graphics
     * @param x position
     * @param y position
     */
    private void drawCardsOnTable(PlayerGame.PlayerPlayer n, GraphicsContext gc, int x, int y){
        LeaderCard[] cardsOnTable = n.getCardsOnTable();
        if(cardsOnTable[0] != null){
            String name = cardsOnTable[0].export();
            String fullName = "LeaderCard/" + name + ".png";
            drawCards(gc, x+ 560, y+ 0, fullName, 70, 110);
            drawCards(gc, x+ 560, y+ 110, "Labels/CardInPlay.png", 70, 25);
            int resourcesInTheCard = 0;
            if(cardsOnTable[0] instanceof ExtraStorageLeaderCard){
                resourcesInTheCard = ((ExtraStorageLeaderCard)cardsOnTable[0]).occupiedResources();
            }
            String tickName = "Misc/" + "tick" + ".png";
            if(resourcesInTheCard >= 1){
                drawLittleSquare(gc, x+574, y+60, tickName, 20, 20);
            }
            if(resourcesInTheCard >= 2){
                drawLittleSquare(gc, x+586, y+60, tickName, 20, 20);
            }
        }
        if(cardsOnTable[1] != null){
            String name = cardsOnTable[1].export();
            String fullName = "LeaderCard/" + name + ".png";
            drawCards(gc, x+ 560, y+ 245, fullName, 70, 110);
            drawCards(gc, x+ 560, y+ 355, "Labels/CardInPlay.png", 70, 25);
            int resourcesInTheCard = 0;
            if(cardsOnTable[1] instanceof ExtraStorageLeaderCard){
                resourcesInTheCard = ((ExtraStorageLeaderCard)cardsOnTable[1]).occupiedResources();
            }
            String tickName = "Misc/" + "tick" + ".png";
            if(resourcesInTheCard >= 1){
                drawLittleSquare(gc, x+574, y+280, tickName, 20, 20);
            }
            if(resourcesInTheCard >= 2){
                drawLittleSquare(gc, x+586, y+280, tickName, 20, 20);
            }
        }
    }

    //<--FIXME--> [sorry prof non è bellissima ma funziona] risolvere codici duplicati (anche i nomi delle label sono duplicati)

    /**
     * this method draws the cards in hand
     * @param n player
     * @param gc graphics
     * @param x position
     * @param y position
     */
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

    /**
     * this method draws the trong box
     * @param n player
     * @param gc graphics
     * @param x position
     * @param y position
     */
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

    /**
     * this method draws the actual score of victory points
     * @param n player
     * @param gc graphics
     * @param x position
     * @param y position
     */
    private void drawActualScoreVictoryPointsString(PlayerGame.PlayerPlayer n, GraphicsContext gc, int x, int y){
        int actualScoreVictoryPoints = n.calculateVictoryPoints();
        drawNumber(gc, x+520, y+470, actualScoreVictoryPoints);
    }

    /**
     * this method draws the number
     * @param gc graphics
     * @param x position
     * @param y position
     */
    private void drawNumber(GraphicsContext gc, int x, int y, int number){
        int c2 = number % 10;
        int c1 = number / 10;
        drawLittleSquare(gc, x+0, y+0, "Numbers/" + c1 + ".png", 20, 20);
        drawLittleSquare(gc, x+20, y+0, "Numbers/" + c2 + ".png", 20, 20);
    }

    /**
     * overrides the solo action token
     * @param actionToken action token
     */
    @Override
    public void showSoloActionToken(ActionToken actionToken) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                _showSoloActionToken(actionToken);
            }
        }     );
    }

    /**
     * this method shows the solo action token
     * @param actionToken action token
     */
    private void _showSoloActionToken(ActionToken actionToken) {
        System.out.println("UPDATE_SOLO_ACTION_TOKEN" + " " + actionToken.getWhatIAm());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        String name = "";
        switch(actionToken.getWhatIAm()){
            case BLUE:
                name = "BlueSoloToken";
                break;
            case GREEN:
                name = "GreenSoloToken";
                break;
            case YELLOW:
                name = "YellowSoloToken";
                break;
            case PURPLE:
                name = "PurpleSoloToken";
                break;
            case BLACKCROSS1:
                name = "BlackCross1SoloToken";
                break;
            case BLACKCROSS2:
                name = "BlackCross2SoloToken";
                break;
        }
        String fullName = "SoloActionToken/" + name + ".png";

        drawLittleSquare(gc, 320, 955, fullName,40, 40);
    }

    /**
     * reload the view
     */
    public void reloadView(){
        if(!ClientMain.getPlayerGame().gameReallyStarted()){
            return;
        }
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image img = loadImage("Misc/SemiBackGround.png");
        gc.drawImage(img, 610, 0, 1995, 1025);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                _showDevCard();
                _showMarket();
                _showPersonalBoard();
            }
        }     );
    }

}
