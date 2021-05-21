package it.polimi.ingsw.Mains;
import it.polimi.ingsw.Messages.GameMessages.ChooseNoActionLeaderCardGameMessage;
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

    private  void buttonTurn(Group root){

        Button b = new Button("_NO_A_LC");
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                out.println(new ChooseNoActionLeaderCardGameMessage());
            }
        };

        b.setOnAction(event);
        b.setLayoutX(625);

        TilePane r = new TilePane();
        root.getChildren().add(b);
    }

}
