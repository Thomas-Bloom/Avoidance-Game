package com.tombloom.game;

import java.awt.*;

public class FollowEnemy extends GameObject {
    private ObjectUpdater objectUpdater;
    private GameObject target;


    public FollowEnemy(int xPos, int yPos, int size, ObjectID id, ObjectUpdater objectUpdater){
        super(xPos, yPos, size, id);
        this.objectUpdater = objectUpdater;

        for(int i = 0; i < objectUpdater.objectList.size(); i++){
            if(objectUpdater.objectList.get(i).getId() == ObjectID.Player){
                target = objectUpdater.objectList.get(i);
            }
        }
    }

    @Override
    public void tick() {
        xPos += velX;
        yPos += velY;

        float diffX = xPos - target.getxPos() - 16;
        float diffY = yPos - target.getyPos() - 16;
        float distance = (float)Math.sqrt((xPos - target.getxPos())*(xPos - target.getxPos()) + (yPos - target.getyPos()) * (yPos - target.getyPos()));

        velX = ((-1f/distance) * diffX);
        velY = ((-1f/distance) * diffY);

        if(yPos <= 0 || yPos >= Game.height - 32 - 16) velY *= -1;
        if(xPos <= 0 || xPos >= Game.width - 16) velX *= -1;

        objectUpdater.addObject(new Trail(xPos, yPos, size, ObjectID.Trail, Color.pink, 0.05f, objectUpdater));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect((int)xPos, (int)yPos, size, size);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)xPos, (int)yPos, size, size);
    }
}
