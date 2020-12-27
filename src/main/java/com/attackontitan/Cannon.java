package com.attackontitan;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Cannon {

    private Group cannonGroup=new Group();
    private Timeline timeline = new Timeline();
    private ImageView cannon0 = new ImageView();
    private ImageView cannon1 = new ImageView();
    private ImageView cannon2 = new ImageView();
    private ImageView cannon3 = new ImageView();
    private ImageView cannon4 = new ImageView();
    private ImageView cannon5 = new ImageView();
    private ImageView cannon6 = new ImageView();
    private ImageView cannon7 = new ImageView();
    private ImageView cannon8 = new ImageView();
    private ImageView cannon9 = new ImageView();

    public Cannon() {
        cannonGroup.getChildren().addAll(cannon0,cannon1,cannon2,cannon3,cannon4,cannon5,cannon6,cannon7,cannon8,cannon9);
        List<Image> cannonImage = new ArrayList<>();
        cannonImage.add(new javafx.scene.image.Image("com/attackontitan/CannonFire_00000.png"));
        int y=550;
        Platform.runLater(new TimerTask() {
            @Override
            public void run() {
                print(cannonImage, y, 0);
            }
        });
    }

    public Cannon(int status) {
        cannonGroup.getChildren().addAll(cannon0,cannon1,cannon2,cannon3,cannon4,cannon5,cannon6,cannon7,cannon8,cannon9);
        List<Image> cannonImage = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            if (i == 10) {
                cannonImage.add(new javafx.scene.image.Image("com/attackontitan/CannonFire_00010.png"));
            } else {
                cannonImage.add(new javafx.scene.image.Image("com/attackontitan/CannonFire_0000" + i + ".png"));
            }
        }
        int y=550;
        print(cannonImage, y, status);
    }

    private void print(List<Image>cannonImage,int y,int status){
        fire(cannon0, cannonImage, 85, y, status);
        fire(cannon1, cannonImage, 205, y, status);
        fire(cannon2, cannonImage, 325, y, status);
        fire(cannon3, cannonImage, 446, y, status);
        fire(cannon4, cannonImage, 567, y, status);
        fire(cannon5, cannonImage, 688, y, status);
        fire(cannon6, cannonImage, 809, y, status);
        fire(cannon7, cannonImage, 930, y, status);
        fire(cannon8, cannonImage, 1051, y, status);
        fire(cannon9, cannonImage, 1172, y, status);
    }

    private void fire(ImageView cannon, List<Image> cannonImage, int x, int y,int status) {
        if(status==1) {
            timeline.setCycleCount(1);
            cannon.setX(x);
            cannon.setY(y);
            KeyFrame kf1 = new KeyFrame(Duration.millis(250), t -> cannon.setImage(cannonImage.get(0)));
            KeyFrame kf2 = new KeyFrame(Duration.millis(500), t -> cannon.setImage(cannonImage.get(1)));
            KeyFrame kf3 = new KeyFrame(Duration.millis(750), t -> cannon.setImage(cannonImage.get(2)));
            KeyFrame kf4 = new KeyFrame(Duration.millis(1000), t -> cannon.setImage(cannonImage.get(3)));
            KeyFrame kf5 = new KeyFrame(Duration.millis(1250), t -> cannon.setImage(cannonImage.get(4)));
            KeyFrame kf6 = new KeyFrame(Duration.millis(1500), t -> cannon.setImage(cannonImage.get(5)));
            KeyFrame kf7 = new KeyFrame(Duration.millis(1750), t -> cannon.setImage(cannonImage.get(6)));
            KeyFrame kf8 = new KeyFrame(Duration.millis(2000), t -> cannon.setImage(cannonImage.get(7)));
            KeyFrame kf9 = new KeyFrame(Duration.millis(2250), t -> cannon.setImage(cannonImage.get(8)));
            KeyFrame kf10 = new KeyFrame(Duration.millis(2500), t -> cannon.setImage(cannonImage.get(9)));
            KeyFrame kf11 = new KeyFrame(Duration.millis(2750), t -> cannon.setImage(cannonImage.get(0)));
            timeline.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6, kf7, kf8, kf9, kf10, kf11);
            timeline.play();
        }else{
            FadeTransition ft = new FadeTransition(Duration.millis(2000), cannonGroup);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
            cannon.setImage(cannonImage.get(0));
            cannon.setX(x);
            cannon.setY(y);
        }
    }

    public Group getCannonGroup() {
        return cannonGroup;
    }
}
