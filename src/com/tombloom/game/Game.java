package com.tombloom.game;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = WIDTH / 16 * 9;

    private Thread thread;
    private boolean isRunning = false;

    private ObjectUpdater objectUpdater;

    public Game(){
        objectUpdater = new ObjectUpdater();
        this.addKeyListener(new InputManager(objectUpdater));
        new Window(this, WIDTH, HEIGHT, "Avoidance Game");

        objectUpdater.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ObjectID.Player));
        objectUpdater.addObject(new Player(WIDTH/2 - 32 + 100, HEIGHT/2 - 32, ObjectID.Player2));

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
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
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

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }
}
