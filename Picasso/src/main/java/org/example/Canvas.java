package org.example;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import javax.swing.*;
import java.awt.*;


public class Canvas {

    private final int PADDING = 5;

    public void init() {

        org.academiadecodigo.simplegraphics.graphics.Canvas.setMaxY(700);
        org.academiadecodigo.simplegraphics.graphics.Canvas.setMaxX(700);

        Rectangle rectangle = new Rectangle(5, 5, 700, 700);
        rectangle.draw();

        for (int i = 0; i < 35; i++) {
            for (int j = 0; j < 35; j++) {
                Rectangle mini = new Rectangle(i * 20 + PADDING, j * 20 + PADDING, 20, 20);
                mini.draw();
            }
        }
    }

    public void start () {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // First, create the ColorManager
        ColorManager colorManager = new ColorManager(null); // Temporarily pass null

        // Now, create the PlayerCube and pass the ColorManager to it
        PlayerCube picasso = new PlayerCube(colorManager);

        // Update the ColorManager with the created PlayerCube
        colorManager.setPlayerCube(picasso);

        // Now call chooseColor() on the ColorManager object
        colorManager.chooseColor(); // Correct method call

        // Set up the keyboard handler
        new MyKeyBoardHandler(picasso);
    }
}