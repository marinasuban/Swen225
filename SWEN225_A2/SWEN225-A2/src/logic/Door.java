package logic;

import players.Player;
import util.Direction;

import java.awt.*;

public class Door extends Tile {

    private final Location destination;       //Door destination (i.e. the door's associated room)
    private final Direction facing;           //The direction the door is facing

    /**
     * Door constructor
     * @param location The door's destination
     * @param position The position of the door on the board
     * @param facing The direction the door is facing
     */
    Door(Location location, Point position, Direction facing) {

        this.destination = location;
        this.facing = facing;
        this.setPosition(position);

    }

    /**
     * Move player into room
     *
     * @param activePlayer Player to move into room
     */
    public void enterRoom(Player activePlayer){ destination.addPlayer(activePlayer); activePlayer.setIsInRoom(true); }

    /**
     * Remove player from room
     *
     * @param activePlayer Player to remove from room
     * @param board Board to place player back on to
     */
    public void leaveRoom(Player activePlayer, Board board){

        //Remove the player from the associated room
        destination.removePlayer(activePlayer);
        activePlayer.setIsInRoom(false);

        //Set the players tile according to the doors facing position
        switch(facing) {
            case NORTH:
                activePlayer.setTile(board.getTile(this.getPosition().x, this.getPosition().y - 1));
                break;
            case EAST:
                activePlayer.setTile(board.getTile(this.getPosition().x + 1, this.getPosition().y));
                break;
            case SOUTH:
                activePlayer.setTile(board.getTile(this.getPosition().x, this.getPosition().y + 1));
                break;
            case WEST:
                activePlayer.setTile(board.getTile(this.getPosition().x - 1, this.getPosition().y));
                break;
        }

    }

    /**
     * Get the direction the door is facing
     * @return The direction the door is facing
     */
    public Direction getFacing(){ return this.facing; }

    public String toString(){
        return "D";
    }

}
