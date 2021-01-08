package com.attackontitan;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameInfo {

    private Group gameInfo = new Group();
    private Group wallHp = new Group();
    private Group coinGroup = new Group();
    private Group hourGroup = new Group();
    private Group titanHealthBar = new Group();
    private Group damageGroup = new Group();
    private Group noCoinWeapon = new Group();
    private Group noCoinWall = new Group();
    private Rectangle rectangle = new Rectangle();
    private int[] curHp;

    public GameInfo() {
        gameInfo.getChildren().addAll(rectangle, wallHp, coinGroup, hourGroup, titanHealthBar, damageGroup, noCoinWall, noCoinWeapon);
        curHp = new int[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
    }

    public void drawInfoPane() {
        rectangle.setLayoutX(App.getCoinView().getCoinImage().getLayoutX() - 4);
        rectangle.setLayoutY(App.getCoinView().getCoinImage().getLayoutY() - 45);
        rectangle.setWidth(70.0f);
        rectangle.setHeight(530.0f);
        rectangle.setStroke(Color.rgb(94, 40, 13));
        rectangle.setFill(Color.rgb(168, 110, 27));
        rectangle.setStrokeWidth(3);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setArcHeight(20.0);
    }

    public void drawWallHp() {
        wallHp.getChildren().clear();
        ImageView heart = new ImageView(new Image(getClass().getResourceAsStream("images/heart.png")));
        heart.setLayoutX(App.getCoinView().getCoinImage().getLayoutX());
        heart.setLayoutY(App.getCoinView().getCoinImage().getLayoutY() + 40);
        int yIncrement = 50;
        int hpIncrement = 20;
        for (int i = 0; i <= 9; i++) {
            int healthPoint = App.getWall().get(i).getHp();
            if (healthPoint < 0) {
                healthPoint = 0;
            }
            Text wall = new Text(heart.getLayoutX() + 5, heart.getLayoutY() + yIncrement, "Wall " + i);
            wall.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
            wall.setFill(Color.WHITE);
            Text hp = new Text(heart.getLayoutX() + 5, heart.getLayoutY() + yIncrement + hpIncrement, Integer.toString(healthPoint));
            hp.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 20));
            boolean blink = wallHpChanged(i, healthPoint);
            if (blink) {
                wallHpBlink(i);
            }
            if (healthPoint >= 40) {
                hp.setFill(Color.LAWNGREEN);
            } else if (healthPoint >= 20) {
                hp.setFill(Color.YELLOW);
            } else {
                hp.setFill(Color.RED);
            }
            wallHp.getChildren().add(hp);
            wallHp.getChildren().add(wall);
            yIncrement += 40;
        }
        wallHp.getChildren().add(heart);
    }

    public void wallHpBlink(int i) {
        int finalI = i * 2;
        Timeline timeline = new Timeline();
        KeyFrame kf1 = new KeyFrame(Duration.millis(150), actionEvent -> wallHp.getChildren().get(finalI).setVisible(false));
        KeyFrame kf2 = new KeyFrame(Duration.millis(300), actionEvent -> wallHp.getChildren().get(finalI).setVisible(true));
        timeline.getKeyFrames().addAll(kf1, kf2);
        timeline.setCycleCount(3);
        timeline.play();
    }

    public boolean wallHpChanged(int i, int healthPoint) {
        if (curHp[i] > healthPoint) {
            curHp[i] = healthPoint;
            return true;
        } else if (curHp[i] < healthPoint) {
            curHp[i] = healthPoint;
            return false;
        } else {
            return false;
        }
    }

    public void wallDamage(int wallIndex, int damage) {
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(1500));
        pauseTransition.play();
        pauseTransition.setOnFinished(actionEvent ->
                Platform.runLater(() -> {
                    int xIncrement = 0;
                    for (int i = 0; i <= 9; i++) {
                        if (i == wallIndex) {
                            Text text = new Text(100 + xIncrement, 700, "-" + damage);
                            text.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 20));
                            text.setFill(Color.RED);
                            damageGroup.getChildren().add(text);
                            TranslateTransition t = setTransition(text, 0, -20);
                            t.setOnFinished(e -> {
                                text.setText("");
                                text.setTranslateX(0);
                            });
                            setFadeTransition(text, 1500);
                        }
                        xIncrement += 120;
                    }
                    drawWallHp();
                })
        );
    }

    public void drawCoinNum() {
        coinGroup.getChildren().clear();
        Text coinNum = new Text(App.getCoinView().getCoinImage().getLayoutX() + 32, App.getCoinView().getCoinImage().getLayoutY() + 20, Integer.toString(App.getCoin().getCurCoin()));
        coinNum.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        coinNum.setFill(Color.WHITE);
        coinGroup.getChildren().addAll(coinNum, App.getCoinView().getCoinImage(), App.getCoinView().getCoinIncrease());
    }

    public void drawNotEnoughCoinWeapon() {
        Text text = new Text();
        text.setText("Coins not enough to upgrade all WEAPONS");
        text.setLayoutX(App.getCoinView().getCoinImage().getLayoutX() - 360);
        text.setLayoutY(App.getCoinView().getCoinImage().getLayoutY());
        text.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        text.setFill(Color.RED);
        TranslateTransition t4 = new TranslateTransition();
        t4.setNode(text);
        t4.setByY(-10);
        t4.setDuration(Duration.millis(8000));
        t4.play();
        t4.setOnFinished(actionEvent -> {
            text.setText("");
            text.setTranslateY(0);
        });
        FadeTransition ft = new FadeTransition(Duration.millis(5000), text);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.play();
        noCoinWeapon.getChildren().add(text);
    }

    public void drawNotEnoughCoinWall() {
        Text text = new Text();
        text.setText("Coins not enough to upgrade all WALLS");
        text.setLayoutX(App.getCoinView().getCoinImage().getLayoutX() - 360);
        text.setLayoutY(App.getCoinView().getCoinImage().getLayoutY() + 30);
        text.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        text.setFill(Color.RED);
        TranslateTransition t4 = new TranslateTransition();
        t4.setNode(text);
        t4.setByY(-10);
        t4.setDuration(Duration.millis(9000));
        t4.play();
        t4.setOnFinished(actionEvent -> {
            text.setText("");
            text.setTranslateY(0);
        });
        FadeTransition ft = new FadeTransition(Duration.millis(6000), text);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.play();
        noCoinWall.getChildren().add(text);
    }

    public void drawHourNum() {
        hourGroup.getChildren().clear();
        int hour = App.getHour().getCurrentHour();
        if (hour < 0) {
            hour = 0;
        }
        Text hourNum = new Text(App.getCoinView().getCoinImage().getLayoutX(), App.getCoinView().getCoinImage().getLayoutY() - 20, "Hour " + hour);
        hourNum.setFont(Font.font("Calibri", FontWeight.BOLD, 19.5));
        hourNum.setFill(Color.WHITE);
        hourGroup.getChildren().add(hourNum);
    }

    public void drawTitanHp(Titan titan, int healthPoint) {
        double x;
        double y;
        double maxHp;
        if (titan instanceof ArmouredTitan) {
            x = titan.getArmouredTitanView().getView().getLayoutX() + titan.getArmouredTitanView().getView().getTranslateX();
            y = titan.getArmouredTitanView().getView().getLayoutY() + titan.getArmouredTitanView().getView().getTranslateY();
            maxHp = 100.0;
        } else {
            x = titan.getColossusTitanView().getView().getLayoutX() + titan.getColossusTitanView().getView().getTranslateX();
            y = titan.getColossusTitanView().getView().getLayoutY() + titan.getColossusTitanView().getView().getTranslateY();
            maxHp = 50.0;
        }
        if (healthPoint < 0) {
            healthPoint = 0;
        }
        HealthBar healthBar = new HealthBar(x + 7, y, healthPoint / maxHp);
        Text hp = new Text(x + 20, y - 2, Integer.toString(healthPoint));
        hp.setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, 15));
        hp.setFill(Color.WHITE);
        titanHealthBar.getChildren().addAll(healthBar.getHealthBar(), hp);
    }

    public void titanDamage(Titan titan, int damage) {
        damageGroup.getChildren().clear();
        Platform.runLater(() -> {
            double x;
            double y;
            if (titan instanceof ArmouredTitan) {
                x = titan.getArmouredTitanView().getView().getLayoutX();
                y = titan.getArmouredTitanView().getView().getLayoutY();
            } else {
                x = titan.getColossusTitanView().getView().getLayoutX();
                y = titan.getColossusTitanView().getView().getLayoutY();
            }
            Text text = new Text();
            text.setText("-" + damage);
            text.setLayoutX(x + 10);
            text.setLayoutY(y);
            text.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
            text.setFill(Color.RED);
            TranslateTransition t = setTransition(text, 0, -20);
            t.setOnFinished(actionEvent -> {
                text.setText("");
                text.setTranslateY(0);
            });
            setFadeTransition(text, 1500);
            damageGroup.getChildren().add(text);
        });
    }

    public void setFadeTransition(Node node, int duration) {
        FadeTransition ft = new FadeTransition(Duration.millis(duration), node);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.play();
    }

    public TranslateTransition setTransition(Node node, int byX, int byY) {
        TranslateTransition t = new TranslateTransition();
        t.setNode(node);
        t.setByX(byX);
        t.setByY(byY);
        t.setDuration(Duration.millis(1500));
        t.play();
        return t;
    }

    public Group getGameInfo() {
        return gameInfo;
    }

    public Group getTitanHealthBar() {
        return titanHealthBar;
    }

}
