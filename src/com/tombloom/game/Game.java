package com.tombloom.game;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public enum STATE{
        Menu,
        Game,
        Help,
        End
    }

    public STATE currentState = STATE.Menu;
    public static int width;
    public static int height;
    private float fpsLmit;

    private Thread thread;
    private boolean isRunning = false;

    private ObjectUpdater objectUpdater;
    private HUD hud;
    private GameState gameState;
    private InputManager inputManager;
    private EnemySpawner enemySpawner;

    private Menu mainMenu;

    public Game(){
        gameState = new GameState();
        objectUpdater = new ObjectUpdater(this);
        inputManager = new InputManager(objectUpdater, this);
        loadProperties();
        hud = new HUD();
        enemySpawner = new EnemySpawner(objectUpdater, hud);
        mainMenu = new Menu(this);
        this.addMouseListener(mainMenu);
        this.addKeyListener(inputManager);
        new Window(this, width, height, "Dodge Blocks");

        if(currentState == STATE.Game) {
           loadGame();
        }
    }

    public void loadGame(){
        objectUpdater.addObject(new Player(width/2 - 32, height/2 - 32, 32, ObjectID.Player, objectUpdater));
        objectUpdater.addObject(new BasicEnemy(width/2 + 8, 20, 16, ObjectID.BasicEnemy, objectUpdater));
    }

    /*
    public void loadMainMenu(){
        System.out.println("size: " + objectUpdater.objectList.size());
        for(int i = 0; i < objectUpdater.objectList.size(); i++){
            objectUpdater.removeObject(objectUpdater.objectList.get(i));
        }
    }
    */

    private void loadProperties(){
        PropertiesManager propertiesManager = new PropertiesManager();

        width = propertiesManager.getWidth();
        height = propertiesManager.getHeight();
        fpsLmit = propertiesManager.getFpsLimit();

        inputManager.setUpKey(propertiesManager.getUpKey());
        inputManager.setDownKey(propertiesManager.getDownKey());
        inputManager.setLeftKey(propertiesManager.getLeftKey());
        inputManager.setRightKey(propertiesManager.getRightKey());
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            isRunning = false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = fpsLmit;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1){
                tick();
                delta--;
            }

            if(isRunning){
                render();
            }

            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        objectUpdater.tick();

        if(currentState == STATE.Game){
            hud.tick();
            gameState.tick();
            enemySpawner.tick();

            if(GameState.health <= 0){
                GameState.health = 100;
                currentState = STATE.End;
                objectUpdater.clearEnemies();
            }
        }
        else if(currentState == STATE.Menu || currentState == STATE.End){
            mainMenu.tick();
        }

    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        if(currentState == STATE.Game){
            hud.render(g);
        }
        else if(currentState == STATE.Menu || currentState == STATE.Help || currentState == STATE.End){
            mainMenu.render(g);
        }

        objectUpdater.render(g);

        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max){
        if(var >= max){
            return (var = max);
        }
        else if(var <= min){
            return (var = min);
        }
        else{
            return var;
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
