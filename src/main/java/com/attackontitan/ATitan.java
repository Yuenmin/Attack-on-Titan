package com.attackontitan;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
//x -50,80,210,330,440,550...
//when walk y -140,-107.5,-75,-42.5.....
//when attack if titan at row 9 y=450

public class ATitan {

    private TranslateTransition translateTransition;
    private ImageView aTitan;
    private List<Image> aTitanWalk;
    private List<Image> aTitanLeft;
    private List<Image> aTitanAttack;
    private double x;
    private double y;
    private boolean isAnimating;

    public ATitan(double x, double y){
        initImage();
        this.x=x;
        this.y=y;
        aTitan =new ImageView();
        translateTransition=new TranslateTransition();
    }

    private void initImage(){
        aTitanWalk = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            aTitanWalk.add(new javafx.scene.image.Image("com/attackontitan/0walk"+i+".png"));
        }
        aTitanLeft = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            aTitanLeft.add(new javafx.scene.image.Image("com/attackontitan/0left"+i+".png"));
        }
        aTitanAttack = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            aTitanAttack.add(new javafx.scene.image.Image("com/attackontitan/0attack"+i+".png"));
        }
    }

    private void walkAni(){
        Timeline timeline=new Timeline();
        timeline.setCycleCount(3);
        aTitan.setX(x);
        aTitan.setY(y);
        KeyFrame kf1=new KeyFrame(Duration.millis(300), t -> aTitan.setImage(aTitanWalk.get(1)));
        KeyFrame kf2=new KeyFrame(Duration.millis(600), t -> aTitan.setImage(aTitanWalk.get(2)));
        KeyFrame kf3=new KeyFrame(Duration.millis(900), t -> aTitan.setImage(aTitanWalk.get(3)));
        KeyFrame kf4=new KeyFrame(Duration.millis(1200), t -> aTitan.setImage(aTitanWalk.get(5)));
        KeyFrame kf5=new KeyFrame(Duration.millis(1500), t -> aTitan.setImage(aTitanWalk.get(6)));
        KeyFrame kf6=new KeyFrame(Duration.millis(1800), t -> aTitan.setImage(aTitanWalk.get(7)));
        timeline.getKeyFrames().addAll(kf1,kf2,kf3,kf4,kf5,kf6);
        timeline.play();
        translateTransition.setNode(aTitan);
        translateTransition.setFromY(aTitan.getY());
        translateTransition.setToY(aTitan.getY()+65);
        translateTransition.setDuration(Duration.millis(5400));
        translateTransition.play();
        timeline.setOnFinished(actionEvent -> isAnimating=false);
    }

    private void leftAni(){
        Timeline timeline=new Timeline();
        timeline.setCycleCount(2);
        aTitan.setX(300);
        aTitan.setY(245);
        KeyFrame kf1=new KeyFrame(Duration.millis(300), t -> aTitan.setImage(aTitanLeft.get(0)));
        KeyFrame kf2=new KeyFrame(Duration.millis(600), t -> aTitan.setImage(aTitanLeft.get(1)));
        KeyFrame kf3=new KeyFrame(Duration.millis(900), t -> aTitan.setImage(aTitanLeft.get(2)));
        KeyFrame kf4=new KeyFrame(Duration.millis(1200), t -> aTitan.setImage(aTitanLeft.get(3)));
        KeyFrame kf5=new KeyFrame(Duration.millis(1500), t -> aTitan.setImage(aTitanLeft.get(4)));
        KeyFrame kf6=new KeyFrame(Duration.millis(1800), t -> aTitan.setImage(aTitanLeft.get(5)));
        KeyFrame kf7=new KeyFrame(Duration.millis(2100), t -> aTitan.setImage(aTitanLeft.get(6)));
        KeyFrame kf8=new KeyFrame(Duration.millis(2400), t -> aTitan.setImage(aTitanLeft.get(7)));
        timeline.getKeyFrames().addAll(kf1,kf2,kf3,kf4,kf5,kf6,kf7,kf8);
        timeline.play();
        translateTransition.setNode(aTitan);
        translateTransition.setFromX(aTitan.getX());
        translateTransition.setToX(aTitan.getX()-30);
        translateTransition.setDuration(Duration.millis(4800));
        translateTransition.play();
        timeline.setOnFinished(actionEvent -> isAnimating=false);
    }

    private void attackAni(){
        Timeline timeline=new Timeline();
        timeline.setCycleCount(2);
        aTitan.setX(x);
        aTitan.setY(y);
        KeyFrame kf1=new KeyFrame(Duration.millis(300), t -> aTitan.setImage(aTitanAttack.get(0)));
        KeyFrame kf2=new KeyFrame(Duration.millis(600), t -> aTitan.setImage(aTitanAttack.get(1)));
        KeyFrame kf3=new KeyFrame(Duration.millis(900), t -> aTitan.setImage(aTitanAttack.get(2)));
        KeyFrame kf4=new KeyFrame(Duration.millis(1200), t -> aTitan.setImage(aTitanAttack.get(3)));
        KeyFrame kf5=new KeyFrame(Duration.millis(1500), t -> aTitan.setImage(aTitanAttack.get(2)));
        KeyFrame kf6=new KeyFrame(Duration.millis(1800), t -> aTitan.setImage(aTitanAttack.get(1)));
        KeyFrame kf7=new KeyFrame(Duration.millis(2100), t -> aTitan.setImage(aTitanAttack.get(0)));
        timeline.getKeyFrames().addAll(kf1,kf2,kf3,kf4,kf5,kf6,kf7);
        timeline.play();
        timeline.setOnFinished(actionEvent -> isAnimating=false);
    }

    public void walk(){
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(!isAnimating()){
                    walkAni();
                    isAnimating=true;
                    stop();
                }
            }
        }.start();
    }

    public void left(){
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(!isAnimating()){
                    leftAni();
                    isAnimating=true;
                    stop();
                }
            }
        }.start();
    }

    public void attack(){
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(!isAnimating()){
                    attackAni();
                    isAnimating=true;
                    stop();
                }
            }
        }.start();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public ImageView getView() {
        return aTitan;
    }

    public boolean isAnimating() {
        return isAnimating;
    }
}
