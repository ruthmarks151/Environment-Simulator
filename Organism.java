import java.util.Collections;
import java.lang.Comparable;

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
  public Organism (Ecosystem eco,String createAs,int valueAsFood)
    //eco, THe ecoystem the organism lives in. 
    //createAs the species of the organism.
    //valueAsFood How many "Food Points" the creature is worth 
  {
    parent=eco;
    species=createAs;
    foodValue=valueAsFood;
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

