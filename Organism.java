/**********************************************************************************************************************/
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Comparable;
import java.io.*;


abstract class Organism implements Cloneable
{
  protected int foodValue, reproductiveSuccess;
  protected int energy;
  protected String species;
  protected Ecosystem parent;
  protected PreferenceTable habitats;
  
  //Getters
  public int getRow(){return parent.getRow();}
  public int getCol(){return parent.getCol();}
  public String getSpecies(){return species;}
  public int getFoodValue(){return foodValue;}
  public Ecosystem getParent(){return parent;}
  public int getEnergy(){return energy;}
  public void addEnergy (int food){energy = energy + food;}
  public PreferenceTable getHabitats() {return habitats;}
  public int getReproductiveSuccess() {return reproductiveSuccess;}
  
  
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
  
  
  public ArrayList<Ecosystem> getAdjacent() {return parent.getAdjacent();}
  
  public boolean act(){
    // decrement the energy
    energy--;
    // if out of energy
    if (energy <= 0) {
      // die
      die();
      return true;
    }
    return false;
  }
  
  public void die()
  {
    // remove self from ecosystem
    parent.remove(this);
  }
  
  public void reproduce()
  {
    // add clone of self to ecosystem
    parent.add(SpeciesTable.make(getSpecies()));
  }
  
  public Organism clone()
  {
    // clone self
    return this.clone();
  }
  
  
}

