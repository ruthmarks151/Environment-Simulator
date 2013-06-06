import java.util.*;

class Ecosystem
{
  ArrayList<Organism> inhabitants;
  Terrain habitat;
  int row, col;
  Grid parent;
  //Getters   
  public int getRow(){return row;}
  public int getCol(){return col;}  
  
  public Ecosystem (Grid map, int x,int y){
    parent=map;
    row=x;
    col=y;

    habitat = new Terrain();
    inhabitants = new ArrayList<Organism>();
  }
      
  public Ecosystem ()
  {
    habitat = new Terrain();
    inhabitants = new ArrayList<Organism>();
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
  
  public String report(){
   

      for (Organism inhabitant : inhabitants)//For each loop
    {
     
    }
    
    
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