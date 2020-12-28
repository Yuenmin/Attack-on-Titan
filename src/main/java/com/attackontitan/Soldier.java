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

public class Soldier{

    private Group soldierGroup=new Group();
    private ImageView soldier0 = new ImageView();
    private ImageView soldier1 = new ImageView();
    private ImageView soldier2 = new ImageView();
    private ImageView soldier3 = new ImageView();
    private ImageView soldier4 = new ImageView();
    private ImageView soldier5 = new ImageView();
    private ImageView soldier6 = new ImageView();
    private ImageView soldier7 = new ImageView();
    private ImageView soldier8 = new ImageView();
    private ImageView soldier9 = new ImageView();
    private List<Image> soldierImage;
    private boolean isAnimating;
    private int y;

    public Soldier() {
        soldierGroup.getChildren().addAll(soldier0,soldier1, soldier2, soldier3, soldier4, soldier5, soldier6, soldier7, soldier8, soldier9);
        soldierImage = new ArrayList<>();
        soldierImage.add(new Image("com/attackontitan/sback1.png"));
        soldierImage.add(new Image("com/attackontitan/sback2.png"));
        soldierImage.add(new Image("com/attackontitan/sback3.png"));
        soldierImage.add(new Image("com/attackontitan/sright1.png"));
        soldierImage.add(new Image("com/attackontitan/sright2.png"));
        soldierImage.add(new Image("com/attackontitan/sright3.png"));
        isAnimating =true;
        y=800;
    }

    public void show(){
        animation(soldier0, soldierImage, 52);
        animation(soldier1, soldierImage, 174);
        animation(soldier2, soldierImage, 295);
        animation(soldier3, soldierImage, 416);
        animation(soldier4, soldierImage, 537);
        animation(soldier5, soldierImage, 658);
        animation(soldier6, soldierImage, 779);
        animation(soldier7, soldierImage, 902);
        animation(soldier8, soldierImage, 1023);
        animation(soldier9, soldierImage, 1145);
    }

    private void animation(ImageView soldier, List<Image> soldierImage, int x) {
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
        walkTransition();
    }

    private void walkTransition(){
        TranslateTransition t1=new TranslateTransition();
        TranslateTransition t2=new TranslateTransition();
        t1.setDelay(Duration.millis(1500));
        t1.setNode(soldierGroup);
        t1.setToY(-138);
        t1.setDuration(Duration.millis(3500));
        t1.play();
        t2.setNode(soldierGroup);
        t2.setToX(62);
        t2.setDuration(Duration.millis(2250));
        t1.setOnFinished(actionEvent -> t2.play());
        t2.setOnFinished(actionEvent -> isAnimating =false);
    }

    public Group getSoldierGroup() {
        return soldierGroup;
    }

    public boolean isAnimating() {
        return isAnimating;
    }
}
