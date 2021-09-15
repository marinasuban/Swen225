/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.0.5692.1a9e80997 modeling language!*/



// line 45 "model.ump"
// line 111 "model.ump"
public class TreasureChest extends GameFixture
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TreasureChest Attributes
  private boolean open;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TreasureChest(String aFixtureDescription)
  {
    super(aFixtureDescription);
    open = false;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setOpen(boolean aOpen)
  {
    boolean wasSet = false;
    open = aOpen;
    wasSet = true;
    return wasSet;
  }

  public boolean getOpen()
  {
    return open;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isOpen()
  {
    return open;
  }

  public void delete()
  {
    super.delete();
  }

  // line 51 "model.ump"
   public String getFixtureDescription(){
    String basic = "A large and heavy treasure chest. ";
      return basic;
  }

  // line 56 "model.ump"
   public String getExtraFixtureDescription(){
    if (getOpen() == true){
      return "It contains the room key!";
    }
    else{
    return "It's lid is closed.";
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "open" + ":" + getOpen()+ "]";
  }
}