package com.attackontitan;

public class ArmouredTitan extends Titan {

    public ArmouredTitan() {
        this.hp = 100;
        this.attackPoint = 5;
    }

    @Override
    String getName() {
        return "AA";
    }
}
