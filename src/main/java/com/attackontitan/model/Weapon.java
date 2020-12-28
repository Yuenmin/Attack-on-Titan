package com.attackontitan.model;

public class Weapon {
    private int level;

    private static final int[] ATTACK_AND_UPGRADE_COIN_PAYMENT_PER_LEVEL = new int[] {0, 2, 5, 10};

    public Weapon() {
        level = 0;
    }

    public void upgrade() {
        if (level >= 0 && level < 3) {
            level++;
        } else {
            // TODO throw exception or handle related to game role
        }
    }

    public int getLevel() {
        return level;
    }

    public int getAttack() {
        return this.ATTACK_AND_UPGRADE_COIN_PAYMENT_PER_LEVEL[this.level];
    }

    public int getUpgradeCost() {
        if (level < 0 || level == 3) return 0;
        return this.ATTACK_AND_UPGRADE_COIN_PAYMENT_PER_LEVEL[this.level + 1];
    }

    public void destroy() {
        level = 0;
    }
}
