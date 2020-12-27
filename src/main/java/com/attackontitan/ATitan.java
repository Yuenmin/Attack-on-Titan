package com.attackontitan;

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
    private double x;
    private double y;

    public ATitan(double x, double y,char status){
        this.x=x;
        this.y=y;
        aTitan =new ImageView();
        translateTransition=new TranslateTransition();
        switch(status) {
            case 'w':
                walk(x, y);
                break;
            case 'a':
                attack(x,y);
        }
    }

    private void walk(double x, double y){
        List<Image> aTitanWalk = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            aTitanWalk.add(new javafx.scene.image.Image("com/attackontitan/0walk"+i+".png"));
        }
        Timeline timeline=new Timeline();
        timeline.setCycleCount(3);
        aTitan.setX(x);
        aTitan.setY(y);
        aTitan.setScaleX(1.7);
        aTitan.setScaleY(1.7);
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
        translateTransition.setToY(aTitan.getY()+60);
        translateTransition.setDuration(Duration.millis(5400));
        translateTransition.play();
    }

    private void attack(double x, double y){
        List<Image> aTitanAttack = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            aTitanAttack.add(new javafx.scene.image.Image("com/attackontitan/0attack"+i+".png"));
        }
        Timeline timeline=new Timeline();
        timeline.setCycleCount(2);
        this.aTitan.setX(x);
        this.aTitan.setY(y);
        aTitan.setScaleY(1.5);
        aTitan.setScaleX(1.5);
        KeyFrame kf1=new KeyFrame(Duration.millis(300), t -> this.aTitan.setImage(aTitanAttack.get(0)));
        KeyFrame kf2=new KeyFrame(Duration.millis(600), t -> this.aTitan.setImage(aTitanAttack.get(1)));
        KeyFrame kf3=new KeyFrame(Duration.millis(900), t -> this.aTitan.setImage(aTitanAttack.get(2)));
        KeyFrame kf4=new KeyFrame(Duration.millis(1200), t -> this.aTitan.setImage(aTitanAttack.get(3)));
        KeyFrame kf5=new KeyFrame(Duration.millis(1500), t -> this.aTitan.setImage(aTitanAttack.get(2)));
        KeyFrame kf6=new KeyFrame(Duration.millis(1800), t -> this.aTitan.setImage(aTitanAttack.get(1)));
        KeyFrame kf7=new KeyFrame(Duration.millis(300), t -> this.aTitan.setImage(aTitanAttack.get(0)));
        timeline.getKeyFrames().addAll(kf1,kf2,kf3,kf4,kf5,kf6,kf7);
        timeline.play();
    }

    public ImageView getView() {
        return aTitan;
    }
}
