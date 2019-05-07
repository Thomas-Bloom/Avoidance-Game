package com.tombloom.game;

public class GameState {
    public static int health;
    public static int score = 0;
    public static int level = 1;

    public GameState(){
        health = 100;
    }

    public void tick(){
        setHealth(Game.clamp(health, 0, 100));
        score++;
    }

    public static void reduceHealth(int amount){
        health -= amount;
    }

    public static void setHealth(int value){
        health = value;
    }
}
