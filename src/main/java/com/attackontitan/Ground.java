package com.attackontitan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ground {
    private Titan[][] titans;

    private List<ArmouredTitanView> aTitanList = new ArrayList<>();

    private List<ColossusTitanView> cTitanList = new ArrayList<>();

    public Ground() {
        titans = new Titan[10][10];
    }

    public Titan[][] getTitans() {
        return titans;
    }

    public void addColossusTitan(int column) {
        // show up on row 9
        Titan curTitan = titans[9][column];
        Titan colossusTitan = new ColossusTitan(column);
        cTitanList.add(colossusTitan.getColossusTitanView());
        if (curTitan == null) {
            titans[9][column] = colossusTitan;
        } else if (curTitan instanceof ArmouredTitan) {
            titans[9][column] = new ArmouredAndColossusTitan((ArmouredTitan) curTitan, (ColossusTitan) colossusTitan);
        } else if (curTitan instanceof ColossusTitan){
            // TODO can not overlap titan CC with CC or CC with AC
        }
    }

    public void addArmouredTitan(int column) {
        Titan curTitan = titans[0][column];
        Titan armouredTitan = new ArmouredTitan(column);
        aTitanList.add(armouredTitan.getArmouredTitanView());
        if (curTitan == null) {
            titans[0][column] = armouredTitan;
        } else if (curTitan instanceof ColossusTitan) {
            titans[9][column] = new ArmouredAndColossusTitan((ArmouredTitan) armouredTitan, (ColossusTitan) curTitan);
        } else {
            // TODO can not overlap titan AA with AA or AA with AC
        }
    }

    private boolean moveRight=false;
    public void move(int curRow, int curColumn) {
        Random ran = new Random();
        int r=ran.nextInt(3);
        Titan curTitan = titans[curRow][curColumn];
        if (curTitan instanceof ColossusTitan) {

            if (isMoveLeftAndRightAvailable(curRow, curColumn)) {
                if (r == 1) {
                    // moveRight
                    moveRight=true;
                    System.out.println("move right1");
                    curTitan.getColossusTitanView().right();
                    if (this.titans[curRow][curColumn + 1] == null) {
                        this.titans[curRow][curColumn + 1] = curTitan;
                    } else {
                        this.titans[curRow][curColumn + 1] = new ArmouredAndColossusTitan(( ArmouredTitan) this.titans[curRow][curColumn + 1], (ColossusTitan) curTitan);
                    }
                    this.titans[curRow][curColumn]=null;

                } else if(r == 0){
                    // move left
                    System.out.println("move left1");
                    curTitan.getColossusTitanView().left();
                    if (this.titans[curRow][curColumn - 1] == null) {
                        this.titans[curRow][curColumn - 1] = curTitan;
                    } else {
                        this.titans[curRow][curColumn - 1] = new ArmouredAndColossusTitan(( ArmouredTitan) this.titans[curRow][curColumn - 1], (ColossusTitan) curTitan);
                    }
                    this.titans[curRow][curColumn]=null;

                }else {
                    //not move
                    System.out.println("not move1");
                }
            } else if (isMoveLeftAvailable(curRow, curColumn)){
                if (ran.nextInt(2) == 1) {
                    // move left
                    System.out.println("move left2");
                    curTitan.getColossusTitanView().left();
                    if (this.titans[curRow][curColumn - 1] == null) {
                        this.titans[curRow][curColumn - 1] = curTitan;
                    } else {
                        this.titans[curRow][curColumn - 1] = new ArmouredAndColossusTitan(( ArmouredTitan) this.titans[curRow][curColumn - 1], (ColossusTitan) curTitan);
                    }
                    this.titans[curRow][curColumn]=null;

                } else {
                    //  not move
                    System.out.println("not move2");
                }
            } else if (isMoveRightAvailable(curRow, curColumn)) {
                if (ran.nextInt(2) == 1) {
                    // move right
                    moveRight=true;
                    System.out.println("move right2");
                    curTitan.getColossusTitanView().right();
                    if (this.titans[curRow][curColumn + 1] == null) {
                        this.titans[curRow][curColumn + 1] = curTitan;
                    } else {
                        this.titans[curRow][curColumn + 1] = new ArmouredAndColossusTitan(( ArmouredTitan) this.titans[curRow][curColumn + 1], (ColossusTitan) curTitan);
                    }
                    this.titans[curRow][curColumn]=null;

                } else {
                    //  not move
                    System.out.println("not move3");
                }
            } else {
                // not move
                System.out.println("not move4");
            }
        } else if (curTitan instanceof ArmouredTitan) {

        } else {
            // curTitan instance of ArmouredAndColossusTitan
        }
    }

    public boolean isMoveLeftAvailable(int curRow, int curColumn) {
        if (curColumn > 0) {
            return this.titans[curRow][curColumn - 1] == null || isCanOverlapTitan(this.titans[curRow][curColumn - 1], this.titans[curRow][curColumn]);
        }
        return false;
    }

    public boolean isMoveRightAvailable(int curRow, int curColumn) {
        if (curColumn < 9) {
            return this.titans[curRow][curColumn + 1] == null || isCanOverlapTitan(this.titans[curRow][curColumn + 1], this.titans[curRow][curColumn]);
        }
        return false;
    }

    public boolean isMoveLeftAndRightAvailable(int curRow, int curColumn) {
        return isMoveLeftAvailable(curRow, curColumn) && isMoveRightAvailable(curRow, curColumn);
    }

    public boolean isCanOverlapTitan(Titan titan1, Titan titan2) {
        if (titan1 instanceof ColossusTitan && titan2 instanceof  ArmouredTitan) {
            return true;
        }

        if (titan1 instanceof ArmouredTitan && titan2 instanceof  ColossusTitan) {
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

        if (curColumn > 0 && this.titans[curRow][curColumn - 1] == null || this.titans[curRow][curColumn - 1] instanceof ArmouredTitan) {
            movements.add(new AvailableMovement(curRow, curColumn - 1));
        }

        if (curColumn < 9 && this.titans[curRow][curColumn + 1] == null || this.titans[curRow][curColumn + 1] instanceof ArmouredTitan) {
            movements.add(new AvailableMovement(curRow, curColumn - 1));
        }

        return movements;
    }

    private List<AvailableMovement> getAvailableArmouredTitanMove(int curRow, int curColumn) {
        List<AvailableMovement> movements = new ArrayList<>();

        if (curRow < 9) {
            // normal move, only up left or up right
            // up left
            if (curColumn > 0 && this.titans[curRow + 1][curColumn - 1] == null || this.titans[curRow + 1][curColumn - 1] instanceof ColossusTitan) {
                movements.add(new AvailableMovement(curRow + 1, curColumn - 1));
            }

            // up right
            if (curColumn < 9 && this.titans[curRow + 1][curColumn + 1] == null || this.titans[curRow + 1][curColumn + 1] instanceof ColossusTitan) {
                movements.add(new AvailableMovement(curRow + 1, curColumn - 1));
            }
        } else {
            // can overlap, only left or right
            if (curColumn > 0 && this.titans[curRow][curColumn - 1] == null || this.titans[curRow][curColumn - 1] instanceof ColossusTitan) {
                movements.add(new AvailableMovement(curRow, curColumn - 1));
            }

            if (curColumn < 9 && this.titans[curRow][curColumn + 1] == null || this.titans[curRow][curColumn + 1] instanceof ColossusTitan) {
                movements.add(new AvailableMovement(curRow, curColumn - 1));
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

            if (leftWeaponIndexes != -1 && curColumn > 0 && this.titans[curRow][curColumn - 1] == null) {
                movements.add(new AvailableMovement(curRow, curColumn - 1));
            }

            if (rightWeaponIndexes != -1 && curColumn < 9 && this.titans[curRow][curColumn + 1] == null) {
                movements.add(new AvailableMovement(curRow, curColumn - 1));
            }
        }

        return movements;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public List<ArmouredTitanView> getATitanList() {
        return aTitanList;
    }

    public List<ColossusTitanView> getCTitanList() {
        return cTitanList;
    }
}
