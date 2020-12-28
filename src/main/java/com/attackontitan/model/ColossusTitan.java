package com.attackontitan.model;

public class ColossusTitan extends Titan {

    public ColossusTitan() {
        this.hp = 50;
        this.attackPoint = 10;
    }

    @Override
    String getName() {
        return "CC";
    }
}
