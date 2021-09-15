package logic;

import players.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a Murder Madness estate, can hold players and a singular weapon token.
 */
public class Location {

    //Top left and bottom right of location
    private final Point topLeft;
    private final Point bottomRight;

    //Name of location
    private final String name;

    //Players in current location
    private final List<Player> players;

    //Weapon in current room
    private Weapon weapon;

    //Doors in current room
    private final List<Door> doors;

    /** Creates location
     *
     * @param topLeft top left corner of the room [x,y]
     * @param bottomRight bottom right corner of the room [x,y]
     * @param name the name of this location
     */
    Location(Point topLeft, Point bottomRight, String name){

        this.topLeft = topLeft;
        this.bottomRight = bottomRight;

        this.name = name;

        players = new ArrayList<>();
        this.doors = new ArrayList<>();

    }

    //Getter, setter and swap methods for weapon field
    public Weapon getWeapon(){ return this.weapon; }
    public void setWeapon(Weapon w){ this.weapon = w; }


    /** Swaps the weapons in two locations
     *
     * @param l the location that we need to swap weapon with
     */
    public void swapWeapons(Location l){
        Weapon temp = l.getWeapon();

        l.setWeapon(this.weapon);
        this.weapon = temp;
    }

    //Getter and setter methods for door list
    public List<Door> getDoors(){ return this.doors; }
    public void addDoor(Door... doors){ this.doors.addAll(new ArrayList<>(Arrays.asList(doors))); }

    //Getter and setter methods for player list
    public List<Player> getPlayers(){ return players; }
    public void addPlayer(Player play){ players.add(play); }
    public void removePlayer(Player play){ players.remove(play); }

    //Getter for top left and bottom right positions
    public Point getTopLeft() { return topLeft; }
    public Point getBottomRight() { return bottomRight; }

    //Getter for name
    public String toString(){ return name; }
    public String getName(){ return name; }

}
