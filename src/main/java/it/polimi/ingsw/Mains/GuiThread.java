package it.polimi.ingsw.Mains;
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
        ClientMain.setCanvas(canvas);
        ClientMain.setRoot(root);


        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 1820, 980);
        stage.setScene(scene);
        stage.show();
    }

}
