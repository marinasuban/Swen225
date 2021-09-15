package logic;

import cards.Card;
import cards.CharacterCard;
import cards.LocationCard;
import cards.WeaponCard;
import players.Player;
import ui.*;
import util.Direction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class Game {
    //The final folder containing the cards the players must workout
    private WeaponCard evidenceWeapon;
    private LocationCard evidenceLocation;
    private CharacterCard evidenceCharacter;

    //All the players inside of this game
    private Queue<Player> allPlayers = new ArrayDeque<>();

    //The board and current active player
    private Board board;
    private Player activePlayer;

    //Has the game ended
    private boolean endOfGame;
    private volatile boolean turnEnded;
    private int movesLeft;
    private int diceOne;
    private int diceTwo;

    GameUI gameUI;
    public JFrame gameWindow;
    public JFrame playerSelectWindow;
    public DecisionSelector theSelector;

    public int amountOfPlayers;
    public Map<String, String> playerCharacterList;

    /**
     * Create a new game object and populate the lists
     */
    public Game() {
        //number of player selector
        playerSelectWindow = new JFrame("Murder Madness");
        playerSelectWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        NumberOfPlayers playerSelect = new NumberOfPlayers();
        playerSelectWindow.add(playerSelect);
        playerSelectWindow.pack();
        playerSelectWindow.setVisible(true);
        playerSelectWindow.setResizable(false);


        movesLeft = -1;
        amountOfPlayers = -1;
        while(amountOfPlayers == -1) {
            amountOfPlayers = NumberOfPlayers.checkButton();
        }
        playerSelectWindow.dispose();

        PlayerNameSelector playerNameSelector = new PlayerNameSelector();
        playerSelectWindow.add(playerNameSelector);

        playerCharacterList = new HashMap<>();
        while(!PlayerNameSelector.buttonPressed) {
            PlayerNameSelector.checkConfirmation(playerCharacterList, amountOfPlayers);
        }

        InitialiseGame();
    }

    public void InitialiseGame() {

        board = new Board();
        allPlayers = new ArrayDeque<>();
        endOfGame = false;
        turnEnded = false;

        playerSetup(amountOfPlayers, playerCharacterList);
        board.addPlayers(new ArrayList<>(allPlayers));

        //Setup Cards
        cardSetup();

        //Setup weapons
        weaponSetup();

        //Clear board for redraw
        board.clear();

        //Create main game window
        gameWindow = new JFrame("Murder Madness");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create game display
        gameUI = new GameUI(this);
        gameWindow.add(gameUI.getDisplay());

        //Create and add Jmenu bar
        gameWindow.setJMenuBar(createJMenuBar());
        gameWindow.addWindowListener(new OnCloseListener(this));

        //Push game display to game window
        gameWindow.pack();
        gameWindow.setVisible(true);
        gameWindow.setResizable(false);

        //gameWindow.addKeyListener(new KeyMoveListener(this));
        gameWindow.setFocusable(true);
        gameWindow.requestFocus();

    }

    private JMenuBar createJMenuBar() {

        JMenu menu = new JMenu("Menu");
        JMenuItem newGameButton = new JMenuItem("New Game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.setVisible(false);
                InitialiseGame();
            }
        });
        menu.add(newGameButton);

        JMenuItem exitButton = new JMenuItem("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openQuitPrompt();
            }
        });
        menu.add(exitButton);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);

        return menuBar;
    }

    /**
     * Processes all of the game moves
     */
    public void run() {
        //Run until end of the game
        while(!endOfGame) {
            gameUI.getActionDisplay().setGuessAllowed(true);
            gameUI.getActionDisplay().repaint();
            gameUI.getBoardDisplay().repaint();
            gameUI.getCardDisplay().repaint();
            if(activePlayer.isAlive()) {
                board.clear();

                //Stall the player till they click a button
                while(!turnEnded) {
                    Thread.onSpinWait();
                    if(!(DecisionSelector.returnList() == null) && (DecisionSelector.buttonPressed) && !(DecisionSelector.returnList().isEmpty())) {
                        if(DecisionSelector.returnCondition().equals("GUESS")) {
                            makeGuess(DecisionSelector.returnList());
                        }
                        else {
                            makeAccusation(DecisionSelector.returnList());
                        }
                        theSelector.clearList();
                    }
                    if(!activePlayer.isAlive()) turnEnded = true;
                }

                turnEnded = false;
                movesLeft = -1;
            }
            //Reset board and change player turn
            board.clear();
            setNextPlayer();
        }
        gameUI.getActionDisplay().repaint();
        gameUI.getBoardDisplay().repaint();
        gameUI.getCardDisplay().repaint();
    }


    /**
     * Allows the player to make a guess,
     * This asks all the players for a list of cards
     * however the player only receives one piece of information
     * if a card is found informs the player who's hand it was from
     * else if a card was not found we inform the player
     */
    private void makeGuess(ArrayList<String> choices) {

        //Create new cards to query the players hand
        LocationCard locationCard = new LocationCard(board.getLocation(activePlayer).toString());
        WeaponCard weaponCard = new WeaponCard(choices.get(0));
        CharacterCard characterCard = new CharacterCard(choices.get(1));

        //Add cards to a list for method
        ArrayList<Card> cardsToAskFor = new ArrayList<>();
        cardsToAskFor.add(locationCard);
        cardsToAskFor.add(characterCard);
        cardsToAskFor.add(weaponCard);

        //Move the correct weapon to this room
        board.getLocation(activePlayer).swapWeapons(board.getWeaponLocation(new Weapon(weaponCard.toString())));

        //Pull the player to the room
        for(Player el : allPlayers) {
            //So we dont pull ourselves
            if(el != activePlayer) {
                //Correct player check
                if(el.toString().toLowerCase(Locale.ROOT).equals(characterCard.toString().toLowerCase(Locale.ROOT))) {
                    // if inside a location remove them from that location
                    if(board.getLocation(el) != null) {
                        board.getLocation(el).removePlayer(el);
                    }
                    else {
                        //Remove them from tile they dont belong on
                        el.getTile().setPlayer(null);
                        el.setIsInRoom(true);
                    }
                    //Add them to the new location
                    board.getLocation(activePlayer).addPlayer(el);
                }
            }
        }

        //Ask all other players for those cards
        for(Player el : allPlayers) {
            //So long as it is not this own player
            if(!el.toString().equals(activePlayer.toString())) {
                //Ask the player for the card
                Card returnedCard = el.askForCard(cardsToAskFor);

                //If they had a card
                if(returnedCard != null) {
                    showMessageDialog(null, "Found card " + returnedCard + " in " + el + "'s hand!");
                    activePlayer.addSeen(returnedCard);
                    //Exit searching for cards
                    this.gameUI.getBoardDisplay().repaint();
                    this.gameUI.getCardDisplay().repaint();
                    return;
                }
            }
        }

        //Didnt find a card
        showMessageDialog(null, "Nobody could help you with that guess!");
        this.gameUI.getBoardDisplay().repaint();
    }

    /**
     * Allows the player to make an accusation
     * Accusation requires entry of a location, weapon and character
     * if the accusation is incorrect that player is removed from the game
     * else the game is ended and the player gets a congratulations message!
     */
    private void makeAccusation(ArrayList<String> choices) {
        //Create new cards to query the players hand
        WeaponCard weaponCard = new WeaponCard(choices.get(0));
        CharacterCard characterCard = new CharacterCard(choices.get(1));
        LocationCard locationCard = new LocationCard(choices.get(2));

        //Check if guess is correct
        boolean guessCorrect = (weaponCard.equals(evidenceWeapon) && characterCard.equals(evidenceCharacter) && locationCard.equals(evidenceLocation));

        if(guessCorrect) {
            //If they guessed correctly end game
            showMessageDialog(null, "Congratulations you guessed correctly!!!");
            endOfGame = true;
            turnEnded = true;
        }
        else {
            activePlayer.setDead();
            showMessageDialog(null, "Incorrect accusation, you have been removed from the game; you will still be used for refuting questions");

            //Check that all players are not dead
            endOfGame = true;
            for(Player el : allPlayers){
                if(el.isAlive()) endOfGame = false;
            }
            if(endOfGame) showMessageDialog(null, "All players have lost, please use the menu bar to create a new game");
        }
        this.gameUI.getBoardDisplay().repaint();
    }


    /**
     * Rolls Two dice
     * Moves player around board in desired direction for sum of dice
     * Allows them to guess once they enter an estate
     */
    public void makeMove() {
        //Clear board for redraw
        board.clear();

        //Dice Logic
        diceOne = rollADice();
        diceTwo = rollADice();

        movesLeft = diceOne + diceTwo;

        //Update to show how many moves left
        this.gameUI.getActionDisplay().repaint();
        board.resetValidTiles();
    }

    public void movementHelper(Direction desiredDirection) {
        if(movesLeft <= 0) {
            return;
        }

        if(board.checkValidMove(activePlayer, desiredDirection)) {
            //Move the player
            movesLeft--;
            board.movePlayerInDirection(desiredDirection, activePlayer);
        }

        //Emergency end turn
        if(!board.checkValidMove(activePlayer, Direction.NORTH) && !board.checkValidMove(activePlayer, Direction.EAST) && !board.checkValidMove(activePlayer, Direction.SOUTH) && !board.checkValidMove(activePlayer, Direction.WEST)) {
            movesLeft = 0;
        }

        this.gameUI.getBoardDisplay().repaint();
        this.gameUI.getActionDisplay().repaint();
    }


    /**
     * This method creates the players and evaluates if an AI player is required
     *
     * @param amountOfPlayers the amount of Human Players to create
     */
    private void playerSetup(int amountOfPlayers, Map<String, String> playerAndCharacterNames) {
        String[] playerName = playerAndCharacterNames.keySet().toArray(new String[0]);

        // Create as many human players as specified;
        for(int i = 0; i < amountOfPlayers; i++) {
            allPlayers.offer(new Player(playerAndCharacterNames.get(playerName[i]), board, playerName[i]));
        }
        if(allPlayers.size() != 4) {
            Player el = new Player(playerAndCharacterNames.get(playerName[3]), board, playerName[3]);
            el.setDead();
            allPlayers.offer(el);
        }

    }

    /**
     * Initialize the weapons and add them to the board
     */
    private void weaponSetup() {
        ArrayList<Weapon> weaponList = new ArrayList<>(Arrays.asList(new Weapon("Knife", "->>"),
                new Weapon("Ipad", "[I]"),
                new Weapon("Shovel", "|-D"),
                new Weapon("Scissors", "8-<"),
                new Weapon("Broom", "--E")));
        Collections.shuffle(weaponList);
        board.addWeapons(weaponList);
    }

    /**
     * Method to populate the card sets and deal out the cards to all the players
     */
    public void cardSetup() {
        // Populate the lists of cards
        ArrayList<WeaponCard> allWeaponCards = new ArrayList<>(Arrays.asList(new WeaponCard("Broom"), new WeaponCard("Scissors"), new WeaponCard("Knife"), new WeaponCard("Shovel"), new WeaponCard("Ipad")));
        ArrayList<LocationCard> allLocationCards = new ArrayList<>(Arrays.asList(new LocationCard("Haunted House"), new LocationCard("Manic Manor"), new LocationCard("Villa Celia"), new LocationCard("Calamity Castle"), new LocationCard("Peril Palace")));
        ArrayList<CharacterCard> allCharacterCards = new ArrayList<>(Arrays.asList(new CharacterCard("Lucilla"), new CharacterCard("Bert"), new CharacterCard("Malina"), new CharacterCard("Percy")));

        //Shuffle each of the cards
        Collections.shuffle(allWeaponCards);
        Collections.shuffle(allLocationCards);
        Collections.shuffle(allCharacterCards);

        //Remove one item from each set and add it to the evidence bag
        evidenceWeapon = allWeaponCards.remove(0);
        evidenceLocation = allLocationCards.remove(0);
        evidenceCharacter = allCharacterCards.remove(0);

        //Add all the cards not in the evidence folder to a hashset to be dealt out to the players.
        ArrayList<Card> cardsToDeal = new ArrayList<>();
        cardsToDeal.addAll(allWeaponCards);
        cardsToDeal.addAll(allLocationCards);
        cardsToDeal.addAll(allCharacterCards);

        //Shuffle the cards to deal;
        Collections.shuffle(cardsToDeal);


        //Deal all cards to players
        for(Card el : cardsToDeal) {
            setNextPlayer();
            activePlayer.addCard(el);
        }

        for(int i = 0; i < new Random().nextInt(allPlayers.size()); i++)
            setNextPlayer();

    }

    /**
     * Set the next active player and advance the player order
     */
    private void setNextPlayer() {
        activePlayer = allPlayers.poll();
        allPlayers.offer(activePlayer);
    }

    /**
     * Generates a random number between 1 and 6
     *
     * @return a random number between 1 - 6
     */
    private int rollADice() {
        return (int) (Math.random() * 6 + 1);
    }

    public Board getBoard() {
        return this.board;
    }

    public Player getActivePlayer() {
        return this.activePlayer;
    }

    public void endTurn() {
        turnEnded = true;
    }

    public void openQuitPrompt() {
        int QuitPromptOption = JOptionPane.showConfirmDialog(gameWindow,
                "Are you sure you want to quit the game?", "Quit Game?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if(QuitPromptOption == JOptionPane.YES_OPTION) {
            System.exit(0); // close the game
        }
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    public ArrayList<String> getMovesLeftStringFormated() {
        ArrayList <String> returningList = new ArrayList<>();
        returningList.add("Rolled a " + diceOne + " and a " + diceTwo + ", totalling to " + (diceOne + diceTwo));
        returningList.add("You have " + movesLeft + " moves left!");
        return returningList;

    }

    public void playerMakeGuess() {
        theSelector = new DecisionSelector(this, "GUESS");
        DecisionSelector.toggleMenuShow();
        gameUI.getActionDisplay().setGuessAllowed(false);
        gameUI.getActionDisplay().repaint();
        gameUI.getCardDisplay().repaint();
    }

    public void playerMakeAccusation() {
        theSelector = new DecisionSelector(this, "ACCUSE");
        DecisionSelector.toggleMenuShow();
        gameUI.getActionDisplay().setGuessAllowed(false);
        gameUI.getActionDisplay().repaint();
        gameUI.getCardDisplay().repaint();
    }

    public boolean hasGameEnded() {
        return endOfGame;
    }
}
