import java.util.*;

class Animal extends Organism
{
  private static ArrayList<Organism> foodSources;
  private static int mobility;
  private PreferenceTable foods; 
  
  //Getters
  public int getMobility (){return mobility;}
  
  //Constructor
  Animal (Ecosystem eco,String createAs)//eco, THe ecoystem the organism lives in. //createAs the species of the organism.
  {
    super(eco,createAs );
  }
  
  Animal (String createAs,int foodPointValue,PreferenceTable placesToLive,PreferenceTable foodsToEat){
    super(createAs,foodPointValue,placesToLive);
    foods=foodsToEat;
  }
  
  
  public void eat (Organism other)
  {
  }
}