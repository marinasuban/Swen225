package logic;

import players.Player;

import java.awt.*;

public class Tile {

    private Player player;      //The player on the current tile
    private Point position;     //The position of the tile

    private Boolean canMove = true;     //Move validity indicator (can the player move to this tile)

    public Tile(Point pos) {

        this.position = pos;
        this.player = null;

    }

    public Tile(){}

    /**
     *  Getter and setter methods for player
     */
    public void setPlayer(Player p) { this.player = p; }
    public Player getPlayer() { return this.player; }

    /**
     *  Getter and setter methods for position
     */
    public void setPosition(Point p) { this.position = p; }
    public Point getPosition() { return this.position; }

    /**
     *  Getter and setter methods for movement validity indicator
     */
    public Boolean getCanMove() { return canMove; }
    public void setCanMove(Boolean canMove) { this.canMove = canMove; }

    public String toString() {

        if(this.player != null) {
            return Character.toString(this.player.toString().charAt(0));
        }
        return "_";

    }

}
