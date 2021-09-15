package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberOfPlayers extends JPanel{

    //JButton
    static JButton threePlayerButton;
    static JButton fourPlayerButton;

    //Frame label
    JLabel prompt;

    //Amount of player in game
    static int amountOfPlayers = -1;

    /**
     * Method to create screen to select amount of player playing
     */
    public NumberOfPlayers(){
        //create prompt
        prompt = new JLabel("How many players are participating?");

        //create button
        threePlayerButton = new JButton("Three");
        fourPlayerButton = new JButton("Four");

        //Add elements to frame
        this.add(prompt, BorderLayout.NORTH);
        this.add(threePlayerButton, BorderLayout.SOUTH);
        this.add(fourPlayerButton, BorderLayout.SOUTH);
    }

    /**
     * Method which produce appropriate action based on user's selection
     * @return int value of the number of players playing
     */
    public static int checkButton(){
        //if three players return 3
        threePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NumberOfPlayers.amountOfPlayers = 3;
            }
        });

        //if four players return 4
        fourPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NumberOfPlayers.amountOfPlayers = 4;
            }
        });
        return amountOfPlayers;
    }
}

