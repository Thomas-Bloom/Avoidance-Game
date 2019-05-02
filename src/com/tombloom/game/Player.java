package com.tombloom.game;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    public Player(int xPos, int yPos, ObjectID id){
        super(xPos, yPos, id);

    }

    @Override
    public void tick() {
        xPos += velX;
        yPos += velY;

        xPos = Game.clamp(xPos, 0, Game.WIDTH - 36);
        yPos = Game.clamp(yPos, 0, Game.HEIGHT - 64);

    }

    @Override
    public void render(Graphics g) {
        if(id.equals(ObjectID.Player))
            g.setColor(Color.cyan);

        g.fillRect(xPos, yPos, 32, 32);
    }
}
