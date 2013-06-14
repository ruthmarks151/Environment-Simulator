/**********************************************************************************************************************/
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Comparable;
import java.io.*;


abstract class Organism implements Cloneable
{
  protected static int foodValue, reproductiveSuccess;
  protected int energy;
  protected String species;
  protected Ecosystem parent;
  protected PreferenceTable habitats; 
  
  //Getters
  public int getRow(){return parent.getRow();}
  public int getCol(){return parent.getCol();}
  public synchronized String getSpecies(){return species;}
  public synchronized int getFoodValue(){return foodValue;}
  public synchronized Ecosystem getParent(){return parent;}
  public synchronized int getEnergy(){return energy;}
  public synchronized void addEnergy (int food){energy = energy + food;}
  public synchronized PreferenceTable getHabitats() {return habitats;}
  public synchronized int getReproductiveSuccess() {return reproductiveSuccess;}
  
  
//Setters
  public synchronized void setParent(Ecosystem newParent){parent=newParent;}

//Constructors
  public Organism (Ecosystem eco,String createAs)
    //eco, THe ecoystem the organism lives in. 
    //createAs the species of the organism.
  {
    parent=eco;
    species=createAs;
    
  }
  
    public Organism (String createAs,int foodPointValue,PreferenceTable placesToLive, int reproduction)

    //createAs the species of the organism.
  {
    species=createAs;
    foodValue=foodPointValue;
    habitats=placesToLive; 
    energy=3;
    reproductiveSuccess = reproduction;
    
  }
  //Other methods
  

  public synchronized ArrayList<Ecosystem> getAdjacent() {return parent.getAdjacent();}
  
  public synchronized boolean act(){
    energy--;
    if (energy <= 0) {
      die();
      return true;
    }
    return false;
  }
  
  public synchronized void die()
  {
    parent.remove(this);
  }
  
  public synchronized void reproduce()
  {
    parent.add(SpeciesTable.make(getSpecies()));
  }
  
  public synchronized Organism clone()
  {
      return this.clone();
  }
  
  
}

