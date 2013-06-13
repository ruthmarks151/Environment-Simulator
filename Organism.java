/**********************************************************************************************************************/
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Comparable;
import java.io.*;


class Organism implements Cloneable
{
  public static int foodValue, reproductiveSuccess;
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
  public PreferenceTable getHabitats(){return habitats;}
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
    energy=5;
    
  }
  
    public Organism (String createAs,int foodPointValue,PreferenceTable placesToLive, int reproduction)

    //createAs the species of the organism.
  {
    species=createAs;
    foodValue=foodPointValue;
    habitats=placesToLive;
    reproductiveSuccess=reproduction;
    energy = 5;
    
  }
  //Other methods
  

  public ArrayList<Ecosystem> getAdjacent() {return parent.getAdjacent();}
  
  public boolean act(){
    energy--;
    reproduce();
    if (energy <= 0) {
                System.out.println("Something died! E="+energy);
      die();
      return true;
    }
    return false;
  }
  
  public void die()
  {

    parent.remove(this);
  }
  
  public void reproduce()
  {
    {
      //try
      //{
      //Class species = getClass();
      //parent.add((Organism) species.newInstance());
        parent.add(SpeciesTable.make(getSpecies()));
      //}
      //catch (InstantiationException iex) {}
      //catch (IllegalAccessException iaex) {}
    }
  }
  
  public Organism clone()
  {
    try{return (Organism) super.clone();}
    catch (CloneNotSupportedException ex) {
      System.out.println("Cloning failed");
      return null;
    }
  }
  
}

