package com.attackontitan;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class HealthBar {

    private Rectangle outerRectangle;
    private Rectangle innerRectangle;
    private Group healthBar=new Group();

    public HealthBar(double x,double y,double value) {

        double height = 5;
        double outerWidth = 40;
        double innerWidth = 20;

        outerRectangle = new Rectangle( x, y, outerWidth, height);
        outerRectangle.setStroke(Color.BLACK);
        outerRectangle.setStrokeWidth(1);
        outerRectangle.setStrokeType( StrokeType.OUTSIDE);
        outerRectangle.setFill(Color.WHITE);

        innerRectangle = new Rectangle( x, y, innerWidth, height);
        if(value>0.6) {
            innerRectangle.setFill(Color.LIMEGREEN);
        }else if(value>0.3){
            innerRectangle.setFill(Color.YELLOW);
        }else{
            innerRectangle.setFill(Color.RED);
        }
        innerRectangle.setWidth( outerRectangle.getWidth() * value);

        healthBar.getChildren().addAll(outerRectangle, innerRectangle);
    }

    public Group getHealthBar() {
        return healthBar;
    }
}