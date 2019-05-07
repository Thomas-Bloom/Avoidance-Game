package com.tombloom.game;

import java.awt.*;

public class Trail extends GameObject{
    private ObjectUpdater objectUpdater;
    private Color color;
    private float lifeTime;

    private float alpha = 1;

    public Trail(float xPos, float yPos, int size, ObjectID id, Color color, float lifeTime, ObjectUpdater objectUpdater) {
        super(xPos, yPos, size, id);
        this.objectUpdater = objectUpdater;
        this.color = color;
        this.lifeTime = lifeTime;
    }

    @Override
    public void tick() {
        if(alpha > lifeTime){
            alpha -= (lifeTime - 0.0001f);
        }
        else{
            objectUpdater.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setComposite(makeTransparent(alpha));
        g2d.setColor(color);
        g.fillRect((int)xPos, (int)yPos, size, size);
        g2d.setComposite(makeTransparent(1f));
    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
