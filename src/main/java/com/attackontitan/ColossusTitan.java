package com.attackontitan;

public class ColossusTitan extends Titan {

    public ColossusTitan(int column, int row) {
    	this.hp = 50;
        this.attackPoint = 10;
        this.currentColumn = column;
        this.currentRow = row;
        this.canMove=true;
        this.colossusTitanView =new ColossusTitanView(column);
	}

    @Override
    String getName() {
        return "CC";
    }
}
