package com.attackontitan;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class App extends Application {

    private Soldier soldier = new Soldier();
    private WeaponView cannon = new WeaponView();
    private Group group = new Group();
    private Group column = new Group();
    private Group number = new Group();
    private Group hp = new Group();
    private Pane pane = new Pane();
    private Scene scene;
    private static Stage pStage;
    private static double height;
    private static double width;
    private static final Wall wall = new Wall();
    private static final Ground ground = new Ground();
    private static final Hour hour = new Hour();
    private static final Coin coin = new Coin();

    @Override
    public void start(Stage primaryStage) throws Exception {

        pStage = primaryStage;
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        scene = new Scene(root);
        primaryStage.setTitle("Attack On Titan");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/homepage/icon.png")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        height = scene.getHeight();
        width = scene.getWidth();
    }

    public void initMap() {
        ImageView background = new ImageView();
        background.setImage(new Image(getClass().getResourceAsStream("images/background.png")));
        background.setFitWidth(App.getWidth());
        background.setFitHeight(App.getHeight());
        ImageView wallImage = new ImageView();
        wallImage.setImage(new Image(getClass().getResourceAsStream("images/wall.png")));
        wallImage.setY(0);
        wallImage.setFitWidth(App.getWidth());
        wallImage.setFitHeight(App.getHeight());
        FadeTransition ft = setFadeTransition(background, 2000);
        group.getChildren().add(background);
        scene = new Scene(group, getWidth(), getHeight());
        Stage stage = App.getPrimaryStage();
        stage.setScene(scene);
        stage.setResizable(false);
        ft.setOnFinished(actionEvent -> {
            group.getChildren().addAll(
                    column,
                    pane,
                    wallImage,
                    number,
                    cannon.getCannonGroup(),
                    soldier.getSoldierGroup(),
                    hp
            );
            Platform.runLater(new TimerTask() {
                @Override
                public void run() {
                    soldier.show();
                    cannon.spawn(10);
                    drawNum();
                    setFadeTransition(number, 1000);
                    drawColumn();
                    setFadeTransition(column, 2000);
                    startGame();
                }
            });
        });
    }

    public void drawColumn() {
        for (int i = 590; i > 20; i -= 65) {
            Line line = new Line(0, i, getWidth(), i);
            line.setStroke(Color.GRAY);
            line.setStrokeWidth(1.5);
            column.getChildren().add(line);
        }
    }

    public void drawNum() {
        int y = 635;
        for (int i = 0; i <= 9; i++) {
            Text num = new Text(30, y, Integer.toString(i));
            num.setFont(Font.font("Calibri", FontWeight.BOLD, 52));
            number.getChildren().add(num);
            y -= 64;
        }

    }

    public void drawHp(int index){
        double x=cannon.getCannonGroup().getChildren().get(index).getLayoutX()+30;
        double y=cannon.getCannonGroup().getChildren().get(index).getLayoutY()+200;
        Text num = new Text(x, y, "HP "+wall.get(index).getHp());
        num.setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, 15));
        num.setFill(Color.WHITE);
        hp.getChildren().add(num);
    }

    public void startGame() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!soldier.isAnimating()) {
                    update();
                    new Thread(App.this::game).start();
                    stop();
                }
            }
        }.start();
    }

    public void update() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                pane.getChildren().clear();
                for (ArmouredTitanView titan : ground.getATitanList()) {
                    if (!titan.getView().isVisible()) {
                        ground.getATitanList().remove(titan);
                    }
                    pane.getChildren().addAll(titan.getView());
                }
                for (ColossusTitanView titan : ground.getCTitanList()) {
                    if (!titan.getView().isVisible()) {
                        ground.getCTitanList().remove(titan);
                    }
                    pane.getChildren().addAll(titan.getView());
                }
                for(int i=0;i<10;i++){
                    int level=wall.get(i).getWeapon().getLevel();
                    cannon.showLevel(level,i);
                    drawHp(i);
                }
            }
        }.start();
    }

    public void game() {
        int max = 10;
        Scanner in = new Scanner(System.in);
        Random r = new Random();
        hour.setCurrentHour(5);
        while (hour.getCurrentHour()>=0) {
            if (hour.isPlayerTurn()) {
                // player turn
                System.out.println("Player's turn");
                System.out.println("Choose the weapon(s) you would like to upgrade (Type a string of integer or hit Enter to skip)");
                String weaponUpgradeIndex = in.nextLine();
                // upgrade weapons
                if (!weaponUpgradeIndex.equals("")) {
                    System.out.println("hi");
                    upgradeMultipleWeapons(weaponUpgradeIndex);
                }
                System.out.println("Do you want to upgrade all walls? (press 1 if yes, press Enter if no)");
                String confirm = in.nextLine();
                if (confirm.isBlank()) {
                    System.out.println("Choose the wall(s) you would like to upgrade (Type a string of integer or hit Enter to skip)");
                    String wallUpgradeIndex = in.next();
                    //upgrade wall
                    if (!wallUpgradeIndex.equals("\n")) {
                        System.out.println("How many HP do you want to add up to the wall(s)?");
                        String hp = in.nextLine();
                        addHpToTheWall(wallUpgradeIndex, hp);

                    }
                } else if(confirm.strip().equals("1")) {
                    System.out.println("How many HP do you want to add up to all the wall?");
                    String hp = in.nextLine();
                    addHpToTheWall("0123456789", hp);

                }
                cannon.show();
                attackTitan();
                //check if titans die
            } else {
                System.out.println("Titan's turn");
                if (hour.getCurrentHour() / 5 == 1) {
                    ground.addArmouredTitan(r.nextInt(max));
                    ground.addColossusTitan(r.nextInt(max));
                    // need to integrate titan's image to the object.
                } else {
                    // titans walks or attack
                    // check if tower destroyed or not
                }
            }
            hour.nextHour();
            coin.increaseCoinPerHours();
            System.out.println(coin.getCurCoin());
            for(WallUnit wallUnit:wall.getWallUnits()){
                if(wallUnit.getHp()<=0){
                    hour.setCurrentHour(-1);
                    break;
                }
            }
        }
    }

    // Q1
    public static void upgradeMultipleWeapons(String weaponIndexes) {
        for (char cIndex : weaponIndexes.toCharArray()) {
            int index = Integer.parseInt(cIndex + "");
            if (index >= 0 && index <= 9) { // is wall index
                upgradeWeapon(wall.get(index).getWeapon());
            }
        }
    }

    // Q2
    public static void upgradeAllWeaponOneLevel() {
        for (int i = 0; i < 10; i++) {
            upgradeWeapon(wall.get(i).getWeapon());
        }
    }

    private static void upgradeWeapon(Weapon weapon) {
        int upgradeCost = weapon.getUpgradeCost();

        if (upgradeCost > coin.getCurCoin()) {
            System.out.println("Not enough money!");
        } else {
            weapon.upgrade();
            coin.pay(upgradeCost);
        }
    }

    public static void addHpToTheWall(String wallIndexes, String wallHps) {
        if (wallHps.length() == wallHps.length()) {
            int length = wallHps.length();
            for (int i = 0; i < length; i++) {

                int index = Integer.parseInt(wallIndexes.charAt(i) + "");
                int hp = Integer.parseInt(wallHps.charAt(i) + "");
                if (index >= 0 && index <= 9) { // is wall index
                    if (hp > coin.getCurCoin()) {
                        System.out.println("Your money is not enough");
                    } else {
                        WallUnit wallUnit = wall.get(index);
                        wallUnit.addHp(wallUnit.getHp() + hp);
                        coin.pay(hp);
                    }
                }
            }
        }
    }

    public void attackTitan() {
        Titan[][] titans = ground.getTitans();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (titans[i][j] != null) {
                    Weapon weapon = wall.get(j).getWeapon();
                    if (weapon.getLevel() > 0) {
                        // attack titan
                        int attackPoint = weapon.getAttack();
                        titans[i][j].takeDamage(attackPoint);
                    }
                }
            }
        }
    }

    public void attackWall() {
        Titan[][] titans = ground.getTitans();

        for (int i = 0; i < 10; i++) {
            if (titans[9][i] != null) {
                WallUnit wallUnit = wall.get(i);
                Weapon weapon = wallUnit.getWeapon();
                if (weapon.getLevel() > 0) {
                    // attack weapon
                    weapon.destroy();
                    //cannon.spawn(i);
                } else {
                    // attack wall
                    wallUnit.takeDamage(titans[9][i].getAttackPoint());
                }
            }
        }

    }

    public FadeTransition setFadeTransition(Node node, double duration) {
        FadeTransition ft = new FadeTransition(Duration.millis(duration), node);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        return ft;
    }

    public static Wall getWall() {
        return wall;
    }

    public static Ground getGround() {
        return ground;
    }

    public static Hour getHour() {
        return hour;
    }

    public static Coin getCoin() {
        return coin;
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }

    public static double getHeight() {
        return height;
    }

    public static double getWidth() {
        return width;
    }

    public static void main(String[] args) {
        launch(args);
    }
}




