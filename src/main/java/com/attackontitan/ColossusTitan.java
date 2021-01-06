package com.attackontitan;

public class ColossusTitan extends Titan {

    public ColossusTitan(int column) {
        this.hp = 50;
        this.attackPoint = 10;
        this.colossusTitanView =new ColossusTitanView(column);
    }

    @Override
    String getName() {
        return "CC";
    }
}
