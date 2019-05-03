package com.tombloom.game;

public class GameState {
    private int health;

    public GameState(){
        health = 100;
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int amount){
        health -= amount;
    }

    public void setHealth(int value){
        health = value;
    }
}
