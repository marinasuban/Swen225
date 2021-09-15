package logic;

import players.Player;
import util.Direction;

import java.awt.*;
import java.util.ArrayList;

/**
 * CO-ORDINATES STANDARD
 * <p>
 * The board is addressed from the top left, starting from 0 as [Y, X] (this coordinate system is based on how
 * scan-lines work, which makes looping through these lists more intuitive). However, to keep in-line with how
 * the java Point class stores points (as [X, Y]), whenever referencing points from this class, we need to make sure
 * we swap the coordinates around when needed.
 */

public class Board {

    private final Tile[][] board = new Tile[24][24];                      //Board tiles
    private final Location[] locations = new Location[5];                 //Board locations
    private final Door[] doors = new Door[12];                            //Board doors
    private final Obstruction[] obstructions = new Obstruction[4];        //Board obstructions (4 squares on each side of the board)

    public Location[] getLocations() {
        return locations;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    private ArrayList<Player> players;                                    //Players on the board

    String defaultBoard = "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
            "┃_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_┃\n" +
            "┃_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_┃\n" +
            "┃_|_╔═════════╗_|_|_|_|_|_|_|_|_|_╔═════════╗_|_┃\n" +
            "┃_|_║        ← _|_|_|_|_|_|_|_|_|_║  Manic  ║_|_┃\n" +
            "┃_|_║ Haunted ║_|_|_|_|_|_|_|_|_|_║  Manor  ║_|_┃\n" +
            "┃_|_║  House  ║_|_|_|_╔═══╗_|_|_|_ →        ║_|_┃\n" +
            "┃_|_╚═════ ↑ ═╝_|_|_|_╚═══╝_|_|_|_╚═════ ↑ ═╝_|_┃\n" +
            "┃_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_┃\n" +
            "┃_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_┃\n" +
            "┃_|_|_|_|_|_|_|_|_|_|_|_| |_|_|_|_|_|_|_|_|_|_|_┃\n" +
            "┃_|_|_|_|_|_|_|_|_╔═════ ↓ ═══╗_|_|_|_|_|_|_|_|_┃\n" +
            "┃_|_|_|_|_╔═══╗_|_║   Villa  ← _|_╔═══╗_|_|_|_|_┃\n" +
            "┃_|_|_|_|_╚═══╝_|_ →  Celia   ║_|_╚═══╝_|_|_|_|_┃\n" +
            "┃_|_|_|_|_|_|_|_|_╚═══ ↑ ═════╝_|_|_|_|_|_|_|_|_┃\n" +
            "┃_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_┃\n" +
            "┃_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_┃\n" +
            "┃_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_┃\n" +
            "┃_|_╔═ ↓ ═════╗_|_|_|_╔═══╗_|_|_|_╔═ ↓ ═════╗_|_┃\n" +
            "┃_|_║Calamity← _|_|_|_╚═══╝_|_|_|_║  Peril  ║_|_┃\n" +
            "┃_|_║ Castle  ║_|_|_|_|_|_|_|_|_|_║  Palace ║_|_┃\n" +
            "┃_|_║         ║_|_|_|_|_|_|_|_|_|_ →        ║_|_┃\n" +
            "┃_|_╚═════════╝_|_|_|_|_|_|_|_|_|_╚═════════╝_|_┃\n" +
            "┃_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_┃\n" +
            "┃_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_┃\n" +
            "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";

    /**
     * Board constructor
     */
    public Board() {

        //Create locations
        locations[0] = new Location(new Point(2, 2), new Point(6, 6), "Haunted House");
        locations[1] = new Location(new Point(17, 2), new Point(21, 6), "Manic Manor");
        locations[2] = new Location(new Point(9, 10), new Point(14, 13), "Villa Celia");
        locations[3] = new Location(new Point(2, 17), new Point(6, 21), "Calamity Castle");
        locations[4] = new Location(new Point(17, 17), new Point(21, 21), "Peril Palace");

        //Create doors
        doors[0] = new Door(locations[0], new Point(5, 6), Direction.SOUTH);
        doors[1] = new Door(locations[0], new Point(6, 3), Direction.EAST);

        doors[2] = new Door(locations[1], new Point(17, 5), Direction.WEST);
        doors[3] = new Door(locations[1], new Point(20, 6), Direction.SOUTH);

        doors[4] = new Door(locations[2], new Point(12, 10), Direction.NORTH);
        doors[5] = new Door(locations[2], new Point(11, 13), Direction.SOUTH);
        doors[6] = new Door(locations[2], new Point(14, 11), Direction.EAST);
        doors[7] = new Door(locations[2], new Point(9, 12), Direction.WEST);

        doors[8] = new Door(locations[3], new Point(3, 17), Direction.NORTH);
        doors[9] = new Door(locations[3], new Point(6, 18), Direction.EAST);

        doors[10] = new Door(locations[4], new Point(18, 17), Direction.NORTH);
        doors[11] = new Door(locations[4], new Point(17, 20), Direction.WEST);

        //Add doors to locations
        locations[0].addDoor(doors[0], doors[1]);
        locations[1].addDoor(doors[2], doors[3]);
        locations[2].addDoor(doors[4], doors[5], doors[6], doors[7]);
        locations[3].addDoor(doors[8], doors[9]);
        locations[4].addDoor(doors[10], doors[11]);

        //Create obstructions
        obstructions[0] = new Obstruction(new Point(11, 5), new Point(12, 6));
        obstructions[1] = new Obstruction(new Point(5, 11), new Point(6, 12));
        obstructions[2] = new Obstruction(new Point(11, 17), new Point(12, 18));
        obstructions[3] = new Obstruction(new Point(17, 11), new Point(18, 12));

        //Generate tiles for board
        generateTiles();

    }

    /**
     * Generate tiles for board
     */
    private void generateTiles() {

        //Generate null tiles
        for(int y = 0; y < 24; y++) {
            for(int x = 0; x < 24; x++) {
                if(board[y][x] == null) {
                    board[y][x] = new Tile(new Point(x, y));
                }
            }
        }

        //Generate tiles for valid movement
        resetValidTiles();

    }

    /**
     * Generate/Reset the move validity indicators for each tile. These determine where the players can/cannot move
     * (the player cannot move through obstructions, locations or through tiles the player has previously moved from
     * within a single turn)
     */
    public void resetValidTiles() {

        //Reset all tiles to be movable
        for(int y = 0; y < 24; y++) {
            for(int x = 0; x < 24; x++) {
                board[y][x].setCanMove(true);
            }
        }

        //Reset immovable tiles
        for(int y = 0; y < 24; y++) {
            for(int x = 0; x < 24; x++) {

                //If the position is part of a location, we cannot move through it, set it as unmovable
                for(Location l : locations) {

                    if(x >= l.getTopLeft().x && x <= l.getBottomRight().x &&
                            y >= l.getTopLeft().y && y <= l.getBottomRight().y) {
                        board[y][x].setCanMove(false);
                    }

                }

                //Set the obstructions as blocks the players cannot move through
                for(Obstruction o : obstructions) {

                    if(y >= o.getTopLeft().x && y <= o.getBottomRight().x &&
                            x >= o.getTopLeft().y && x <= o.getBottomRight().y) {
                        board[y][x].setCanMove(false);
                    }

                }

            }
        }

        //Set the doors as positions the players can move to
        for(Door d : doors) {
            board[d.getPosition().y][d.getPosition().x].setCanMove(true);
        }

    }

    /**
     * Obtain a visual string representation of the board
     *
     * @return The string representation of the board
     */
    public String toString() {

        //Take empty template board
        StringBuilder result = new StringBuilder();
        result.append(defaultBoard);

        //Loop through and place players on board
        for(Player p : players) {

            //If the player is not in a room, draw them on the board.
            if(getLocation(p) == null) {

                //Get position of player and set their position on the board accordingly
                int x = p.getTile().getPosition().x;
                int y = p.getTile().getPosition().y;

                result.setCharAt(((y + 1) * 50) + ((x * 2) + 1), p.toString().charAt(0));

            }

        }

        //Loop through locations and display player count & weapons
        for(Location l : locations) {

            //Get the position to display the characters currently in a given room
            int tlX = l.getTopLeft().x;     //Top left X
            int tlY = l.getTopLeft().y;     //Top left Y

            int brX = l.getBottomRight().x;     //Bottom right X
            int brY = l.getBottomRight().y;     //Bottom right Y

            //Display the number of players.
            switch(l.getName()) {

                case "Haunted House":
                case "Manic Manor":
                case "Villa Celia":

                    //Draw players in location
                    for(int i = 0; i < l.getPlayers().size(); i++) {
                        result.setCharAt(((tlY + 1) * 50) + ((tlX * 2) + 1) + i, l.getPlayers().get(i).toString().charAt(0));
                    }

                    break;

                case "Calamity Castle":
                case "Peril Palace":

                    //Draw players in location
                    for(int i = 0; i < l.getPlayers().size(); i++) {
                        result.setCharAt(((brY + 1) * 50) + ((brX * 2) + 1) - i, l.getPlayers().get(i).toString().charAt(0));
                    }

                    break;

            }

            //Display the weapons in rooms
            switch(l.getName()) {

                case "Villa Celia":

                    //Draw weapon glyph
                    for(int i = 0; i < 3; i++) {
                        result.setCharAt(((brY + 1) * 50) + ((brX * 2) + 1) - i, l.getWeapon().getGlyph().charAt(2 - i));
                    }

                    break;

                case "Haunted House":
                case "Manic Manor":

                    //Draw weapon glyph
                    for(int i = 0; i < 3; i++) {
                        result.setCharAt(((brY + 1) * 50) + ((tlX * 2) + 1) + i, l.getWeapon().getGlyph().charAt(i));
                    }

                    break;

                case "Calamity Castle":
                case "Peril Palace":

                    //Draw weapon glyph
                    for(int i = 0; i < 3; i++) {
                        result.setCharAt(((tlY + 1) * 50) + ((brX * 2) - 1) + i, l.getWeapon().getGlyph().charAt(i));
                    }

                    break;

            }

        }

        //Return the final board
        return result.toString();

    }

    /**
     * Gets the location of a given player
     *
     * @param activePlayer Player who's location is to be retrieved
     * @return The location of the specified player
     */
    public Location getLocation(Player activePlayer) {

        //Loop through locations until the active player is found, and return it
        for(Location l : locations) {
            if(l.getPlayers().contains(activePlayer)) {
                return l;
            }
        }

        //Return null if the player was not found (i.e. is not in a room)
        return null;

    }

    /**
     * Check if a given move is valid and not obstructed, or retraces any steps
     *
     * @param activePlayer The player to check move validity against
     * @param direction    The direction the player wishes to move in
     * @return Boolean as to if the move is valid or not
     */
    public boolean checkValidMove(Player activePlayer, Direction direction) {

        //Calculate new position of the player
        Point newPos = calculateNewPosition(direction, activePlayer.getTile().getPosition());

        //Get player's current location
        Location currLocation = this.getLocation(activePlayer);

        //If the player is in a location...
        if(currLocation != null) {

            //Return validity depending on the location's doors.
            switch(currLocation.getName()) {
                case "Manic Manor":
                    return direction == Direction.WEST || direction == Direction.SOUTH;
                case "Haunted House":
                    return direction == Direction.EAST || direction == Direction.SOUTH;
                case "Villa Celia":
                    return true;
                case "Calamity Castle":
                    return direction == Direction.NORTH || direction == Direction.EAST;
                case "Peril Palace":
                    return direction == Direction.NORTH || direction == Direction.WEST;
                default:
                    throw new Error("Invalid Location Name!");
            }

        }

        //Otherwise return true
        return newPos.x >= 0 && newPos.x <= 23 &&
                newPos.y >= 0 && newPos.y <= 23
                && board[newPos.y][newPos.x].getCanMove();

    }

    /**
     * Moves the player in direction in an un-safe manner (game logic must verify that the move is valid separately)
     *
     * @param direction    The direction to move the player in
     * @param activePlayer The player to move
     */
    public void movePlayerInDirection(Direction direction, Player activePlayer) {

        //Get player's current location
        Location currLocation = this.getLocation(activePlayer);

        //If the player is in a room...
        if(currLocation != null) {

            //Move the player out of the appropriate door
            for(Door d : currLocation.getDoors()) {

                if(d.getFacing().equals(direction)) {
                    d.leaveRoom(activePlayer, this);
                    return;
                }

            }

        }

        //Calculate new position on board for player
        Point newPos = calculateNewPosition(direction, activePlayer.getTile().getPosition());

        //Make sure player cannot move back to this tile
        activePlayer.getTile().setCanMove(false);

        //If the player trys to move into a door, move them into the room
        for(Door d : doors) {
            if(d.getPosition().x == newPos.x && d.getPosition().y == newPos.y) {
                d.enterRoom(activePlayer);
            }
        }


        //Move the player
        activePlayer.setTile(board[newPos.y][newPos.x]);
    }

    /**
     * Get a specific tile on the board, based on it's X, Y coordinates
     *
     * @param x X look-up coordinate
     * @param y Y look-up coordinate
     * @return The tile located at position (x, y).
     */
    public Tile getTile(int x, int y) {
        return board[y][x];
    }

    /**
     * Generate new position given direction
     *
     * @param direction The direction to move point
     * @param position  The point to move
     * @return The new translated point
     */
    private Point calculateNewPosition(Direction direction, Point position) {

        //Calculate new position of the player
        Point newPos = new Point(position.x, position.y);

        //Calculate new position based on direction
        switch(direction) {
            case NORTH:
                newPos.y = newPos.y - 1;
                break;
            case SOUTH:
                newPos.y = newPos.y + 1;
                break;
            case EAST:
                newPos.x = newPos.x + 1;
                break;
            case WEST:
                newPos.x = newPos.x - 1;
                break;
        }

        return newPos;

    }

    /**
     * Add a list of players to the board's player list
     *
     * @param playerList List of players to add
     */
    public void addPlayers(ArrayList<Player> playerList) {
        this.players = playerList;
    }

    /**
     * "Clear" screen with new line statements, pushing previous content out of view
     */
    public void clear() {

        for(int i = 0; i < 40; i++) {
            System.out.println("\n");
        }

    }

    /**
     * Add a weapons list to the boards list of weapons
     *
     * @param weaponList List to import to board object
     */
    public void addWeapons(ArrayList<Weapon> weaponList) {
        //Add weapons to locations ()
        locations[0].setWeapon(weaponList.get(0));
        locations[1].setWeapon(weaponList.get(1));
        locations[2].setWeapon(weaponList.get(2));
        locations[3].setWeapon(weaponList.get(3));
        locations[4].setWeapon(weaponList.get(4));

    }

    public Location getWeaponLocation(Weapon el) {

        for(Location l : locations) {
            if(l.getWeapon().equals(el)) {
                return l;
            }
        }
        return null;
    }
}
