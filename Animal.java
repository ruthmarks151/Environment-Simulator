import java.util.*;

abstract class Animal extends Organism
{
  private static ArrayList<Organism> foodSources;
  private static int mobility;
  
  //Getters
  public int getMobility (){return mobility;}
  
  //Constructor
  public Animal (Ecosystem eco,String createAs,int valueAsFood)
    //eco, THe ecoystem the organism lives in. 
    //createAs the species of the organism.
    //valueAsFood How many "Food Points" the creature is worth 
  {
  super(eco,createAs,valueAsFood);
  }

  public void eat (Organism other)
  {
  }
}