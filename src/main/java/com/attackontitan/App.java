package com.attackontitan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    private static double height;
    private static double width;

    @Override
    public void start(Stage stage) throws Exception {

        Parent root= FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Attack On Titan");
        stage.getIcons().add(new Image("com/attackontitan/icon.png"));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
        height=scene.getHeight();
        width=scene.getWidth();
    }

    public static double getHeight(){ return height; }

    public static double getWidth(){ return width; }

    public static void main(String[] args) { launch(args); }
}




