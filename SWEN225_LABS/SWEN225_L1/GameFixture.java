/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.0.5692.1a9e80997 modeling language!*/



// line 31 "model.ump"
// line 105 "model.ump"
public class GameFixture
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GameFixture Attributes
  private String fixtureDescription;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameFixture(String aFixtureDescription)
  {
    fixtureDescription = aFixtureDescription;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setFixtureDescription(String aFixtureDescription)
  {
    boolean wasSet = false;
    fixtureDescription = aFixtureDescription;
    wasSet = true;
    return wasSet;
  }

  public String getFixtureDescription()
  {
    return fixtureDescription;
  }

  public void delete()
  {}

  // line 36 "model.ump"
   public String getFullFixtureDescription(){
    String finalDescription = getFixtureDescription()+ getExtraFixtureDescription();
  return finalDescription;
  }

  // line 41 "model.ump"
   public String getExtraFixtureDescription(){
    return "";
  }


  public String toString()
  {
    return super.toString() + "["+
            "fixtureDescription" + ":" + getFixtureDescription()+ "]";
  }
}