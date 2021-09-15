/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.0.5692.1a9e80997 modeling language!*/



// line 65 "model.ump"
// line 116 "model.ump"
public class Wardrobe extends GameFixture
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Wardrobe Attributes
  private boolean locked;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Wardrobe(String aFixtureDescription)
  {
    super(aFixtureDescription);
    locked = true;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLocked(boolean aLocked)
  {
    boolean wasSet = false;
    locked = aLocked;
    wasSet = true;
    return wasSet;
  }

  public boolean getLocked()
  {
    return locked;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isLocked()
  {
    return locked;
  }

  public void delete()
  {
    super.delete();
  }

  // line 71 "model.ump"
   public String getFixtureDescription(){
    String basic = "A large and heavy wardrobe. ";
      return basic;
  }

  // line 76 "model.ump"
   public String getExtraFixtureDescription(){
    if (getLocked() == false){
      return "Its doors are " + doorDescription() + "." + " It contains a robe!";
    }
    else{
    return "Its doors are " + doorDescription() + ".";
    }
  }

  // line 86 "model.ump"
   private String doorDescription(){
    if (getLocked() == false){
      return "unlocked";
  }
    else {
    return "locked";
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "locked" + ":" + getLocked()+ "]";
  }
}