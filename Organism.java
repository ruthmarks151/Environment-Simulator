import java.util.Collections;
import java.lang.Comparable;
import java.io.*;

abstract class Organism
{
  private static int foodValue;
  private String species;
  private Ecosystem parent;
  //Getters
  public int getRow(){return parent.getRow();}
  public int getCol(){return parent.getCol();}
  public String getSpecies(){return species;}
  
//Constructors
  public Organism (Ecosystem eco,String createAs)
    //eco, THe ecoystem the organism lives in. 
    //createAs the species of the organism.
  {
    parent=eco;
    species=createAs;

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

