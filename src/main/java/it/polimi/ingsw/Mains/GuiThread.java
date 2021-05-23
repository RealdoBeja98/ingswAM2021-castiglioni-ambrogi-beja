package it.polimi.ingsw.Mains;
import it.polimi.ingsw.Enums.LeaderWarehouse;
import it.polimi.ingsw.Enums.NormalAction;
import it.polimi.ingsw.Enums.RowColumn;
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
        Canvas canvas = new Canvas(1960, 980);
        ClientMain.setCanvas(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image img = new Image("Misc/BackGround.png");
        gc.drawImage(img, 0, 0, 1960, 980);

        root.getChildren().add(canvas);
        buttonTurn(root, stage);
        Scene scene = new Scene(root, 1820, 980);
        stage.setScene(scene);
        stage.show();
    }

    private void buttonTurn(Group root, Stage stage){
        addButton(root, "_NO_A_LC", 615, 0, new ChooseNoActionLeaderCardGameMessage());
        addButton(root, "_DISC_LC", 615, 35, new ChooseDiscardLeaderCardGameMessage());
        addButton(root, "_PLAY_LC", 615, 70, new ChoosePlayLeaderCardGameMessage());
        addButton(root, "_NA_TAKE", 615, 105, new SelectNormalActionGameMessage(NormalAction.TAKE_RESOURCES_FROM_THE_MARKET));
        addButton(root, "_NA_BUY", 615, 140, new SelectNormalActionGameMessage(NormalAction.BUY_DEVELOPMENT_CARD));
        addButton(root, "_NA_PROD", 615, 175, new SelectNormalActionGameMessage(NormalAction.ACTIVATE_PRODUCTION));
        addButton(root, "_END_T", 615, 210, new EndTurnGameMessage());

        addStateButton(root, "_PLACE", 615, 500, 1);
        addStateButton(root, "_SELECT", 615, 535, 2);
        addStateButton(root, "_WD_TO_WD", 615, 570, 3);
        addStateButton(root, "_WD_TO_LC", 615, 605, 4);
        addStateButton(root, "_LC_TO_WD", 615, 640, 5);
        addStateButton(root, "_PAY_W_WD", 615, 675, 6);
        addButton(root, "_DISCARD", 615, 710, new AddResourceToGameMessage(LeaderWarehouse.DISCARD, 0));
        addWhereButton(root, "_W0", 755, 190, 0, LeaderWarehouse.WAREHOUSEDEPOTS);
        addWhereButton(root, "_W1", 720, 255, 1, LeaderWarehouse.WAREHOUSEDEPOTS);
        addWhereButton(root, "_W2", 790, 255, 2, LeaderWarehouse.WAREHOUSEDEPOTS);
        addWhereButton(root, "_W3", 710, 300, 3, LeaderWarehouse.WAREHOUSEDEPOTS);
        addWhereButton(root, "_W4", 755, 320, 4, LeaderWarehouse.WAREHOUSEDEPOTS);
        addWhereButton(root, "_W5", 800, 300, 5, LeaderWarehouse.WAREHOUSEDEPOTS);

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

        addButton(root, "_S_BP", 850, 450, new SelectDefaultProductionPowerGameMessage());
        addButton(root, "_S_P1", 942, 450, new SelectProductionDevelopmentCardGameMessage(1));
        addButton(root, "_S_P2", 1052, 450, new SelectProductionDevelopmentCardGameMessage(2));
        addButton(root, "_S_P3", 1162, 450, new SelectProductionDevelopmentCardGameMessage(3));

        addButton(root, "_T_R1", 330, 720, new TakeResourcesFromTheMarketGameMessage(RowColumn.ROW,1));
        addButton(root, "_T_R2", 330, 800, new TakeResourcesFromTheMarketGameMessage(RowColumn.ROW,2));
        addButton(root, "_T_R3", 330, 880, new TakeResourcesFromTheMarketGameMessage(RowColumn.ROW,3));
        addButton(root, "_T_C1", 20, 940, new TakeResourcesFromTheMarketGameMessage(RowColumn.COLUMN,1));
        addButton(root, "_T_C2", 100, 940, new TakeResourcesFromTheMarketGameMessage(RowColumn.COLUMN,2));
        addButton(root, "_T_C3", 180, 940, new TakeResourcesFromTheMarketGameMessage(RowColumn.COLUMN,3));
        addButton(root, "_T_C3", 260, 940, new TakeResourcesFromTheMarketGameMessage(RowColumn.COLUMN,4));

        addQuitButton(root, "_QUIT", 615, 900, "quit", stage);
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

    private void addWhereButton(Group root, String buttonName, int x, int y, int pos, LeaderWarehouse where){
        Button b = new Button(buttonName);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                if(state == 1){
                    out.println(new AddResourceToGameMessage(where, pos));
                    state = 0;
                }else if(state == 2){
                    out.println(new SelectAWarehouseDepotsSlotGameMessage(pos));
                    state = 0;
                }else if(state == 3){
                    out.println(new MoveResourcesInWarehouseDepotsGameMessage(pos));
                    state = 0;
                }else if(state == 4){
                    out.println(new MoveResourcesWarehouseToESLCGameMessage(pos));
                    state = 0;
                }else if(state == 5){
                    out.println(new MoveResourceESLCToWEarehouseGameMessage(pos));
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

}
