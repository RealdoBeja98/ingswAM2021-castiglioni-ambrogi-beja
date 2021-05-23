package it.polimi.ingsw.Mains;
import it.polimi.ingsw.Enums.*;
import it.polimi.ingsw.Messages.GameMessages.*;
import it.polimi.ingsw.Messages.Message;
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
import javafx.stage.Stage;

import java.io.PrintWriter;

public class GuiThread extends Application implements Runnable{

    private static PrintWriter out;
    private int state = 0;
    private LeaderCardState leaderCardState = null;

    public static void setOut(PrintWriter out) {
        GuiThread.out = out;
    }

    @Override
    public void run(){
        launch();
        return;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Masters of Renaissance" + " - " + ClientMain.getClientNick());
        Group root = new Group();
        Canvas canvas = new Canvas(1995, 1025);
        ClientMain.setCanvas(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image img = new Image("Misc/BackGround.png");
        gc.drawImage(img, 0, 0, 1995, 1025);

        root.getChildren().add(canvas);
        buttonTurn(root, stage);
        Scene scene = new Scene(root, 1995, 1025);
        stage.setScene(scene);
        stage.show();
    }

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

        addLeaderCardButton(root, "_LC_1", 1307, 135, 1);
        addLeaderCardButton(root, "_LC_2", 1307, 380, 2);

        addLeaderCardStateButton(root, "_DISC_LC", 615, 528, LeaderCardState.DISCARD_LEADER_CARD);
        addLeaderCardStateButton(root, "_PLAY_LC", 615, 563, LeaderCardState.PLAY_LEADER_CARD);
        addLeaderCardStateButton(root, "_ADD_R_LC", 615, 598, LeaderCardState.ADD_RESOURCE_TO_LEADER_CARD);
        addLeaderCardStateButton(root, "_CH_W_M_W", 615, 633, LeaderCardState.CHANGE_WHITE_MARBLE_WITH);
        addLeaderCardStateButton(root, "_PAY_W_ESLC", 615, 668, LeaderCardState.PAY_WITH_EXTRA_STORAGE_LEADER_CARD);
        addLeaderCardStateButton(root, "_SEL_PPLC", 615, 703, LeaderCardState.SELECT_PRODUCTION_POWER_LEADER_CARD);
        addLeaderCardStateButton(root, "_WD_TO_LC", 615, 738, LeaderCardState.MOVE_RESOURCES_WAREHOUSE_TO_ES_LC);
        addLeaderCardStateButton(root, "_LC_TO_WD", 615, 773, LeaderCardState.MOVE_RESOURCE_ES_LC_TO_WAREHOUSE);

        addButton(root, "_START_PAY", 615, 863, new StartPaymentGameMessage());
        addButton(root, "_DRAW_SOLO", 615, 898, new DrawSoloActionTokenGameMessage());

        addButton(root, "_P", 811, 351, new PayWithStrongboxGameMessage(Resource.COIN));
        addButton(root, "_OG", 839, 351, new ObtainGenericResourceGameMessage(Resource.COIN));
        addButton(root, "_P", 811, 379, new PayWithStrongboxGameMessage(Resource.STONE));
        addButton(root, "_OG", 839, 379, new ObtainGenericResourceGameMessage(Resource.STONE));
        addButton(root, "_P", 811, 407, new PayWithStrongboxGameMessage(Resource.SERVANT));
        addButton(root, "_OG", 839, 407, new ObtainGenericResourceGameMessage(Resource.SERVANT));
        addButton(root, "_P", 811, 435, new PayWithStrongboxGameMessage(Resource.SHIELD));
        addButton(root, "_OG", 839, 435, new ObtainGenericResourceGameMessage(Resource.SHIELD));



        addWhereButton(root, "_W0", 830, 190, 0);
        addWhereButton(root, "_W1", 755, 255, 1);
        addWhereButton(root, "_W2", 825, 255, 2);
        addWhereButton(root, "_W3", 745, 300, 3);
        addWhereButton(root, "_W4", 790, 320, 4);
        addWhereButton(root, "_W5", 835, 300, 5);

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

        addButton(root, "_S_BP", 885, 450, new SelectDefaultProductionPowerGameMessage());
        addButton(root, "_S_P1", 977, 450, new SelectProductionDevelopmentCardGameMessage(1));
        addButton(root, "_S_P2", 1087, 450, new SelectProductionDevelopmentCardGameMessage(2));
        addButton(root, "_S_P3", 1197, 450, new SelectProductionDevelopmentCardGameMessage(3));

        addButton(root, "_T_R1", 330, 720, new TakeResourcesFromTheMarketGameMessage(RowColumn.ROW,1));
        addButton(root, "_T_R2", 330, 800, new TakeResourcesFromTheMarketGameMessage(RowColumn.ROW,2));
        addButton(root, "_T_R3", 330, 880, new TakeResourcesFromTheMarketGameMessage(RowColumn.ROW,3));
        addButton(root, "_T_C1", 20, 940, new TakeResourcesFromTheMarketGameMessage(RowColumn.COLUMN,1));
        addButton(root, "_T_C2", 100, 940, new TakeResourcesFromTheMarketGameMessage(RowColumn.COLUMN,2));
        addButton(root, "_T_C3", 180, 940, new TakeResourcesFromTheMarketGameMessage(RowColumn.COLUMN,3));
        addButton(root, "_T_C3", 260, 940, new TakeResourcesFromTheMarketGameMessage(RowColumn.COLUMN,4));

        addQuitButton(root, "_QUIT", 615, 940, "quit", stage);
    }

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

        TilePane r = new TilePane();
        root.getChildren().add(b);
    }

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

        TilePane r = new TilePane();
        root.getChildren().add(b);
    }

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

        TilePane r = new TilePane();
        root.getChildren().add(b);
    }

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

        TilePane r = new TilePane();
        root.getChildren().add(b);
    }

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

        TilePane r = new TilePane();
        root.getChildren().add(b);
    }

}
