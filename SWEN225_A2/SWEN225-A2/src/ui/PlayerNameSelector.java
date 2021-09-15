package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class PlayerNameSelector extends JPanel {
    //Boolean used to determine if button has been pressed by user
    public static boolean buttonPressed = false;
    private static boolean player1Selected = false;
    private static boolean player2Selected = false;
    private static boolean player3Selected = false;
    private static boolean player4Selected = false;

    //Start and confirmation button
    private static JButton startGame;
    private static JButton confirmPlayer1;
    private static JButton confirmPlayer2;
    private static JButton confirmPlayer3;
    private static JButton confirmPlayer4;

    //Display
    final int SCREENWIDTH = 400;
    final int SCREENHEIGHT = 500;
    final int TEXTBOXWIDTH = 200;
    final int TEXTBOXHEIGHT = 30;
    final int JRADIOWIDTH = 80;
    private int radioGroupHeight = 20;
    final int STARTINGGROUPHEIGHT = 20;
    final int SPACING = 20;

    //Text field logic
    private static JTextField textField1;
    private static JTextField textField2;
    private static JTextField textField3;
    private static JTextField textField4;

    //Radio button
    private JRadioButton P1Lucilla;
    private JRadioButton P1Bert;
    private JRadioButton P1Malina;
    private JRadioButton P1Percy;
    private JRadioButton P2Lucilla;
    private JRadioButton P2Bert;
    private JRadioButton P2Malina;
    private JRadioButton P2Percy;
    private JRadioButton P3Lucilla;
    private JRadioButton P3Bert;
    private JRadioButton P3Malina;
    private JRadioButton P3Percy;
    private JRadioButton P4Lucilla;
    private JRadioButton P4Bert;
    private JRadioButton P4Malina;
    private JRadioButton P4Percy;

    //Button groups
    private static ButtonGroup buttonGroup1 = new ButtonGroup();
    private static ButtonGroup buttonGroup2 = new ButtonGroup();
    private static ButtonGroup buttonGroup3 = new ButtonGroup();
    private static ButtonGroup buttonGroup4 = new ButtonGroup();

    //Labels and Text
    private static ArrayList<JButton> confirmationLabels = new ArrayList<>();
    private ArrayList<JRadioButton> jradioLabelsP1 = new ArrayList<>();
    private ArrayList<JRadioButton> jradioLabelsP2 = new ArrayList<>();
    private ArrayList<JRadioButton> jradioLabelsP3 = new ArrayList<>();
    private ArrayList<JRadioButton> jradioLabelsP4 = new ArrayList<>();
    private static ArrayList<JTextField> textFieldLabels = new ArrayList<>();
    private String[] characterNames = {"Lucilla", "Bert", "Malina", "Percy"};
    private String[] playerTextbox = {"Player1", "Player2", "Player3", "Player4"};
    private String[] confirmTextbox = {"Confirm player1", "Confirm player2", "Confirm player3", "Confirm player4"};

    //Jframe
    static JFrame frame;

    /**
     * Method to create Player and Character name selector
     */
    public PlayerNameSelector() {
        //Initiates frame
        frame = new JFrame("Please enter player names");

        //Initiate arrays
        confirmationLabels.addAll(Arrays.asList(confirmPlayer1, confirmPlayer2, confirmPlayer3, confirmPlayer4));
        jradioLabelsP1.addAll(Arrays.asList(P1Lucilla, P1Bert, P1Malina, P1Percy));
        jradioLabelsP2.addAll(Arrays.asList(P2Lucilla, P2Bert, P2Malina, P2Percy));
        jradioLabelsP3.addAll(Arrays.asList(P3Lucilla, P3Bert, P3Malina, P3Percy));
        jradioLabelsP4.addAll(Arrays.asList(P4Lucilla, P4Bert, P4Malina, P4Percy));
        textFieldLabels.addAll(Arrays.asList(textField1, textField2, textField3, textField4));

        //Create and add button to frame
        startGame = new JButton("Start Game");
        startGame.setBounds(100, 420, TEXTBOXWIDTH, TEXTBOXHEIGHT);
        frame.add(startGame);
        createAndAddJButton(confirmTextbox);
        createAndAddJTextField(playerTextbox);
        createAndAddJRadioButton1(characterNames);
        createAndAddJRadioButton2(characterNames);
        createAndAddJRadioButton3(characterNames);
        createAndAddJRadioButton4(characterNames);

        //Allow only player 1 character selection to be available
        assignActiveButton(buttonGroup1);
        toggleButtonVisibility(buttonGroup2);
        toggleButtonVisibility(buttonGroup3);
        toggleButtonVisibility(buttonGroup4);
        confirmationLabels.get(1).setEnabled(false);
        confirmationLabels.get(2).setEnabled(false);
        confirmationLabels.get(3).setEnabled(false);
        textFieldLabels.get(1).setEnabled(false);
        textFieldLabels.get(2).setEnabled(false);
        textFieldLabels.get(3).setEnabled(false);
        startGame.setEnabled(false);

        //Frame setting
        frame.setSize(SCREENWIDTH, SCREENHEIGHT);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    /**
     * Method to check action performed on Player and Character name selector window and perform corresponding action
     * @param playerCharacterList is a map of player name and character names
     * @param numberOfPlayers is the number of player participating withing the game
     */
    public static void checkConfirmation(Map<String, String> playerCharacterList, int numberOfPlayers) {
        //action for when player 1 confirm button is selected
        confirmationLabels.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!player1Selected) {
                    confirmationAction(playerCharacterList, textFieldLabels.get(0), buttonGroup1, confirmationLabels.get(0), textFieldLabels.get(1), buttonGroup2, confirmationLabels.get(1));
                    player1Selected = true;
                }

            }
        });
        //action for when player 2 confirm button is selected
        confirmationLabels.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!player2Selected) {
                    confirmationAction(playerCharacterList, textFieldLabels.get(1), buttonGroup2, confirmationLabels.get(1), textFieldLabels.get(2), buttonGroup3, confirmationLabels.get(2));
                    player2Selected = true;
                }

            }
        });

        //action for when player 3 confirm button is selected
        confirmationLabels.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!player3Selected) {
                    String key3 = textFieldLabels.get(2).getText(); //Get player 3 name
                    String value3 = getSelectedButtonText(buttonGroup3); //Get player 3 character
                    playerCharacterList.put(key3, value3); //Add name and character to Map
                    textFieldLabels.get(2).setEnabled(false); //Turn player 3 text field off
                    confirmationLabels.get(2).setEnabled(false); //Turn player 3 confirm button off
                    turnButtonOff(buttonGroup3); //Turn player 3 radio button off

                    //if three people are playing allow enable start button otherwise allow access to player 4 information entry
                    if (numberOfPlayers == 3) {
                        startGame.setEnabled(true);

                    } else {
                        confirmationLabels.get(3).setEnabled(true); //turn Player 4 confirm button on
                        textFieldLabels.get(3).setEnabled(true); //turn Player 4 text field on

                    }

                    toggleButtonVisibility(buttonGroup4); //Turn player 4 radio button on
                    removeSelectedCharacter(buttonGroup4, playerCharacterList); //Remove selected character from radio group
                    assignActiveButton(buttonGroup4);//Assign active character from unselected radio button
                    player3Selected = true;

                }
            }
        });

        //If we have 3 players create a motion listener for start game button, otherwise create a motion listener for start game button and player 4 entry
        if (numberOfPlayers == 3) {
            startGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String key4 = textFieldLabels.get(3).getText(); //Get player 4 name
                    String value4 = getSelectedButtonText(buttonGroup4); //Get player 4 character
                    playerCharacterList.put(key4, value4); //Add name and character to Map
                    PlayerNameSelector.frame.setVisible(false); //close window
                    buttonPressed = true; //all entry is completed
                }
            });
        }
        //action for when player 4 confirm button is selected
        else {
            confirmationLabels.get(3).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!player4Selected) {
                        String key4 = textFieldLabels.get(3).getText(); //Get player 4 name
                        String value4 = getSelectedButtonText(buttonGroup4); //Get player 4 character
                        playerCharacterList.put(key4, value4); //Add name and character to Map
                        textFieldLabels.get(3).setEnabled(false); //turn Player text box off
                        confirmationLabels.get(3).setEnabled(false);//turn Player 4 confirm button off
                        startGame.setEnabled(true);//enable start game button
                        player4Selected = true;//player 4 entry completed
                    }
                }
            });

            startGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PlayerNameSelector.frame.setVisible(false);//close window
                    buttonPressed = true; //all entry is completed

                }
            });
        }
    }

    /**
     * Method to get the chracter selection made by player
     * @param buttonGroup group of radio buttons
     * @return String of the selected radio button
     */
    private static String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    /**
     * Method which toggles the visbility of a radio button group
     * @param buttonGroup group of radio buttons
     */
    private static void toggleButtonVisibility(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            boolean active = button.isEnabled();
            button.setEnabled(!active);
        }
    }

    /**
     * Method to turn a group of buttons off
     * @param buttonGroup group of radio buttons
     */
    private static void turnButtonOff(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            button.setEnabled(false);
        }
    }

    /**
     * Method to disable characters already selected by previous player
     * @param buttonGroup group of radio buttons
     * @param selected Map of player name and character selection already made by previous player
     */
    private static void removeSelectedCharacter(ButtonGroup buttonGroup, Map<String, String> selected) {

        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            for (String s : selected.values()) {
                if ((button.getText()).contains(s)) {

                    button.setEnabled(false);
                }
            }
        }
    }

    /**
     * Method which assigns selection to a accessible radio button
     * @param buttonGroup group of radio buttons
     */
    private static void assignActiveButton(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isEnabled()) {
                button.setSelected(true);
                break;
            }
        }
    }

    /**
     * Method used to iterate through textField list to create and set the textfields
     * @param text is the string display associated with a textField
     */
    private void createAndAddJTextField(String[] text) {
        int startHeight = STARTINGGROUPHEIGHT;
        for (int i = 0; i < textFieldLabels.size(); i++) {
            setJtext(text[i], startHeight, i);
            startHeight = startHeight + 100;
        }
    }

    /**
     * Method used to iterate through JRadio list to create and set the JRadio buttons and add it to a buttonGroup
     * @param text is the string display associated with the JRadio buttons
     */
    private void createAndAddJRadioButton1(String[] text) {
        for (int i = 0; i < jradioLabelsP1.size(); i++) {
            setRadio(text[i], radioGroupHeight, i, buttonGroup1);
        }
        radioGroupHeight = radioGroupHeight + 100;
    }

    /**
     * Method used to iterate through JRadio list to create and set the JRadio buttons and add it to a buttonGroup
     * @param text is the string display associated with the JRadio buttons
     */
    private void createAndAddJRadioButton2(String[] text) {
        for (int i = 0; i < jradioLabelsP2.size(); i++) {
            setRadio(text[i], radioGroupHeight, i, buttonGroup2);
        }
        radioGroupHeight = radioGroupHeight + 100;
    }

    /**
     * Method used to iterate through JRadio list to create and set the JRadio buttons and add it to a buttonGroup
     * @param text is the string display associated with the JRadio buttons
     */
    private void createAndAddJRadioButton3(String[] text) {
        for (int i = 0; i < jradioLabelsP3.size(); i++) {
            setRadio(text[i], radioGroupHeight, i, buttonGroup3);
        }
        radioGroupHeight = radioGroupHeight + 100;
    }

    /**
     * Method used to iterate through JRadio list to create and set the JRadio buttons and add it to a buttonGroup
     * @param text is the string display associated with the JRadio buttons
     */
    private void createAndAddJRadioButton4(String[] text) {
        for (int i = 0; i < jradioLabelsP4.size(); i++) {
            setRadio(text[i], radioGroupHeight, i, buttonGroup4);
        }
    }

    /**
     * Method used to iterate through JButton list to create and set the JButton
     * @param text is the string display associated with the JButton
     */
    private void createAndAddJButton(String[] text) {
        int startHeight = STARTINGGROUPHEIGHT;
        for (int i = 0; i < confirmationLabels.size(); i++) {
            setButton(text[i], startHeight, i);
            startHeight = startHeight + 100;
        }
    }

    /**
     * Method used to initialise Jbuttons, set its bounds and add it to the frame
     * @param text is the string display associated with the JButton
     * @param h is the height placement of the button
     * @param i is the value of the iterator
     */
    private void setButton(String text, int h, int i) {
        JButton b = new JButton(text);
        b.setBounds(TEXTBOXWIDTH + SPACING, h, TEXTBOXWIDTH - SPACING, TEXTBOXHEIGHT);
        confirmationLabels.set(i, b);
        frame.add(b);
    }

    /**
     * Method used to initialise Jtext, set its bounds and add it to the frame
     * @param text is the string display associated with the jtext
     * @param h is the height placement of the button
     * @param i is the value of the iterator
     */
    private void setJtext(String text, int h, int i) {
        JTextField t = new JTextField(text);
        t.setBounds(0, h, TEXTBOXWIDTH, TEXTBOXHEIGHT);
        textFieldLabels.set(i, t);
        frame.add(t);
    }

    /**
     *  Method used to initialise JRadioButton, set its bounds and add it to the frame
     * @param text is the string display associated with the JButton
     * @param h is the height placement of the button
     * @param i is the value of the iterator
     * @param group is the group the button will be added to
     */
    private void setRadio(String text, int h, int i, ButtonGroup group) {
        JRadioButton r = new JRadioButton(text);
        r.setBounds(((JRADIOWIDTH + SPACING) * i), h + TEXTBOXHEIGHT, JRADIOWIDTH, TEXTBOXHEIGHT);
        group.add(r);
        frame.add(r);
    }

    /**
     * Method which toggles the necessary buttons when the confirmation button is pressed
     * @param playerCharacterList is the map of selected player and character names
     * @param text1 is the text field belonging to the confirmation button pressed
     * @param group1 is the group of radio buttons belonging to the confirmation button pressed
     * @param confirm1 is the confirmation button pressed
     * @param text2 is the text field belonging to the next player entry
     * @param group2 is the group of radio buttons belonging to the next player entry
     * @param confirm2 is the confirmation button belonging to the next player entry
     */
    public static void confirmationAction(Map<String, String> playerCharacterList, JTextField text1, ButtonGroup group1, JButton confirm1, JTextField text2, ButtonGroup group2, JButton confirm2) {
        String key = text1.getText();
        String value = getSelectedButtonText(group1);
        playerCharacterList.put(key, value); //add player and selected character to Map
        text1.setEnabled(false); //turn player1 text box off
        confirm1.setEnabled(false); //turn player1 confirmation button off
        turnButtonOff(group1); //turn radio button off
        text2.setEnabled(true); //turn player2 text box on
        confirm2.setEnabled(true); //turn player2 confirmation button on
        toggleButtonVisibility(group2); //turn radio button on
        removeSelectedCharacter(group2, playerCharacterList); //turn off radio button for characters selected previously
        assignActiveButton(group2); //randomly select avaliable radio button
    }
}