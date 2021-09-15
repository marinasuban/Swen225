package players;

import cards.Card;
import logic.Board;
import logic.Tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Player represents a token on the board
 * They have a hand that stores cards
 * as well as hashsets that store which additional cards the player has seen to help them guess
 * can also be queried to see if the player has a particular card
 */
public class Player {
    //The name of that particular player
    private final String characterName;

    //Name of the Player
    private final String playerName;

    //The players hand
    private final Set<Card> hand = new HashSet<>();

    //Seen cards
    private final Set<Card> cardsSeen = new HashSet<>();

    private Boolean isInRoom = false;

    private Tile position;

    private boolean isDead;

    /**
     * Assign starting position (tile) based on an entered character name
     *
     * @param name The name of the character assigned to the player
     */
    public Player(String name, Board board, String realName){
        switch(name) {
            case "Lucilla":
                this.position = board.getTile(11, 1);
                break;
            case "Bert":
                this.position = board.getTile(1, 9);
                break;
            case "Malina":
                this.position = board.getTile(9, 22);
                break;
            case "Percy":
                this.position = board.getTile(22, 14);
                break;
            default:
                throw new Error("Invalid player Name");
        }

        //Set the player name
        this.characterName = name;
        this.playerName = realName;
    }



    /**
     * Return name of character that player is using
     *
     * @return The name of the character
     */
    @Override
    public String toString() {
        return characterName;
    }

    /**
     * Add card to player's hand
     *
     * @param card Card to add to player's hand
     */
    public void addCard(Card card) {
        hand.add(card);
        addSeen(card);
    }

    /**
     * Add card to player's hand that have been seen
     *
     * @param card Card that is seen by player
     */
    public void addSeen(Card card) {
         cardsSeen.add(card);
    }

    /**
     * Add card to player's hand that have been seen
     *
     * @param card Card that is seen by player
     */
    public boolean hasCard(Card card) {
        for(Card el : cardsSeen){
            if(el.equals(card)) return true;
        }
       return false;
    }

    /**
     * Return a player's tile on the board
     *
     * @return The current tile a player is on
     */
    public Tile getTile() {
        return position;
    }

    /**
     * Set the player's tile on the board
     *
     * @param pos tile to change to
     */
    public void setTile(Tile pos) { this.position = pos; }


    /** Method for getting players hand
     *
     * @return This players hand
     */
    public Set<Card> getHand() {
        return hand;
    }

    /**
     * Looks through the players hand to see if there's a match from the list of cards entered,
     * return the card which first match, else return null.
     *
     * @param cards A list of cards to search for
     * @return A card from the list which matches the player's hand
     */
    public Card askForCard(ArrayList<Card> cards) {
        //For every card the player is asking for
        for(Card el : cards) {
            //Go through all the cards in the players hand
            for(Card c : this.hand) {
                //If we have found a card we were looking for
                if(c.equals(el)) {
                    //Return it
                    return c;
                }
            }
        }
        //Otherwise we did not find a card so return null
        return null;
    }

    public String getCharacterName(){ return this.characterName; }
    public String getPlayerName(){ return this.playerName; }

    public void setIsInRoom(Boolean b){ this.isInRoom = b; }
    public Boolean getInRoom() { return isInRoom; }

    public void setDead() {
        isDead = true;
    }

    public boolean isAlive() {
        return !isDead;
    }
}
