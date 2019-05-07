package com.tombloom.game;

import java.awt.*;

public class BasicEnemy extends GameObject {
    private ObjectUpdater objectUpdater;

    private int minVelX = -7, maxVelX = 7;
    private int minVelY = -7, maxVelY = 7;

    public BasicEnemy(int xPos, int yPos, int size, ObjectID id, ObjectUpdater objectUpdater){
        super(xPos, yPos, size, id);
        this.objectUpdater = objectUpdater;

        velX = (int)(Math.random() * ((maxVelX - -minVelX) + 1)) + minVelX;
        velY = (int)(Math.random() * ((maxVelY - -minVelY) + 1)) + minVelY;
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
