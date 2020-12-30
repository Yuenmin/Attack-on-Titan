package com.attackontitan;

public class Hour {
    private int currentHour;

    public Hour(){
        currentHour=0;
    }

    public int getCurrentHour() {
        return currentHour;
    }

    public void setCurrentHour(int currentHour) {
        this.currentHour = currentHour;
    }

    public void nextHour() {
        currentHour++;
    }

    public boolean isPlayerTurn() {
        return currentHour % 2 == 0;
    }
}
