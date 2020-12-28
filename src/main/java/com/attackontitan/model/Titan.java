package com.attackontitan.model;

import java.util.Random;

public abstract class Titan {
//    protected int currentRow;
//    protected int currentColumn;

    protected int hp;
    protected int attackPoint;

//    public int getCurrentRow() {
//        return currentRow;
//    }
//
//    public int getCurrentColumn() {
//        return currentColumn;
//    }

    public int getAttackPoint() {
        return this.attackPoint;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        System.out.println("The titan takes " + damage + " damage");
        System.out.println("The titan remains HP: " + hp);
    }

    public boolean isAlive() {
        return hp > 0;
    }

//    void move() {
//        Random ran = new Random();
//        if (ran.nextInt(2) == 1) {
//            moveRight();
//        } else {
//            moveLeft();
//        }
//        moveUp();
//    }

//    protected void moveLeft() {
//        if (this.currentColumn > 0) this.currentColumn--;
//    }
//
//    protected void moveRight() {
//        if (this.currentColumn < 9)  this.currentColumn++;
//    }
//
//    protected void moveUp() {
//        if (this.currentRow < 9) this.currentRow++;
//    }

    abstract String getName();

//    public boolean isFrontOfTheWall() {
//        return this.currentRow == 9;
//    }
}
