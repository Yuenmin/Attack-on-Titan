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

//x -1
//when walk y -130
//when attack if titan at row 9 y=450

public class ATitan {

    private TranslateTransition translateTransition;
    private ImageView aTitan;
    private List<Image> aTitanWalk;
    private List<Image> aTitanLeft;
    private List<Image> aTitanRight;
    private List<Image> aTitanAttack;
    private double x;
    private double y;
    private boolean isAnimating;

    public ATitan(double x){
        initImage();
        this.x=x;
        y=-130;
        aTitan =new ImageView();
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
        aTitanRight = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            aTitanRight.add(new javafx.scene.image.Image("com/attackontitan/0right"+i+".png"));
        }
        aTitanAttack = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            aTitanAttack.add(new javafx.scene.image.Image("com/attackontitan/0attack"+i+".png"));
        }
    }

    private void walkAni(char mode){
        translateTransition=new TranslateTransition();
        Timeline timeline=new Timeline();
        timeline.setCycleCount(2);
        aTitan.setLayoutX(x);
        aTitan.setLayoutY(y);
        KeyFrame kf1=new KeyFrame(Duration.millis(300), t -> aTitan.setImage(aTitanWalk.get(1)));
        KeyFrame kf2=new KeyFrame(Duration.millis(600), t -> aTitan.setImage(aTitanWalk.get(2)));
        KeyFrame kf3=new KeyFrame(Duration.millis(900), t -> aTitan.setImage(aTitanWalk.get(3)));
        KeyFrame kf4=new KeyFrame(Duration.millis(1200), t -> aTitan.setImage(aTitanWalk.get(5)));
        KeyFrame kf5=new KeyFrame(Duration.millis(1500), t -> aTitan.setImage(aTitanWalk.get(6)));
        KeyFrame kf6=new KeyFrame(Duration.millis(1800), t -> aTitan.setImage(aTitanWalk.get(7)));
        timeline.getKeyFrames().addAll(kf1,kf2,kf3,kf4,kf5,kf6);
        timeline.play();
        if(mode=='s') {
            translateTransition.setFromY(15);
            translateTransition.setByY(50);
        }else{
            translateTransition.setByY(65);
        }
        translateTransition.setNode(aTitan);
        translateTransition.setDuration(Duration.millis(3600));
        translateTransition.play();
        translateTransition.setOnFinished(actionEvent -> {
            isAnimating=false;
            update();
        });
    }

    private void leftAni(){
        translateTransition=new TranslateTransition();
        Timeline timeline=new Timeline();
        timeline.setCycleCount(1);
        aTitan.setLayoutX(x);
        aTitan.setLayoutY(y);
        KeyFrame kf1=new KeyFrame(Duration.millis(300), t -> aTitan.setImage(aTitanLeft.get(0)));
        KeyFrame kf2=new KeyFrame(Duration.millis(600), t -> aTitan.setImage(aTitanLeft.get(1)));
        KeyFrame kf3=new KeyFrame(Duration.millis(900), t -> aTitan.setImage(aTitanLeft.get(2)));
        KeyFrame kf4=new KeyFrame(Duration.millis(1200), t -> aTitan.setImage(aTitanLeft.get(3)));
        KeyFrame kf5=new KeyFrame(Duration.millis(1500), t -> aTitan.setImage(aTitanLeft.get(4)));
        KeyFrame kf6=new KeyFrame(Duration.millis(1800), t -> aTitan.setImage(aTitanLeft.get(5)));
        KeyFrame kf7=new KeyFrame(Duration.millis(2100), t -> aTitan.setImage(aTitanLeft.get(6)));
        KeyFrame kf8=new KeyFrame(Duration.millis(2400), t -> aTitan.setImage(aTitanLeft.get(7)));
        KeyFrame kf9=new KeyFrame(Duration.millis(2700), t -> aTitan.setImage(aTitanWalk.get(0)));
        timeline.getKeyFrames().addAll(kf1,kf2,kf3,kf4,kf5,kf6,kf7,kf8,kf9);
        timeline.play();
        translateTransition.setNode(aTitan);
        translateTransition.setByX(-120);
        translateTransition.setDuration(Duration.millis(2700));
        translateTransition.play();
        translateTransition.setOnFinished(actionEvent -> walk('s'));
    }

    private void rightAni(){
        translateTransition=new TranslateTransition();
        Timeline timeline=new Timeline();
        timeline.setCycleCount(1);
        aTitan.setLayoutX(x);
        aTitan.setLayoutY(y);
        KeyFrame kf1=new KeyFrame(Duration.millis(300), t -> aTitan.setImage(aTitanRight.get(0)));
        KeyFrame kf2=new KeyFrame(Duration.millis(600), t -> aTitan.setImage(aTitanRight.get(1)));
        KeyFrame kf3=new KeyFrame(Duration.millis(900), t -> aTitan.setImage(aTitanRight.get(2)));
        KeyFrame kf4=new KeyFrame(Duration.millis(1200), t -> aTitan.setImage(aTitanRight.get(3)));
        KeyFrame kf5=new KeyFrame(Duration.millis(1500), t -> aTitan.setImage(aTitanRight.get(4)));
        KeyFrame kf6=new KeyFrame(Duration.millis(1800), t -> aTitan.setImage(aTitanRight.get(5)));
        KeyFrame kf7=new KeyFrame(Duration.millis(2100), t -> aTitan.setImage(aTitanRight.get(6)));
        KeyFrame kf8=new KeyFrame(Duration.millis(2400), t -> aTitan.setImage(aTitanRight.get(7)));
        KeyFrame kf9=new KeyFrame(Duration.millis(2700), t -> aTitan.setImage(aTitanWalk.get(0)));
        timeline.getKeyFrames().addAll(kf1,kf2,kf3,kf4,kf5,kf6,kf7,kf8,kf9);
        timeline.play();
        translateTransition.setNode(aTitan);
        translateTransition.setByX(120);
        translateTransition.setDuration(Duration.millis(2700));
        translateTransition.play();
        translateTransition.setOnFinished(actionEvent -> walk('s'));
    }

    private void attackAni(){
        Timeline timeline=new Timeline();
        timeline.setCycleCount(2);
        aTitan.setLayoutX(x);
        aTitan.setLayoutY(y);
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

    private void update(){
        aTitan.setLayoutX(aTitan.getLayoutX()+aTitan.getTranslateX());
        aTitan.setLayoutY(aTitan.getLayoutY()+aTitan.getTranslateY());
        aTitan.setTranslateX(0);
        aTitan.setTranslateY(0);
        x=aTitan.getLayoutX();
        y=aTitan.getLayoutY();
    }

    public void walk(){
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(!isAnimating()){
                    walkAni('a');
                    isAnimating=true;
                    stop();
                }
            }
        }.start();
    }

    private void walk(char mode){
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                walkAni(mode);
                stop();
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

    public void right(){
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(!isAnimating()){
                    rightAni();
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
