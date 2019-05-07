package com.tombloom.game;

import java.awt.*;

public class Player extends GameObject {
    private ObjectUpdater objectUpdater;


    public Player(int xPos, int yPos, int size, ObjectID id, ObjectUpdater objectUpdater){
        super(xPos, yPos, size, id);
        this.objectUpdater = objectUpdater;
    }

    @Override
    public void tick() {
        xPos += velX;
        yPos += velY;

        xPos = Game.clamp(xPos, 0, Game.width - 36);
        yPos = Game.clamp(yPos, 0, Game.height - 64);

        collision();

        objectUpdater.addObject(new Trail(xPos, yPos, size, ObjectID.Trail, Color.cyan, 0.1f, objectUpdater));
    }

    @Override
    public void render(Graphics g) {
        if(id.equals(ObjectID.Player))
            g.setColor(Color.cyan);

        g.fillRect((int)xPos, (int)yPos, size, size);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)xPos, (int)yPos, size, size);
    }

    private void collision(){
        for (GameObject go : objectUpdater.objectList) {
            if(go.getId() == ObjectID.BasicEnemy || go.getId() == ObjectID.FastEnemy || go.getId() == ObjectID.FollowEnemy){
                if(getBounds().intersects(go.getBounds())){
                    GameState.reduceHealth(3);
                    //go.velX *= -1;
                    //go.velY *= -1;
                }
            }
        }
    }
}
