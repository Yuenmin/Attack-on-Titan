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

public class CoinView {
    private Image[]coinArray=new Image[7];
    private ImageView coinImage=new ImageView();
    private Text coinIncrease = new Text();

    public CoinView(){
        initImage();
    }

    public void initImage(){
        for (int i = 1; i < 7; i++) {
            coinArray[i]=new Image(getClass().getResourceAsStream("images/coin/Coin" + i + ".png"));
        }
        coinImage.setImage(coinArray[1]);
        coinImage.setScaleX(1.05);
        coinImage.setLayoutX(1285);
        coinImage.setLayoutY(130);
    }

    public void coinAni(){
        Timeline timeline=new Timeline();
        KeyFrame kf1=new KeyFrame(Duration.millis(150), t -> coinImage.setImage(coinArray[1]));
        KeyFrame kf2=new KeyFrame(Duration.millis(300), t -> coinImage.setImage(coinArray[2]));
        KeyFrame kf3=new KeyFrame(Duration.millis(450), t -> coinImage.setImage(coinArray[3]));
        KeyFrame kf4=new KeyFrame(Duration.millis(600), t -> coinImage.setImage(coinArray[4]));
        KeyFrame kf5=new KeyFrame(Duration.millis(750), t -> coinImage.setImage(coinArray[5]));
        KeyFrame kf6=new KeyFrame(Duration.millis(900), t -> coinImage.setImage(coinArray[6]));
        KeyFrame kf7=new KeyFrame(Duration.millis(1050), t -> coinImage.setImage(coinArray[1]));
        timeline.getKeyFrames().addAll(kf1,kf2,kf3,kf4,kf5,kf6,kf7);
        timeline.play();
        TranslateTransition t1=new TranslateTransition();
        t1.setNode(coinImage);
        t1.setByY(-20);
        t1.setDuration(Duration.millis(500));
        TranslateTransition t2=new TranslateTransition();
        t2.setNode(coinImage);
        t2.setByY(20);
        t2.setDuration(Duration.millis(500));
        t1.play();
        t1.setOnFinished(actionEvent -> t2.play());
        textAni();
    }

    private void textAni(){
        coinIncrease.setText("+5");
        coinIncrease.setLayoutX(coinImage.getLayoutX()+35);
        coinIncrease.setLayoutY(coinImage.getLayoutY()+40);
        coinIncrease.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        coinIncrease.setFill(Color.WHITE);
        TranslateTransition t3=new TranslateTransition();
        t3.setNode(coinIncrease);
        t3.setByY(- 10);
        t3.setDuration(Duration.millis(600));
        t3.play();
        t3.setOnFinished(actionEvent -> {
            coinIncrease.setText("");
            coinIncrease.setTranslateY(0);
        });
        FadeTransition ft=new FadeTransition(Duration.millis(600),coinIncrease);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.play();
    }

    public Text getCoinIncrease() {
        return coinIncrease;
    }

    public ImageView getCoinImage() {
        return coinImage;
    }
}
