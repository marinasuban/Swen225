package cards;

import java.util.Locale;

public class WeaponCard implements Card {

    //The name of the weapon
    private final String weaponName;

    /**
     * Creates a Weapon Card
     *
     * @param name The name of the Weapon assigned to the card
     */
    public WeaponCard(String name) {
        this.weaponName = name;
    }

    /**
     * Get the weaponName,
     *
     * @return A string of the weapon name
     */
    public String toString() {
        return weaponName;
    }

    /**
     * Determine if object is equivalent to a WeaponCard object.
     *
     * @param other The object being compared to the Weapon card
     * @return True if the object matches the Weapon card else return false
     */
    @Override
    public boolean equals(Object other) {

        //Check if other object is an instance of WeaponCard
        if(!(other instanceof WeaponCard)) return false;

        //Check if the weapon names are the same
        WeaponCard el = (WeaponCard) other;
        return this.weaponName.toLowerCase(Locale.ROOT).equals(el.weaponName.toLowerCase(Locale.ROOT));

    }

}
