package com.attackontitan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ground {
    private Titan[][] titans;
    private String[][] ground = new String[10][20];
    private List<ArmouredTitanView> aTitanList = new ArrayList<>();
    private List<ColossusTitanView> cTitanList = new ArrayList<>();
    private List<ArmouredAndColossusTitanView> acTitanList = new ArrayList<>();

    public Ground() {
        titans = new Titan[10][20];
    }

    public Titan[][] getTitans() {
        return titans;
    }

    public void setGround() {
        for (int i = 0; i < ground.length; i++) {
            for (int j = 0; j < ground[i].length; j++) {
                if (j % 2 == 0)
                    if (titans[i][j] instanceof ColossusTitan) {
                        ground[i][j] = "CC";
                    } else if (titans[i][j] instanceof ArmouredTitan) {
                        ground[i][j] = "AA";
                    } else if (titans[i][j] instanceof ArmouredAndColossusTitan) {
                        ground[i][j] = "AC";
                    } else
                        ground[i][j] = "^^";
                else
                    ground[i][j] = " ";
            }
        }
    }

    //print row index + ground
    public void printGround(int hour, int coin) {
        System.out.println("Row");
        for (int i = 0; i < ground.length; i++) {
            System.out.print(i + "   ");
            for (int j = 0; j < ground[i].length; j++) {
                System.out.print(ground[i][j]);
            }
            if (i == 0)
                System.out.print("\tHOUR " + hour);
            if (i == 1)
                System.out.print("\tCoin: " + coin);
            System.out.println();
        }
    }

    public void addColossusTitan(int column) {
        // show up on row 9
        System.out.println("adding colossus");
        Titan curTitan = titans[9][column];
        Titan colossusTitan = new ColossusTitan(column, 9);
        if (curTitan == null) {
            titans[9][column] = colossusTitan;
        } else if (curTitan instanceof ArmouredTitan) {
            titans[9][column] = new ArmouredAndColossusTitan((ArmouredTitan) curTitan, (ColossusTitan) colossusTitan);
            acTitanList.add(0, titans[9][column].getArmouredAndColossusTitanView());
        } else if (curTitan instanceof ColossusTitan || curTitan instanceof ArmouredAndColossusTitan) {
            colossusTitan = null;
        }
        if (colossusTitan != null) {
            colossusTitan.canMove = false;
            cTitanList.add(0, colossusTitan.getColossusTitanView());
        }
    }

    public void addArmouredTitan(int column) {
        System.out.println("adding armoured");
        Titan curTitan = titans[0][column];
        Titan armouredTitan = new ArmouredTitan(column, 0);
        if (curTitan == null) {
            titans[0][column] = armouredTitan;
        } else if (curTitan instanceof ColossusTitan) {
            titans[9][column] = new ArmouredAndColossusTitan((ArmouredTitan) armouredTitan, (ColossusTitan) curTitan);
        } else {
            armouredTitan = null;
        }
        if (armouredTitan != null) {
            armouredTitan.canMove = false;
            aTitanList.add(0, armouredTitan.getArmouredTitanView());
        }
    }

    public void move() {
        System.out.println("moving titan");
        Random ran = new Random();
        //Wall wall = new Wall();
        int curRow, curColumn;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                Titan curTitan = titans[i][j];
                if (curTitan != null) {
                    System.out.println(curTitan.getName());
                    curRow = curTitan.currentRow;
                    curColumn = curTitan.currentColumn;
                    System.out.println(curTitan.currentRow);
                    if (curTitan.canMove) {
                        App.attackWall(curRow, curColumn);
                        if (curTitan instanceof ColossusTitan) {
                            curTitan.canMove = false;
                            if (isMoveLeftAndRightAvailable(curRow, curColumn)) {
                                if (ran.nextInt(2) == 1) {
                                    // moveRight
                                    if (this.titans[curRow][curColumn + 2] == null) {
                                        this.titans[curRow][curColumn + 2] = curTitan;
                                        this.titans[curRow][curColumn] = null;
                                        curTitan.setPosition(curRow, curColumn + 2);
                                        curTitan.getColossusTitanView().right();
                                    } else if (this.titans[curRow][curColumn + 2] instanceof ArmouredTitan) {
                                        this.titans[curRow][curColumn + 2] = new ArmouredAndColossusTitan((ColossusTitan) curTitan, (ArmouredTitan) this.titans[curRow][curColumn + 2]);
                                        this.titans[curRow][curColumn] = null;
                                        this.titans[curRow][curColumn + 2].setPosition(curRow, curColumn + 2);
                                        acTitanList.add(0, titans[curRow][curColumn + 2].getArmouredAndColossusTitanView());
                                    }

                                } else {
                                    // move left

                                    if (this.titans[curRow][curColumn - 2] == null) {
                                        this.titans[curRow][curColumn - 2] = curTitan;
                                        this.titans[curRow][curColumn] = null;
                                        curTitan.setPosition(curRow, curColumn - 2);
                                        curTitan.getColossusTitanView().left();
                                    } else if (this.titans[curRow][curColumn - 2] instanceof ArmouredTitan) {
                                        this.titans[curRow][curColumn - 2] = new ArmouredAndColossusTitan((ColossusTitan) curTitan, (ArmouredTitan) this.titans[curRow][curColumn - 2]);
                                        this.titans[curRow][curColumn] = null;
                                        this.titans[curRow][curColumn - 2].setPosition(curRow, curColumn - 2);
                                        acTitanList.add(0, titans[curRow][curColumn - 2].getArmouredAndColossusTitanView());
                                    }
                                }
                            } else if (isMoveLeftAvailable(curRow, curColumn)) {
                                if (ran.nextInt(2) == 1) {
                                    // move left

                                    if (this.titans[curRow][curColumn - 2] == null) {
                                        this.titans[curRow][curColumn - 2] = curTitan;
                                        this.titans[curRow][curColumn] = null;
                                        curTitan.setPosition(curRow, curColumn - 2);
                                        curTitan.getColossusTitanView().left();

                                    } else if (this.titans[curRow][curColumn - 2] instanceof ArmouredTitan) {
                                        this.titans[curRow][curColumn - 2] = new ArmouredAndColossusTitan((ColossusTitan) curTitan, (ArmouredTitan) this.titans[curRow][curColumn - 2]);
                                        this.titans[curRow][curColumn] = null;
                                        this.titans[curRow][curColumn - 2].setPosition(curRow, curColumn - 2);
                                        acTitanList.add(0, titans[curRow][curColumn - 2].getArmouredAndColossusTitanView());
                                    }
                                } else {
                                    //  not move
                                }
                            } else if (isMoveRightAvailable(curRow, curColumn)) {
                                if (ran.nextInt(2) == 1) {
                                    // move right

                                    if (this.titans[curRow][curColumn + 2] == null) {
                                        this.titans[curRow][curColumn + 2] = curTitan;
                                        this.titans[curRow][curColumn] = null;
                                        curTitan.setPosition(curRow, curColumn + 2);
                                        curTitan.getColossusTitanView().right();
                                    } else if (this.titans[curRow][curColumn + 2] instanceof ArmouredTitan) {
                                        this.titans[curRow][curColumn + 2] = new ArmouredAndColossusTitan((ColossusTitan) curTitan, (ArmouredTitan) this.titans[curRow][curColumn + 2]);
                                        this.titans[curRow][curColumn] = null;
                                        this.titans[curRow][curColumn + 2].setPosition(curRow, curColumn + 2);
                                        acTitanList.add(0, titans[curRow][curColumn + 2].getArmouredAndColossusTitanView());
                                    }

                                } else {
                                    //  not move
                                }
                            } else {
                                // not move
                            }

                        } else if (curTitan instanceof ArmouredTitan) {
                            curTitan.canMove = false;
                            if (!curTitan.isFrontOfTheWall()) {
                                if (isMoveLeftAndRightAvailable(curRow, curColumn)) {
                                    if (ran.nextInt(2) == 1) {
                                        // moveRight
                                        if (this.titans[curRow + 1][curColumn + 2] == null) {
                                            this.titans[curRow + 1][curColumn + 2] = curTitan;
                                            this.titans[curRow][curColumn] = null;
                                            curTitan.setPosition(curRow + 1, curColumn + 2);
                                            curTitan.getArmouredTitanView().rightAndWalk();

                                        } else if (this.titans[curRow + 1][curColumn + 2] instanceof ColossusTitan) {
                                            this.titans[curRow + 1][curColumn + 2] = new ArmouredAndColossusTitan((ArmouredTitan) curTitan, (ColossusTitan) this.titans[curRow + 1][curColumn + 2]);
                                            this.titans[curRow][curColumn] = null;
                                            this.titans[curRow + 1][curColumn + 2].setPosition(curRow + 1, curColumn + 2);
                                            acTitanList.add(0, titans[curRow + 1][curColumn + 2].getArmouredAndColossusTitanView());
                                            curTitan.getArmouredTitanView().rightAndWalk();
                                        }
                                    } else {
                                        // move left
                                        if (this.titans[curRow + 1][curColumn - 2] == null) {
                                            this.titans[curRow + 1][curColumn - 2] = curTitan;
                                            this.titans[curRow][curColumn] = null;
                                            curTitan.setPosition(curRow + 1, curColumn - 2);
                                            curTitan.getArmouredTitanView().leftAndWalk();

                                        } else if (this.titans[curRow + 1][curColumn - 2] instanceof ColossusTitan) {
                                            this.titans[curRow + 1][curColumn - 2] = new ArmouredAndColossusTitan((ArmouredTitan) curTitan, (ColossusTitan) this.titans[curRow + 1][curColumn - 2]);
                                            this.titans[curRow][curColumn] = null;
                                            this.titans[curRow + 1][curColumn - 2].setPosition(curRow + 1, curColumn - 2);
                                            acTitanList.add(0, titans[curRow + 1][curColumn - 2].getArmouredAndColossusTitanView());
                                            curTitan.getArmouredTitanView().leftAndWalk();

                                        }
                                    }
                                } else if (isMoveLeftAvailable(curRow, curColumn)) {
                                    if (ran.nextInt(2) == 1) {
                                        // move left
                                        if (this.titans[curRow + 1][curColumn - 2] == null) {
                                            this.titans[curRow + 1][curColumn - 2] = curTitan;
                                            this.titans[curRow][curColumn] = null;
                                            curTitan.setPosition(curRow + 1, curColumn - 2);
                                            curTitan.getArmouredTitanView().leftAndWalk();

                                        } else if (this.titans[curRow + 1][curColumn - 2] instanceof ColossusTitan) {
                                            this.titans[curRow + 1][curColumn - 2] = new ArmouredAndColossusTitan((ArmouredTitan) curTitan, (ColossusTitan) this.titans[curRow + 1][curColumn - 2]);
                                            this.titans[curRow][curColumn] = null;
                                            this.titans[curRow + 1][curColumn - 2].setPosition(curRow + 1, curColumn - 2);
                                            acTitanList.add(0, titans[curRow + 1][curColumn - 2].getArmouredAndColossusTitanView());
                                            curTitan.getArmouredTitanView().leftAndWalk();
                                        }

                                    } else {
                                        //  not move
                                    }
                                } else if (isMoveRightAvailable(curRow, curColumn)) {
                                    if (ran.nextInt(2) == 1) {
                                        // move right
                                        if (this.titans[curRow + 1][curColumn + 2] == null) {
                                            this.titans[curRow + 1][curColumn + 2] = curTitan;
                                            this.titans[curRow][curColumn] = null;
                                            curTitan.setPosition(curRow + 1, curColumn + 2);
                                            curTitan.getArmouredTitanView().rightAndWalk();

                                        } else if (this.titans[curRow + 1][curColumn + 2] instanceof ColossusTitan) {
                                            this.titans[curRow + 1][curColumn + 2] = new ArmouredAndColossusTitan((ArmouredTitan) curTitan, (ColossusTitan) this.titans[curRow + 1][curColumn + 2]);
                                            this.titans[curRow][curColumn] = null;
                                            this.titans[curRow + 1][curColumn + 2].setPosition(curRow + 1, curColumn + 2);
                                            acTitanList.add(0, titans[curRow + 1][curColumn + 2].getArmouredAndColossusTitanView());
                                            curTitan.getArmouredTitanView().rightAndWalk();
                                        }

                                    } else {
                                        //  not move
                                        System.out.println("no move 1");
                                    }
                                } else {
                                    // not move
                                    System.out.println("no move 2");
                                }
                            } else {
                                if (!App.getWall().armouredTitanDestroy(curColumn)) {
                                    if (isMoveLeftAndRightAvailable(curRow, curColumn)) {
                                        int x = 2;
                                        while (true) {
                                            try {
                                                if (App.getWall().get((curColumn + x) / 2).getWeapon().getLevel() > 0) {
                                                    if (this.titans[curRow][curColumn + 2] == null) {
                                                        this.titans[curRow][curColumn + 2] = curTitan;
                                                        this.titans[curRow][curColumn] = null;
                                                        curTitan.setPosition(curRow, curColumn + 2);
                                                        curTitan.getArmouredTitanView().right();
                                                        break;

                                                    } else if (this.titans[curRow][curColumn + 2] instanceof ColossusTitan) {
                                                        this.titans[curRow][curColumn + 2] = new ArmouredAndColossusTitan((ArmouredTitan) curTitan, (ColossusTitan) this.titans[curRow][curColumn + 2]);
                                                        this.titans[curRow][curColumn] = null;
                                                        this.titans[curRow][curColumn + 2].setPosition(curRow, curColumn + 2);
                                                        curTitan.getArmouredTitanView().right();
                                                        break;
                                                    }

                                                } else if (App.getWall().get((curColumn - x) / 2).getWeapon().getLevel() > 0) {
                                                    // move left
                                                    if (this.titans[curRow][curColumn - 2] == null) {
                                                        this.titans[curRow][curColumn - 2] = curTitan;
                                                        this.titans[curRow][curColumn] = null;
                                                        curTitan.setPosition(curRow, curColumn - 2);
                                                        curTitan.getArmouredTitanView().left();
                                                        break;

                                                    } else if (this.titans[curRow][curColumn - 2] instanceof ColossusTitan) {
                                                        this.titans[curRow][curColumn - 2] = new ArmouredAndColossusTitan((ArmouredTitan) curTitan, (ColossusTitan) this.titans[curRow][curColumn - 2]);
                                                        this.titans[curRow][curColumn] = null;
                                                        this.titans[curRow][curColumn - 2].setPosition(curRow, curColumn - 2);
                                                        curTitan.getArmouredTitanView().left();
                                                        break;
                                                    }
                                                }
                                                x += 2;
                                            } catch (ArrayIndexOutOfBoundsException ignored) {

                                            }
                                        }

                                    } else if (isMoveLeftAvailable(curRow, curColumn)) {
                                        // move left
                                        if (this.titans[curRow][curColumn - 2] == null) {
                                            this.titans[curRow][curColumn - 2] = curTitan;
                                            this.titans[curRow][curColumn] = null;
                                            curTitan.setPosition(curRow, curColumn - 2);
                                            curTitan.getArmouredTitanView().left();

                                        } else if (this.titans[curRow][curColumn - 2] instanceof ColossusTitan) {
                                            this.titans[curRow][curColumn - 2] = new ArmouredAndColossusTitan((ArmouredTitan) curTitan, (ColossusTitan) this.titans[curRow][curColumn - 2]);
                                            this.titans[curRow][curColumn] = null;
                                            this.titans[curRow][curColumn - 2].setPosition(curRow, curColumn - 2);
                                            curTitan.getArmouredTitanView().left();
                                        }
                                    } else if (isMoveRightAvailable(curRow, curColumn)) {
                                        // move right
                                        if (this.titans[curRow][curColumn + 2] == null) {
                                            this.titans[curRow][curColumn + 2] = curTitan;
                                            this.titans[curRow][curColumn] = null;
                                            curTitan.setPosition(curRow, curColumn + 2);
                                            curTitan.getArmouredTitanView().right();

                                        } else if (this.titans[curRow][curColumn + 2] instanceof ColossusTitan) {
                                            this.titans[curRow][curColumn + 2] = new ArmouredAndColossusTitan((ArmouredTitan) curTitan, (ColossusTitan) this.titans[curRow][curColumn + 2]);
                                            this.titans[curRow][curColumn] = null;
                                            this.titans[curRow][curColumn + 2].setPosition(curRow, curColumn + 2);
                                            curTitan.getArmouredTitanView().right();
                                        }
                                    }
                                }else{
                                    curTitan.getArmouredTitanView().attack();
                                }
                            }
                        } else if (curTitan instanceof ArmouredAndColossusTitan) {
                            curTitan.canMove = false;
                        }
                    }
                }
            }
        }
        System.out.println("moved titan");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                Titan curTitan = titans[i][j];
                if (curTitan != null) {
                    curTitan.canMove = true;
                }
            }
        }
    }

    public boolean isMoveLeftAvailable(int curRow, int curColumn) {
        if (curColumn > 0) {
            return this.titans[curRow][curColumn - 2] == null
                    || isCanOverlapTitan(this.titans[curRow][curColumn - 2], this.titans[curRow][curColumn]);
        }
        return false;
    }

    public boolean isMoveRightAvailable(int curRow, int curColumn) {
        if (curColumn < 18) {
            return this.titans[curRow][curColumn + 2] == null
                    || isCanOverlapTitan(this.titans[curRow][curColumn + 2], this.titans[curRow][curColumn]);
        }
        return false;
    }

    public boolean isMoveLeftAndRightAvailable(int curRow, int curColumn) {
        return isMoveLeftAvailable(curRow, curColumn) && isMoveRightAvailable(curRow, curColumn);
    }

    public boolean isCanOverlapTitan(Titan titan1, Titan titan2) {
        if (titan1 instanceof ColossusTitan && titan2 instanceof ArmouredTitan) {
            return true;
        }

        if (titan1 instanceof ArmouredTitan && titan2 instanceof ColossusTitan) {
            return true;
        }

        return false;
    }

    public void randomMove(int curRow, int curColumn, List<Integer> weaponIndexes) {
        List<AvailableMovement> movements = getAvailableMove(curRow, curColumn, weaponIndexes);

        if (movements.size() == 0) {
            return;
        }

        Random ran = new Random();
        int movementRandomIndex = ran.nextInt(movements.size());
        AvailableMovement availableMovement = movements.get(movementRandomIndex);

        // if only have 1 available move
        // random move or stay on
        if (movements.size() > 1) {
            move(curRow, curColumn, availableMovement.getRow(), availableMovement.getColumn());
        } else {
            if (ran.nextBoolean()) {
                move(curRow, curColumn, availableMovement.getRow(), availableMovement.getColumn());
            }
        }
    }

    private void move(int curRow, int curColumn, int nextRow, int nextColumn) {
        Titan curTitan = titans[curRow][curColumn];
        Titan nextTitan = titans[nextRow][nextColumn];

        if (nextTitan == null) {
            titans[nextRow][nextColumn] = curTitan;
        } else {
            titans[nextRow][nextColumn] = new ArmouredAndColossusTitan(curTitan, nextTitan);
        }
        titans[curRow][curColumn] = null;
    }

    private List<AvailableMovement> getAvailableMove(int curRow, int curColumn, List<Integer> weaponIndexes) {
        Titan curTitan = titans[curRow][curColumn];
        if (curTitan instanceof ColossusTitan) {
            return getAvailableColossusTitanMove(curRow, curColumn);
        } else if (curTitan instanceof ArmouredTitan) {
            // AA
            return getAvailableArmouredTitanMove(curRow, curColumn);
        }
        // AC
        return getAvailableArmouredAndColossusTitanMove(curRow, curColumn, weaponIndexes);
    }

    private List<AvailableMovement> getAvailableColossusTitanMove(int curRow, int curColumn) {
        List<AvailableMovement> movements = new ArrayList<>();

        if (curColumn > 0 && this.titans[curRow][curColumn - 2] == null || this.titans[curRow][curColumn - 2] instanceof ArmouredTitan) {
            movements.add(new AvailableMovement(curRow, curColumn - 2));
        }

        if (curColumn < 9 && this.titans[curRow][curColumn + 2] == null || this.titans[curRow][curColumn + 2] instanceof ArmouredTitan) {
            movements.add(new AvailableMovement(curRow, curColumn - 2));
        }

        return movements;
    }

    private List<AvailableMovement> getAvailableArmouredTitanMove(int curRow, int curColumn) {
        List<AvailableMovement> movements = new ArrayList<>();

        if (curRow < 9) {
            // normal move, only up left or up right
            // up left
            if (curColumn > 0 && this.titans[curRow + 1][curColumn - 2] == null || this.titans[curRow + 1][curColumn - 2] instanceof ColossusTitan) {
                movements.add(new AvailableMovement(curRow + 1, curColumn - 2));
            }

            // up right
            if (curColumn < 9 && this.titans[curRow + 1][curColumn + 2] == null || this.titans[curRow + 1][curColumn + 2] instanceof ColossusTitan) {
                movements.add(new AvailableMovement(curRow + 1, curColumn - 2));
            }
        } else {
            // can overlap, only left or right
            if (curColumn > 0 && this.titans[curRow][curColumn - 2] == null || this.titans[curRow][curColumn - 2] instanceof ColossusTitan) {
                movements.add(new AvailableMovement(curRow, curColumn - 2));
            }

            if (curColumn < 9 && this.titans[curRow][curColumn + 2] == null || this.titans[curRow][curColumn + 2] instanceof ColossusTitan) {
                movements.add(new AvailableMovement(curRow, curColumn - 2));
            }
        }
        return movements;
    }

    private List<AvailableMovement> getAvailableArmouredAndColossusTitanMove(int curRow, int curColumn, List<Integer> weaponIndexes) {
        List<AvailableMovement> movements = new ArrayList<>();

        if (weaponIndexes.size() != 0) {
            int leftWeaponIndexes = -1;
            int rightWeaponIndexes = -1;
            for (int i = 0; i < weaponIndexes.size(); i++) {
                int weaponIndex = weaponIndexes.get(i);
                if (weaponIndex < curColumn) {
                    leftWeaponIndexes = weaponIndex;
                }
                if (weaponIndex > curColumn) {
                    rightWeaponIndexes = weaponIndex;
                    break;
                }
            }

            if (leftWeaponIndexes != -1 && curColumn > 0 && this.titans[curRow][curColumn - 2] == null) {
                movements.add(new AvailableMovement(curRow, curColumn - 2));
            }

            if (rightWeaponIndexes != -1 && curColumn < 9 && this.titans[curRow][curColumn + 2] == null) {
                movements.add(new AvailableMovement(curRow, curColumn - 2));
            }
        }

        return movements;
    }

    public List<ArmouredTitanView> getATitanList() {
        return aTitanList;
    }

    public List<ColossusTitanView> getCTitanList() {
        return cTitanList;
    }

    public List<ArmouredAndColossusTitanView> getAcTitanList() {
        return acTitanList;
    }
}
