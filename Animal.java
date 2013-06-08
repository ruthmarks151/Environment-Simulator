/**********************************************************************************************************************/
import java.util.*;

class Animal extends Organism
{
  private static ArrayList<Organism> foodSources;
  private static int mobility;
  private PreferenceTable foods; 
  
  
  public Animal (Ecosystem eco,String createAs){
    super(eco,createAs);
  }
  public int getMobility() {return mobility;}
  
  public void move()
  {
    getParent().remove(this);
    ArrayList<Ecosystem> destinations = getAdjacent();
    
    int choice = (int) (Math.random() * destinations.size());
    Ecosystem newparent = destinations.get(choice);
    newparent.add(this);
    setParent (newparent);
  }
  
  Animal (String createAs,
          int foodPointValue,
          PreferenceTable placesToLive,
          PreferenceTable foodsToEat){
    super(createAs,foodPointValue,placesToLive);
    foods=foodsToEat;
  }
  
  
  public void eat (Organism other)
  {
    addEnergy (other.getFoodValue());
    other.die();
  }
}