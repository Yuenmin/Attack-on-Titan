package com.attackontitan;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

//cTitan possible x(45,165,285,405....)

public class CTitan {
    private ImageView cTitan =new ImageView();

    public CTitan(double x){
        ArrayList<CTitan>cTitanArrayList=new ArrayList<>();
        int y=495;
        List<Image> cTitanArray = new ArrayList<>();
        cTitanArray.add(new Image("com/attackontitan/walk1.png"));
        cTitanArray.add(new Image("com/attackontitan/attack.png"));
        print(cTitanArray,x,y);
    }

    private void print(List<Image>cTitanArray, double x, double y){
        Timeline timeline=new Timeline();
        timeline.setCycleCount(3);
        this.cTitan.setX(x);
        this.cTitan.setY(y);
        this.cTitan.setScaleX(0.6);
        this.cTitan.setScaleY(0.7);
        KeyFrame kf1=new KeyFrame(Duration.millis(500), t -> this.cTitan.setImage(cTitanArray.get(0)));
        KeyFrame kf2=new KeyFrame(Duration.millis(1000), t -> this.cTitan.setImage(cTitanArray.get(1)));
        KeyFrame kf3=new KeyFrame(Duration.millis(1500), t -> this.cTitan.setImage(cTitanArray.get(0)));
        timeline.getKeyFrames().addAll(kf1,kf2,kf3);
        timeline.play();
    }

    public ImageView getCTitan() {
        return cTitan;
    }
}
