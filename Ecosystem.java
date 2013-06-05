import java.util.*;

class Ecosystem
{
  ArrayList<Organism> inhabitants;
  Terrain habitat;
  int row, col;
  
  public Ecosystem ()
  {
    habitat = new Terrain();
    inhabitants = new ArrayList<Organism>();
  }
  
  public int getRow()
  {
    return row;
  }
  
  public int getCol()
  {
    return col;
  }
  
  public void add (Organism baby)
  {
    inhabitants.add(baby);
  }
  
  public Organism remove (Organism deceased)
  {
    inhabitants.remove(deceased);
    return deceased;
  }
  
  public void update ()
  {
    for (Organism lifeform : inhabitants)
    {
      lifeform.act(this);
      if (lifeform.getClass().isAssignableFrom(Animal.class) && (int) Math.random() * 100 < ((Animal) lifeform).getMobility())
      {
        //remove(lifeform);
      }
    }
  }
}