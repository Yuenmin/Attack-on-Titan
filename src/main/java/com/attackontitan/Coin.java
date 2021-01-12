package com.attackontitan;

public class Coin {
    private int curCoin;

    public Coin() {
        curCoin = 70;
    }

    public int getCurCoin() {
        return curCoin;
    }

    public void increaseCoinPerHours() {
        curCoin += 5;
    }

    public void pay(int paymentCoin) {
        curCoin -= paymentCoin;
    }
}
