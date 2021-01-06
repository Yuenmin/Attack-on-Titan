package com.attackontitan;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

public class GameInfo {

    private Group gameInfo = new Group();
    private Group wallHp = new Group();
    private Group coinGroup = new Group();
    private Group hourGroup = new Group();
    private Group titanHealthBar = new Group();

    public GameInfo() {
        gameInfo.getChildren().addAll(wallHp, coinGroup, hourGroup, titanHealthBar);
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
            Text wall = new Text(heart.getLayoutX() + 5, heart.getLayoutY() + yIncrement, "Wall " + i);
            wall.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
            wall.setFill(Color.WHITE);
            Text hp = new Text(heart.getLayoutX() + 5, heart.getLayoutY() + yIncrement + hpIncrement, Integer.toString(healthPoint));
            hp.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 20));
            if (healthPoint >= 40) {
                hp.setFill(Color.GREEN);
            } else if (healthPoint >= 20) {
                hp.setFill(Color.YELLOW);
            } else {
                hp.setFill(Color.RED);
            }
            wallHp.getChildren().addAll(wall, hp);
            yIncrement += 40;
        }
        wallHp.getChildren().add(heart);
    }

    public void drawCoinNum() {
        coinGroup.getChildren().clear();
        Text coinNum = new Text(App.getCoinView().getCoinImage().getLayoutX() + 35, App.getCoinView().getCoinImage().getLayoutY() + 20, Integer.toString(App.getCoin().getCurCoin()));
        coinNum.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        coinNum.setFill(Color.WHITE);
        coinGroup.getChildren().addAll(coinNum, App.getCoinView().getCoinImage(), App.getCoinView().getCoinIncrease());
    }

    public void drawHourNum() {
        hourGroup.getChildren().clear();
        Text hourNum = new Text(App.getCoinView().getCoinImage().getLayoutX(), App.getCoinView().getCoinImage().getLayoutY() - 20, "Hour " + App.getHour().getCurrentHour());
        hourNum.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        hourNum.setFill(Color.WHITE);
        hourGroup.getChildren().add(hourNum);
    }

    public void drawTitanHp(int healthPoint, double x, double y) {
        HealthBar healthBar = new HealthBar(x + 7, y, healthPoint / 50.0);
        Text hp = new Text(x + 20, y - 2, Integer.toString(healthPoint));
        hp.setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, 15));
        hp.setFill(Color.WHITE);
        titanHealthBar.getChildren().addAll(healthBar.getHealthBar(), hp);
    }

    /*
        private void textAni() {
            coinIncrease.setText("+5");
            coinIncrease.setLayoutX(coinImage.getLayoutX() + 35);
            coinIncrease.setLayoutY(coinImage.getLayoutY() + 40);
            coinIncrease.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
            coinIncrease.setFill(Color.WHITE);
            TranslateTransition t = new TranslateTransition();
            t.setNode(coinIncrease);
            t.setByY(-10);
            t.setDuration(Duration.millis(600));
            t.play();
            t.setOnFinished(actionEvent -> {
                coinIncrease.setText("");
                coinIncrease.setTranslateY(0);
            });
            FadeTransition ft = new FadeTransition(Duration.millis(600), coinIncrease);
            ft.setFromValue(1.0);
            ft.setToValue(0);
            ft.play();
        }
    */
    public Group getGameInfo() {
        return gameInfo;
    }

    public Group getTitanHealthBar() {
        return titanHealthBar;
    }

}
