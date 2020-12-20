package com.attackontitan;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class App extends Application {

    private static double height;
    private static double width;
    private static Stage pStage;
    final SwingNode swingNode = new SwingNode();
    Group group=new Group();
    Scene scene;

    public App(){}

    @Override
    public void start(Stage primaryStage) throws Exception {

        pStage = primaryStage;
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        Parent root= FXMLLoader.load(getClass().getResource("FXML.fxml"));
        scene = new Scene(root);
        primaryStage.setTitle("Attack On Titan");
        primaryStage.getIcons().add(new Image("com/attackontitan/icon.png"));
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        height=scene.getHeight();
        width=scene.getWidth();
    }

    public void initMap(){

        new MapView(swingNode);
        group.getChildren().addAll(swingNode);
        FadeTransition ft = new FadeTransition(Duration.millis(3500),swingNode);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        scene = new Scene(group,getWidth(),getHeight());
        Stage stage=App.getPrimaryStage();
        stage.setScene(scene);
        repaint();
        ft.play();
        ft.setOnFinished(actionEvent ->group.getChildren().addAll(new Cannon(0).cannonGroup,new Soldier().soldierGroup, column));
    }

    Group column =new Group();
    public void drawColumn(){
        for (int i = 0; i<600; i+=67) {
            Line line = new Line(0, i, getWidth(), i);
            line.setStroke(Color.WHITE);
            line.setStrokeWidth(2);
            column.getChildren().add(line);
        }
        int x=0;
        for(int i=1;i<=9;i++){
            Text number=new Text(10,50+x,Integer.toString(i));
            number.setFont(Font.font("Calibri", FontWeight.BOLD,60));
            column.getChildren().add(number);
            x+=67;
        }
    }

    public void repaint(){
        new Timer().schedule(new TimerTask() {
            public void run() {
                swingNode.getContent().repaint();
                drawColumn();
            }
        }, 1500);
    }

    public static Stage getPrimaryStage() { return pStage; }

    public static double getHeight(){ return height; }

    public static double getWidth(){ return width; }

    public static void main(String[] args) { launch(args); }
}




