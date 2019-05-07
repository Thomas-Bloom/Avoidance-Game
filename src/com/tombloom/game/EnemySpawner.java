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

            // Spawn a fast enemy if level count is odd number
            if((GameState.level % 2) != 0){
                objectUpdater.addObject(new FastEnemy(random.nextInt(Game.width), random.nextInt(Game.height), 16, ObjectID.FastEnemy, objectUpdater));
            }

            // Every tenth level, add enemy that follows the player around
            if((GameState.level % 10) == 0){
                objectUpdater.addObject(new FollowEnemy(random.nextInt(Game.width), random.nextInt(Game.height), 16, ObjectID.FollowEnemy, objectUpdater));
            }
        }
    }
}
