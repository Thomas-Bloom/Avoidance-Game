package com.tombloom.game;

import java.awt.*;

public abstract class GameObject {
    protected int xPos, yPos;
    protected ObjectID id;
    protected int velX, velY;
    protected int size;

    public GameObject(int xPos, int yPos, int size, ObjectID id){
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public void setxPos(int x){
        xPos = x;
    }

    public void setyPos(int y){
        yPos = y;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setId(ObjectID id) {
        this.id = id;
    }

    public ObjectID getId() {
        return id;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }
}
