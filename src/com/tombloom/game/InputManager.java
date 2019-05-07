package com.tombloom.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputManager extends KeyAdapter {
    private ObjectUpdater objectUpdater;
    private Game game;

    private String upKey, downKey, leftKey, rightKey;

    // Used to stop key press delay
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public InputManager(ObjectUpdater objectUpdater, Game game){
        this.objectUpdater = objectUpdater;
        this.game = game;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for (int i = 0; i < objectUpdater.objectList.size(); i++) {
            if(objectUpdater.objectList.get(i).getId().equals(ObjectID.Player)){
                // Key events for player 1
                if(key == KeyEvent.VK_W){
                    upPressed = true;
                    objectUpdater.objectList.get(i).setVelY(-5);
                }
                if(key == KeyEvent.VK_S){
                    downPressed = true;
                    objectUpdater.objectList.get(i).setVelY(5);
                }
                if(key == KeyEvent.VK_A){
                    leftPressed = true;
                    objectUpdater.objectList.get(i).setVelX(-5);
                }
                if(key == KeyEvent.VK_D){
                    rightPressed = true;
                    objectUpdater.objectList.get(i).setVelX(5);
                }
            }
        }
        if(key == KeyEvent.VK_ESCAPE){
            //game.currentState = Game.STATE.Menu;
            System.exit(1);
        }

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for (int i = 0; i < objectUpdater.objectList.size(); i++) {
            if (objectUpdater.objectList.get(i).getId().equals(ObjectID.Player)) {
                if(key == KeyEvent.VK_W){
                    upPressed = false;

                    if(downPressed){
                        objectUpdater.objectList.get(i).setVelY(5);
                    }
                    else{
                        objectUpdater.objectList.get(i).setVelY(0);
                    }
                }

                if(key == KeyEvent.VK_S){
                    downPressed = false;

                    if(upPressed){
                        objectUpdater.objectList.get(i).setVelY(-5);
                    }
                    else{
                        objectUpdater.objectList.get(i).setVelY(0);
                    }
                }

                if(key == KeyEvent.VK_A){
                    leftPressed = false;

                    if(rightPressed){
                        objectUpdater.objectList.get(i).setVelX(5);
                    }
                    else{
                        objectUpdater.objectList.get(i).setVelX(0);
                    }
                }

                if(key == KeyEvent.VK_D){
                    rightPressed = false;

                    if(leftPressed){
                        objectUpdater.objectList.get(i).setVelX(-5);
                    }
                    else{
                        objectUpdater.objectList.get(i).setVelX(0);
                    }
                }
            }
        }
    }

    public void setUpKey(String upKey) {
        this.upKey = upKey;
    }

    public void setDownKey(String downKey) {
        this.downKey = downKey;
    }

    public void setLeftKey(String leftKey) {
        this.leftKey = leftKey;
    }

    public void setRightKey(String rightKey) {
        this.rightKey = rightKey;
    }
}
