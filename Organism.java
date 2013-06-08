import java.util.ArrayList;
import java.util.Collections;
import java.lang.Comparable;
import java.io.*;


abstract class Organism
{
  private static int foodValue, reproductiveSuccess;
  private int energy;
  private String species;
  private Ecosystem parent;
  private PreferenceTable habitats; 
  
  //Getters
  public int getRow(){return parent.getRow();}
  public int getCol(){return parent.getCol();}
  public String getSpecies(){return species;}
  public int getFoodValue(){return foodValue;}
  public Ecosystem getParent(){return parent;}
  public int getEnergy(){return energy;}
  public void addEnergy (int food){energy += food;}
  
  
//Setters
  public void setParent(Ecosystem newParent){parent=newParent;}

//Constructors
  public Organism (Ecosystem eco,String createAs)
    //eco, THe ecoystem the organism lives in. 
    //createAs the species of the organism.
  {
    parent=eco;
    species=createAs;
    
  }
  
    public Organism (String createAs,int foodPointValue,PreferenceTable placesToLive)

    //createAs the species of the organism.
  {
    species=createAs;
    foodValue=foodPointValue;
    habitats=placesToLive;   
    
  }
  //Other methods
  

  public ArrayList<Ecosystem> getAdjacent() {return parent.getAdjacent();}
  
  public void act(Ecosystem habitat)

  {
    
  }
  
  public void act(){
  
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

