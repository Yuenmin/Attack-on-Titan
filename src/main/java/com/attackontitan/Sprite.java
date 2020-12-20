package com.attackontitan;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Sprite {
    TranslateTransition translateTransition=new TranslateTransition();
    public ImageView titan =new ImageView();

    public Sprite(int x,int y){
        List<Image> titanImage = new ArrayList<>();
        titanImage.add(new Image("com/attackontitan/walk1.png"));
        titanImage.add(new Image("com/attackontitan/walk2.png"));
        titanImage.add(new Image("com/attackontitan/walk3.png"));
        print(titanImage,x,y);
    }

    private void print(List<Image>titanImage, int x, int y){
        Timeline timeline=new Timeline();
        timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
        titan.setX(x);
        titan.setY(y);
        titan.setScaleX(0.5);
        titan.setScaleY(0.5);
        KeyFrame kf1=new KeyFrame(Duration.millis(500), t -> titan.setImage(titanImage.get(0)));
        KeyFrame kf2=new KeyFrame(Duration.millis(1000), t -> titan.setImage(titanImage.get(1)));
        KeyFrame kf3=new KeyFrame(Duration.millis(1500), t -> titan.setImage(titanImage.get(2)));
        timeline.getKeyFrames().addAll(kf1,kf2,kf3);
        timeline.play();
    }

    public void titanWalk(){
        translateTransition.setNode(titan);
        translateTransition.setFromY(titan.getY());
        translateTransition.setToY(titan.getY()+50);
        translateTransition.setDuration(Duration.millis(5000));
        translateTransition.play();
    }
}