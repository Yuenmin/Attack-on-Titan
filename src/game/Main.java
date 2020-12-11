package game;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    final SwingNode swingNode = new SwingNode();

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Scene scene=new Scene(root, 1000, 800);
        scene.setFill(Color.GREY);
        initMap();
        root.getChildren().add(swingNode);

        stage.setTitle("Attack On Titan");
        stage.setScene(scene);
        stage.show();
    }

    public void initMap(){

        MapView mapView = new MapView();
        mapView.jPanel(swingNode);
        swingNode.setVisible(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}




