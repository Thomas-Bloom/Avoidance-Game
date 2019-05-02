package com.tombloom.game;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    public Window(Game game, int width, int height, String title){
        // Create frame
        JFrame frame = new JFrame(title);

        // Set window size
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        // Set what to do when X button pressed
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // NOT resizable
        frame.setResizable(false);

        // Set position in the centre of the screen
        frame.setLocationRelativeTo(null);

        // Add game canvas to the frame
        frame.add(game);

        // Allow the window to be viewed by the user
        frame.setVisible(true);

        frame.requestFocus();

        // Start the game's thread
        game.start();
    }
}
