package com.tombloom.game;

import java.awt.*;

public class HUD {
    private final int healthPosX = 15, healthPosY = 15;
    private final int healthWidth = 200, healthHeight = 30;

    private GameState gameState;

    public HUD(GameState gs){
        gameState = gs;
    }

    public void tick(){
        gameState.reduceHealth(1);

        gameState.setHealth(Game.clamp(gameState.getHealth(), 0, 100));
    }

    public void render(Graphics g){
        // Health display
        g.setColor(Color.gray);
        g.fillRect(healthPosX, healthPosY, healthWidth, healthHeight);

        g.setColor(Color.green);
        g.fillRect(healthPosX, healthPosY, gameState.getHealth() * 2 , healthHeight);

        g.setColor(Color.white);
        g.drawRect(healthPosX, healthPosY, healthWidth, healthHeight);
    }
}
