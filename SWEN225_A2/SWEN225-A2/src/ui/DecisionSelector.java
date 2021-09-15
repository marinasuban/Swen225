package ui;

import javax.swing.*;

import logic.Game;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DecisionSelector extends JPanel {
    //ComboBox
    static JComboBox<String> character;
    static JComboBox<String> weapon;
    static JComboBox<String> locationBox;

    //String Array
    String[] characterName = {"Lucilla", "Bert", "Malina", "Percy"};
    String[] weaponName = {"Broom", "Scissors", "Shovel", "Knife", "Ipad"};
    String[] locationName = {"Haunted House", "Manic Manor", "Villa Celia", "Calamity Castle", "Peril Palace"};

    //Display
    final int SCREENWIDTH = 500;
    final int SCREENHEIGHT = 300;
    final int TEXTBOXWIDTH = 200;
    final int TEXTBOXHEIGHT = 30;
    final int SPACING = 20;

    //Label
    static JLabel characterPrompt;
    static JLabel weaponPrompt;
    static JLabel locationPrompt;
    static JLabel locationLabel;

    //Jbutton
    private static JButton submit;

    //Jframe
    static JFrame frame = new JFrame("Make your selection");

    //Boolean
    public static boolean buttonPressed = false;

    static Game game;
    static ArrayList<String> el;
    static String condition;

    /**
     * Create decision screen
     *
     * @param game is the current game
     */
    public DecisionSelector(Game game, String condition) {
        DecisionSelector.condition=condition;
        DecisionSelector.game = game;
        el = new ArrayList<String>();

        //if a player is guessing they cannot chose location but if they are accusing they are able to select location
        if(condition.equals("GUESS")) {
            locationPrompt = new JLabel("Your current location: ");
            locationPrompt.setBounds(SPACING, SPACING * 5, TEXTBOXWIDTH, TEXTBOXHEIGHT);
            locationLabel = new JLabel(game.getBoard().getLocation(game.getActivePlayer()).getName());
            locationLabel.setBounds(TEXTBOXWIDTH + SPACING + 10, SPACING * 5, TEXTBOXWIDTH, TEXTBOXHEIGHT);
            frame.add(locationLabel);
        }
        else{
            locationPrompt = new JLabel("Select a location: ");
            locationPrompt.setBounds(SPACING, SPACING * 5, TEXTBOXWIDTH, TEXTBOXHEIGHT);
            locationBox = new JComboBox(locationName);
            locationBox.setBounds(TEXTBOXWIDTH + SPACING, SPACING * 5, TEXTBOXWIDTH, TEXTBOXHEIGHT);
            frame.add(locationBox);
        }

        //Create and set prompts
        characterPrompt = new JLabel("Select a character: ");
        characterPrompt.setBounds(SPACING, SPACING, TEXTBOXWIDTH, TEXTBOXHEIGHT);
        weaponPrompt = new JLabel("Select a weapon: ");
        weaponPrompt.setBounds(SPACING, SPACING * 3, TEXTBOXWIDTH, TEXTBOXHEIGHT);

        //Create and set combo box
        character = new JComboBox(characterName);
        character.setBounds(TEXTBOXWIDTH + SPACING, SPACING, TEXTBOXWIDTH, TEXTBOXHEIGHT);
        weapon = new JComboBox(weaponName);
        weapon.setBounds(TEXTBOXWIDTH + SPACING, SPACING * 3, TEXTBOXWIDTH, TEXTBOXHEIGHT);

        //create and set submit button
        submit = new JButton("Submit");
        submit.setBounds(100, SPACING * 10, TEXTBOXWIDTH, TEXTBOXHEIGHT);

        //add element to frame
        frame.add(characterPrompt);
        frame.add(weaponPrompt);
        frame.add(locationPrompt);
        frame.add(character);
        frame.add(weapon);
        frame.add(submit);

        //Frame setting
        frame.setSize(SCREENWIDTH, SCREENHEIGHT);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(false);

        //When submit button pressed add decision to array
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(condition.equals("GUESS")) {
                    el.add(weapon.getSelectedItem().toString());
                    el.add(character.getSelectedItem().toString());
                    el.add(game.getBoard().getLocation(game.getActivePlayer()).getName());
                    toggleMenuShow();
                    //prevents duplicate
                    frame.remove(characterPrompt);
                    frame.remove(weaponPrompt);
                    frame.remove(locationPrompt);
                    frame.remove(character);
                    frame.remove(weapon);
                    frame.remove(submit);
                    frame.remove(locationLabel);
                    buttonPressed = true; //knows when end condition of method met
                }
                else{
                    el.add(weapon.getSelectedItem().toString());
                    el.add(character.getSelectedItem().toString());
                    el.add(locationBox.getSelectedItem().toString());
                    frame.remove(characterPrompt);
                    frame.remove(weaponPrompt);
                    frame.remove(locationPrompt);
                    frame.remove(character);
                    frame.remove(weapon);
                    frame.remove(submit);
                    frame.remove(locationBox);
                    toggleMenuShow();
                    buttonPressed = true; //knows when end condition of method met
                }
            }
        });

    }

    public static void toggleMenuShow() {
        frame.setVisible(!frame.isVisible());
    }

    public static ArrayList<String> returnList() {
        return el;
    }

    public void clearList() {
        el = new ArrayList<>();
    }
    public static String returnCondition() {
        return condition;
    }
}