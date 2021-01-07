package com.attackontitan;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class WeaponView {
    private Group cannonGroup = new Group();
    private Group levelGroup = new Group();
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
    private List<Image> cannonImage;
    private Timeline timeline;
    private int y;

    public WeaponView() {
        cannonGroup.getChildren().addAll(cannon0, cannon1, cannon2, cannon3, cannon4, cannon5, cannon6, cannon7, cannon8, cannon9, levelGroup);
        cannonImage = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            if (i == 10) {
                cannonImage.add(new Image(getClass().getResourceAsStream("images/cannon/CannonFire_00010.png")));
            } else {
                cannonImage.add(new Image(getClass().getResourceAsStream("images/cannon/CannonFire_0000" + i + ".png")));
            }
        }
        y = 550;
    }

    public void spawn(int index) {
        show();
        if (index == 10) {
            for (int i = 0; i < 10; i++) {
                FadeTransition ft = new FadeTransition(Duration.millis(2000), cannonGroup.getChildren().get(i));
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                ft.play();
            }
        } else {
            for (int i = 0; i < 10; i++) {
                if (i == index) {
                    FadeTransition ft = new FadeTransition(Duration.millis(5000), cannonGroup.getChildren().get(i));
                    ft.setFromValue(0.0);
                    ft.setToValue(1.0);
                    ft.play();
                }
            }
            timeline.stop();
        }
    }

    public void show() {
        boolean[] gotTitan = new boolean[10];
        Titan[][] titans = App.getGround().getTitans();
        for (int column = 0; column < 10; column++) {
            for (int row = 0; row < 10; row++) {
                if (titans[row][column] != null) {
                    gotTitan[column] = true;
                    break;
                } else {
                    gotTitan[column] = false;
                }
            }
        }
        int x = 83;
        for (int j = 0; j < 10; j++) {
            boolean shoot = true;
            if (App.getWall().get(j).getWeapon().getLevel() == 0 || !gotTitan[j]) {
                shoot = false;
            }
            animation((ImageView) cannonGroup.getChildren().get(j), cannonImage, x, shoot);
            x += 121;
        }
    }

    public void showLevel(int level, int index) {
        Text num = new Text(cannonGroup.getChildren().get(index).getLayoutX() + 28, y + 110, "Level " + level);
        num.setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, 15));
        num.setFill(Color.WHITE);
        levelGroup.getChildren().add(num);
    }

    private void animation(ImageView cannon, List<Image> cannonImage, int x, boolean shoot) {
        if (shoot) {
            timeline = new Timeline();
            timeline.setCycleCount(1);
            cannon.setLayoutX(x);
            cannon.setLayoutY(y);
            KeyFrame kf1 = new KeyFrame(Duration.millis(200), t -> cannon.setImage(cannonImage.get(0)));
            KeyFrame kf2 = new KeyFrame(Duration.millis(400), t -> cannon.setImage(cannonImage.get(1)));
            KeyFrame kf3 = new KeyFrame(Duration.millis(600), t -> cannon.setImage(cannonImage.get(2)));
            KeyFrame kf4 = new KeyFrame(Duration.millis(800), t -> cannon.setImage(cannonImage.get(3)));
            KeyFrame kf5 = new KeyFrame(Duration.millis(1000), t -> cannon.setImage(cannonImage.get(4)));
            KeyFrame kf6 = new KeyFrame(Duration.millis(1200), t -> cannon.setImage(cannonImage.get(5)));
            KeyFrame kf7 = new KeyFrame(Duration.millis(1400), t -> cannon.setImage(cannonImage.get(6)));
            KeyFrame kf8 = new KeyFrame(Duration.millis(1600), t -> cannon.setImage(cannonImage.get(7)));
            KeyFrame kf9 = new KeyFrame(Duration.millis(180), t -> cannon.setImage(cannonImage.get(8)));
            KeyFrame kf10 = new KeyFrame(Duration.millis(2000), t -> cannon.setImage(cannonImage.get(9)));
            KeyFrame kf11 = new KeyFrame(Duration.millis(2200), t -> cannon.setImage(cannonImage.get(0)));
            timeline.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6, kf7, kf8, kf9, kf10, kf11);
            timeline.play();
        } else {
            cannon.setImage(cannonImage.get(0));
            cannon.setLayoutX(x);
            cannon.setLayoutY(y);
        }
    }

    public Group getCannonGroup() {
        return cannonGroup;
    }

    public Group getLevelGroup() {
        return levelGroup;
    }
}
