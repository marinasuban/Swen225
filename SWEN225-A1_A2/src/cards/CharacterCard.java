package cards;

import java.util.Locale;

public class CharacterCard implements Card {

    //The name of this character
    private final String characterName;

    /**
     * Creates a Character Card
     *
     * @param name The name of the character
     */
    public CharacterCard(String name) {
        this.characterName = name;
    }

    /**
     * To string method, used for debugging
     *
     * @return A String of the player's name
     */
    @Override
    public String toString() {
        return characterName;
    }


    /**
     * If object is an instance of character card and is equal to this characterCard return true
     *
     * @param other The object being compared to the Weapon card
     * @return True if the object is equal to the Weapon card else return false
     */
    @Override
    public boolean equals(Object other) {

        //Check other is a CharacterCard
        if(!(other instanceof CharacterCard)) return false;

        //Check if the character names are the same
        CharacterCard el = (CharacterCard) other;
        return this.characterName.toLowerCase(Locale.ROOT).equals(el.characterName.toLowerCase(Locale.ROOT));

    }

}
