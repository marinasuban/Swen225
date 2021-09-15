package logic;

import java.awt.*;

public class Obstruction {

    //Top left and bottom right coordinates of obstruction
    private final Point topLeft;
    private final Point bottomRight;

    public Obstruction(Point tl, Point br){
        this.topLeft = tl;
        this.bottomRight = br;
    }

    /**
     *  Getter method for the top left position
     *
     * @return The top left position of the obstruction
     */
    public Point getTopLeft() { return topLeft; }

    /**
     *  Getter method for the bottom right position
     *
     * @return The bottom right position
     */
    public Point getBottomRight() { return bottomRight; }
}
