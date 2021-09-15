package ui;

import logic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionDisplay extends JPanel {

    JButton accuseButton;
    JButton guessButton;
    JButton moveButton;
    JButton endTurnButton;
    Game game;
    boolean allowGuess = true;

    public ActionDisplay(Game g) {
        game = g;

        moveButton = new JButton();
        moveButton.setText("Move");

        guessButton = new JButton();
        guessButton.setText("Guess");

        accuseButton = new JButton();
        accuseButton.setText("Accuse");

        endTurnButton = new JButton();
        endTurnButton.setText("End Turn");

        this.add(accuseButton);
        this.add(guessButton);
        this.add(moveButton);
        this.add(endTurnButton);

        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveButton.setEnabled(false);
                game.makeMove();
            }
        });

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.playerMakeGuess();
            }
        });

        accuseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.playerMakeAccusation();
            }
        });

        endTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.endTurn();
            }
        });

    }


    //This method updates so the buttons can be used at the correct times
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(game.getMovesLeft() == -1) g.drawString("Press move button to move!", 10, 30);
        else if(game.getMovesLeft() == 0) g.drawString("No moves left :(", 10, 30);
        else {
            g.drawString(game.getMovesLeftStringFormated().get(0), 10, 20);
            g.drawString(game.getMovesLeftStringFormated().get(1), 10, 40);
        }

        //If the player is in a room then allow them to guess
        guessButton.setEnabled((game.getBoard().getLocation(game.getActivePlayer()) != null && allowGuess));
        accuseButton.setEnabled(allowGuess);

        if(game.getMovesLeft() == -1) {
            moveButton.setEnabled(true);
        }

        if(game.hasGameEnded()) {
            moveButton.setEnabled(false);
            guessButton.setEnabled(false);
            accuseButton.setEnabled(false);
            endTurnButton.setEnabled(false);
        }
    }

    public void setGuessAllowed(boolean el) {
        allowGuess = el;
    }
}
