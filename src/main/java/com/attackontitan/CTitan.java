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

    public CTitan(double x){
        int y=570;
        /*List<Image> cTitanArray = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            if (i >= 10) {
                cTitanArray.add(new javafx.scene.image.Image("com/attackontitan/0_Warrior_Attack_1_01"+(i-10)+".png"));
            } else {
                cTitanArray.add(new javafx.scene.image.Image("com/attackontitan/0_Warrior_Attack_1_00" + i + ".png"));
            }
        }*/
        List<Image> cTitanArray = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            cTitanArray.add(new javafx.scene.image.Image("com/attackontitan/attack" + i + ".png"));
        }
        cTitanArray.add(new javafx.scene.image.Image("com/attackontitan/idle.png"));
        //cTitanArray.add(new Image("com/attackontitan/attack.png"));
        print(cTitanArray,x,y);
    }

    private void print(List<Image>cTitanArray, double x, double y){
        Timeline timeline=new Timeline();

        this.cTitan.setX(x);
        this.cTitan.setY(y);
        this.cTitan.setScaleX(1.5);
        this.cTitan.setScaleY(1.5);

        KeyFrame kf1=new KeyFrame(Duration.millis(300), t -> this.cTitan.setImage(cTitanArray.get(0)));
        KeyFrame kf2=new KeyFrame(Duration.millis(600), t -> this.cTitan.setImage(cTitanArray.get(1)));
        KeyFrame kf3=new KeyFrame(Duration.millis(900), t -> this.cTitan.setImage(cTitanArray.get(2)));
        KeyFrame kf4=new KeyFrame(Duration.millis(1200), t -> this.cTitan.setImage(cTitanArray.get(3)));
        KeyFrame kf5=new KeyFrame(Duration.millis(1500), t -> this.cTitan.setImage(cTitanArray.get(4)));
        KeyFrame kf6=new KeyFrame(Duration.millis(1800), t -> this.cTitan.setImage(cTitanArray.get(5)));
        timeline.getKeyFrames().addAll(kf1,kf2,kf3,kf4,kf5,kf6);
        /*KeyFrame kf2=new KeyFrame(Duration.millis(200), t -> this.cTitan.setImage(cTitanArray.get(1)));
        KeyFrame kf3=new KeyFrame(Duration.millis(300), t -> this.cTitan.setImage(cTitanArray.get(2)));
        KeyFrame kf4=new KeyFrame(Duration.millis(400), t -> this.cTitan.setImage(cTitanArray.get(3)));
        KeyFrame kf5=new KeyFrame(Duration.millis(500), t -> this.cTitan.setImage(cTitanArray.get(4)));
        KeyFrame kf6=new KeyFrame(Duration.millis(600), t -> this.cTitan.setImage(cTitanArray.get(5)));
        KeyFrame kf7=new KeyFrame(Duration.millis(700), t -> this.cTitan.setImage(cTitanArray.get(6)));
        KeyFrame kf8=new KeyFrame(Duration.millis(800), t -> this.cTitan.setImage(cTitanArray.get(7)));
        KeyFrame kf9=new KeyFrame(Duration.millis(900), t -> this.cTitan.setImage(cTitanArray.get(8)));
        KeyFrame kf10=new KeyFrame(Duration.millis(1000), t -> this.cTitan.setImage(cTitanArray.get(9)));
        KeyFrame kf11=new KeyFrame(Duration.millis(1100), t -> this.cTitan.setImage(cTitanArray.get(10)));
        KeyFrame kf12=new KeyFrame(Duration.millis(1200), t -> this.cTitan.setImage(cTitanArray.get(11)));
        KeyFrame kf13=new KeyFrame(Duration.millis(1300), t -> this.cTitan.setImage(cTitanArray.get(12)));
        KeyFrame kf14=new KeyFrame(Duration.millis(1400), t -> this.cTitan.setImage(cTitanArray.get(13)));
        KeyFrame kf15=new KeyFrame(Duration.millis(1500), t -> this.cTitan.setImage(cTitanArray.get(14)));
        timeline.getKeyFrames().addAll(kf1,kf2,kf3,kf4,kf5,kf6,kf7,kf8,kf9,kf10,kf11,kf12,kf13,kf14,kf15);8?
        /*KeyFrame kf1=new KeyFrame(Duration.millis(500), t -> this.cTitan.setImage(cTitanArray.get(0)));
        KeyFrame kf2=new KeyFrame(Duration.millis(1000), t -> this.cTitan.setImage(cTitanArray.get(1)));
        KeyFrame kf3=new KeyFrame(Duration.millis(1500), t -> this.cTitan.setImage(cTitanArray.get(0)));
        timeline.getKeyFrames().addAll(kf1,kf2,kf3);*/
        timeline.play();
    }

    public ImageView getCTitan() {
        return cTitan;
    }
}
