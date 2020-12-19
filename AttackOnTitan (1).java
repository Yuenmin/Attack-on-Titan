import model.*;

import java.util.ArrayList;
import java.util.List;

public class AttackOnTitan {

    private static final Wall wall = new Wall();
    private static final Ground ground = new Ground();
    private static final Hour hour = new Hour();
    private static final Coin coin = new Coin();

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (true) {
//            hour.nextHour();
//            coin.increaseCoinPerHours();
//
//            if (hour.isPlayerTurn()) {
//                // player turn
//                System.out.println("Player's turn");
//                System.out.println("Choose the weapon(s) you would like to upgrade (Type a string of integer or hit Enter to skip)");
//                String weaponUpgradeIndex = in.next();
//                // upgrade weapons
//
//
//                System.out.println(weaponUpgradeIndex);
//            } else {
//                // enemies turn
//            }
//        }

//        upgradeMultipleWeapons("1110022229");
//        upgradeAllWeaponOneLevel();
        addHpToTheWall("123", "765");
        wall.printToConsole();
        System.out.println(coin.getCurCoin());
    }

    // Q1
    public static void upgradeMultipleWeapons(String weaponIndexes) {
        for (char cIndex : weaponIndexes.toCharArray()) {
            int index = Integer.parseInt(cIndex + "");
            if (index >= 0 && index <= 9) { // is wall index
                upgradeWeapon(wall.get(index).getWeapon());
            }
        }
    }

    // Q2
    public static void upgradeAllWeaponOneLevel() {
        for (int i = 0; i < 10; i++) {
            upgradeWeapon(wall.get(i).getWeapon());
        }
    }

    private static void upgradeWeapon(Weapon weapon) {
        int upgradeCost = weapon.getUpgradeCost();

        if (upgradeCost > coin.getCurCoin()) {
            System.out.println("Not enough money!");
        } else {
            weapon.upgrade();
            coin.pay(upgradeCost);
        }
    }

    public static void addHpToTheWall(String wallIndexes, String wallHps) {
        if (wallHps.length() == wallHps.length()) {
            int length = wallHps.length();
            for (int i = 0; i < length; i++) {

                int index = Integer.parseInt(wallIndexes.charAt(i) + "");
                int hp = Integer.parseInt(wallHps.charAt(i) + "");
                if (index >= 0 && index <= 9) { // is wall index
                    if (hp > coin.getCurCoin()) {
                        System.out.println("Your money is not enough");
                    } else {
                        WallUnit wallUnit = wall.get(index);
                        wallUnit.addHp(wallUnit.getHp() + hp);
                        coin.pay(hp);
                    }
                }
            }
        }
    }

    public void attackTitan() {
        Titan[][] titans = ground.getTitans();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10;j++) {
                if (titans[i][j] != null) {
                    Weapon weapon = wall.get(j).getWeapon();
                    if (weapon.getLevel() > 0) {
                        // attack titan
                        int attackPoint = weapon.getAttack();
                        titans[i][j].takeDamage(attackPoint);
                    }
                }
            }
        }
    }

    public void attackWall() {
        Titan[][] titans = ground.getTitans();

        for (int i = 0; i < 10;i++) {
            if (titans[9][i] != null) {
                WallUnit wallUnit = wall.get(i);
                Weapon weapon = wallUnit.getWeapon();
                if (weapon.getLevel() > 0) {
                    // attack weapon
                    weapon.destroy();
                } else {
                    // attack wall
                    wallUnit.takeDamage(titans[9][i].getAttackPoint());
                }
            }
        }

    }


}
