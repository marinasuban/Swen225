package ui;

import logic.Game;
import util.Direction;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameUI extends JFrame {

    private final JPanel MainPanel;                   //Main display panel
    private final BoardDisplay boardDisplay;          //Board graphics
    private final CardDisplay cardDisplay;            //Shows all recorded cards
    private final ActionDisplay actionDisplay;
    private final Game game;

    public GameUI(Game g) {

        game = g;

        //Set up main display panel
        MainPanel = new JPanel();
        MainPanel.setLayout(new BorderLayout(0, 0));
        MainPanel.setName("Murder Madness");

        //Set up board display
        boardDisplay = new BoardDisplay(g);
        boardDisplay.setMinimumSize(new Dimension(482, 482));
        boardDisplay.setMaximumSize(new Dimension(482, 482));
        boardDisplay.setPreferredSize(new Dimension(482, 482));

        //Initialise the move actions
        Action up = new UpAction();
        Action down = new DownAction();
        Action left = new LeftAction();
        Action right = new RightAction();

        //Set the binding for W and up key
        boardDisplay.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "UpAction");
        boardDisplay.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UpAction");
        boardDisplay.getActionMap().put("UpAction", up);

        //Set the binding for A and left key
        boardDisplay.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "LeftAction");
        boardDisplay.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LeftAction");
        boardDisplay.getActionMap().put("LeftAction", left);

        //Set the binding for S and down key
        boardDisplay.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "DownAction");
        boardDisplay.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DownAction");
        boardDisplay.getActionMap().put("DownAction", down);

        //Set the binding for D and right key
        boardDisplay.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "RightAction");
        boardDisplay.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RightAction");
        boardDisplay.getActionMap().put("RightAction", right);

        //Set up card display
        cardDisplay = new CardDisplay(g);
        cardDisplay.setMinimumSize(new Dimension(260, 482));
        cardDisplay.setPreferredSize(new Dimension(260, 482));
        cardDisplay.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        //Set up action display
        actionDisplay = new ActionDisplay(g);
        actionDisplay.setMinimumSize(new Dimension(742, 48));
        actionDisplay.setPreferredSize(new Dimension(742, 48));
        actionDisplay.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        //Add panel
        MainPanel.add(boardDisplay, BorderLayout.CENTER);
        MainPanel.add(cardDisplay, BorderLayout.EAST);
        MainPanel.add(actionDisplay, BorderLayout.NORTH);

    }


    //Return panel to main window
    public JPanel getDisplay() {
        return this.MainPanel;
    }

    public BoardDisplay getBoardDisplay() {
        return this.boardDisplay;
    }

    public ActionDisplay getActionDisplay() {
        return this.actionDisplay;
    }

    public CardDisplay getCardDisplay() {
        return this.cardDisplay;
    }

    /**
     * Action Keybind Classes
     */
    public class UpAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            game.movementHelper(Direction.NORTH);
        }

    }

    public class DownAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            game.movementHelper(Direction.SOUTH);
        }

    }

    public class LeftAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            game.movementHelper(Direction.WEST);
        }

    }

    public class RightAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            game.movementHelper(Direction.EAST);
        }

    }

}
