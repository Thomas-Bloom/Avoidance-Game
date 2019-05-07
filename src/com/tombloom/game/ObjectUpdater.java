package com.tombloom.game;

import java.awt.*;
import java.util.LinkedList;

public class ObjectUpdater {
    private Game game;
    LinkedList<GameObject> objectList = new LinkedList<>();

    public ObjectUpdater(Game game){
        this.game = game;
    }

    public void tick(){
        for(int i = 0; i < objectList.size(); i++){
            objectList.get(i).tick();
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < objectList.size(); i++){
            objectList.get(i).render(g);
        }
    }

    public void addObject(GameObject go){
        objectList.add(go);
    }

    public void removeObject(GameObject go){
        objectList.remove(go);
    }

    public void clearEnemies(){
        for(int i = 0; i < objectList.size(); i++){
            GameObject temp = objectList.get(i);

            if(temp.getId() == ObjectID.Player){
                objectList.clear();
                if(game.currentState != Game.STATE.End){
                    addObject(new Player((int)temp.getxPos(), (int)temp.getyPos(), 32, ObjectID.Player, this));
                }
            }
        }
    }

    public void clearAllGameObjects(){
        objectList.clear();
    }
}
