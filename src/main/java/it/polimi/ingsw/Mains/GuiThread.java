package it.polimi.ingsw.Mains;

import it.polimi.ingsw.View.Gui;
import it.polimi.ingsw.View.View;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class GuiThread extends Application implements Runnable{

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

        View v = new Gui(canvas, root);
        v.showDevCard();
        v.showPersonalBoard();
        v.showPBCurrent();

        Scene scene = new Scene(root, 1820, 980);
        stage.setScene(scene);
        stage.show();
    }



}
