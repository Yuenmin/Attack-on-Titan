package com.attackontitan;

import java.util.ArrayList;
import java.util.List;

public class Wall {
    private WallUnit[] wallUnits;

    public Wall() {
        wallUnits = new WallUnit[10];
        for (int i = 0; i < 10; i++) {
            wallUnits[i] = new WallUnit();
        }
    }

    public WallUnit get(int index) {
        return wallUnits[index];
    }

    public List<Integer> getWeaponIndexes() {
        List<Integer> weaponIndexes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (wallUnits[i].getWeapon().getLevel() != 0) {
                weaponIndexes.add(i);
            }
        }
        return weaponIndexes;
    }

    public void printToConsole() {
        for (int level = 3; level > 0; level--) {
            for (int i = 0; i < 10; i++) {
                if (wallUnits[i].getWeapon().getLevel() >= level) {
                    System.out.print("** ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }

        System.out.println("-- -- -- -- -- -- -- -- -- -- \t\tThe Wall");
        System.out.println(" 0  1  2  3  4  5  6  7  8  9 \t\tIndex");
        System.out.println(wallUnits[0].getHp() + " " + wallUnits[1].getHp() + " " + wallUnits[2].getHp() + " " + wallUnits[3].getHp() + " " + wallUnits[4].getHp() + " "
                + wallUnits[5].getHp() + " " + wallUnits[6].getHp() + " " + wallUnits[7].getHp() + " " + wallUnits[8].getHp() + " " + wallUnits[9].getHp() + " \t\tHP");
        System.out.println("-- -- -- -- -- -- -- -- -- -- ");

    }

    public WallUnit[] getWallUnits() {
        return wallUnits;
    }
}