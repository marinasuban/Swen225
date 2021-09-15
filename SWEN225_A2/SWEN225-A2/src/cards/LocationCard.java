package cards;

import java.util.Locale;

public class LocationCard implements Card {

    //The name of this location
    private final String locationName;

    /**
     * Creates a Location Card
     *
     * @param name The name of the location
     */
    public LocationCard(String name) {
        this.locationName = name;
    }

    /**
     * To string method used for debugging
     *
     * @return A string of the name of the location
     */
    public String toString() {
        return locationName;
    }

    /**
     * if object is an instance of Location Card and is equal return true
     *
     * @param other The object which is being compared to a LocationCard
     * @return True if the object is equal to the Location card else return false
     */
    public boolean equals(Object other) {

        //Check if the other object is a LocationCard
        if(!(other instanceof LocationCard)) return false;

        //Check if the location names are the same
        LocationCard el = (LocationCard) other;
        return this.locationName.toLowerCase(Locale.ROOT).equals(el.locationName.toLowerCase(Locale.ROOT));

    }

}
