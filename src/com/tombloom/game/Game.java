package com.tombloom.game;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH;
    public static final int HEIGHT;
    private static final float FPSLIMIT;

    private Thread thread;
    private boolean isRunning = false;

    private ObjectUpdater objectUpdater;
    private HUD hud;
    private GameState gameState;

    static{
        PropertiesManager propertiesManager = new PropertiesManager();

        WIDTH = propertiesManager.getWidth();
        HEIGHT = propertiesManager.getHeight();
        FPSLIMIT = propertiesManager.getFpsLimit();
    }

    public Game(){
        gameState = new GameState();
        objectUpdater = new ObjectUpdater();
        hud = new HUD();
        this.addKeyListener(new InputManager(objectUpdater));
        new Window(this, WIDTH, HEIGHT, "Avoidance Game");

        objectUpdater.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, 32, ObjectID.Player, objectUpdater));

        for(int i = 0; i < 10; i++){
            objectUpdater.addObject(new BasicEnemy(WIDTH/2 + 8, 20, 16, ObjectID.BasicEnemy));
        }
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
        double amountOfTicks = FPSLIMIT;
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
        hud.tick();
        gameState.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        objectUpdater.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max){
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
