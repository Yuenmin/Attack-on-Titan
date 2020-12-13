package game;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    final SwingNode swingNode = new SwingNode();

    @Override
    public void start(Stage stage) throws Exception {

        Parent root =FXMLLoader.load(getClass().getResource("/game/FXMLDocument.fxml"));
        Pane pane=new Pane();
        Scene scene=new Scene(root,1000,800);
        pane.getChildren().add(swingNode);
       
        stage.setTitle("Attack On Titan");
        stage.getIcons().add(new Image("/resources/Textures/Homepage/icon.png"));
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
        //repaint();
    }

    public void initMap(){
        new MapView(swingNode);
        swingNode.setScaleX(0.59);
        swingNode.setScaleY(0.55);
        swingNode.setTranslateX(-540);
        swingNode.setTranslateY(-330);

    }

    public void repaint(){
        new Timer().schedule(new TimerTask() {
            public void run() {
                swingNode.getContent().repaint();
            }
        }, 100L);
    }

    public static void main(String[] args) {
        launch(args);
    }
}




