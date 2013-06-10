/**********************************************************************************************************************/
import java.util.*;

class Animal extends Organism
{
  private static ArrayList<Organism> foodSources;
  private static int mobility;
  private PreferenceTable foods; 
  private int huntingSuccess;
  private int eveasionSuccess;
  //Getters
 
  public int getMobility() {return mobility;}
  //Constructors
  public Animal (Ecosystem eco,String createAs){
    super(eco,createAs);
  }
  
   Animal (String createAs,
          int foodPointValue,
          PreferenceTable placesToLive,
          PreferenceTable foodsToEat,
           int successAtHunting,
           int successAtEvasion){
     
    super(createAs,foodPointValue,placesToLive);
    foods=foodsToEat;
    huntingSuccess=successAtHunting; 
    eveasionSuccess=successAtEvasion;  }

  
  public void move()
  {
    getParent().remove(this);
    ArrayList<Ecosystem> destinations = getAdjacent();
    
    int choice = (int) (Math.random() * destinations.size());
    Ecosystem newparent = destinations.get(choice);
    newparent.add(this);
    setParent (newparent);
  }
  
 
  
  
  public void eat (Organism other)
  {
    addEnergy (other.getFoodValue());
    other.die();
  }
}