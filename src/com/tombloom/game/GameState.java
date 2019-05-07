package com.tombloom.game;

public class GameState {
    public static float health;
    public static int score = 0;
    public static int level = 1;

    public GameState(){
        health = 100;
    }

    public void tick(){
        setHealth(Game.clamp(health, 0f, 100f));
        score++;
    }

    public static void reduceHealth(int amount){
        health -= amount;
    }

    public static void setHealth(float value){
        health = value;
    }
}
