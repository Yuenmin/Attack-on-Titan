package com.attackontitan;

import java.awt.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class Controller implements Initializable {
    
    @FXML
    private javafx.scene.control.TextField TF1; 

    public void handleButtonAction(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent NAMEParent =FXMLLoader.load(this.getClass().getResource("NAME.fxml"));
        Scene NAMEScene = new Scene(NAMEParent,window.getWidth(),window.getHeight());
        window.setScene(NAMEScene);
        window.show();
    }
    @FXML
    public void handleButtonAction2(ActionEvent event) throws IOException {
        String name = TF1.getText();
        
        
        
    }

    public void initialize(URL url, ResourceBundle rb) {
    }
}