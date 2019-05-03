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

        xPos = Game.clamp(xPos, 0, Game.WIDTH - 36);
        yPos = Game.clamp(yPos, 0, Game.HEIGHT - 64);

        collision();
    }

    @Override
    public void render(Graphics g) {
        if(id.equals(ObjectID.Player))
            g.setColor(Color.cyan);

        g.fillRect(xPos, yPos, size, size);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(xPos, yPos, size, size);
    }

    private void collision(){
        for (GameObject go : objectUpdater.objectList) {
            if(go.getId() == ObjectID.BasicEnemy){
                if(getBounds().intersects(go.getBounds())){
                    GameState.reduceHealth(2);
                }
            }
        }
    }
}
