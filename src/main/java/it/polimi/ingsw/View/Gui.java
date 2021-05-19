package it.polimi.ingsw.View;

import it.polimi.ingsw.Mains.ClientMain;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

public class Gui extends View{

    private Canvas canvas;
    private Group root;

    public Gui(Canvas canvas, Group root){
        setGuiInstance(this);
        this.canvas = canvas;
        this.root = root;
    }

    @Override
    public void showMarket() {

    }

    @Override
    public void showDevCard() {
        //System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");//
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                String name;
                try{
                    name = ClientMain.getPlayerGame().getDevelopmentDeck().visualize()[i][j].export();
                } catch (NullPointerException e){
                    name = "nullDevCard";
                }
                String fullName = "DevelopmentCards/" + name + ".png";
                drawCards(gc, i * 100, j * 200, fullName);
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
