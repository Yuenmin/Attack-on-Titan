package com.attackontitan;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

//x 95
//when attack if titan at row 9 y=450

public class ArmouredTitanView {

    private TranslateTransition translateTransition;
    private ImageView aTitan;
    private List<Image> aTitanWalk;
    private List<Image> aTitanLeft;
    private List<Image> aTitanRight;
    private List<Image> aTitanAttack;
    private double x;
    private double y;
    private boolean isAnimating;

    public ArmouredTitanView(double column) {
        column /= 2;
        initImage();
        x = 95 + (column * 120);
        y = -65;
        aTitan = new ImageView();
        idle();
    }

    public void idle() {
        aTitan.setLayoutX(x);
        aTitan.setLayoutY(y);
        aTitan.setImage(aTitanWalk.get(0));
        FadeTransition ft = new FadeTransition(Duration.millis(2000), aTitan);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    private void initImage() {
        aTitanWalk = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            aTitanWalk.add(new Image(getClass().getResourceAsStream("images/titan/0walk" + i + ".png")));
        }
        aTitanLeft = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            aTitanLeft.add(new Image(getClass().getResourceAsStream("images/titan/0left" + i + ".png")));
        }
        aTitanRight = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            aTitanRight.add(new Image(getClass().getResourceAsStream("images/titan/0right" + i + ".png")));
        }
        aTitanAttack = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            aTitanAttack.add(new Image(getClass().getResourceAsStream("images/titan/0attack" + i + ".png")));
        }
    }

    private void walkAni(char mode) {
        translateTransition = new TranslateTransition();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
        aTitan.setLayoutX(x);
        aTitan.setLayoutY(y);
        KeyFrame kf1 = new KeyFrame(Duration.millis(300), t -> aTitan.setImage(aTitanWalk.get(1)));
        KeyFrame kf2 = new KeyFrame(Duration.millis(600), t -> aTitan.setImage(aTitanWalk.get(2)));
        KeyFrame kf3 = new KeyFrame(Duration.millis(900), t -> aTitan.setImage(aTitanWalk.get(3)));
        KeyFrame kf4 = new KeyFrame(Duration.millis(1200), t -> aTitan.setImage(aTitanWalk.get(5)));
        KeyFrame kf5 = new KeyFrame(Duration.millis(1500), t -> aTitan.setImage(aTitanWalk.get(6)));
        KeyFrame kf6 = new KeyFrame(Duration.millis(1800), t -> aTitan.setImage(aTitanWalk.get(7)));
        timeline.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6);
        timeline.play();
        if (mode == 's') {
            translateTransition.setFromY(15);
            translateTransition.setByY(50);
        } else {
            translateTransition.setByY(65);
        }
        translateTransition.setNode(aTitan);
        translateTransition.setDuration(Duration.millis(3600));
        translateTransition.play();
        translateTransition.setOnFinished(actionEvent -> {
            isAnimating = false;
            update();
        });
    }

    private void leftAni(int n) {
        translateTransition = new TranslateTransition();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        aTitan.setLayoutX(x);
        aTitan.setLayoutY(y);
        KeyFrame kf1 = new KeyFrame(Duration.millis(300), t -> aTitan.setImage(aTitanLeft.get(0)));
        KeyFrame kf2 = new KeyFrame(Duration.millis(600), t -> aTitan.setImage(aTitanLeft.get(1)));
        KeyFrame kf3 = new KeyFrame(Duration.millis(900), t -> aTitan.setImage(aTitanLeft.get(2)));
        KeyFrame kf4 = new KeyFrame(Duration.millis(1200), t -> aTitan.setImage(aTitanLeft.get(3)));
        KeyFrame kf5 = new KeyFrame(Duration.millis(1500), t -> aTitan.setImage(aTitanLeft.get(4)));
        KeyFrame kf6 = new KeyFrame(Duration.millis(1800), t -> aTitan.setImage(aTitanLeft.get(5)));
        KeyFrame kf7 = new KeyFrame(Duration.millis(2100), t -> aTitan.setImage(aTitanLeft.get(6)));
        KeyFrame kf8 = new KeyFrame(Duration.millis(2400), t -> aTitan.setImage(aTitanLeft.get(7)));
        KeyFrame kf9 = new KeyFrame(Duration.millis(2700), t -> aTitan.setImage(aTitanWalk.get(0)));
        timeline.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6, kf7, kf8, kf9);
        timeline.play();
        translateTransition.setNode(aTitan);
        translateTransition.setByX(-120);
        translateTransition.setDuration(Duration.millis(2700));
        translateTransition.play();
        if (n == 1) {
            translateTransition.setOnFinished(actionEvent -> walkAfterMove('s'));
        } else {
            translateTransition.setOnFinished(actionEvent -> isAnimating = false);
        }
    }

    private void rightAni(int n) {
        translateTransition = new TranslateTransition();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        aTitan.setLayoutX(x);
        aTitan.setLayoutY(y);
        KeyFrame kf1 = new KeyFrame(Duration.millis(300), t -> aTitan.setImage(aTitanRight.get(0)));
        KeyFrame kf2 = new KeyFrame(Duration.millis(600), t -> aTitan.setImage(aTitanRight.get(1)));
        KeyFrame kf3 = new KeyFrame(Duration.millis(900), t -> aTitan.setImage(aTitanRight.get(2)));
        KeyFrame kf4 = new KeyFrame(Duration.millis(1200), t -> aTitan.setImage(aTitanRight.get(3)));
        KeyFrame kf5 = new KeyFrame(Duration.millis(1500), t -> aTitan.setImage(aTitanRight.get(4)));
        KeyFrame kf6 = new KeyFrame(Duration.millis(1800), t -> aTitan.setImage(aTitanRight.get(5)));
        KeyFrame kf7 = new KeyFrame(Duration.millis(2100), t -> aTitan.setImage(aTitanRight.get(6)));
        KeyFrame kf8 = new KeyFrame(Duration.millis(2400), t -> aTitan.setImage(aTitanRight.get(7)));
        KeyFrame kf9 = new KeyFrame(Duration.millis(2700), t -> aTitan.setImage(aTitanWalk.get(0)));
        timeline.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6, kf7, kf8, kf9);
        timeline.play();
        translateTransition.setNode(aTitan);
        translateTransition.setByX(120);
        translateTransition.setDuration(Duration.millis(2700));
        translateTransition.play();
        if (n == 1) {
            translateTransition.setOnFinished(actionEvent -> walkAfterMove('s'));
        } else {
            translateTransition.setOnFinished(actionEvent -> isAnimating = false);
        }
    }

    private void attackAni() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        aTitan.setLayoutX(x);
        aTitan.setLayoutY(y);
        KeyFrame kf1 = new KeyFrame(Duration.millis(300), t -> aTitan.setImage(aTitanAttack.get(0)));
        KeyFrame kf2 = new KeyFrame(Duration.millis(600), t -> aTitan.setImage(aTitanAttack.get(1)));
        KeyFrame kf3 = new KeyFrame(Duration.millis(900), t -> aTitan.setImage(aTitanAttack.get(2)));
        KeyFrame kf4 = new KeyFrame(Duration.millis(1200), t -> aTitan.setImage(aTitanAttack.get(3)));
        KeyFrame kf5 = new KeyFrame(Duration.millis(1500), t -> aTitan.setImage(aTitanAttack.get(2)));
        KeyFrame kf6 = new KeyFrame(Duration.millis(1800), t -> aTitan.setImage(aTitanAttack.get(1)));
        KeyFrame kf7 = new KeyFrame(Duration.millis(2100), t -> aTitan.setImage(aTitanAttack.get(0)));
        timeline.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6, kf7);
        timeline.play();
        timeline.setOnFinished(actionEvent -> isAnimating = false);
    }

    private void update() {
        aTitan.setLayoutX(aTitan.getLayoutX() + aTitan.getTranslateX());
        aTitan.setLayoutY(aTitan.getLayoutY() + aTitan.getTranslateY());
        aTitan.setTranslateX(0);
        aTitan.setTranslateY(0);
        x = aTitan.getLayoutX();
        y = aTitan.getLayoutY();
    }

    public void walk() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isAnimating) {
                    walkAni('a');
                    isAnimating = true;
                    stop();
                }
            }
        }.start();
    }

    private void walkAfterMove(char mode) {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                walkAni(mode);
                stop();
            }
        }.start();
    }

    public void leftAndWalk() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isAnimating) {
                    leftAni(1);
                    isAnimating = true;
                    stop();
                }
            }
        }.start();
    }

    public void left() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isAnimating) {
                    leftAni(0);
                    isAnimating = true;
                    stop();
                }
            }
        }.start();
    }

    public void rightAndWalk() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isAnimating) {
                    rightAni(1);
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
                    rightAni(0);
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
                    isAnimating = true;
                    System.out.println("hi");
                    attackAni();
                    stop();
                }
            }
        }.start();
    }

    public ImageView getView() {
        return aTitan;
    }

}
