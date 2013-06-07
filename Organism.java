import java.util.ArrayList;
import java.util.Collections;
import java.lang.Comparable;


abstract class Organism
{
  private static int foodValue, reproductiveSuccess;
  private int energy;
  private String species;
  private Ecosystem parent;
  //Getters
  public int getRow(){return parent.getRow();}
  public int getCol(){return parent.getCol();}
  public String getSpecies(){return species;}
  public int getFoodValue(){return foodValue;}
  public Ecosystem getParent(){return parent;}
  
  public void addEnergy (int food){energy += food;}
  
  public void setParent (Ecosystem newparent) {parent = newparent;}
  
//Constructors
  public Organism (Ecosystem eco,String createAs)
  {
    parent=eco;
    species=createAs;
  }
  
  //Other methods
  
  public ArrayList<Ecosystem> getAdjacent() {return parent.getAdjacent();}
  
  public void act(Ecosystem habitat)
  {
  }
  
  
  public void die()
  {
    parent.remove(this);
  }
  
  public void reproduce()
  {
    if (Math.random() < reproductiveSuccess)
    {
      try
      {
      Class species = getClass();
      parent.add((Organism) species.newInstance());
      }
      catch (InstantiationException iex) {}
      catch (IllegalAccessException iaex) {}
    }
  }
  

}

