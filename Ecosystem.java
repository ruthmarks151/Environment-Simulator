/**********************************************************************************************************************/
import java.util.*;

class Ecosystem
{
  ArrayList<Organism> inhabitants;
  Terrain habitat;
  int row, col;
  Grid parent;
  //Getters
  public ArrayList<Organism> getInhabitants() {return inhabitants;}
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
  
  public ArrayList<Ecosystem> getAdjacent()
  {
    ArrayList<Ecosystem> neighbours = new ArrayList<Ecosystem>();
    for (int r = row - 1; r <= row + 1; r++)
    {
      for (int c = col - 1; c <= col + 1; c++)
      {
        if (r >= 0 && r < parent.getRows() && c >= 0 && c < parent.getCols() && r != row && r != col)
          neighbours.add(parent.getMap()[r][c]);
      }
    }
    return neighbours;
  }

  public void add (Organism baby)
  {
    baby.setParent(this);
    inhabitants.add(baby);
  }
  
  public Organism remove (Organism deceased)
  {
    inhabitants.remove(deceased);
    return deceased;
  }
  
  public String manifest(){
    
    String manifest="";//Start with an empty manifest
    
    List<String> species=new ArrayList<String>();
    for (Organism inhabitant : inhabitants)//For each loop
    {
      species.add(inhabitant.getSpecies());//Fill the arraylist of strings with the species of every animal
    }
    
    //Deep magic uses HashSets more info at http://stackoverflow.com/a/5211215
    //Runs in  O(2(n^2)) so it could be improved
    Set<String> unique = new HashSet<String>(species);
    for (String key : unique) {
      manifest+=(key + ":" + Collections.frequency(species, key)+"\n");//THis can be changed. We want to maximize parseability
    }
    
    
    return manifest;  
    
  }
  
  public void update ()
  {
    for (Organism lifeform : inhabitants)
    {
      lifeform.act();
      //I think we want to implement this at the lifeform level rather than here
      /*if (lifeform.getClass().isAssignableFrom(Animal.class) && (int) Math.random() * 100 < ((Animal) lifeform).getMobility())
       {
       //remove(lifeform);
       }*/
    }
  }
}