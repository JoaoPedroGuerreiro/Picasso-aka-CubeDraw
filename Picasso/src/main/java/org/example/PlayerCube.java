package org.example;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.HashMap;
import java.util.HashSet;

public class PlayerCube {

    private Rectangle cubeSprite;
    private Rectangle positionIndicator;
    private final Color indicatorColor = Color.WHITE;

    private HashMap<Rectangle, Color> colorMap = new HashMap<>();
    private HashSet<Rectangle> painted = new HashSet<>();

    private ColorManager colorManager;

    public PlayerCube(ColorManager colorManager) {
        this.colorManager = colorManager;

        cubeSprite = new Rectangle(5, 5, 20, 20);
        cubeSprite.draw();

        colorMap.put(cubeSprite, colorManager.getDefaultColor());
        cubeSprite.setColor(colorManager.getDefaultColor());
        cubeSprite.fill();

        positionIndicator = new Rectangle(cubeSprite.getX(), cubeSprite.getY(), 20, 20);
        positionIndicator.setColor(indicatorColor);
        positionIndicator.draw();

    }

    // Method to immediately update the sprite color when the color is changed
    public void updateSpriteColor(Color newColor) {
        cubeSprite.setColor(newColor);  // Update the sprite color
        cubeSprite.fill();              // Fill with the new color immediately
    }

    // Movement methods (no change here)
    public void moveUp() {
        if (cubeSprite.getY() - 20 >= 5) {
            cubeSprite.translate(0, -20);
            updateIndicator();
        }
    }

    public void moveDown() {
        if (cubeSprite.getY() + 20 < 700) {
            cubeSprite.translate(0, 20);
            updateIndicator();
        }
    }

    public void moveRight() {
        if (cubeSprite.getX() + 20 < 700) {
            cubeSprite.translate(20, 0);
            updateIndicator();
        }
    }

    public void moveLeft() {
        if (cubeSprite.getX() - 20 >= 5) {
            cubeSprite.translate(-20, 0);
            updateIndicator();
        }
    }

    private void updateIndicator() {
        positionIndicator.delete();
        positionIndicator = new Rectangle(cubeSprite.getX(), cubeSprite.getY(), 20, 20);
        positionIndicator.setColor(indicatorColor);
        positionIndicator.draw();
    }

    public void draw() {
        Rectangle newRectangle = new Rectangle(cubeSprite.getX(), cubeSprite.getY(), 20, 20);

        // Check if there's already a painted tile at this position
        boolean tileExists = false;
        for (Rectangle existing : painted) {
            if (existing.getX() == newRectangle.getX() && existing.getY() == newRectangle.getY()) {
                tileExists = true;
                // Remove the old painted tile
                existing.delete();
                painted.remove(existing);
                colorMap.remove(existing);
                break;
            }
        }

        // Paint the tile with the current selected color
        newRectangle.setColor(colorManager.getDefaultColor()); // Use the current selected color
        newRectangle.fill(); // Paint the tile with the selected color

        // Add the new tile to the list of painted tiles
        painted.add(newRectangle);
        colorMap.put(newRectangle, colorManager.getDefaultColor());

        // Draw the tile first (painted tile under the sprite)
        if (!tileExists) {
            newRectangle.setColor(colorManager.getDefaultColor());
            newRectangle.fill(); // Tile gets painted
        }

        // **Redraw the sprite over the painted tile** with its correct color
        cubeSprite.setColor(colorManager.getDefaultColor());
        cubeSprite.fill(); // Ensure the sprite retains the updated color on top of the tile

        // Draw the position indicator (if needed)
        positionIndicator.draw();
    }

    public void delete() {
        // Delete the rectangle at the current position
        for (Rectangle current : painted) {
            if (cubeSprite.getX() == current.getX() && cubeSprite.getY() == current.getY()) {
                current.delete();
                painted.remove(current);
                break;
            }
        }
        positionIndicator.delete();
    }

    public void reset() {
        // Reset the canvas by deleting all painted tiles and the position indicator
        for (Rectangle rect : painted) {
            rect.delete();
        }
        painted.clear();
        colorMap.clear();

        positionIndicator.delete();
        positionIndicator = new Rectangle(cubeSprite.getX(), cubeSprite.getY(), 20, 20);
        positionIndicator.setColor(indicatorColor);
        positionIndicator.draw();

        System.out.println("Canvas has been reset!");
    }

    public HashSet<Rectangle> getPainted() {
        return painted;
    }

    public HashMap<Rectangle, Color> getColorMap() {
        return colorMap;
    }

    public void hoverOverRectangle(Rectangle rect) {

        rect.setColor(Color.WHITE);
        rect.fill();

        if (colorMap.containsKey(rect)) {
            rect.setColor(colorManager.getDefaultColor());
            rect.fill();
        }
    }
}
