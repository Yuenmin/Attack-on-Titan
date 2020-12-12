package game;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    final SwingNode swingNode = new SwingNode();

    @Override
    public void start(Stage stage) throws Exception {

        Pane root = new Pane();
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        
        Scene scene=new Scene(root,1000,800);
        initMap();
        root.getChildren().add(swingNode);

        stage.setTitle("Attack On Titan");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
        repaint();
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




