package org.example;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

public class FileManager {

    public void save(HashSet<Rectangle> painted, HashMap<Rectangle, Color> colorMap) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File");

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(fileToSave, false))) {
                for (Rectangle cube : painted) {
                    Color color = colorMap.getOrDefault(cube, Color.BLACK); // Fetch the color from colorMap
                    bWriter.write(cube.getX() + "," + cube.getY() + "," + cube.getWidth() + "," + cube.getHeight() + "," + getColorName(color));
                    bWriter.newLine();
                }

                JOptionPane.showMessageDialog(null, "File saved successfully!", "Save Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void load(PlayerCube playerCube) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open file");

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToLoad))) {
                playerCube.reset();

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] parts = line.split(",");
                    Rectangle rect = new Rectangle(
                            Integer.parseInt(parts[0]),  // X
                            Integer.parseInt(parts[1]),  // Y
                            Integer.parseInt(parts[2]),  // Width
                            Integer.parseInt(parts[3])   // Height
                    );
                    Color color = getColorFromName(parts[4]); // Get the color using getColorFromName
                    rect.setColor(color);  // Set the color of the rectangle
                    rect.fill();

                    playerCube.getPainted().add(rect);
                    playerCube.getColorMap().put(rect, color);  // Store the color in colorMap
                }

                JOptionPane.showMessageDialog(null, "File loaded successfully!", "Load Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error loading file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Converts a Color object to a String name
    private String getColorName(Color color) {
        if (color == Color.RED) return "RED";
        if (color == Color.GREEN) return "GREEN";
        if (color == Color.BLUE) return "BLUE";
        if (color == Color.YELLOW) return "YELLOW";
        if (color == Color.ORANGE) return "ORANGE";
        if (color == Color.CYAN) return "CYAN";
        if (color == Color.MAGENTA) return "MAGENTA";
        if (color == Color.PINK) return "PINK";
        if (color == Color.BLACK) return "BLACK";
        if (color == Color.GRAY) return "GRAY";
        return "BLACK";  // Default color
    }

    // Converts a color name string to a Color object
    private Color getColorFromName(String colorName) {
        switch (colorName.toUpperCase()) {
            case "RED":
                return Color.RED;
            case "GREEN":
                return Color.GREEN;
            case "BLUE":
                return Color.BLUE;
            case "YELLOW":
                return Color.YELLOW;
            case "ORANGE":
                return Color.ORANGE;
            case "CYAN":
                return Color.CYAN;
            case "MAGENTA":
                return Color.MAGENTA;
            case "PINK":
                return Color.PINK;
            case "BLACK":
                return Color.BLACK;
            case "GRAY":
                return Color.GRAY;
            default:
                return Color.BLACK; // Default color
        }
    }
}
