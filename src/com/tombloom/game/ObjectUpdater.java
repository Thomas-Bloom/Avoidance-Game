package com.tombloom.game;

import java.awt.*;
import java.util.LinkedList;

public class ObjectUpdater {
    LinkedList<GameObject> objectList = new LinkedList<>();

    public void tick(){
        for(int i = 0; i < objectList.size(); i++){
            objectList.get(i).tick();
        }
    }

    public void render(Graphics g){
        for (GameObject go : objectList) {
            go.render(g);
        }
    }

    public void addObject(GameObject go){
        objectList.add(go);
    }

    public void removeObject(GameObject go){
        objectList.remove(go);
    }
}
