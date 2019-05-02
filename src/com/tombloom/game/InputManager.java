package com.tombloom.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;

public class InputManager extends KeyAdapter {
    private ObjectUpdater objectUpdater;

    // Used to stop key press delay
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public InputManager(ObjectUpdater objectUpdater){
        this.objectUpdater = objectUpdater;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for (GameObject go : objectUpdater.objectList) {
            if(go.getId().equals(ObjectID.Player)){
                // Key events for player 1
                if(key == KeyEvent.VK_W){
                    upPressed = true;
                    go.setVelY(-5);
                }
                if(key == KeyEvent.VK_S){
                    downPressed = true;
                    go.setVelY(5);
                }
                if(key == KeyEvent.VK_A){
                    leftPressed = true;
                    go.setVelX(-5);
                }
                if(key == KeyEvent.VK_D){
                    rightPressed = true;
                    go.setVelX(5);
                }
            }
        }
        if(key == KeyEvent.VK_ESCAPE) System.exit(1);

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for (GameObject go : objectUpdater.objectList) {
            if (go.getId().equals(ObjectID.Player)) {
                if(key == KeyEvent.VK_W){
                    upPressed = false;

                    if(downPressed){
                        go.setVelY(5);
                    }
                    else{
                        go.setVelY(0);
                    }
                }

                if(key == KeyEvent.VK_S){
                    downPressed = false;

                    if(upPressed){
                        go.setVelY(-5);
                    }
                    else{
                        go.setVelY(0);
                    }
                }

                if(key == KeyEvent.VK_A){
                    leftPressed = false;

                    if(rightPressed){
                        go.setVelX(5);
                    }
                    else{
                        go.setVelX(0);
                    }
                }

                if(key == KeyEvent.VK_D){
                    rightPressed = false;

                    if(leftPressed){
                        go.setVelX(-5);
                    }
                    else{
                        go.setVelX(0);
                    }
                }
            }
        }
    }
}
