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

    private TranslateTransition translateTransition=new TranslateTransition();
    private ImageView aTitan =new ImageView();

    public ATitan(double x, double y){
        List<Image> aTitanWalk = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            if (i >= 10) {
                aTitanWalk.add(new javafx.scene.image.Image("com/attackontitan/Tuscan_Walk_0001"+(i-10)+".png"));
            } else {
                aTitanWalk.add(new javafx.scene.image.Image("com/attackontitan/Tuscan_Walk_0000" + i + ".png"));
            }
        }
        List<Image> aTitanAttack = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            if (i >= 10) {
                aTitanAttack.add(new javafx.scene.image.Image("com/attackontitan/Tuscan_Attack_02_0001"+(i-10)+".png"));
            } else {
                aTitanAttack.add(new javafx.scene.image.Image("com/attackontitan/Tuscan_Attack_02_0000" + i + ".png"));
            }
        }
        print(aTitanWalk,x,y);
    }

    private void print(List<Image>aTitanWalk, double x, double y){
        Timeline timeline=new Timeline();
        timeline.setCycleCount(1);
        this.aTitan.setX(x);
        this.aTitan.setY(y);
        aTitan.setScaleY(0.8);
        aTitan.setScaleX(0.9);
        KeyFrame kf1=new KeyFrame(Duration.millis(300), t -> this.aTitan.setImage(aTitanWalk.get(0)));
        KeyFrame kf2=new KeyFrame(Duration.millis(600), t -> this.aTitan.setImage(aTitanWalk.get(1)));
        KeyFrame kf3=new KeyFrame(Duration.millis(900), t -> this.aTitan.setImage(aTitanWalk.get(2)));
        KeyFrame kf4=new KeyFrame(Duration.millis(1200), t -> this.aTitan.setImage(aTitanWalk.get(3)));
        KeyFrame kf5=new KeyFrame(Duration.millis(1500), t -> this.aTitan.setImage(aTitanWalk.get(4)));
        KeyFrame kf6=new KeyFrame(Duration.millis(1800), t -> this.aTitan.setImage(aTitanWalk.get(5)));
        KeyFrame kf7=new KeyFrame(Duration.millis(2100), t -> this.aTitan.setImage(aTitanWalk.get(6)));
        KeyFrame kf8=new KeyFrame(Duration.millis(2400), t -> this.aTitan.setImage(aTitanWalk.get(7)));
        KeyFrame kf9=new KeyFrame(Duration.millis(2700), t -> this.aTitan.setImage(aTitanWalk.get(8)));
        KeyFrame kf10=new KeyFrame(Duration.millis(3000), t -> this.aTitan.setImage(aTitanWalk.get(9)));
        KeyFrame kf11=new KeyFrame(Duration.millis(3300), t -> this.aTitan.setImage(aTitanWalk.get(10)));
        KeyFrame kf12=new KeyFrame(Duration.millis(3600), t -> this.aTitan.setImage(aTitanWalk.get(11)));
        KeyFrame kf13=new KeyFrame(Duration.millis(3900), t -> this.aTitan.setImage(aTitanWalk.get(12)));
        KeyFrame kf14=new KeyFrame(Duration.millis(4200), t -> this.aTitan.setImage(aTitanWalk.get(13)));
        KeyFrame kf15=new KeyFrame(Duration.millis(4500), t -> this.aTitan.setImage(aTitanWalk.get(14)));
        timeline.getKeyFrames().addAll(kf1,kf2,kf3,kf4,kf5,kf6,kf7,kf8,kf9,kf10,kf11,kf12,kf13,kf14,kf15);
        timeline.play();
        walkTransition();
    }

    private void walkTransition(){
        translateTransition.setNode(aTitan);
        translateTransition.setFromY(aTitan.getY());
        translateTransition.setToY(aTitan.getY()+65);
        translateTransition.setDuration(Duration.millis(4500));
        translateTransition.play();
    }

    private void attack(List<Image>aTitanAttack, double x, double y){
        Timeline timeline=new Timeline();
        timeline.setCycleCount(1);
        this.aTitan.setX(x);
        this.aTitan.setY(y);
        aTitan.setScaleY(0.8);
        aTitan.setScaleX(0.9);
        KeyFrame kf1=new KeyFrame(Duration.millis(250), t -> this.aTitan.setImage(aTitanAttack.get(0)));
        KeyFrame kf2=new KeyFrame(Duration.millis(500), t -> this.aTitan.setImage(aTitanAttack.get(1)));
        KeyFrame kf3=new KeyFrame(Duration.millis(750), t -> this.aTitan.setImage(aTitanAttack.get(2)));
        KeyFrame kf4=new KeyFrame(Duration.millis(1000), t -> this.aTitan.setImage(aTitanAttack.get(3)));
        KeyFrame kf5=new KeyFrame(Duration.millis(1250), t -> this.aTitan.setImage(aTitanAttack.get(4)));
        KeyFrame kf6=new KeyFrame(Duration.millis(1500), t -> this.aTitan.setImage(aTitanAttack.get(5)));
        KeyFrame kf7=new KeyFrame(Duration.millis(1750), t -> this.aTitan.setImage(aTitanAttack.get(6)));
        KeyFrame kf8=new KeyFrame(Duration.millis(2000), t -> this.aTitan.setImage(aTitanAttack.get(7)));
        KeyFrame kf9=new KeyFrame(Duration.millis(2250), t -> this.aTitan.setImage(aTitanAttack.get(8)));
        KeyFrame kf10=new KeyFrame(Duration.millis(2500), t -> this.aTitan.setImage(aTitanAttack.get(9)));
        KeyFrame kf11=new KeyFrame(Duration.millis(2750), t -> this.aTitan.setImage(aTitanAttack.get(10)));
        KeyFrame kf12=new KeyFrame(Duration.millis(3000), t -> this.aTitan.setImage(aTitanAttack.get(11)));
        KeyFrame kf13=new KeyFrame(Duration.millis(3250), t -> this.aTitan.setImage(aTitanAttack.get(12)));
        KeyFrame kf14=new KeyFrame(Duration.millis(3500), t -> this.aTitan.setImage(aTitanAttack.get(13)));
        KeyFrame kf15=new KeyFrame(Duration.millis(3750), t -> this.aTitan.setImage(aTitanAttack.get(14)));
        KeyFrame kf16=new KeyFrame(Duration.millis(4000), t -> this.aTitan.setImage(aTitanAttack.get(15)));
        KeyFrame kf17=new KeyFrame(Duration.millis(4250), t -> this.aTitan.setImage(aTitanAttack.get(16)));
        KeyFrame kf18=new KeyFrame(Duration.millis(4500), t -> this.aTitan.setImage(aTitanAttack.get(17)));
        timeline.getKeyFrames().addAll(kf1,kf2,kf3,kf4,kf5,kf6,kf7,kf8,kf9,kf10,kf11,kf12,kf13,kf14,kf15,kf16,kf17,kf18);
        timeline.play();
    }

    public ImageView getATitan() {
        return aTitan;
    }
}
