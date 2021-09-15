package ui;

import logic.*;
import players.Player;

import javax.swing.*;
import java.awt.*;

public class BoardDisplay extends JPanel {

    //Reference to board
    Game game;

    //Board Backdrop
    Image backdrop;

    public BoardDisplay(Game g) {
        game = g;
        backdrop = Toolkit.getDefaultToolkit().getImage("./assets/board.png");

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);    // paints background

        //Paint Board Backdrop
        g.drawImage(backdrop, 0, 0, this);

        //Draw players on board
        for(Player p : game.getBoard().getPlayers()) {

            //Only render player on board if it is not in room
            if(!p.getInRoom()) {

                //Player's icon
                Image playerIcon = getPlayerImage(p);

                //X, Y position of player
                int x = p.getTile().getPosition().x * 20 + 1;
                int y = p.getTile().getPosition().y * 20 + 1;

                //Draw player icon
                g.drawImage(playerIcon, x, y, this);

                if(game.getActivePlayer().getCharacterName().equals(p.getCharacterName())) {
                    drawIndicator(g, x, y);
                }

            }

        }

        for(Location l : game.getBoard().getLocations()) {

            Weapon weapon = l.getWeapon();
            Image weaponIcon;

            //Draw weapon icons
            switch(weapon.toString()) {
                case "Knife":
                    weaponIcon = Toolkit.getDefaultToolkit().getImage("./assets/knife.png");
                    break;
                case "Ipad":
                    weaponIcon = Toolkit.getDefaultToolkit().getImage("./assets/tablet.png");
                    break;
                case "Scissors":
                    weaponIcon = Toolkit.getDefaultToolkit().getImage("./assets/scissors.png");
                    break;
                case "Broom":
                    weaponIcon = Toolkit.getDefaultToolkit().getImage("./assets/broom.png");
                    break;
                case "Shovel":
                    weaponIcon = Toolkit.getDefaultToolkit().getImage("./assets/shovel.png");
                    break;
                default:
                    throw new Error("Invalid weapon name: " + l.getName());
            }

            g.drawImage(weaponIcon, (l.getBottomRight().x * 20) - 8, (l.getBottomRight().y * 20) - 8, this);

            for(int i = 0; i < l.getPlayers().size(); i++) {

                Player p = l.getPlayers().get(i);
                Image playerIcon = getPlayerImage(p);

                int x = l.getTopLeft().x * 20 + (i * 20) + 3;
                int y = l.getTopLeft().y * 20 + 3;

                g.drawImage(playerIcon, l.getTopLeft().x * 20 + (i * 20) + 3, l.getTopLeft().y * 20 + 3, this);

                if(game.getActivePlayer().getCharacterName().equals(p.getCharacterName())) {
                    drawIndicator(g, x, y);
                }

            }

        }
    }

    public Image getPlayerImage(Player p) {

        Image result;

        switch(p.getCharacterName()) {
            case "Lucilla":
                result = Toolkit.getDefaultToolkit().getImage("./assets/lucilla.png");
                break;
            case "Percy":
                result = Toolkit.getDefaultToolkit().getImage("./assets/percy.png");
                break;
            case "Bert":
                result = Toolkit.getDefaultToolkit().getImage("./assets/bert.png");
                break;
            case "Malina":
                result = Toolkit.getDefaultToolkit().getImage("./assets/malina.png");
                break;
            default:
                throw new Error("Invalid player name!");
        }

        return result;

    }

    public void drawIndicator(Graphics g, int x, int y) {

        g.setColor(Color.GREEN);
        g.drawRect(x, y, 20, 20);

    }

}
