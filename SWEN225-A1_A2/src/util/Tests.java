package util;

import logic.Board;
import logic.Location;
import players.Player;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * testing Board class
 */
public class Tests {
    /**
     * Testing to see that set up is working
     * Checking that basic information is consistent
     */
    @Test public void basicTest(){
        Board board = new Board();
        board.addPlayers(new ArrayList<>(Arrays.asList(
                new Player("Lucilla", board, "Lucilla"),
                new Player("Bert", board, "Bert"),
                new Player("Malina", board, "Malina"),
                new Player("Percy", board, "Percy"))));
        Player player = board.getPlayers().get(0);

        assertEquals(player.toString(),player.getCharacterName());
        assertEquals(player.getTile(),board.getTile(11,1));
    }

    /**
     * Testing one space valid move
     * Checking method movePlayerInDirection
     */
    @Test public void validMoveTest(){
        Board board = new Board();
        board.addPlayers(new ArrayList<>(Arrays.asList(
                new Player("Lucilla", board, "Lucilla"),
                new Player("Bert", board, "Bert"),
                new Player("Malina", board, "Malina"),
                new Player("Percy", board, "Percy"))));
        Player player = board.getPlayers().get(0);

        move(player, board, Direction.SOUTH);

        assertEquals(player.getTile(),board.getTile(11,2));
    }

    /**
     * Testing multiple valid moves
     * Checking method movePlayerInDirection multiple times
     * Checking method checkValidMove with valid moves
     */
    @Test public void validMultiMoveTest(){
        Board board = new Board();
        board.addPlayers(new ArrayList<>(Arrays.asList(
                new Player("Lucilla", board, "Lucilla"),
                new Player("Bert", board, "Bert"),
                new Player("Malina", board, "Malina"),
                new Player("Percy", board, "Percy"))));
        Player player = board.getPlayers().get(0);

        for (int i = 1; i<4; i++){move(player, board, Direction.SOUTH);}

        assertEquals(player.getTile(),board.getTile(11,4));
    }

    /**
     * Testing valid move into a room through a door
     * Checking method checkValidMove with valid moves
     */
    @Test public void validMoveTest_intoRoom(){
        Board board = new Board();
        board.addPlayers(new ArrayList<>(Arrays.asList(
                new Player("Lucilla", board, "Lucilla"),
                new Player("Bert", board, "Bert"),
                new Player("Malina", board, "Malina"),
                new Player("Percy", board, "Percy"))));
        Player player = board.getPlayers().get(0);
        Location location = board.getLocations()[0];

        for (int i = 1; i<3; i++){move(player, board, Direction.SOUTH);}
        for (int i = 1; i<6; i++){move(player, board, Direction.WEST);}

        assertEquals(location,board.getLocation(player));
    }

    /**
     * Testing valid move out of room through a door
     * Checking method checkValidMove with valid move out of room through a door
     */
    @Test public void validMoveTest_outOfRoom(){
        Board board = new Board();
        board.addPlayers(new ArrayList<>(Arrays.asList(
                new Player("Lucilla", board, "Lucilla"),
                new Player("Bert", board, "Bert"),
                new Player("Malina", board, "Malina"),
                new Player("Percy", board, "Percy"))));
        Player player = board.getPlayers().get(0);

        for (int i = 1; i<3; i++){
            move(player, board, Direction.SOUTH);
        }
        for (int i = 1; i<6; i++){
            move(player, board, Direction.WEST);
        }
        board.resetValidTiles();
        move(player, board, Direction.EAST);

        assertEquals(player.getTile(),board.getTile(7,3));
    }

    /**
     * Testing for not moving on invalid moves
     * Checking method checkValidMove for detecting an invalid move into an obstruction
     */
    @Test public void invalidMoveTest_Obstruction(){
        Board board = new Board();
        board.addPlayers(new ArrayList<>(Arrays.asList(
                new Player("Lucilla", board, "Lucilla"),
                new Player("Bert", board, "Bert"),
                new Player("Malina", board, "Malina"),
                new Player("Percy", board, "Percy"))));
        Player player = board.getPlayers().get(0);

        for (int i = 1; i<6; i++){
            move(player, board, Direction.SOUTH);
        }

        assertEquals(player.getTile(),board.getTile(11,4));
    }

    /**
     * Testing for not moving on invalid moves
     * Checking method checkValidMove for detecting an invalid move into an wall
     */
    @Test public void invalidMoveTest_Wall(){
        Board board = new Board();
        board.addPlayers(new ArrayList<>(Arrays.asList(
                new Player("Lucilla", board, "Lucilla"),
                new Player("Bert", board, "Bert"),
                new Player("Malina", board, "Malina"),
                new Player("Percy", board, "Percy"))));
        Player player = board.getPlayers().get(0);

        for (int i = 1; i<3; i++){
            move(player, board, Direction.SOUTH);
        }
        for (int i = 1; i<6; i++){
            move(player, board, Direction.EAST);
        }

        assertEquals(player.getTile(),board.getTile(16,3));
    }

    /**
     * Testing for not moving on invalid moves
     * Checking method checkValidMove for detecting an invalid move backwards
     */
    @Test public void invalidMoveTest_RetraceStepBackwards(){
        Board board = new Board();
        board.addPlayers(new ArrayList<>(Arrays.asList(
                new Player("Lucilla", board, "Lucilla"),
                new Player("Bert", board, "Bert"),
                new Player("Malina", board, "Malina"),
                new Player("Percy", board, "Percy"))));
        Player player = board.getPlayers().get(0);

        move(player, board, Direction.SOUTH);
        move(player, board, Direction.NORTH);

        assertEquals(player.getTile(),board.getTile(11,2));
    }

    /**
     * Testing for not moving on invalid moves
     * Checking method checkValidMove for detecting an invalid move retracing
     */
    @Test public void invalidMoveTest_RetraceStepAround(){
        Board board = new Board();
        board.addPlayers(new ArrayList<>(Arrays.asList(
                new Player("Lucilla", board, "Lucilla"),
                new Player("Bert", board, "Bert"),
                new Player("Malina", board, "Malina"),
                new Player("Percy", board, "Percy"))));
        Player player = board.getPlayers().get(0);

        move(player, board, Direction.SOUTH);
        move(player, board, Direction.EAST);
        move(player, board, Direction.NORTH);
        move(player, board, Direction.WEST);

        assertEquals(player.getTile(),board.getTile(12,1));
    }

    /**
     * Testing attempting to move out of room in direction with no door
     * Checking method checkValidMove with invalid move out of room not using a door
     */
    @Test public void invalidMoveTest_OutOfRoom(){
        Board board = new Board();
        board.addPlayers(new ArrayList<>(Arrays.asList(
                new Player("Lucilla", board, "Lucilla"),
                new Player("Bert", board, "Bert"),
                new Player("Malina", board, "Malina"),
                new Player("Percy", board, "Percy"))));
        Player player = board.getPlayers().get(0);
        Location location = board.getLocations()[0];

        for (int i = 1; i<3; i++){
            move(player, board, Direction.SOUTH);
        }
        for (int i = 1; i<6; i++){
            move(player, board, Direction.WEST);
        }
        move(player, board, Direction.WEST);

        assertEquals(location,board.getLocation(player));
    }


    /**
     * Moves player in a direction on the board
     * This is to make the tests smaller and easier to read
     */
    private void move(Player player, Board board, Direction direction){
        if (board.checkValidMove(player, direction)){
            board.movePlayerInDirection(direction,player);
        }
    }
}
