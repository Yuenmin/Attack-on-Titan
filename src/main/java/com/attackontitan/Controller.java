package com.attackontitan;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField TF1;
    @FXML
    private TextField chooseWeapon;
    @FXML
    private TextField chooseWall;
    @FXML
    private TextField fillHP;

    private static String CWeaponStr, CWallStr, UpHPStr = "0";
    private static int CWallStrLen;
    private static boolean cAll = false;
    private double xOffset;
    private double yOffset;
    private Stage stage;

    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException {

        Parent nameParent = FXMLLoader.load(this.getClass().getResource("NAME.fxml"));
        Scene nameScene = new Scene(nameParent, App.getWidth(), App.getHeight());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FadeTransition ft = new FadeTransition(Duration.millis(1000), nameParent);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        window.setScene(nameScene);
    }

    @FXML
    public void handleButtonAction2(ActionEvent event) throws IOException {
        String name = TF1.getText();
        if (name.isEmpty() || name.isBlank()) {
            Parent nameParent = FXMLLoader.load(this.getClass().getResource("NAME.fxml"));
            Scene nameScene = new Scene(nameParent, App.getWidth(), App.getHeight());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(nameScene);

        } else {
            App app = new App();
            App.getScoreBoard().setNewName(name);
            app.initMap();
        }
    }

    @FXML
    public void showGameRules() throws Exception {
        if (stage == null) {
            Parent instruction = FXMLLoader.load(this.getClass().getResource("Instruction.fxml"));
            stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initOwner(App.getPrimaryStage());
            stage.setScene(new Scene(instruction));
            stage.show();
        } else {
            stage.close();
            stage = null;
        }
    }

    public void showLeaderBoard() {
        if (stage == null) {
            stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initOwner(App.getPrimaryStage());
            App.getScoreBoard().sort();
            Scene scene = new Scene(App.getScoreBoard().getScoreBoard());
            scene.getStylesheets().add("file:src/main/resources/com/attackontitan/tableview.css");
            stage.setScene(scene);
            stage.show();
        } else {
            stage.close();
            stage = null;
        }
    }

    public void handleButtonActionU1(ActionEvent event) {
        try {
            CWeaponStr = chooseWeapon.getText();
            if (!CWeaponStr.equalsIgnoreCase("Go") && (!onlyDigits(CWeaponStr, CWeaponStr.length()) || contains3OrMoreSameDigits(CWeaponStr, CWeaponStr.length()))) {
                Parent q1 = FXMLLoader.load(this.getClass().getResource("UpgradeBoard.fxml"));
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(q1));
            }
            App.upgradeMultipleWeapons(CWeaponStr);
            Parent q2 = FXMLLoader.load(this.getClass().getResource("UpgradeBoard2.fxml"));
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(q2));
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
                    Parent q4 = FXMLLoader.load(this.getClass().getResource("UpgradeBoard4.fxml"));
                    window.setScene(new Scene(q4));
                    App.getPrimaryStage().requestFocus();
                    window.wait();
                } else {
                    Parent q3 = FXMLLoader.load(this.getClass().getResource("UpgradeBoard3.fxml"));
                    window.setScene(new Scene(q3));
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
            Parent q4 = FXMLLoader.load(this.getClass().getResource("UpgradeBoard4.fxml"));
            Scene q4Scene = new Scene(q4);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            if (cAll) {
                if (UpHPStr.split(" ").length != 1 || !onlyDigits(UpHPStr, UpHPStr.length())) {
                    window.setScene(q4Scene);
                    window.wait();
                }
            } else {
                if (UpHPStr.split(" ").length != CWallStrLen || !onlyDigits(UpHPStrArr, UpHPStrArr.length)) {
                    window.setScene(q4Scene);
                    window.wait();
                }
            }
            if (cAll) {
                CWallStr = "0123456789";
            }
            App.addHpToTheWall(CWallStr, UpHPStr, cAll);

            //close upgradeBoard
            window.close();
        } catch (Exception ignored) {

        }
    }

    public void handleButtonActionYes(ActionEvent event) throws IOException {
        cAll = true;
        Parent q4 = FXMLLoader.load(this.getClass().getResource("UpgradeBoard4.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(q4));
        App.getPrimaryStage().requestFocus();

    }

    public void handleButtonActionNo(ActionEvent event) throws IOException {
        cAll = false;
        Parent q3 = FXMLLoader.load(this.getClass().getResource("UpgradeBoard3.fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(q3));
        App.getPrimaryStage().requestFocus();

    }

    public void onPressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    public void onDragged(MouseEvent event) {
        App.getUpgradeStage().setX(event.getScreenX() - xOffset);
        App.getUpgradeStage().setY(event.getScreenY() - yOffset);
    }

    public void onEntered() {
        App.getUpgradeStage().setOpacity(1);
    }

    public void onExited() {
        App.getUpgradeStage().setOpacity(0.1);
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
