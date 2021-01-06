package com.attackontitan;

public class ArmouredAndColossusTitan extends Titan {

    public ArmouredAndColossusTitan(Titan titan1, Titan titan2) {
        this.hp = titan1.hp + titan2.hp;
        this.attackPoint = titan1.attackPoint + titan2.attackPoint;
    }

    @Override
    String getName() {
        return "AC";
    }

    public void takeDamage(int damage) {
        hp -= 2 * damage;
        System.out.println("The titan takes " + 2 * damage + " damage");
        System.out.println("The titan remains HP: " + hp);
    }
}

