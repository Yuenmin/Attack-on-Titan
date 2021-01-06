package com.attackontitan;

import javafx.animation.*;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

public class App extends Application {

    private Group group = new Group();
    private Group column = new Group();
    private Group columnNumber = new Group();
    private Soldier soldier = new Soldier();
    private WeaponView cannon = new WeaponView();
    private Pane pane = new Pane();
    private Scene scene;
    private static Stage UPGRADEStage;
    private static Stage pStage;
    private static double height;
    private static double width;
    private static final CoinView coinView = new CoinView();
    private static final GameInfo gameInfo = new GameInfo();
    private static final Wall wall = new Wall();
    private static final Ground ground = new Ground();
    private static final Hour hour = new Hour();
    private static final Coin coin = new Coin();

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        scene = new Scene(root);
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
        scene = new Scene(group, width, height);
        Stage stage = pStage;
        stage.setScene(scene);
        stage.setResizable(false);
        ft.setOnFinished(actionEvent -> {
            group.getChildren().addAll(
                    column,
                    pane,
                    wallImage,
                    columnNumber,
                    cannon.getCannonGroup(),
                    soldier.getSoldierGroup()
            );
            Platform.runLater(new TimerTask() {
                @Override
                public void run() {
                    soldier.show();
                    cannon.spawn(10);
                    drawColumnNum();
                    setFadeTransition(columnNumber, 1000);
                    drawColumn();
                    setFadeTransition(column, 2000);
                    startGame();
                }
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
                    group.getChildren().add(gameInfo.getGameInfo());
                    gameInfo.drawInfoPane();
                    UPGRADEStage = new Stage();
                    //UPGRADEStage.initStyle(StageStyle.TRANSPARENT);
                    UPGRADEStage.initOwner(pStage);
                    update();
                    Platform.runLater(() -> new Thread(App.this::game).start());
                    stop();
                }
            }
        }.start();
    }

    public void endGame() {
        Platform.runLater(() -> {
            UPGRADEStage.close();
            group.getChildren().clear();
            Rectangle rectangle = new Rectangle(width, height, Color.BLACK);
            Text text = new Text(500, 900, "Game Over");
            text.setFont(Font.font("Calibri", FontWeight.BOLD, 60));
            text.setFill(Color.WHITE);
            group.getChildren().addAll(rectangle, text);
            setFadeTransition(rectangle, 2000);
            TranslateTransition t = new TranslateTransition();
            t.setNode(text);
            t.setByY(-500);
            t.setDuration(Duration.millis(2000));
            Timeline timeline = new Timeline();
            KeyFrame kf1 = new KeyFrame(Duration.millis(2000), actionEvent -> t.play());
            KeyFrame kf2 = new KeyFrame(Duration.millis(6000), actionEvent -> {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
                    getPrimaryStage().setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            timeline.getKeyFrames().addAll(kf1, kf2);
            timeline.play();
        });
    }

    public void update() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (hour.getCurrentHour() < 0) {
                    stop();
                }
                pane.getChildren().clear();
                cannon.getLevelGroup().getChildren().clear();
                gameInfo.getTitanHealthBar().getChildren().clear();
                //display titan
                for (ArmouredTitanView titan : ground.getATitanList()) {
                    pane.getChildren().addAll(titan.getView());
                }
                for (ColossusTitanView titan : ground.getCTitanList()) {
                    pane.getChildren().addAll(titan.getView());
                }
                //display level of weapon
                for (int i = 0; i < 10; i++) {
                    cannon.showLevel(wall.get(i).getWeapon().getLevel(), i);
                }
                //display hp of titan
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        Titan[][] titans = ground.getTitans();
                        Titan curTitan = titans[i][j];
                        if (curTitan != null) {
                            gameInfo.drawTitanHp(curTitan,curTitan.hp);
                        }
                    }
                }
                gameInfo.drawWallHp();
                gameInfo.drawHourNum();
                gameInfo.drawCoinNum();
            }
        }.start();
    }

    public void requestUpdateInfo() {
        Platform.runLater(() -> {
            try {
                Parent UPGRADEParent = FXMLLoader.load(getClass().getResource("UpgradeBoard.fxml"));
                UPGRADEStage.setScene(new Scene(UPGRADEParent));
                UPGRADEStage.show();
                pStage.requestFocus();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void game() {
        Random r = new Random();
        hour.setCurrentHour(3);
        coin.setCurCoin(270);
        while (hour.getCurrentHour() >= 0) {
            if (hour.isPlayerTurn()) {
                // player turn
                requestUpdateInfo();
                do {
                    delay(1000);
                } while (UPGRADEStage.isShowing());

                try {
                    upgradeMultipleWeapons(Controller.CWeaponStr);
                    addHpToTheWall(Controller.CWallStr, Controller.UpHPStr);
                } catch (NullPointerException ignored) {

                }

                Timeline timeline = new Timeline();
                KeyFrame kf1 = new KeyFrame(Duration.millis(0), actionEvent -> cannonShoot());
                KeyFrame kf2 = new KeyFrame(Duration.millis(1000), actionEvent -> attackTitan());
                KeyFrame kf3 = new KeyFrame(Duration.millis(2000), actionEvent -> checkAlive());
                timeline.getKeyFrames().addAll(kf1, kf2, kf3);
                timeline.play();

            } else if (hour.getCurrentHour() >= 5) {
                //Titan's turn
                double chance = (hour.getCurrentHour() - 5) / 15.0;
                chance = 1;
                ArrayList<Integer> newTitan = new ArrayList<>();
                if (chance < 1) {
                    if (r.nextInt(101) <= (int) (chance * 100)) {
                        newTitan.add(spawnTitan());
                    }
                } else {
                    for (int i = 0; i < chance; i++) {
                        newTitan.add(spawnTitan());
                    }
                }
                titanMoveOrAttack(newTitan);
            }
            // check if wall is destroyed
            for (WallUnit wallUnit : wall.getWallUnits()) {
                if (wallUnit.getHp() <= 0) {
                    hour.setCurrentHour(-1);
                    endGame();
                    break;
                }
            }
            if (hour.getCurrentHour() >= 0) {
                delay(4000);
                hour.nextHour();
                coinView.coinAni();
                coin.increaseCoinPerHours();
                delay(1500);
            }
        }
    }

    public void delay(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public int spawnTitan() {
        int max = 10;
        Random r = new Random();
        int ran = r.nextInt(max);
        ground.addColossusTitan(ran);
        /*if(r.nextInt(2) == 0){
            ground.addArmouredTitan(r.nextInt(max));
        }else{
            ground.addColossusTitan(r.nextInt(max));
        }*/
        return ran;
    }

    public void cannonShoot() {
        Titan[][] titans = ground.getTitans();
        Platform.runLater(() -> {
            cannon.show();
            /*outer:
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (titans[i][j] != null) {
                        cannon.show();
                        break outer;
                    }
                }
            }*/
        });
    }

    public void checkAlive() {
        Titan[][] titans = ground.getTitans();
        Platform.runLater(() -> {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (titans[i][j] != null) {
                        if (!titans[i][j].isAlive()) {
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
                            } else {
                                ft.setNode(titans[i][j].colossusTitanView.getView());
                                ft.setOnFinished(actionEvent -> {
                                    titans[row][column].colossusTitanView.getView().setVisible(false);
                                    titans[row][column] = null;
                                });
                            }
                            ft.play();
                        }
                    }
                }
            }
            ground.getATitanList().removeIf(titan -> !titan.getView().isVisible());
            ground.getCTitanList().removeIf(titan -> !titan.getView().isVisible());
        });
    }

    public void titanMoveOrAttack(ArrayList<Integer> newTitan) {
        Titan[][] titans = ground.getTitans();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boolean canAttack = true;
                Iterator<Integer> iterator = newTitan.iterator();
                if (titans[i][j] != null) {
                    if (titans[i][j] instanceof ColossusTitan) {
                        //check for newTitan
                        while (iterator.hasNext()) {
                            if (j == iterator.next()) {
                                canAttack = false;
                                break;
                            }
                        }
                        if (canAttack) {
                            attackWall(i, j);
                            boolean canMove = false;
                            Iterator<ColossusTitanView> iterator1 = ground.getCTitanList().iterator();
                            while (!canMove) {
                                if (!iterator1.hasNext()) {
                                    iterator1 = ground.getCTitanList().iterator();
                                }
                                canMove = !iterator1.next().isAnimating();
                            }
                            ground.move(i, j);
                        }
                    }
                    if (ground.isMoveRight()) {
                        j++;
                    }
                }
            }
        }
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

    }

    private static void upgradeWeapon(Weapon weapon) {
        int upgradeCost = weapon.getUpgradeCost();
        //System.out.println("UCOST: " + upgradeCost);
        if (upgradeCost > coin.getCurCoin()) {
            System.out.println("Not enough money!");
        } else {
            weapon.upgrade();
            coin.pay(upgradeCost);
        }
    }

    public static void addHpToTheWall(String wallIndexes, String wallHps) {
        if (!wallIndexes.equalsIgnoreCase("GO")) {

            int length = wallIndexes.length();
            String[] wallHpsArr = wallHps.split(" ");
            if (Controller.cAll) {
                for (int i = 0; i < length; i++) {

                    int hp = Integer.parseInt(wallHpsArr[0]);

                    if (hp > coin.getCurCoin()) {
                        System.out.println("Your money is not enough");
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
                        } else {
                            WallUnit wallUnit = wall.get(index);
                            wallUnit.addHp(hp);
                            coin.pay(hp);
                        }
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
                        gameInfo.titanDamage(titans[i][j],attackPoint);
                    }
                }
            }
        }
    }

    public void attackWall(int curRow, int curColumn) {
        Titan[][] titans = ground.getTitans();
        Titan curTitan = titans[curRow][curColumn];
        if (curRow == 9) {
            curTitan.getColossusTitanView().attack();
            WallUnit wallUnit = wall.get(curColumn);
            Weapon weapon = wallUnit.getWeapon();
            delay(1500);
            if (weapon.getLevel() > 0 && curTitan instanceof ArmouredTitan) {
                // attack weapon
                weapon.destroy();
                cannon.spawn(curColumn);
            } else {
                // attack wall
                wallUnit.takeDamage(titans[9][curColumn].getAttackPoint());
                gameInfo.wallDamage(curColumn,titans[9][curColumn].getAttackPoint());
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

    public static Stage getUPGRADEStage() {
        return UPGRADEStage;
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






