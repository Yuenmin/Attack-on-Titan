package com.attackontitan;

public class ArmouredTitan extends Titan {

    public ArmouredTitan(int column) {
        this.hp = 100;
        this.attackPoint = 5;
        this.armouredTitanView=new ArmouredTitanView(column);
    }

    @Override
    String getName() {
        return "AA";
    }
}
