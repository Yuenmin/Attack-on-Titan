package com.attackontitan;

public class Weapon {
    private int level;

    private static final int[] ATTACK_AND_UPGRADE_COIN_PAYMENT_PER_LEVEL = new int[] {0, 2, 5, 10};

    public Weapon() {
        level = 0;
    }

    public void upgrade() {
        if (level >= 0 && level < 3) {
            level ++;
        }else {
            System.out.println("Failed");
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
        this.level = 0;
        System.out.println("weapon destroyed");
    }
}
