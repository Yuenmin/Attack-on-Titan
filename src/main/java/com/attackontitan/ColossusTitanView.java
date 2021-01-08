package com.attackontitan;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

//cTitan possible x(105,225,345,464....)

public class ColossusTitanView {

    private TranslateTransition translateTransition;
    private ImageView cTitan;
    private List<Image> cTitanLeft;
    private List<Image> cTitanRight;
    private List<Image> cTitanAttack;
    private double x;
    private double y;
    private boolean isAnimating;

    public ColossusTitanView(int column){
        initImage();
        x = 105 + (column * 120);
        y = 545;
        cTitan = new ImageView();
        idle();
    }

    public void idle() {
        cTitan.setLayoutX(x);
        cTitan.setLayoutY(y);
        cTitan.setImage(cTitanAttack.get(0));
        FadeTransition ft = new FadeTransition(Duration.millis(2000), cTitan);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    private void initImage() {
        cTitanAttack = new ArrayList<>();
        cTitanAttack.add(new Image(getClass().getResourceAsStream("images/titan/idle.png")));
        for (int i = 1; i < 6; i++) {
            cTitanAttack.add(new Image(getClass().getResourceAsStream("images/titan/attack" + i + ".png")));
        }
        cTitanLeft = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            cTitanLeft.add(new Image(getClass().getResourceAsStream("images/titan/left" + i + ".png")));
        }
        cTitanRight = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            cTitanRight.add(new Image(getClass().getResourceAsStream("images/titan/right" + i + ".png")));
        }
    }

    private void leftAni() {
        translateTransition = new TranslateTransition();
        Timeline timeline = new Timeline();
        cTitan.setLayoutX(x);
        cTitan.setLayoutY(y);
        KeyFrame kf1 = new KeyFrame(Duration.millis(300), t -> cTitan.setImage(cTitanLeft.get(0)));
        KeyFrame kf2 = new KeyFrame(Duration.millis(600), t -> cTitan.setImage(cTitanLeft.get(1)));
        KeyFrame kf3 = new KeyFrame(Duration.millis(900), t -> cTitan.setImage(cTitanLeft.get(2)));
        KeyFrame kf4 = new KeyFrame(Duration.millis(1200), t -> cTitan.setImage(cTitanLeft.get(3)));
        KeyFrame kf5 = new KeyFrame(Duration.millis(1500), t -> cTitan.setImage(cTitanLeft.get(4)));
        KeyFrame kf6 = new KeyFrame(Duration.millis(1800), t -> cTitan.setImage(cTitanLeft.get(5)));
        KeyFrame kf7 = new KeyFrame(Duration.millis(2100), t -> cTitan.setImage(cTitanLeft.get(6)));
        KeyFrame kf8 = new KeyFrame(Duration.millis(2400), t -> cTitan.setImage(cTitanLeft.get(7)));
        KeyFrame kf9 = new KeyFrame(Duration.millis(2700), t -> cTitan.setImage(cTitanAttack.get(0)));
        timeline.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6, kf7, kf8, kf9);
        timeline.play();
        translateTransition.setNode(cTitan);
        translateTransition.setByX(-120);
        translateTransition.setDuration(Duration.millis(2700));
        translateTransition.play();
        translateTransition.setOnFinished(actionEvent -> {
            isAnimating = false;
            update();
        });
    }

    private void rightAni() {
        translateTransition = new TranslateTransition();
        Timeline timeline = new Timeline();
        cTitan.setLayoutX(x);
        cTitan.setLayoutY(y);
        KeyFrame kf1 = new KeyFrame(Duration.millis(300), t -> cTitan.setImage(cTitanRight.get(0)));
        KeyFrame kf2 = new KeyFrame(Duration.millis(600), t -> cTitan.setImage(cTitanRight.get(1)));
        KeyFrame kf3 = new KeyFrame(Duration.millis(900), t -> cTitan.setImage(cTitanRight.get(2)));
        KeyFrame kf4 = new KeyFrame(Duration.millis(1200), t -> cTitan.setImage(cTitanRight.get(3)));
        KeyFrame kf5 = new KeyFrame(Duration.millis(1500), t -> cTitan.setImage(cTitanRight.get(4)));
        KeyFrame kf6 = new KeyFrame(Duration.millis(1800), t -> cTitan.setImage(cTitanRight.get(5)));
        KeyFrame kf7 = new KeyFrame(Duration.millis(2100), t -> cTitan.setImage(cTitanRight.get(6)));
        KeyFrame kf8 = new KeyFrame(Duration.millis(2400), t -> cTitan.setImage(cTitanRight.get(7)));
        KeyFrame kf9 = new KeyFrame(Duration.millis(2700), t -> cTitan.setImage(cTitanAttack.get(0)));
        timeline.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6, kf7, kf8, kf9);
        timeline.play();
        translateTransition.setNode(cTitan);
        translateTransition.setByX(120);
        translateTransition.setDuration(Duration.millis(2700));
        translateTransition.play();
        translateTransition.setOnFinished(actionEvent -> {
            isAnimating = false;
            update();
        });
    }

    private void attackAni() {
        Timeline timeline = new Timeline();
        cTitan.setLayoutX(x);
        cTitan.setLayoutY(y);
        KeyFrame kf1 = new KeyFrame(Duration.millis(300), t -> cTitan.setImage(cTitanAttack.get(1)));
        KeyFrame kf2 = new KeyFrame(Duration.millis(600), t -> cTitan.setImage(cTitanAttack.get(2)));
        KeyFrame kf3 = new KeyFrame(Duration.millis(900), t -> cTitan.setImage(cTitanAttack.get(3)));
        KeyFrame kf4 = new KeyFrame(Duration.millis(1200), t -> cTitan.setImage(cTitanAttack.get(4)));
        KeyFrame kf5 = new KeyFrame(Duration.millis(1500), t -> cTitan.setImage(cTitanAttack.get(5)));
        KeyFrame kf6 = new KeyFrame(Duration.millis(1800), t -> cTitan.setImage(cTitanAttack.get(0)));
        timeline.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6);
        timeline.play();
        timeline.setOnFinished(actionEvent -> isAnimating = false);
    }

    private void update() {
        cTitan.setLayoutX(cTitan.getLayoutX() + cTitan.getTranslateX());
        cTitan.setTranslateX(0);
        x = cTitan.getLayoutX();
    }

    public void left() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isAnimating) {
                    leftAni();
                    isAnimating = true;
                    stop();
                }
            }
        }.start();
    }

    public void right() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isAnimating) {
                    rightAni();
                    isAnimating = true;
                    stop();
                }
            }
        }.start();
    }

    public void attack() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isAnimating) {
                    attackAni();
                    isAnimating = true;
                    stop();
                }
            }
        }.start();
    }

    public ImageView getView() {
        return cTitan;
    }

}
