package com.attackontitan;

import javafx.animation.FadeTransition;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable {

    final SwingNode swingNode = new SwingNode();

    @FXML
    private javafx.scene.control.TextField TF1;

    public void handleButtonAction(ActionEvent event) throws IOException {

        Stage window;
        Parent NAMEParent =FXMLLoader.load(this.getClass().getResource("NAME.fxml"));
        Scene NAMEScene = new Scene(NAMEParent,App.getWidth(),App.getHeight());
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FadeTransition ft = new FadeTransition(Duration.millis(2000),NAMEParent);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        window.setScene(NAMEScene);
        window.show();
    }

    public void handleButtonAction2(ActionEvent event) throws IOException {

        String name = TF1.getText();
        if(name.isBlank()) {
            Parent NAMEParent =FXMLLoader.load(this.getClass().getResource("NAME.fxml"));
            Scene NAMEScene = new Scene(NAMEParent,App.getWidth(),App.getHeight());
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(NAMEScene);
            window.show();
        } else{
            new MapView(swingNode);
            swingNode.setScaleX(0.59);
            swingNode.setScaleY(0.55);
            swingNode.setTranslateX(-540);
            swingNode.setTranslateY(-330);

            AnchorPane pane=new AnchorPane();
            pane.getChildren().add(swingNode);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FadeTransition ft = new FadeTransition(Duration.millis(2000), pane);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            Scene scene = new Scene(pane);
            window.setScene(scene);
            window.hide();
            window.show();
            repaint();
            ft.play();
        }
    }

    public void repaint(){
        new Timer().schedule(new TimerTask() {
            public void run() {
                swingNode.getContent().repaint();
            }
        }, 200);
    }

    public void initialize(URL url, ResourceBundle rb) {
    }
}
