package it.polimi.ingsw.Mains;
import it.polimi.ingsw.Enums.*;
import it.polimi.ingsw.Game.PlayerGame;
import it.polimi.ingsw.Messages.GameMessages.*;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Table.Decks.Leader.LeaderCard;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.PrintWriter;

//<--FIXME--> mettiamo bottone per il debug

/**
 * Class of gui thread
 */
public class GuiThread extends Application implements Runnable{

    private static PrintWriter out;
    private int state = 0;
    private int[] val = {0,0};
    private LeaderCardState leaderCardState = null;
    private static boolean cardPrinted = false;
    private static GuiThread instance;
    private final static Object lock = new Object();
    private static boolean isSetBackground = false;

    public static boolean getIsSetBackground(){
        return GuiThread.isSetBackground;
    }

    /**
     * Constructor of the class
     */
    public GuiThread(){
        instance = this;
    }

    /**
     * Prints the card
     */
    public static void setCardPrinted() {
        GuiThread.cardPrinted = true;
    }

    /**
     * Sets parameter out to other classes
     * @param out sends message to socket
     */
    public static void setOut(PrintWriter out) {
        GuiThread.out = out;
        synchronized (GuiThread.lock){
            GuiThread.lock.notifyAll();
        }
    }

    /**
     * Launches the thread gui
     */
    @Override
    public void run(){

        //try{/////
            launch();
            return;
        //} catch (Exception e){/////
            //e.printStackTrace();/////
        //}/////

    }

    /**
     * This method creates the gui, stages canvas scene
     * @param stage scene
     */
    @Override
    public void start(Stage stage) throws Exception {

        //try{/////
            stage.setTitle("Masters of Renaissance" + " - " + ClientMain.getClientNick());
            Group root = new Group();
            Canvas canvas = new Canvas(1995, 1025);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            Image img = new Image("Misc/BackGround.png");
            ClientMain.setCanvas(canvas);
            synchronized (GuiThread.lock){
                while(out == null){
                    GuiThread.lock.wait();
                }
            }
            gc.drawImage(img, 0, 0, 1995, 1025);
            //System.out.println("STATO APPENA SETTATO LO SFONDO");
            GuiThread.isSetBackground = true;
            ClientMain.notifyClientMain();

            root.getChildren().add(canvas);
            buttonTurn(root, stage);
            popper(root, img, gc);
            Scene scene = new Scene(root, 1995, 1025);
            stage.setScene(scene);
            stage.show();
        //} catch (Exception e){/////
            //e.printStackTrace();/////
        //}/////

    }

    /**
     * This methods creates the buttons for the turn
     * @param root group root
     * @param stage stage
     */
    private void buttonTurn(Group root, Stage stage){
        addButton(root, "_SEL_NO_A_LC", 615, 0, new ChooseNoActionLeaderCardGameMessage());
        addButton(root, "_SEL_DISC_LC", 615, 35, new ChooseDiscardLeaderCardGameMessage());
        addButton(root, "_SEL_PLAY_LC", 615, 70, new ChoosePlayLeaderCardGameMessage());
        addButton(root, "_NA_TAKE", 615, 105, new SelectNormalActionGameMessage(NormalAction.TAKE_RESOURCES_FROM_THE_MARKET));
        addButton(root, "_NA_BUY", 615, 140, new SelectNormalActionGameMessage(NormalAction.BUY_DEVELOPMENT_CARD));
        addButton(root, "_NA_PROD", 615, 175, new SelectNormalActionGameMessage(NormalAction.ACTIVATE_PRODUCTION));
        addButton(root, "_END_TURN", 615, 210, new EndTurnGameMessage());

        addStateButton(root, "_ADD_R_WD", 615, 300, 1);
        addStateButton(root, "_SELECT", 615, 335, 2);
        addStateButton(root, "_WD_TO_WD", 615, 370, 3);
        addStateButton(root, "_PAY_W_WD", 615, 405, 6);
        addButton(root, "_DISCARD", 615, 440, new AddResourceToGameMessage(LeaderWarehouse.DISCARD, 0));

        addLeaderCardButton(root, "_LC_1", 1292, 135, 1);
        addLeaderCardButton(root, "_LC_2", 1292, 380, 2);

        addLeaderCardStateButton(root, "_DISC_LC", 615, 508, LeaderCardState.DISCARD_LEADER_CARD);
        addLeaderCardStateButton(root, "_PLAY_LC", 615, 543, LeaderCardState.PLAY_LEADER_CARD);
        addLeaderCardStateButton(root, "_ADD_R_LC", 615, 578, LeaderCardState.ADD_RESOURCE_TO_LEADER_CARD);
        addLeaderCardStateButton(root, "_CH_W_M_W", 615, 613, LeaderCardState.CHANGE_WHITE_MARBLE_WITH);
        addLeaderCardStateButton(root, "_PAY_W_ESLC", 615, 648, LeaderCardState.PAY_WITH_EXTRA_STORAGE_LEADER_CARD);
        addLeaderCardStateButton(root, "_SEL_PPLC", 615, 683, LeaderCardState.SELECT_PRODUCTION_POWER_LEADER_CARD);
        addLeaderCardStateButton(root, "_WD_TO_LC", 615, 718, LeaderCardState.MOVE_RESOURCES_WAREHOUSE_TO_ES_LC);
        addLeaderCardStateButton(root, "_LC_TO_WD", 615, 753, LeaderCardState.MOVE_RESOURCE_ES_LC_TO_WAREHOUSE);

        addStateButton(root, "_SEL_PPPS", 615, 803, 10);
        addStateButton(root, "_PLACE_DC", 615, 833, 11);


        addButton(root, "_START_PAY", 615, 873, new StartPaymentGameMessage());

        addButton(root, "_DRAW_SOLO", 615, 918, new DrawSoloActionTokenGameMessage());

        addButton(root, "_P", 791, 351, new PayWithStrongboxGameMessage(Resource.COIN));
        addButton(root, "_OG", 819, 351, new ObtainGenericResourceGameMessage(Resource.COIN));
        addButton(root, "_P", 791, 379, new PayWithStrongboxGameMessage(Resource.STONE));
        addButton(root, "_OG", 819, 379, new ObtainGenericResourceGameMessage(Resource.STONE));
        addButton(root, "_P", 791, 407, new PayWithStrongboxGameMessage(Resource.SERVANT));
        addButton(root, "_OG", 819, 407, new ObtainGenericResourceGameMessage(Resource.SERVANT));
        addButton(root, "_P", 791, 435, new PayWithStrongboxGameMessage(Resource.SHIELD));
        addButton(root, "_OG", 819, 435, new ObtainGenericResourceGameMessage(Resource.SHIELD));



        addWhereButton(root, "_W0", 777, 190, 0);
        addWhereButton(root, "_W1", 740, 255, 1);
        addWhereButton(root, "_W2", 810, 255, 2);
        addWhereButton(root, "_W3", 730, 300, 3);
        addWhereButton(root, "_W4", 775, 320, 4);
        addWhereButton(root, "_W5", 820, 300, 5);

        addButton(root, "C11", 55, 50, new BuyDevelopmentCardGameMessage(1, 1));
        addButton(root, "C12", 205, 50, new BuyDevelopmentCardGameMessage(1, 2));
        addButton(root, "C13", 355, 50, new BuyDevelopmentCardGameMessage(1, 3));
        addButton(root, "C14", 505, 50, new BuyDevelopmentCardGameMessage(1, 4));
        addButton(root, "C21", 55, 280, new BuyDevelopmentCardGameMessage(2, 1));
        addButton(root, "C22", 205, 280, new BuyDevelopmentCardGameMessage(2, 2));
        addButton(root, "C23", 355, 280, new BuyDevelopmentCardGameMessage(2, 3));
        addButton(root, "C24", 505, 280, new BuyDevelopmentCardGameMessage(2, 4));
        addButton(root, "C41", 55, 510, new BuyDevelopmentCardGameMessage(3, 1));
        addButton(root, "C42", 205, 510, new BuyDevelopmentCardGameMessage(3, 2));
        addButton(root, "C43", 355, 510, new BuyDevelopmentCardGameMessage(3, 3));
        addButton(root, "C44", 505, 510, new BuyDevelopmentCardGameMessage(3, 4));

        addButton(root, "_S_BP", 875, 450, new SelectDefaultProductionPowerGameMessage());
        addSlotProductionButton(root, "_P_S1", 967, 450, 1);
        addSlotProductionButton(root, "_P_S2", 1077, 450, 2);
        addSlotProductionButton(root, "_P_S3", 1187, 450, 3);

        addButton(root, "_T_R1", 330, 720, new TakeResourcesFromTheMarketGameMessage(RowColumn.ROW,1));
        addButton(root, "_T_R2", 330, 800, new TakeResourcesFromTheMarketGameMessage(RowColumn.ROW,2));
        addButton(root, "_T_R3", 330, 880, new TakeResourcesFromTheMarketGameMessage(RowColumn.ROW,3));
        addButton(root, "_T_C1", 20, 940, new TakeResourcesFromTheMarketGameMessage(RowColumn.COLUMN,1));
        addButton(root, "_T_C2", 100, 940, new TakeResourcesFromTheMarketGameMessage(RowColumn.COLUMN,2));
        addButton(root, "_T_C3", 180, 940, new TakeResourcesFromTheMarketGameMessage(RowColumn.COLUMN,3));
        addButton(root, "_T_C3", 260, 940, new TakeResourcesFromTheMarketGameMessage(RowColumn.COLUMN,4));

        addQuitButton(root, "_QUIT", 615, 960, "quit", stage);
    }

    /**
     * This method creates and adds the button
     * @param root group root
     * @param buttonName name of the button
     * @param x position
     * @param y position
     * @param message message what does the button
     */
    private void addButton(Group root, String buttonName, int x, int y, Message message){
        Button b = new Button(buttonName);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                out.println(message);
            }
        };

        b.setOnAction(event);
        b.setLayoutX(x);
        b.setLayoutY(y);

        root.getChildren().add(b);
    }

    /**
     * This method creates the quit button
     * @param root group root
     * @param buttonName name of the button
     * @param x position
     * @param y position
     * @param message message what doet the button
     * @param stage stage
     */
    private void addQuitButton(Group root, String buttonName, int x, int y, String message, Stage stage){
        Button b = new Button(buttonName);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                out.println(message);
                stage.close();
            }
        };

        b.setOnAction(event);
        b.setLayoutX(x);
        b.setLayoutY(y);

        TilePane r = new TilePane();
        root.getChildren().add(b);
    }

    /**
     * This method creates the state buttons
     * @param root group root
     * @param buttonName name of the button
     * @param x position
     * @param y position
     * @param n integer
     */
    private void addStateButton(Group root, String buttonName, int x, int y, int n){
        Button b = new Button(buttonName);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                state = n;
            }
        };

        b.setOnAction(event);
        b.setLayoutX(x);
        b.setLayoutY(y);

        root.getChildren().add(b);
    }
    /**
     * This method creates slot production button
     * @param root group root
     * @param buttonName name of the button
     * @param x position
     * @param y position
     * @param pos integer
     */
    private void addSlotProductionButton(Group root, String buttonName, int x, int y, int pos){
        Button b = new Button(buttonName);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                if(state == 10){
                    out.println(new SelectProductionDevelopmentCardGameMessage(pos));
                    state = 0;
                }else if(state == 11){
                    out.println(new PlaceDevelopmentCardGameMessage(pos));
                    state = 0;
                }
            }
        };

        b.setOnAction(event);
        b.setLayoutX(x);
        b.setLayoutY(y);

        root.getChildren().add(b);
    }

    /**
     * This method sets the place of a button
     * @param root group root
     * @param buttonName name of the button
     * @param x position
     * @param y position
     * @param pos integer
     */
    private void addWhereButton(Group root, String buttonName, int x, int y, int pos){
        Button b = new Button(buttonName);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                if(state == 1){
                    out.println(new AddResourceToGameMessage(LeaderWarehouse.WAREHOUSEDEPOTS, pos));
                    state = 0;
                }else if(state == 2){
                    out.println(new SelectAWarehouseDepotsSlotGameMessage(pos));
                    state = 0;
                }else if(state == 3){
                    out.println(new MoveResourcesInWarehouseDepotsGameMessage(pos));
                    state = 0;
                }else if(state == 6){
                    out.println(new PayWithWarehouseDepotsGameMessage(pos));
                    state = 0;
                }
            }
        };

        b.setOnAction(event);
        b.setLayoutX(x);
        b.setLayoutY(y);

        root.getChildren().add(b);
    }

    /**
     * This method creates the leader card state
     * @param root group root
     * @param buttonName name of the button
     * @param x position
     * @param y position
     * @param n state
     */
    private void addLeaderCardStateButton(Group root, String buttonName, int x, int y, LeaderCardState n){
        Button b = new Button(buttonName);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                leaderCardState = n;
            }
        };

        b.setOnAction(event);
        b.setLayoutX(x);
        b.setLayoutY(y);

        root.getChildren().add(b);
    }

    /**
     * This method creates the leader card button
     * @param root group root
     * @param buttonName name of the button
     * @param x position
     * @param y position
     * @param pos integer
     */
    private void addLeaderCardButton(Group root, String buttonName, int x, int y, int pos){
        Button b = new Button(buttonName);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                if(leaderCardState != null){
                    switch (leaderCardState){
                        case DISCARD_LEADER_CARD:
                            out.println(new DiscardLeaderCardGameMessage(pos));
                            leaderCardState = null;
                            break;
                        case PLAY_LEADER_CARD:
                            out.println(new PlayLeaderCardGameMessage(pos));
                            leaderCardState = null;
                            break;
                        case ADD_RESOURCE_TO_LEADER_CARD:
                            out.println(new AddResourceToGameMessage(LeaderWarehouse.LEADERCARD, pos));
                            leaderCardState = null;
                            break;
                        case CHANGE_WHITE_MARBLE_WITH:
                            out.println(new ChangeWhiteMarbleWithGameMessage(pos));
                            leaderCardState = null;
                            break;
                        case PAY_WITH_EXTRA_STORAGE_LEADER_CARD:
                            out.println(new PayWithExtraStorageLeaderCardGameMessage(pos));
                            leaderCardState = null;
                            break;
                        case SELECT_PRODUCTION_POWER_LEADER_CARD:
                            out.println(new SelectProductionPowerLeaderCardGameMessage(pos));
                            leaderCardState = null;
                            break;
                        case MOVE_RESOURCES_WAREHOUSE_TO_ES_LC:
                            out.println(new MoveResourcesWarehouseToESLCGameMessage(pos));
                            leaderCardState = null;
                            break;
                        case MOVE_RESOURCE_ES_LC_TO_WAREHOUSE:
                            out.println(new MoveResourceESLCToWEarehouseGameMessage(pos));
                            leaderCardState = null;
                            break;
                    }
                }
            }
        };

        b.setOnAction(event);
        b.setLayoutX(x);
        b.setLayoutY(y);

        root.getChildren().add(b);
    }

    /**
     * This method creates the 4 buttons of the in initial cards
     * @param root group root
     * @param img image
     * @param gc graphics
     */
    private void popper(Group root, Image img, GraphicsContext gc) {
        Button c1 = new Button("C1");
        Button c2 = new Button("C2");
        Button c3 = new Button("C3");
        Button c4 = new Button("C4");

        EventHandler<ActionEvent> ec1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (val[0] == 0 && cardPrinted) {
                    val[0] = 1;
                    c1.setVisible(false);
                } else if (val[1] == 0 && cardPrinted) {
                    val[1] = 1;
                    c1.setVisible(false);
                    c2.setVisible(false);
                    c3.setVisible(false);
                    c4.setVisible(false);
                }
            }
        };
        c1.setOnAction(ec1);
        c1.setLayoutX(1060);
        c1.setLayoutY(850);


        EventHandler<ActionEvent> ec2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (val[0] == 0 && cardPrinted) {
                    val[0] = 2;
                    c2.setVisible(false);
                } else if (val[1] == 0 && cardPrinted) {
                    val[1] = 2;
                    c1.setVisible(false);
                    c2.setVisible(false);
                    c3.setVisible(false);
                    c4.setVisible(false);
                }
            }
        };
        c2.setOnAction(ec2);
        c2.setLayoutX(1230);
        c2.setLayoutY(850);


        EventHandler<ActionEvent> ec3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (val[0] == 0 && cardPrinted) {
                    val[0] = 3;
                    c3.setVisible(false);
                } else if (val[1] == 0 && cardPrinted) {
                    val[1] = 3;
                    c1.setVisible(false);
                    c2.setVisible(false);
                    c3.setVisible(false);
                    c4.setVisible(false);
                }
            }
        };
        c3.setOnAction(ec3);
        c3.setLayoutX(1400);
        c3.setLayoutY(850);


        EventHandler<ActionEvent> ec4 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (val[0] == 0 && cardPrinted) {
                    val[0] = 4;
                    c4.setVisible(false);
                } else if (val[1] == 0 && cardPrinted) {
                    val[1] = 4;
                    c1.setVisible(false);
                    c2.setVisible(false);
                    c3.setVisible(false);
                    c4.setVisible(false);
                }
            }
        };
        c4.setOnAction(ec4);
        c4.setLayoutX(1570);
        c4.setLayoutY(850);

        Button ok = new Button("OK");
        EventHandler<ActionEvent> finalEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if(val[0] != 0 && val[1] != 0 && ClientMain.getClientNick().equals(ClientMain.getCurrentP())){
                    ok.setVisible(false);
                    gc.drawImage(img, 0, 0, 1995, 1025);
                    out.println(new SelectTwoCardsToKeepGameMessage(val[0], val[1]));
                }
            }
        };
        ok.setOnAction(finalEvent);
        ok.setLayoutX(1310);
        ok.setLayoutY(950);

        root.getChildren().add(c1);
        root.getChildren().add(c2);
        root.getChildren().add(c3);
        root.getChildren().add(c4);
        root.getChildren().add(ok);
    }

}
