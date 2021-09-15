package ui;

import logic.Game;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/***
 * Listener class for window/pane events
 */
public class OnCloseListener extends WindowAdapter {

    Game game;

    public OnCloseListener (Game g) {
        game = g;
    }
    @Override
    public void windowClosing(WindowEvent e) {
        game.openQuitPrompt();
    }
}
