package com.attackontitan;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Coin {
    private int curCoin;

    public Coin() {
        curCoin = 50;

    }

    public int getCurCoin() {
        return curCoin;
    }

    public void setCurCoin(int curCoin) {
        this.curCoin = curCoin;
    }

    public void increaseCoinPerHours() {
        curCoin += 5;
    }

    public void pay(int paymentCoin) {
        curCoin -= paymentCoin;
    }

}
