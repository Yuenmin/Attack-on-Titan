package com.attackontitan;

public class ArmouredTitan extends Titan {

    public ArmouredTitan(int col, int row) {
        this.hp = 100;
        this.attackPoint = 5;
        this.currentColumn = col;
        this.currentRow = row;
        this.armouredTitanView=new ArmouredTitanView(col/2);
    }

    @Override
    String getName() {
        return "AA";
    }
}
