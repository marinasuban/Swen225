package logic;

import java.util.Locale;

/**
 * Stores a weapon on the board for displaying
 */
public class Weapon {

    private final String name;        //The name of the weapon
    private final String glyph;       //The display glyph of the weapon


    /** Create a new weapon to display on the board, glyphs are made up of three characters
     *
     * @param name The name of this weapon
     * @param glyph The visual representation on
     */
    public Weapon(String name, String glyph) {
        this.name = name;
        this.glyph = glyph;
    }

    /** Useful for equality checking when searching for a specific weapon
     *
     * @param name of the weapon to search for
     */
    public Weapon(String name) {
        this.name = name;
        this.glyph = null;
    }

    /**
     * Getter for glyph
     *
     * @return String glyph for displaying weapon on board
     */
    public String getGlyph() {
        return glyph;
    }

    /** Used for searching for a weapon
     *
     * @param other the other weapon to look at
     * @return true if the same named weapon
     */
    @Override
    public boolean equals(Object other) {
        Weapon el = (Weapon) other;
        return el.name.toLowerCase(Locale.ROOT).equals(this.name.toLowerCase(Locale.ROOT));
    }

    /** Quick way to get the name for debugging
     *
     * @return the weapons name
     */
    @Override
    public String toString(){
        return this.name;
    }

}
