import java.util.Collections;
import java.lang.Comparable;
import java.io.*;

abstract class Organism
{
  private static int foodValue;
  private String species;
  private Ecosystem parent;
  private PreferenceTable habitats; 
  
  //Getters
  public int getRow(){return parent.getRow();}
  public int getCol(){return parent.getCol();}
  public String getSpecies(){return species;}
  
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
  
  public void act()
  {
    
  }
  
  public void die()
  {
  }
  
  public void reproduce()
  {
  }
  
  
}

