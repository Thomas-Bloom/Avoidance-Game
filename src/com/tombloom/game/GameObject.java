package com.tombloom.game;

import java.awt.*;

public abstract class GameObject {
    protected float xPos, yPos;
    protected ObjectID id;
    protected float velX, velY;
    protected int size;

    public GameObject(float xPos, float yPos, int size, ObjectID id){
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

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
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

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }
}
