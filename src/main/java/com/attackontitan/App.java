package com.attackontitan;

import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class App extends Application {

    private Pane pane = new Pane();
    private Group group = new Group();
    private Group column = new Group();
    private Group columnNumber = new Group();
    private Soldier soldier = new Soldier();
    private static Stage pStage;
    private static Stage upgradeStage;
    private static double height;
    private static double width;
    private static ScoreBoard scoreBoard = new ScoreBoard();
    private static final WeaponView cannon = new WeaponView();
    private static final CoinView coinView = new CoinView();
    private static final GameInfo gameInfo = new GameInfo();
    private static final Ground ground = new Ground();
    private static final Wall wall = new Wall();
    private static final Hour hour = new Hour();
    private static final Coin coin = new Coin();
    private int score;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Attack On Titan");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/homepage/icon.png")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        pStage = primaryStage;
        height = scene.getHeight();
        width = scene.getWidth();
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public void initMap() {
        ImageView background = new ImageView();
        background.setImage(new Image(getClass().getResourceAsStream("images/background.png")));
        background.setFitWidth(width);
        background.setFitHeight(height);
        ImageView wallImage = new ImageView();
        wallImage.setImage(new Image(getClass().getResourceAsStream("images/wall.png")));
        wallImage.setY(0);
        wallImage.setFitWidth(width);
        wallImage.setFitHeight(height);
        FadeTransition ft = setFadeTransition(background, 2000);
        group.getChildren().add(background);
        pStage.setScene(new Scene(group, width, height));
        ft.setOnFinished(actionEvent -> {
            group.getChildren().addAll(
                    column,
                    pane,
                    wallImage,
                    columnNumber,
                    cannon.getCannonGroup(),
                    soldier.getSoldierGroup()
            );
            Platform.runLater(() -> {
                soldier.show();
                cannon.spawn(10);
                setFadeTransition(columnNumber, 1000);
                drawColumnNum();
                setFadeTransition(column, 2000);
                drawColumn();
                startGame();
            });
        });
    }

    public void drawColumn() {
        for (int i = 590; i > 20; i -= 65) {
            Line line = new Line(0, i, width, i);
            line.setStroke(Color.GRAY);
            line.setStrokeWidth(1.5);
            column.getChildren().add(line);
        }
    }

    public void drawColumnNum() {
        int y = 635;
        for (int i = 9; i >= 0; i--) {
            Text num = new Text(30, y, Integer.toString(i));
            num.setFont(Font.font("Calibri", FontWeight.BOLD, 52));
            columnNumber.getChildren().add(num);
            y -= 64;
        }
    }

    public void startGame() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!soldier.isAnimating()) {
                    gameInfo.drawWallHp();
                    gameInfo.drawInfoPane();
                    group.getChildren().add(gameInfo.getGameInfo());
                    upgradeStage = new Stage();
                    upgradeStage.initStyle(StageStyle.TRANSPARENT);
                    upgradeStage.initOwner(pStage);
                    update();
                    new Thread(App.this::game).start();
                    stop();
                }
            }
        }.start();
    }

    public void endGame() {
        scoreBoard.setNewScore(score);
        scoreBoard.newRecord();
        Platform.runLater(() -> {
            upgradeStage.close();
            group.getChildren().clear();
            Rectangle rectangle = new Rectangle(width, height, Color.BLACK);
            Text text = new Text(550, 900, "Game Over");
            text.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
            text.setFill(Color.WHITE);
            group.getChildren().addAll(rectangle, text);
            setFadeTransition(rectangle, 2000);
            TranslateTransition t = new TranslateTransition();
            t.setNode(text);
            t.setByY(-500);
            t.setDuration(Duration.millis(2000));
            t.play();
            rectangle.setOnMouseClicked(new EventHandler<>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
                        pStage.setScene(new Scene(root));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
    }

    public void update() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (hour.getCurrentHour() < 0) {
                    stop();
                }
                cannon.getLevelGroup().getChildren().clear();
                gameInfo.getTitanHealthBar().getChildren().clear();
                //display titan
                pane.getChildren().clear();
                for (ArmouredTitanView titan : ground.getATitanList()) {
                    pane.getChildren().addAll(titan.getView());
                }
                for (ColossusTitanView titan : ground.getCTitanList()) {
                    pane.getChildren().addAll(titan.getView());
                }
                for (ArmouredAndColossusTitanView titan : ground.getAcTitanList()) {
                    pane.getChildren().addAll(titan.getView());
                }
                //display level of weapon
                for (int i = 0; i < 10; i++) {
                    cannon.showLevel(wall.get(i).getWeapon().getLevel(), i);
                }
                //display hp of titan
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 20; j++) {
                        Titan[][] titans = ground.getTitans();
                        Titan curTitan = titans[i][j];
                        if (curTitan != null) {
                            gameInfo.drawTitanHp(i, curTitan, curTitan.hp);
                        }
                    }
                }
                gameInfo.drawHourNum(hour.getCurrentHour());
                gameInfo.drawCoinNum(coin.getCurCoin());
                gameInfo.drawScore(score);
                ground.getATitanList().removeIf(titan -> !titan.getView().isVisible());
                ground.getCTitanList().removeIf(titan -> !titan.getView().isVisible());
                ground.getAcTitanList().removeIf(titan -> !titan.getView().isVisible());
            }
        }.start();
    }

    public void requestUpdateInfo() {
        Platform.runLater(() -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("UpgradeBoard.fxml"));
                upgradeStage.setScene(new Scene(root));
                upgradeStage.show();
                pStage.requestFocus();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void game() {
        Random r = new Random();
        score = 0;
        while (hour.getCurrentHour() >= 0) {
            if (hour.isPlayerTurn()) {
                // player turn
                requestUpdateInfo();
                do {
                    delay(1000);
                } while (upgradeStage.isShowing());

                Timeline timeline = new Timeline();
                KeyFrame kf1 = new KeyFrame(Duration.millis(0), actionEvent -> cannon.show());
                KeyFrame kf2 = new KeyFrame(Duration.millis(1000), actionEvent -> attackTitan());
                KeyFrame kf3 = new KeyFrame(Duration.millis(2000), actionEvent -> checkAlive());
                timeline.getKeyFrames().addAll(kf1, kf2, kf3);
                timeline.play();

            } else if (hour.getCurrentHour() >= 5) {
                //Titan's turn
                double chance = hour.getCurrentHour() / 15.0;
                if (chance < 1) {
                    if (r.nextInt(101) <= (int) (chance * 100)) {
                        spawnTitan();
                    }
                } else {
                    for (int i = 0; i < chance; i++) {
                        spawnTitan();
                    }
                }
                ground.move();
            }
            // check if wall is destroyed
            for (WallUnit wallUnit : wall.getWallUnits()) {
                if (wallUnit.getHp() <= 0) {
                    hour.setCurrentHour(-1);
                    delay(3000);
                    endGame();
                    break;
                }
            }
            if (hour.getCurrentHour() >= 0) {
                delay(4000);
                hour.nextHour();
                coinView.coinAni();
                coin.increaseCoinPerHours();
                delay(1000);
            }
        }
    }

    public void delay(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void spawnTitan() {
        Random r = new Random();
        int max = 20;
        int size = ground.getCTitanList().size();
        int position;
        do {
            position = r.nextInt(max / 2) * 2;
            if (r.nextInt(2) == 0) {
                ground.addArmouredTitan(position);
            } else {
                ground.addColossusTitan(position);
            }
        } while (ground.getCTitanList().size() == size && ground.getATitanList().size() == size);
    }

    public void checkAlive() {
        Platform.runLater(() -> {
            Titan[][] titans = ground.getTitans();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 20; j++) {
                    if (titans[i][j] != null) {
                        if (!titans[i][j].isAlive()) {
                            score++;
                            //set image invisible
                            int row = i;
                            int column = j;
                            FadeTransition ft = new FadeTransition(Duration.millis(2000));
                            ft.setFromValue(1);
                            ft.setToValue(0);
                            if (titans[i][j] instanceof ArmouredTitan) {
                                ft.setNode(titans[i][j].armouredTitanView.getView());
                                ft.setOnFinished(actionEvent -> {
                                    titans[row][column].armouredTitanView.getView().setVisible(false);
                                    titans[row][column] = null;
                                });
                            } else if (titans[i][j] instanceof ColossusTitan) {
                                ft.setNode(titans[i][j].colossusTitanView.getView());
                                ft.setOnFinished(actionEvent -> {
                                    titans[row][column].colossusTitanView.getView().setVisible(false);
                                    titans[row][column] = null;
                                });
                            } else if (titans[i][j] instanceof ArmouredAndColossusTitan) {
                                ft.setNode(titans[i][j].armouredAndColossusTitanView.getView());
                                ft.setOnFinished(actionEvent -> {
                                    titans[row][column].armouredAndColossusTitanView.getView().setVisible(false);
                                    titans[row][column] = null;
                                });
                            }
                            ft.play();
                        }
                    }
                }
            }
        });
    }

    // Q1
    public static void upgradeMultipleWeapons(String weaponIndexes) {
        int c = 1, index, length;
        length = weaponIndexes.length();
        if (!weaponIndexes.equalsIgnoreCase("GO")) {
            for (int i = 0; i < length - 1; i++) {
                if (weaponIndexes.charAt(i) != weaponIndexes.charAt(i + 1)) {
                    index = Integer.parseInt(weaponIndexes.charAt(i) + "");
                    if (wall.get(index).getWeapon().getLevel() + c <= 3) {
                        while (c != 0) {
                            upgradeWeapon(wall.get(index).getWeapon());
                            c--;
                        }
                    }
                    c = 1;
                } else
                    c++;
            }
            while (c != 0) {
                upgradeWeapon(wall.get(Integer.parseInt(weaponIndexes.charAt(length - 1) + "")).getWeapon());
                c--;
            }
        }
        for (int i = 0; i < 10; i++) {
            cannon.changeColour(i, wall.get(i).getWeapon().getLevel());
        }
    }

    private static void upgradeWeapon(Weapon weapon) {
        int upgradeCost = weapon.getUpgradeCost();
        if (upgradeCost > coin.getCurCoin()) {
            System.out.println("Not enough money!");
            Platform.runLater(gameInfo::drawNotEnoughCoinWeapon);
        } else {
            weapon.upgrade();
            coin.pay(upgradeCost);
        }
    }

    public static void addHpToTheWall(String wallIndexes, String wallHps, boolean cAll) {
        if (!wallIndexes.equalsIgnoreCase("GO")) {

            int length = wallIndexes.length();
            String[] wallHpsArr = wallHps.split(" ");
            if (cAll) {
                for (int i = 0; i < length; i++) {

                    int hp = Integer.parseInt(wallHpsArr[0]);

                    if (hp > coin.getCurCoin()) {
                        System.out.println("Your money is not enough");
                        Platform.runLater(gameInfo::drawNotEnoughCoinWall);
                    } else {
                        WallUnit wallUnit = wall.get(i);
                        wallUnit.addHp(hp);
                        coin.pay(hp);
                    }
                }
            } else {
                for (int i = 0; i < length; i++) {

                    int index = Integer.parseInt(wallIndexes.charAt(i) + "");
                    int hp = Integer.parseInt(wallHpsArr[i]);
                    if (index >= 0 && index <= 9) { // is wall index
                        if (hp > coin.getCurCoin()) {
                            System.out.println("Your money is not enough");
                            Platform.runLater(gameInfo::drawNotEnoughCoinWall);
                        } else {
                            WallUnit wallUnit = wall.get(index);
                            wallUnit.addHp(hp);
                            coin.pay(hp);
                        }
                    }
                }
            }
            gameInfo.drawWallHp();
        }
    }

    public static void attackTitan() {
        Titan[][] titans = ground.getTitans();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                if (titans[i][j] != null) {
                    Weapon weapon = wall.get(j / 2).getWeapon();
                    if (weapon.getLevel() > 0) {
                        // attack titan
                        int attackPoint = weapon.getAttack();
                        titans[i][j].takeDamage(attackPoint);
                        gameInfo.titanDamage(titans[i][j], attackPoint);
                    }
                }
            }
        }
    }

    public static void attackWall(int curRow, int curColumn) {
        Titan[][] titans = ground.getTitans();
        Titan curTitan = titans[curRow][curColumn];
        if (curRow == 9) {
            if (curTitan instanceof ColossusTitan) {
                curTitan.getColossusTitanView().attack();
            } else if (curTitan instanceof ArmouredAndColossusTitan) {
                curTitan.getArmouredAndColossusTitanView().attack();
            }
            WallUnit wallUnit = wall.get(curColumn / 2);
            Weapon weapon = wallUnit.getWeapon();
            if (curTitan instanceof ArmouredAndColossusTitan && weapon.getLevel() > 0) {
                weapon.destroy();
                cannon.spawn(curColumn / 2);
                cannon.changeColour(curColumn / 2, 0);

            } else if (!(curTitan instanceof ArmouredTitan)) {
                // attack wall
                wallUnit.takeDamage(titans[9][curColumn].getAttackPoint());
                gameInfo.wallDamage(curColumn / 2, titans[9][curColumn].getAttackPoint());
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

    public static ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public static Stage getUpgradeStage() {
        return upgradeStage;
    }

    public static WeaponView getCannon() {
        return cannon;
    }

    public static GameInfo getGameInfo() {
        return gameInfo;
    }

    public static CoinView getCoinView() {
        return coinView;
    }

    public static Ground getGround() {
        return ground;
    }

    public static Wall getWall() {
        return wall;
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






