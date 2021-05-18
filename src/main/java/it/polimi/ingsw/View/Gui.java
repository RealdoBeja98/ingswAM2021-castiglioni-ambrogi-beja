package it.polimi.ingsw.View;

import it.polimi.ingsw.Mains.ClientMain;
import it.polimi.ingsw.Table.Decks.Development.DevelopmentCard;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

public class Gui extends View{
    private Canvas canvas;
    private Group root;

    public Gui(Canvas canvas, Group root){
        this.canvas = canvas;
        this.root = root;
    }

    @Override
    public void showMarket() {

    }

    @Override
    public void showDevCard() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //DevelopmentCard[][] visualize = ClientMain.getPlayerGame().getDevelopmentDeck().visualize();
        String name = "DevelopmentCards/1!2!b!1!4!1!0!1!1.png";
        for(int x = 0; x < 4; x++){
            for(int y = 0; y < 3; y++){
                drawCards(gc, x * 100, y * 200, name);
            }
        }
    }

    @Override
    public void showPersonalBoard() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        String name = "DevelopmentCards/1!2!b!1!4!1!0!1!1.png";
        for(int x = 0; x < 4; x++){
            for(int y = 0; y < 3; y++){
                drawCards(gc, x * 100+400, y * 200, name);
            }
        }
        root.getChildren().add(canvas);
    }

    @Override
    public void showPBCurrent() {
        Text t = new Text();
        t.setText("Ciao sono andrea");
        t.setX(200);
        t.setY(200);
        root.getChildren().add(t);
    }

    private void drawCards(GraphicsContext gc, int x, int y, String name) {
        Image img = new Image(name);
        gc.drawImage(img, x, y, 100, 200);
    }
}
