package com.attackontitan;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;

public class App extends Application {

    final SwingNode swingNode = new SwingNode();

    @Override
    public void start(Stage stage) throws Exception {

        Parent root =FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Pane pane=new Pane();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        pane.getChildren().add(swingNode);

        stage.setTitle("Attack On Titan");
        stage.getIcons().add(new Image("com/attackontitan/icon.png"));
        stage.setMaximized(true);
        
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



