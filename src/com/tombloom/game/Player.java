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
    }

    @Override
    public void render(Graphics g) {
        if(id.equals(ObjectID.Player))
            g.setColor(Color.cyan);
        if(id.equals(ObjectID.Player2))
            g.setColor(Color.white);

        g.fillRect(xPos, yPos, 32, 32);
    }
}
