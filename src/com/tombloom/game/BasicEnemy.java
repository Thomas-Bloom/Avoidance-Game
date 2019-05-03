package com.tombloom.game;

import java.awt.*;

public class BasicEnemy extends GameObject {
    public BasicEnemy(int xPos, int yPos, int size, ObjectID id){
        super(xPos, yPos, size, id);
        velX = (int)(Math.random() * ((5 - -5) + 1)) + -5;
        velY = (int)(Math.random() * ((5 - -5) + 1)) + -5;
    }

    @Override
    public void tick() {
        xPos += velX;
        yPos += velY;

        if(yPos <= 0 || yPos >= Game.HEIGHT - 32 - 16) velY *= -1;
        if(xPos <= 0 || xPos >= Game.WIDTH - 16) velX *= -1;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(xPos, yPos, size, size);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(xPos, yPos, size, size);
    }
}
