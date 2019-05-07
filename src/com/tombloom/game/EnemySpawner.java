package com.tombloom.game;

import java.util.Random;

public class EnemySpawner {
    private ObjectUpdater objectUpdater;
    private HUD hud;
    private Random random = new Random();

    private int scoreKeep = 0;

    public EnemySpawner(ObjectUpdater objectUpdater, HUD hud){
        this.objectUpdater = objectUpdater;
        this.hud = hud;
    }

    public void tick(){
        scoreKeep++;

        if(scoreKeep >= 500){
            scoreKeep = 0;
            GameState.level++;

            objectUpdater.addObject(new BasicEnemy(random.nextInt(Game.width), random.nextInt(Game.height), 16, ObjectID.BasicEnemy, objectUpdater));
        }
    }
}
