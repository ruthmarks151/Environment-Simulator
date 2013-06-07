import java.util.*;

class Animal extends Organism
{
  private static ArrayList<Organism> foodSources;
  private static int mobility;
  
  //Getters
  public int getMobility (){return mobility;}
  
  //Constructor
  public Animal (Ecosystem eco,String createAs)//eco, THe ecoystem the organism lives in. //createAs the species of the organism.
  {
  super(eco,createAs );
  }

  public void eat (Organism other)
  {
  }
}