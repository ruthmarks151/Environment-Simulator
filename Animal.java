import java.util.*;

abstract class Animal extends Organism
{
  private static ArrayList<Organism> foodSources;
  private static int mobility;
  
  public int getMobility ()
  {
    return mobility;
  }
  
  public void eat (Organism other)
  {
  }
}