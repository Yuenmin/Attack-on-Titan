package com.attackontitan;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.TextField;

public class Controller implements Initializable {

    @FXML
    private TextField TF1;
    @FXML
    private TextField chooseWeapon;
    @FXML
    private TextField chooseWall;
    @FXML
    private TextField fillHP;

    protected static String name, CWeaponStr, CWallStr, UpHPStr = "0";
    private static int CWallStrLen;
    protected static boolean cAll = false;
    private double xOffset;
    private double yOffset;

    public void handleButtonAction(ActionEvent event) throws IOException {

        Parent NAMEParent = FXMLLoader.load(this.getClass().getResource("NAME.fxml"));
        Scene NAMEScene = new Scene(NAMEParent, App.getWidth(), App.getHeight());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FadeTransition ft = new FadeTransition(Duration.millis(1000), NAMEParent);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        window.setScene(NAMEScene);

    }

    public void handleButtonAction2(ActionEvent event) throws IOException {

        name = TF1.getText();
        if (name.isEmpty() || name.isBlank()) {
            Parent NAMEParent = FXMLLoader.load(this.getClass().getResource("NAME.fxml"));
            Scene NAMEScene = new Scene(NAMEParent, App.getWidth(), App.getHeight());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(NAMEScene);

        } else {
            App app = new App();
            app.initMap();
        }
    }

    public void handleButtonActionU1(ActionEvent event) {
        try {
            CWeaponStr = chooseWeapon.getText();
            if (!(CWeaponStr.equalsIgnoreCase("Go") || onlyDigits(CWeaponStr, CWeaponStr.length()))) {
                Parent NAMEParent = FXMLLoader.load(this.getClass().getResource("UpgradeBoard.fxml"));
                Scene NAMEScene = new Scene(NAMEParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(NAMEScene);
            }

            Parent NAMEParent = FXMLLoader.load(this.getClass().getResource("UpgradeBoard2.fxml"));
            Scene NAMEScene = new Scene(NAMEParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(NAMEScene);
            App.getPrimaryStage().requestFocus();

        } catch (Exception ignored) {

        }
    }

    public void handleButtonActionU3(ActionEvent event) {
        try {
            CWallStr = chooseWall.getText();
            CWallStrLen = CWallStr.length();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            if (!CWallStr.equalsIgnoreCase("Go")) {
                if (onlyDigits(CWallStr, CWallStrLen) && !duplicateDigits(CWallStr, CWallStrLen)) {
                    Parent NAMEParent = FXMLLoader.load(this.getClass().getResource("UpgradeBoard4.fxml"));
                    Scene NAMEScene = new Scene(NAMEParent);
                    window.setScene(NAMEScene);
                    App.getPrimaryStage().requestFocus();
                    window.wait();
                } else {
                    Parent NAMEParent = FXMLLoader.load(this.getClass().getResource("UpgradeBoard3.fxml"));
                    Scene NAMEScene = new Scene(NAMEParent);
                    window.setScene(NAMEScene);
                    window.wait();
                }
            }
            window.close();
        } catch (Exception ignored) {

        }
    }

    public void handleButtonActionU4(ActionEvent event) {
        try {
            UpHPStr = fillHP.getText();
            String[] UpHPStrArr = UpHPStr.strip().replaceAll("\\s+", " ").split(" ");
            Parent NAMEParent = FXMLLoader.load(this.getClass().getResource("UpgradeBoard4.fxml"));
            Scene NAMEScene = new Scene(NAMEParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            if (cAll) {
                if (UpHPStr.split(" ").length != 1 || !onlyDigits(UpHPStr, UpHPStr.length())) {
                    window.setScene(NAMEScene);
                    window.wait();
                }
            } else {
                if (UpHPStr.split(" ").length != CWallStrLen || !onlyDigits(UpHPStrArr, UpHPStrArr.length)) {
                    window.setScene(NAMEScene);
                    window.wait();
                }
            }
            if (cAll) {
                CWallStr = "0123456789";
            }

            //close upgradeBoard
            window.close();
        } catch (Exception ignored) {

        }
    }

    public void handleButtonActionYes(ActionEvent event) throws IOException {
        cAll = true;
        Parent NAMEParent = FXMLLoader.load(this.getClass().getResource("UpgradeBoard4.fxml"));
        Scene NAMEScene = new Scene(NAMEParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(NAMEScene);
        App.getPrimaryStage().requestFocus();

    }

    public void handleButtonActionNo(ActionEvent event) throws IOException {
        cAll = false;
        Parent NAMEParent = FXMLLoader.load(this.getClass().getResource("UpgradeBoard3.fxml"));
        Scene NAMEScene = new Scene(NAMEParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(NAMEScene);
        App.getPrimaryStage().requestFocus();

    }

    public void onPressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    public void onDragged(MouseEvent event) {
        App.getUPGRADEStage().setX(event.getScreenX() - xOffset);
        App.getUPGRADEStage().setY(event.getScreenY() - yOffset);
    }

    public void onEntered() {
        App.getUPGRADEStage().setOpacity(1);
    }

    public void onExited() {
        App.getUPGRADEStage().setOpacity(0.1);
    }

    public static boolean onlyDigits(String str, int n) {
        for (int i = 0; i < n; i++) {

            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }

        }
        return n != 0;
    }

    public static boolean onlyDigits(String[] str, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < str[i].length(); j++) {
                if (str[i].charAt(j) < '0' || str[i].charAt(j) > '9') {
                    return false;
                }

            }
        }
        return n != 0;
    }

    public static boolean duplicateDigits(String str, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean contains3OrMoreSameDigits(String str, int n) {
        int c = 1;
        for (int i = 0; i < n - 1; i++) {
            if (str.charAt(i) != str.charAt(i + 1)) { // not
                if (c > 3) {
                    return true;
                }
                c = 1;
            } else //equal
            {
                c++;
            }
        }
        if (c != 0) {
            if (c > 3) {
                return true;
            }
        }
        return false;
    }

    public void initialize(URL url, ResourceBundle rb) {
    }
}
