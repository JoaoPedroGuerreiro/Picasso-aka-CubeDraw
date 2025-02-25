package org.example;

import org.academiadecodigo.simplegraphics.graphics.Color;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ColorManager {

    private Color defaultColor = Color.BLACK;
    private PlayerCube playerCube;

    public ColorManager(PlayerCube playerCube) {
        this.playerCube = playerCube;
    }

    public Color getDefaultColor() {
        return defaultColor;
    }

    public void setPlayerCube(PlayerCube playerCube) {
        this.playerCube = playerCube;
    }

    // Method to allow color selection
    public void chooseColor() {
        // Create a new JFrame for the color selection panel
        JFrame frame = new JFrame("Choose a Color");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 3, 10, 10));
        HashMap<JButton, Color> buttons = new HashMap<>();

        // Adding color buttons
        addColorButton(buttonPanel, buttons, "RED", Color.RED);
        addColorButton(buttonPanel, buttons, "GREEN", Color.GREEN);
        addColorButton(buttonPanel, buttons, "BLUE", Color.BLUE);
        addColorButton(buttonPanel, buttons, "YELLOW", Color.YELLOW);
        addColorButton(buttonPanel, buttons, "ORANGE", Color.ORANGE);
        addColorButton(buttonPanel, buttons, "CYAN", Color.CYAN);
        addColorButton(buttonPanel, buttons, "MAGENTA", Color.MAGENTA);
        addColorButton(buttonPanel, buttons, "PINK", Color.PINK);
        addColorButton(buttonPanel, buttons, "BLACK", Color.BLACK);
        addColorButton(buttonPanel, buttons, "GRAY", Color.GRAY);

        // Instructions label (use BoxLayout to stack components vertically)
        JPanel instructionsPanel = new JPanel();
        instructionsPanel.setLayout(new BoxLayout(instructionsPanel, BoxLayout.Y_AXIS));  // Vertical stack layout

        JLabel instructions = new JLabel("<html>Instructions: <br>" +
                "<br> 1. To paint use the space bar. " +
                "<br>2. To erase a cube use the key 'E'. " +
                "<br>3. To reset/clear the frame use the key 'R'. " +
                "<br>4. To save the file use the key 'S' and to load the key 'L'. <html>");
        instructions.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel chosecolor = new JLabel("<html><center>Choose a color to paint with: <center><html>", SwingConstants.CENTER);
        chosecolor.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));
        chosecolor.setFont(new Font("Arial", Font.BOLD, 20));

        instructionsPanel.add(instructions);
        instructionsPanel.add(Box.createVerticalStrut(20));  // Adds 10px of space
        instructionsPanel.add(chosecolor);

        // Add the instructions panel and the button panel to the frame
        frame.getContentPane().add(instructionsPanel, BorderLayout.NORTH);
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);

        // Set frame properties and make it visible
        SwingUtilities.invokeLater(() -> {
            frame.setSize(600, 600);  // Set the size of the frame
            frame.setLocationRelativeTo(null);  // Center the frame on the screen
            frame.setVisible(true);  // Make the frame visible
        });
    }

    // Helper method to add color buttons
    private void addColorButton(JPanel panel, HashMap<JButton, Color> map, String name, Color color) {
        // Create a new button for each color
        JButton button = new JButton(name);
        button.setBackground(new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue()));
        button.setOpaque(true);
        button.setBorderPainted(false);

        // Set button action listener to change the default color and update the cubeSprite color immediately
        button.addActionListener(e -> {
            defaultColor = color;
            System.out.println("Color changed to " + defaultColor);

            // Immediately update the PlayerCube's sprite color
            if (playerCube != null) {
                playerCube.updateSpriteColor(defaultColor);
            }
        });

        // Add the button to the panel and the map
        panel.add(button);
        map.put(button, color);
    }
}

