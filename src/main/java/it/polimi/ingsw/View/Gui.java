package it.polimi.ingsw.View;

import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Mains.GuiThread;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Gui extends View{

    private Canvas canvas;
    private Group root;


    public Gui(Canvas canvas, Group root){
        this.canvas = canvas;
        this.root = root;

    }

    @Override
    public void showStartingLC() {

    }

    @Override
    public void showMarket() {

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
        Text t = new Text();
        t.setText("Ciao sono andrea");
        t.setX(1000);
        t.setY(500);
        //root.getChildren().add(t); //<--FIXME andrebbe spostato nella classe guithread (non ne son sicuro)-->
    }

    @Override
    public void showPBCurrent() {

    }

    private void drawCards(GraphicsContext gc, int x, int y, String name) {
        Image img = new Image(name);
        gc.drawImage(img, x, y, 150, 230);
    }

}
