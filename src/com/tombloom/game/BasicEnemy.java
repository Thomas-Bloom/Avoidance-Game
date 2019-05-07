package com.tombloom.game;

import java.awt.*;

public class BasicEnemy extends GameObject {
    private ObjectUpdater objectUpdater;

    public BasicEnemy(int xPos, int yPos, int size, ObjectID id, ObjectUpdater objectUpdater){
        super(xPos, yPos, size, id);
        this.objectUpdater = objectUpdater;

        velX = (int)(Math.random() * ((5 - -5) + 1)) + -5;
        velY = (int)(Math.random() * ((5 - -5) + 1)) + -5;
    }

    @Override
    public void tick() {
        xPos += velX;
        yPos += velY;

        if(yPos <= 0 || yPos >= Game.height - 32 - 16) velY *= -1;
        if(xPos <= 0 || xPos >= Game.width - 16) velX *= -1;

        objectUpdater.addObject(new Trail(xPos, yPos, size, ObjectID.Trail, Color.red, 0.05f, objectUpdater));
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
