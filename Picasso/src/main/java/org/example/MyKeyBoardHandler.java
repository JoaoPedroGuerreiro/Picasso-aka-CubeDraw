package org.example;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;

public class MyKeyBoardHandler implements KeyboardHandler {

    private final Keyboard keyboard;
    private final PlayerCube picasso;

    public MyKeyBoardHandler(PlayerCube picasso) {
        this.keyboard = new Keyboard(this);
        this.picasso = picasso;
        setKeyboardEvents();
    }

    private void setKeyboardEvents() {
        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(left);
        System.out.println("Left key registered");

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(right);
        System.out.println("Right key registered");

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(up);
        System.out.println("Up key registered");

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(down);
        System.out.println("Down key registered");

        KeyboardEvent paint = new KeyboardEvent();
        paint.setKey(KeyboardEvent.KEY_SPACE);
        paint.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(paint);
        System.out.println("Space key registered");

        KeyboardEvent erase = new KeyboardEvent();
        erase.setKey(KeyboardEvent.KEY_E);
        erase.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(erase);
        System.out.println("E key registered");

        KeyboardEvent reset = new KeyboardEvent();
        reset.setKey(KeyboardEvent.KEY_R);
        reset.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(reset);
        System.out.println("R key registered");

        KeyboardEvent load = new KeyboardEvent();
        load.setKey(KeyboardEvent.KEY_L);
        load.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(load);
        System.out.println("L key registered");

        KeyboardEvent save = new KeyboardEvent();
        save.setKey(KeyboardEvent.KEY_S);
        save.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(save);
        System.out.println("S key registered");

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        System.out.println("Key pressed " + keyboardEvent.getKey());

        FileManager fileManager = new FileManager();

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT) {
            picasso.moveLeft();
            System.out.println("Key Left was pressed.");
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) {
            picasso.moveRight();
            System.out.println("Key Right was pressed.");
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_UP) {
            picasso.moveUp();
            System.out.println("Key Up was pressed.");
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN) {
            picasso.moveDown();
            System.out.println("Key Down was pressed.");
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            picasso.draw();
            System.out.println("Key 'SPACE' was pressed.");
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_R) {
            picasso.reset();
            System.out.println("Key 'R' was pressed.");
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_E) {
            picasso.delete();
            System.out.println("Key 'E' was pressed.");
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_L) {
            fileManager.load(picasso);
            System.out.println("Key 'L' was pressed.");
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_S) {
            fileManager.save(picasso.getPainted(), picasso.getColorMap());
            System.out.println("Key 'S' was pressed.");
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }
}