package com.attackontitan;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private javafx.scene.control.TextField TF1;

    public void handleButtonAction(ActionEvent event) throws IOException {

        Parent NAMEParent =FXMLLoader.load(this.getClass().getResource("NAME.fxml"));
        Scene NAMEScene = new Scene(NAMEParent,App.getWidth(),App.getHeight());
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        FadeTransition ft = new FadeTransition(Duration.millis(1000),NAMEParent);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        window.setScene(NAMEScene);
        window.show();
    }

    public void handleButtonAction2(ActionEvent event) throws Exception {

        String name = TF1.getText();
        if(name.isBlank()) {
            Parent NAMEParent =FXMLLoader.load(this.getClass().getResource("NAME.fxml"));
            Scene NAMEScene = new Scene(NAMEParent,App.getWidth(),App.getHeight());
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(NAMEScene);
            window.show();
        } else{
            App app=new App();
            app.initMap();
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
    }
}
