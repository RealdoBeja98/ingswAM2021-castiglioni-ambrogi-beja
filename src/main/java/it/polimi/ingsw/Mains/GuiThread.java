package it.polimi.ingsw.Mains;
import it.polimi.ingsw.Enums.LeaderWarehouse;
import it.polimi.ingsw.Enums.NormalAction;
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
        stage.setTitle("Masters of Renaissance");
        Group root = new Group();
        Canvas canvas = new Canvas(1820, 980);
        ClientMain.setCanvas(canvas);
        ClientMain.setRoot(root);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image img = new Image("Misc/BackGround.png");
        gc.drawImage(img, 0, 0, 1820, 980);
        Image img2 = new Image("Misc/PersonalBoard.png");
        gc.drawImage(img2, 700, 0, 560, 490);
        gc.drawImage(img2, 1260, 0, 560, 490);
        gc.drawImage(img2, 700, 490, 560, 490);
        gc.drawImage(img2, 1260, 490, 560, 490);


        root.getChildren().add(canvas);
        buttonTurn(root);
        Scene scene = new Scene(root, 1820, 980);
        stage.setScene(scene);
        stage.show();
    }

    private void buttonTurn(Group root){
        addButton(root, "_NO_A_LC", 625, 0, new ChooseNoActionLeaderCardGameMessage());
        addButton(root, "_DIS_LC", 625, 35, new ChooseDiscardLeaderCardGameMessage());
        addButton(root, "_PLAY_LC", 625, 70, new ChoosePlayLeaderCardGameMessage());
        addButton(root, "_NA_TAKE", 625, 105, new SelectNormalActionGameMessage(NormalAction.TAKE_RESOURCES_FROM_THE_MARKET));
        addButton(root, "_NA_BUY", 625, 140, new SelectNormalActionGameMessage(NormalAction.BUY_DEVELOPMENT_CARD));
        addButton(root, "_NA_PROD", 625, 175, new SelectNormalActionGameMessage(NormalAction.ACTIVATE_PRODUCTION));
        addButton(root, "_END_T", 625, 210, new EndTurnGameMessage());

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

}
