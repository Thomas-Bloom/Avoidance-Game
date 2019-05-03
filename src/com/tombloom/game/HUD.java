package com.tombloom.game;

import java.awt.*;

public class HUD {
    private final int healthPosX = 15, healthPosY = 15;
    private final int healthWidth = 200, healthHeight = 30;

    public void tick(){
    }

    public void render(Graphics g){
        // Health display
        g.setColor(Color.gray);
        g.fillRect(healthPosX, healthPosY, healthWidth, healthHeight);

        g.setColor(Color.green);
        g.fillRect(healthPosX, healthPosY, GameState.health * 2 , healthHeight);

        g.setColor(Color.white);
        g.drawRect(healthPosX, healthPosY, healthWidth, healthHeight);
    }
}
