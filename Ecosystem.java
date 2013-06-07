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