package com.attackontitan;

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

import java.util.TimerTask;

public class App extends Application {

    private static double height;
    private static double width;
    private static Stage pStage;
    private Group group=new Group();
    private Group column =new Group();
    private Group number=new Group();
    private static Pane pane=new Pane();
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        pStage = primaryStage;
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        Parent root= FXMLLoader.load(getClass().getResource("FXML.fxml"));
        scene = new Scene(root);
        primaryStage.setTitle("Attack On Titan");
        primaryStage.getIcons().add(new Image("com/attackontitan/icon.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        height=scene.getHeight();
        width=scene.getWidth();
    }

    public void initMap(){
        ImageView bg=new ImageView();
        bg.setImage(new Image("com/attackontitan/gamemap.png"));
        bg.setFitWidth(App.getWidth());
        bg.setFitHeight(App.getHeight());
        ImageView tower=new ImageView();
        tower.setImage(new Image("com/attackontitan/gamemap1.png"));
        tower.setY(0);
        tower.setFitWidth(App.getWidth());
        tower.setFitHeight(App.getHeight());
        FadeTransition ft=setFadeTransition(bg,2000);
        group.getChildren().add(bg);
        scene = new Scene(group,getWidth(),getHeight());
        Stage stage=App.getPrimaryStage();
        stage.setScene(scene);
        stage.setResizable(false);
        ft.setOnFinished(actionEvent -> {
            group.getChildren().addAll(
                    column,
                    pane,
                    tower,
                    number,
                    new Cannon(0).cannonGroup,
                    new Soldier().soldierGroup
            );
            drawNum();
            Platform.runLater(new TimerTask() {
                @Override
                public void run() {
                    drawColumn();
                    setFadeTransition(column,2000);
                }
            });
        });
    }

    public void drawColumn() {
        Group column = getColumn();
        for (int i = 590; i > 20; i -= 65) {
            Line line = new Line(0, i, getWidth(), i);
            line.setStroke(Color.GRAY);
            line.setStrokeWidth(1.5);
            column.getChildren().add(line);
        }
    }

    public void drawNum(){
        Group number = getNumber();
        int y=635;
        for(int i=0;i<=9;i++){
            Text num=new Text(30,y,Integer.toString(i));
            num.setFont(Font.font("Calibri", FontWeight.BOLD,52));
            number.getChildren().add(num);
            y-=64;
        }
    }

    public void spawnATitan(double x, double y){
        Pane pane=getPane();
        pane.getChildren().add(new ATitan(x,y).getATitan());
    }

    public void spawnCTitan(int x){
        Pane pane=getPane();
        pane.getChildren().add(new CTitan(x).getCTitan());
    }

    public FadeTransition setFadeTransition(Node node,double duration){
        FadeTransition ft = new FadeTransition(Duration.millis(duration),node);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        return ft;
    }

    public Pane getPane() {
        return pane;
    }

    public Group getColumn() {
        return column;
    }

    public Group getNumber() {
        return number;
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }

    public static double getHeight(){
        return height;
    }

    public static double getWidth(){
        return width;
    }

    public static void main(String[] args) {
        launch(args);
    }
}




