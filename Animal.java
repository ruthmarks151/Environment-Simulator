/**********************************************************************************************************************/
import java.util.*;

class Animal extends Organism implements Cloneable
{
  private ArrayList<Organism> foodSources;
  private int mobility;
  private PreferenceTable foods; 
  private int huntingSuccess;
  private int evasionSuccess;
  private boolean gender;
  //Getters
  public int getEvasionSuccess() {return evasionSuccess;}
  public int getMobility() {return mobility;}
  public PreferenceTable getFoods() {return foods;}
  public boolean getGender() {return gender;}
  //Constructors
  public Animal (Ecosystem eco,String createAs){
    super(eco,createAs);
  }
  
  Animal (String createAs,
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
    gender = Math.random() < 0.5;// randomly assign a gender
  }
  
  
  public void move()
  {
    // remove from current ecosystem
    getParent().remove(this);
    // get the adjacent ecossytems
    ArrayList<Ecosystem> destinations = getAdjacent();
    
    // randomly select a neighbouring ecosystem
    int choice = (int) (Math.random() * destinations.size());
    // set the destination as the new parent, and add self to the destination
    Ecosystem newparent = destinations.get(choice);
    newparent.add(this);
    setParent (newparent);
  }
  
  // method called each time the simulation updates
  public boolean act ()
  {
    // select a target for predation
    Organism prey = pickPrey();
    if (prey != null)
      // hunt the prey only if a target was picked
      hunt (prey);
    if (Math.random () * 100 < mobility)
      // move to an adjacent location randomly (based on mobility)
      move();
    return super.act(); // do general organism actions
  }
  
  public void hunt (Organism other)
  {
    if (other instanceof Animal) // case if eating an animal
    {
      if (Math.random() * 100 < ((Animal) other).getEvasionSuccess()* huntingSuccess / 100)
        eat(other); // possibly eat the animal, based on own hunting success, prey's evasion success, and randomness
    }
    else // case if eating a plant
      eat(other); // eat the plant
  }
  
  public Organism pickPrey ()
  {
    Organism preferred = null; // declare a preferred prey
    int maxpreference = 0;
    for (Organism potentialPrey : getParent().getInhabitants()) // cycle through all potential prey (organisms in the same ecosystem)
    {
      if (foods.getPrefFor(potentialPrey.getSpecies()) > maxpreference) // if the organism is the most preferred of all encountered so far
      {
        preferred = potentialPrey; // set this organism to tne new preferred prey
        maxpreference = foods.getPrefFor(preferred.getSpecies()); // set its preference to the new maxpreference, for comparison purposes
      }
    }
    return preferred; // return the preferred organism
    
  }
  
  public void eat (Organism other)
  {
    addEnergy (other.getFoodValue()); // increase own energy by amount gained from eating
    other.die(); // make the other organism die
  }
  
  public Animal clone () // for implementation of cloneable
  {
    // declare and set variables for construction purposes (only ones that are private from superclass)
    String createAs = getSpecies();
    int foodPointValue = getFoodValue();
    
    // construct and return new animal (clone)
    return new Animal (createAs,foodPointValue,habitats,reproductiveSuccess,foods,huntingSuccess,evasionSuccess,mobility);
  }
}