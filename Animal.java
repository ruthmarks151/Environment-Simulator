/**********************************************************************************************************************/
import java.util.*;

class Animal extends Organism implements Cloneable
{
  private static ArrayList<Organism> foodSources;
  private static int mobility;
  private PreferenceTable foods; 
  private int huntingSuccess;
  private int evasionSuccess;
  private boolean gender;
  private boolean available;
  //Getters
  public synchronized int getEvasionSuccess() {return evasionSuccess;}
  public synchronized int getMobility() {return mobility;}
  public synchronized boolean getGender(){return gender;}
  public synchronized PreferenceTable getFoods() {return foods;}
  public synchronized boolean isAvailable() {return available;}
  public synchronized void makeAvailable() {available = true;}
  public synchronized void makeUnavailable() {available = false;}
  //Constructors
  public Animal (Ecosystem eco,String createAs){
    super(eco,createAs);
  }
  
  public Animal (String createAs,
          int foodPointValue,
          PreferenceTable placesToLive,
          int reproductiveSuccess,
          PreferenceTable foodsToEat,
          int successAtHunting,
          int successAtEvasion,
          int agility){
    
    super(createAs,foodPointValue,placesToLive, reproductiveSuccess);
    foods=foodsToEat;
    huntingSuccess=successAtHunting; 
    evasionSuccess=successAtEvasion;
    mobility = agility;
    gender = Math.random() < 0.5;
    available = true;
  }
  
  // migrate to an adjacent ecosystem
  public synchronized void move()
  {
    getParent().remove(this);
    ArrayList<Ecosystem> destinations = getAdjacent();
    
    int choice = (int) (Math.random() * destinations.size());
    Ecosystem newparent = destinations.get(choice);
    newparent.add(this);
    setParent (newparent);
  }
  
  public synchronized boolean act ()
  {
    Organism prey = pickPrey();
    if (prey != null)
      eat (prey);
    mate();
    if (Math.random () * 100 < mobility)
      move();
    return super.act();
  }
  
  public synchronized void hunt (Organism other)
  {
    if (other instanceof Animal)
    {
      if (Math.random() * 100 < ((Animal) other).getEvasionSuccess()* huntingSuccess / 100)
        eat(other);
    }
    else
      eat(other);
  }
  
  public synchronized Organism pickPrey ()
  {
    Organism preferred = getParent().getInhabitants().get(0);
    int maxpreference = foods.getPrefFor(preferred.getSpecies());
    for (Organism potentialPrey : getParent().getInhabitants())
    {
      if (foods.getPrefFor(potentialPrey.getSpecies()) > maxpreference)
      {
        preferred = potentialPrey;
        maxpreference = foods.getPrefFor(preferred.getSpecies());
      }
    }
    if (maxpreference == 0)
      return null;
    else
      return preferred;
    
  }
  
  public synchronized void mate ()
  {
    ArrayList<Organism> coinhabitants = getParent().getInhabitants();
    for (Organism potentialMate : coinhabitants)
    {
      if (potentialMate.getSpecies().equals(getSpecies()) && gender != ((Animal)potentialMate).getGender() && ((Animal)potentialMate).isAvailable())
      {
        makeUnavailable();
        ((Animal)potentialMate).makeUnavailable();
        reproduce();
      }
    }
  }
  
  public synchronized void reproduce ()
  {
    if (Math.random() * 100 < getReproductiveSuccess())
      super.reproduce();
  }
  
  public synchronized void eat (Organism other)
  {
    addEnergy (other.getFoodValue());
    other.die();
  }
  
  public synchronized Animal clone ()
  {
     String createAs = getSpecies();
     int foodPointValue = getFoodValue();
     
     int successAtHunting = huntingSuccess;
     int successAtEvasion = evasionSuccess;
     
     return new Animal (createAs,foodPointValue,habitats,reproductiveSuccess,foods,successAtHunting,successAtEvasion,mobility);
  }
}