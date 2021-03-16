package com.attackontitan;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class ArmouredAndColossusTitanView {

    private double x;
    private double y;
    private List<Image> acTitanWalk;
    private List<Image> acTitanAttack;
    private ImageView acTitan;

    public ArmouredAndColossusTitanView(Titan titan1,Titan titan2,double column) {
        if(titan1 instanceof ArmouredTitan){
            titan1.getArmouredTitanView().getView().setVisible(false);
        }else{
            titan1.getColossusTitanView().getView().setVisible(false);
        }
        if(titan2 instanceof ArmouredTitan){
            titan2.getArmouredTitanView().getView().setVisible(false);
        }else{
            titan2.getColossusTitanView().getView().setVisible(false);
        }
        column /= 2;
        initImage();
        x = 105 + (column * 120);
        y = 545;
        acTitan = new ImageView();
        idle();
    }

    public void idle() {
        Lighting lighting = new Lighting();
        lighting.setLight(new Light.Distant(45, 45, Color.YELLOW));
        acTitan.setEffect(lighting);
        acTitan.setLayoutX(x);
        acTitan.setLayoutY(y);
        acTitan.setImage(acTitanWalk.get(0));
        FadeTransition ft = new FadeTransition(Duration.millis(1000), acTitan);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    private void initImage() {
        acTitanWalk = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            acTitanWalk.add(new Image(getClass().getResourceAsStream("images/titan/0walk" + i + ".png")));
        }
        acTitanAttack = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            acTitanAttack.add(new Image(getClass().getResourceAsStream("images/titan/0attack" + i + ".png")));
        }
    }

    public void attack() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        acTitan.setLayoutX(x);
        acTitan.setLayoutY(y);
        KeyFrame kf1 = new KeyFrame(Duration.millis(300), t -> acTitan.setImage(acTitanAttack.get(0)));
        KeyFrame kf2 = new KeyFrame(Duration.millis(600), t -> acTitan.setImage(acTitanAttack.get(1)));
        KeyFrame kf3 = new KeyFrame(Duration.millis(900), t -> acTitan.setImage(acTitanAttack.get(2)));
        KeyFrame kf4 = new KeyFrame(Duration.millis(1200), t -> acTitan.setImage(acTitanAttack.get(3)));
        KeyFrame kf5 = new KeyFrame(Duration.millis(1500), t -> acTitan.setImage(acTitanAttack.get(2)));
        KeyFrame kf6 = new KeyFrame(Duration.millis(1800), t -> acTitan.setImage(acTitanAttack.get(1)));
        KeyFrame kf7 = new KeyFrame(Duration.millis(2100), t -> acTitan.setImage(acTitanAttack.get(0)));
        timeline.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6, kf7);
        timeline.play();
    }

    public ImageView getView() {
        return acTitan;
    }
}
