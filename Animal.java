/**********************************************************************************************************************/
import java.util.*;

class Animal extends Organism implements Cloneable
{
  private static ArrayList<Organism> foodSources;
  private static int mobility;
  private PreferenceTable foods; 
  private int huntingSuccess;
  private int evasionSuccess;
  //Getters
  public int getEvasionSuccess() {return evasionSuccess;}
  public int getMobility() {return mobility;}
  public void setFoods(PreferenceTable foodsToEat) {foods = foodsToEat;}
  //Constructors
  public Animal (Ecosystem eco,String createAs){
    super(eco,createAs);
  }
  
  Animal (String createAs,
          int foodPointValue,
          PreferenceTable placesToLive,
          PreferenceTable foodsToEat,
          int reproduction,
          int successAtHunting,
          int successAtEvasion){
    
    super(createAs,foodPointValue,placesToLive,reproduction);
    foods=foodsToEat;
    huntingSuccess=successAtHunting; 
    evasionSuccess=successAtEvasion;  }
  
  /* Animal (Animal original)
   {
   String species = original.getSpecies();
   PreferenceTable placesToLive = */
  
  public void move()
  {
    getParent().remove(this);
    ArrayList<Ecosystem> destinations = getAdjacent();
    
    int choice = (int) (Math.random() * destinations.size());
    Ecosystem newparent = destinations.get(choice);
    newparent.add(this);
    setParent (newparent);
  }
  
  public boolean act ()
  {
    Organism prey = pickPrey();
    if (prey != null)
      eat (prey);
    if (Math.random () * 100 < mobility)
      move();
    return super.act();
  }
  
  public void hunt (Organism other)
  {
    if (other instanceof Animal)
    {
      if (Math.random() * 100 < ((Animal) other).getEvasionSuccess()* huntingSuccess / 100)
        eat(other);
    }
    else
      eat(other);
    
  }
  
  public Organism pickPrey ()
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
  
  public void eat (Organism other)
  {
    addEnergy (other.getFoodValue());
    other.die();
  }
  
  public void reproduce ()
  {
    if (Math.random() * 100 < reproductiveSuccess)
      super.reproduce();
  }
  
  public Organism clone ()
  {
    String createAs = getSpecies();
    int foodPointValue = getFoodValue();
    PreferenceTable placesToLive = getHabitats();
    PreferenceTable foodsToEat = foods;
    int reproduction = getReproductiveSuccess();
    int successAtHunting = huntingSuccess;
    int successAtEvasion = evasionSuccess;
    
    return new Animal(createAs,foodPointValue,placesToLive,foodsToEat,reproduction,successAtHunting,successAtEvasion);
  }
}