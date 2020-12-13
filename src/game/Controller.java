package game;

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

public class Controller implements Initializable {
    public Controller() {
    }

    public void handleButtonAction(ActionEvent event) throws IOException {
        Parent NAMEParent =FXMLLoader.load(this.getClass().getResource("NAME.fxml"));
        Scene NAMEScene = new Scene(NAMEParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NAMEScene);
        window.show();
    }

    public void initialize(URL url, ResourceBundle rb) {
    }
}
