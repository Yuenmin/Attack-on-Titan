package com.attackontitan;

public class Record {

    private int score;
    private String name;

    public Record(String name, int score) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}