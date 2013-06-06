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
  
  public String manifest(){
    
    String manifest="";
    
    List<String> species=new ArrayList<String>();
    for (Organism inhabitant : inhabitants)//For each loop
    {
      species.add(inhabitant.getSpecies());
    }
    
    Set<String> unique = new HashSet<String>(species);
    for (String key : unique) {
      manifest+=(key + ": " + Collections.frequency(species, key)+"\n");
    }
    
    
    return manifest;  
    
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