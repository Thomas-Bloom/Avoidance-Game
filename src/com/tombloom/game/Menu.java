package com.tombloom.game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
    private Game game;

    public Menu(Game game){
        this.game = game;
    }

    public void render(Graphics g){
        Font titleFont = new Font("arial", Font.BOLD, 50);
        Font defaultFont = new Font("arial", Font.BOLD, 28);

        if(game.currentState == Game.STATE.Menu){
            g.setFont(titleFont);
            g.setColor(Color.white);

            g.drawString("Dodge Blocks", (Game.width / 2) - 165, 100);

            g.setFont(defaultFont);

            g.drawRect((Game.width / 2) - 100, 200, 200, 50);
            g.drawString("Play", (Game.width / 2) - 30, 235);

            g.drawRect((Game.width / 2) - 100, 300, 200, 50);
            g.drawString("Rules", (Game.width / 2) - 35, 335);

            g.drawRect((Game.width / 2) - 100, 400, 200, 50);
            g.drawString("Quit", (Game.width / 2) - 30, 435);
        }
        else if(game.currentState == Game.STATE.Help){
            g.setFont(titleFont);
            g.setColor(Color.white);

            g.drawString("Rules", (Game.width / 2) - 65, 100);

            g.setFont(defaultFont);

            g.drawString("- Avoid the enemies that bounce around the screen!", (Game.width / 2) - 300, 300);
            g.drawString("- Use WASD to control your player", (Game.width / 2) - 300, 350);
            g.drawString("- Press ESC to exit the game", (Game.width / 2) - 300, 400);


            g.drawRect((Game.width / 2) - 100, (Game.height - 100), 200, 50);
            g.drawString("Back", (Game.width / 2) - 30, Game.height - 65);
        }
        else if(game.currentState == Game.STATE.End){
            g.setFont(titleFont);
            g.setColor(Color.white);

            g.drawString("Game Over!", (Game.width / 2) - 120, 100);

            g.setFont(defaultFont);

            g.drawString("Score: " + GameState.score, (Game.width / 2) - 70, 300);

            g.drawRect((Game.width / 2) - 100, (Game.height - 150), 200, 50);
            g.drawString("Back", (Game.width / 2) - 30, Game.height - 115);
        }
    }

    public void tick(){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if(mouseOverElement(mouseX, mouseY, (Game.width / 2) - 100, 200, 200, 50) && game.currentState == Game.STATE.Menu){
            game.currentState = Game.STATE.Game;
            game.loadGame();
        }

        if(mouseOverElement(mouseX, mouseY, (Game.width / 2) - 100, 300, 200, 50)  && game.currentState == Game.STATE.Menu){
            game.currentState = Game.STATE.Help;
        }

        if(mouseOverElement(mouseX, mouseY, (Game.width / 2) - 100, (Game.height - 100), 200, 50)  && game.currentState == Game.STATE.Help){
            game.currentState = Game.STATE.Menu;
        }

        if(mouseOverElement(mouseX, mouseY, (Game.width / 2) - 100, 400, 200, 50)  && game.currentState == Game.STATE.Menu){
            System.exit(1);
        }

        if(mouseOverElement(mouseX, mouseY, (Game.width / 2) - 100, (Game.height - 150), 200, 50)  && game.currentState == Game.STATE.End){
            game.currentState = Game.STATE.Menu;
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOverElement(int mouseX, int mouseY, int x, int y, int width, int height){
        if(mouseX > x && mouseX < x + width){
            if(mouseY > y && mouseY < y + height){
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
}
