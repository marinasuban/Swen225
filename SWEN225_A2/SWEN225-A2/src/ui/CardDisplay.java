package ui;

import cards.Card;
import cards.CharacterCard;
import cards.LocationCard;
import cards.WeaponCard;
import logic.Game;
import players.Player;

import javax.swing.*;
import java.awt.*;


public class CardDisplay extends JPanel {
    //fields
    Game game;
    Player activePlayer;
    Graphics graphics;


    //Labels and card names
    static String[] characterName = {"Lucilla", "Bert", "Malina", "Percy"};
    static String[] characterLabel = {"Lucilla         : ", "Bert             : ", "Malina         : ", "Percy           : "};
    static String[] weaponLabel = {"Broom          : ", "Scissors       : ", "Shovel          : ", "Knife            : ", "Ipad             : "};
    static String[] weaponName = {"Broom", "Scissors", "Shovel", "Knife", "Ipad"};
    static String[] locationName = {"Haunted House", "Manic Manor", "Villa Celia", "Calamity Castle", "Peril Palace"};
    static String[] locationLabel = {"Haunted House   : ", "Manic Manor       : ", "Villa Celia           : ", "Calamity Castle  : ", "Peril Palace         : "};

    //Positioning
    static final int HORIZONTAL = 30;
    static final int HORIZONTALTRUE = 150;
    static int vertical;

    /**
     * Method to create card display
     * @param g is the current game
     */
    public CardDisplay(Game g) {
        game = g;
        activePlayer = g.getActivePlayer();

    }

    /**
     * Method to display the card display on the panel
     * @param g is the graphic input
     */
    public void paintComponent(Graphics g) {
        graphics=g;
        //vertical positioning on pannel
        vertical=0;

        // paints background
        super.paintComponent(g);

        //current active player
        Player activePlayer = game.getActivePlayer();

        //Print Headers
        g.drawRect(10, 10, 10, 10);
        g.drawString(activePlayer.getCharacterName(), HORIZONTAL, vertical += 20);
        g.drawRect(130, 10, 10, 10);
        g.drawString(activePlayer.getPlayerName(), HORIZONTALTRUE, vertical);
        g.drawString("___________________________________", 10, 25);

        //Print Character
        g.drawString("/////////CHARACTERS:///////", HORIZONTAL, vertical += 20);
        paintListcharacter(graphics, activePlayer);

        //Print Weapon
        g.drawString("//////////WEAPONS:////////", HORIZONTAL, vertical += 30);
        paintListweapon(graphics, activePlayer);

        //Print Location
        g.drawString("//////////LOCATIONS:///////", HORIZONTAL, vertical += 20);
        paintListLocation(graphics, activePlayer);

        //Footers
        g.drawString("//////////PLAYER'S HAND/////", HORIZONTAL, vertical += 20);
        for (Card c : activePlayer.getHand()) {
            g.drawString(c.toString(), HORIZONTAL, vertical += 20);
        }
    }

    /**
     * Method used to print weapon cards
     */
    static public void paintListweapon(Graphics graphics, Player activePlayer) {
        for (int i = 0; i < weaponName.length; i++) {
            graphics.drawString(weaponLabel[i], HORIZONTAL, vertical += 20);
            if (activePlayer.hasCard(new WeaponCard(weaponName[i]))) {
                graphics.drawString("X", HORIZONTALTRUE, vertical);
            } else {
                graphics.drawString(" ", HORIZONTAL, vertical);
            }
        }
    }

    /**
     * Method used to print character cards
     */
    static public void paintListcharacter(Graphics graphics, Player activePlayer) {
        for (int i = 0; i < characterName.length; i++) {
            graphics.drawString(characterLabel[i], HORIZONTAL, vertical += 20);
            if (activePlayer.hasCard(new CharacterCard(characterName[i]))) {
                graphics.drawString("X", HORIZONTALTRUE, vertical);
            } else {
                graphics.drawString(" ", HORIZONTAL, vertical);
            }
        }
    }

    /**
     * Method used to print location cards
     */
    static public void paintListLocation(Graphics graphics, Player activePlayer) {
        for (int i = 0; i < locationName.length; i++) {
            graphics.drawString(locationLabel[i], HORIZONTAL, vertical += 20);
            if (activePlayer.hasCard(new LocationCard(locationName[i]))) {
                graphics.drawString("X", HORIZONTALTRUE, vertical);
            } else {
                graphics.drawString(" ", HORIZONTAL, vertical);
            }
        }
    }
}
