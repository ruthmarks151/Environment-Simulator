/**********************************************************************************************************************/
import java.util.*;
import javax.imageio.*; // allows image loading
import javax.swing.*;
import java.io.*; // allows file access
import java.awt.Image;

class Ecosystem
{
  ArrayList<Organism> inhabitants;
  Terrain habitat;
  int row, col;
  Grid parent;
  Image mapSquare;
  //Getters
  public ArrayList<Organism> getInhabitants() {return inhabitants;}
  public int getRow(){return row;}
  public int getCol(){return col;}  
  
  public Ecosystem (Grid map, int x,int y){
    parent=map;
    row=x;
    col=y;
    
    habitat = new Terrain();
    mapSquare=loadImage(habitat.type());
    inhabitants = new ArrayList<Organism>();
  }
  
  public ArrayList<Ecosystem> getAdjacent()
  {
    // create new arraylist
    ArrayList<Ecosystem> neighbours = new ArrayList<Ecosystem>();
    // cycle through previous, same, and next row
    for (int r = row - 1; r <= row + 1; r++)
    {
      // cycle through previous, same, and next column
      for (int c = col - 1; c <= col + 1; c++)
      {
        // if the point has valid coordinates and is not the current ecosystem
        if (r >= 0 && r < parent.getRows() && c >= 0 && c < parent.getCols() && !(r == row || c == col))
          // add to the list of neighbours
          neighbours.add(parent.getMap()[r][c]);
      }
    }
    // return the list of neighbours
    return neighbours;
  }
  
  public void advance ()
  {
    // cycle through all inhabitants
    for (int i = 0; i < inhabitants.size(); i++)
    {
      // make the organism act
      if (inhabitants.get(i).act())
        i--;
    }
    // get all the animals to mate
    mate();
  }
  
  public void add (Organism baby)
  {
    // set the baby's parent to this ecosystem
    baby.setParent(this);
    // add the baby to this ecosystem
    inhabitants.add(baby);
  }
  
  public void add (String speciesToAdd,int amount){
    // iterate as many times as needed (as many as you need to add)
    for (int i=0;i<amount;i++){
      // add a new clone of the master organism of the specified species
      add(SpeciesTable.make(speciesToAdd));
    }
    
  }
  
  public Organism remove (Organism deceased)
  {
    // remove the dead organism from the ecosystem
    inhabitants.remove(deceased);
    return deceased;
  }
  
  public void remove(String speciesToRemove,int amount){
    // iterate as many times as needed (as many as you need to remove)
    for (int i=0;i<amount;i++){
      // iterate through list of inhabitants
      for (Organism inhabitant : inhabitants)
      {
        // if an inhabitant of target species is found
        if(inhabitant.getSpecies().equals(speciesToRemove)){
          // remove it
          remove(inhabitant);
          // leave the loop
          break;
        }
        
      }
    }
  }
  
  public void mate() {
    // create lists for all organisms of the true gender and false gender
    List<String> trues =new ArrayList<String>();
    List<String> falses=new ArrayList<String>();
    // iterate through the list of inhabitants
    for (Organism inhabitant : inhabitants)
    {
      // if the inhabitant is an animal
      if (inhabitant instanceof Animal)
      {
        // if it is a true gender
        if (((Animal) inhabitant).getGender())
          // add it to the list of trues
          trues.add(inhabitant.getSpecies());
        // if it is a false gender
        else
          // add it to the list of falses
          falses.add(inhabitant.getSpecies());
      }
    }
    
    // make hashsets of the trues
    Set<String> uniquetrues = new HashSet<String>(trues);
    // iterate through the list of species that have at least one true-gendered organism in the ecosystem
    for (String key : uniquetrues) {
      int totaltrues = Collections.frequency(trues, key); // get the total number of true-gendered occurences of this species
      int totalfalses = Collections.frequency(falses, key); // get the total number of false-gendered occurences of this species
      int babies = 0; // declare and initialize the number of children produced
      int limiter = Math.min(totaltrues,totalfalses); // set the limiting amount to the lower of the trues and falses
      // iterate the mating process up to the limiting amount
      for (int i = 1; i < limiter; i++) {
        if (Math.random() * 100 < SpeciesTable.make(key).getReproductiveSuccess())
          // set a new child to be created, depending on randomness and the reproductive success of the species
          babies++;
      }
      // add the required number of babies to the ecosystem
      add (key, babies);
    }
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
  
  public Image getImage (){
    return mapSquare;
  }
  public Image loadImage (String name)  //loads image from file
  {
    Image img = null;
    try
    {
      img = ImageIO.read (new File ("images/" + name + ".png")); // load file into Image object
      
      
    }
    catch (IOException e)
    {
      System.out.println("Image load error"+"\n"+"images/" + name + ".png");
    }
    
    return img;
  }
  
}