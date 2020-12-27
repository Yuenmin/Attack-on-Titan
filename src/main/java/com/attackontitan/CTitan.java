package com.attackontitan;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

//cTitan possible x(110,230,350,470....)

public class CTitan {

    private ImageView cTitan =new ImageView();
    private double x;
    private double y;

    public CTitan(double x){
        this.x=x;
        y=570;
        attack(x,y);
    }

    private void attack(double x, double y){
        List<Image> cTitanArray = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            cTitanArray.add(new javafx.scene.image.Image("com/attackontitan/attack" + i + ".png"));
        }
        cTitanArray.add(new javafx.scene.image.Image("com/attackontitan/idle.png"));
        Timeline timeline=new Timeline();
        cTitan.setX(x);
        cTitan.setY(y);
        cTitan.setScaleX(1.5);
        cTitan.setScaleY(1.5);
        KeyFrame kf1=new KeyFrame(Duration.millis(300), t -> cTitan.setImage(cTitanArray.get(0)));
        KeyFrame kf2=new KeyFrame(Duration.millis(600), t -> cTitan.setImage(cTitanArray.get(1)));
        KeyFrame kf3=new KeyFrame(Duration.millis(900), t -> cTitan.setImage(cTitanArray.get(2)));
        KeyFrame kf4=new KeyFrame(Duration.millis(1200), t -> cTitan.setImage(cTitanArray.get(3)));
        KeyFrame kf5=new KeyFrame(Duration.millis(1500), t -> cTitan.setImage(cTitanArray.get(4)));
        KeyFrame kf6=new KeyFrame(Duration.millis(1800), t -> cTitan.setImage(cTitanArray.get(5)));
        timeline.getKeyFrames().addAll(kf1,kf2,kf3,kf4,kf5,kf6);
        timeline.play();
    }

    public ImageView getView() {
        return cTitan;
    }
}
