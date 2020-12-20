package com.attackontitan;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Soldier {

    public Group soldierGroup=new Group();
    ImageView soldier1 = new ImageView();
    ImageView soldier2 = new ImageView();
    ImageView soldier3 = new ImageView();
    ImageView soldier4 = new ImageView();
    ImageView soldier5 = new ImageView();
    ImageView soldier6 = new ImageView();
    ImageView soldier7 = new ImageView();
    ImageView soldier8 = new ImageView();
    ImageView soldier9 = new ImageView();

    public Soldier() {
        soldierGroup.getChildren().addAll(soldier1, soldier2, soldier3, soldier4, soldier5, soldier6, soldier7, soldier8, soldier9);
        List<Image> soldierImage = new ArrayList<>();
        soldierImage.add(new Image("com/attackontitan/back1.png"));
        soldierImage.add(new Image("com/attackontitan/back2.png"));
        soldierImage.add(new Image("com/attackontitan/back3.png"));
        soldierImage.add(new Image("com/attackontitan/right1.png"));
        soldierImage.add(new Image("com/attackontitan/right2.png"));
        soldierImage.add(new Image("com/attackontitan/right3.png"));
        int y=730;
        print(soldier1, soldierImage, 10, y);
        print(soldier2, soldierImage, 180, y);
        print(soldier3, soldierImage, 350, y);
        print(soldier4, soldierImage, 520, y);
        print(soldier5, soldierImage, 690, y);
        print(soldier6, soldierImage, 860, y);
        print(soldier7, soldierImage, 1030, y);
        print(soldier8, soldierImage, 1200, y);
        print(soldier9, soldierImage, 1370, y);
    }

    private void print(ImageView soldier, List<Image> soldierImage, int x, int y) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        soldier.setX(x);
        soldier.setY(y);
        KeyFrame kf1 = new KeyFrame(Duration.millis(1500), t -> soldier.setImage(soldierImage.get(0)));
        KeyFrame kf2 = new KeyFrame(Duration.millis(2000), t -> soldier.setImage(soldierImage.get(1)));
        KeyFrame kf3 = new KeyFrame(Duration.millis(2500), t -> soldier.setImage(soldierImage.get(0)));
        KeyFrame kf4 = new KeyFrame(Duration.millis(3000), t -> soldier.setImage(soldierImage.get(2)));
        KeyFrame kf5 = new KeyFrame(Duration.millis(3500), t -> soldier.setImage(soldierImage.get(0)));
        KeyFrame kf6 = new KeyFrame(Duration.millis(4000), t -> soldier.setImage(soldierImage.get(1)));
        KeyFrame kf7 = new KeyFrame(Duration.millis(4500), t -> soldier.setImage(soldierImage.get(0)));
        KeyFrame kf8 = new KeyFrame(Duration.millis(5000), t -> soldier.setImage(soldierImage.get(3)));
        KeyFrame kf9 = new KeyFrame(Duration.millis(5500), t -> soldier.setImage(soldierImage.get(4)));
        KeyFrame kf10 = new KeyFrame(Duration.millis(6000), t -> soldier.setImage(soldierImage.get(3)));
        KeyFrame kf11 = new KeyFrame(Duration.millis(6500), t -> soldier.setImage(soldierImage.get(5)));
        KeyFrame kf12 = new KeyFrame(Duration.millis(7000), t -> soldier.setImage(soldierImage.get(5)));
        KeyFrame kf13= new KeyFrame(Duration.millis(7500), t -> soldier.setImage(soldierImage.get(0)));
        timeline.getKeyFrames().addAll(kf1, kf2, kf3,kf4,kf5,kf6,kf7,kf8,kf9,kf10,kf11,kf12,kf13);
        timeline.play();
        soldierWalk();
    }

    public void soldierWalk(){
        TranslateTransition t1=new TranslateTransition();
        TranslateTransition t2=new TranslateTransition();
        t1.setDelay(Duration.millis(1500));
        t1.setNode(soldierGroup);
        t1.setToY(-120);
        t1.setDuration(Duration.millis(3500));
        t1.play();
        t2.setNode(soldierGroup);
        t2.setToX(85);
        t2.setDuration(Duration.millis(2250));
        t1.setOnFinished(actionEvent -> t2.play());
    }
}