import java.util.*;

abstract class Animal extends Organism
{
  private static ArrayList<Organism> foodSources;
  private static int mobility;
  
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
  
  public void eat (Organism other)
  {
    addEnergy (other.getFoodValue());
    other.die();
  }
}