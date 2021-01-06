package com.attackontitan;

public class WallUnit {
    private int hp;
    private Weapon weapon;

    public WallUnit() {
        hp = 50;
        weapon = new Weapon();
    }

    public int getHp() {
        return hp;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
    }

    public void addHp(int hp) {
        this.hp += hp;
    }
}
