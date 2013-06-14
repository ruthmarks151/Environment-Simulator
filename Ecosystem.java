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
  public Terrain getTerrain(){return habitat;}
  
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
    ArrayList<Ecosystem> neighbours = new ArrayList<Ecosystem>();
    for (int r = row - 1; r <= row + 1; r++)
    {
      for (int c = col - 1; c <= col + 1; c++)
      {
        if (r >= 0 && r < parent.getRows() && c >= 0 && c < parent.getCols() && r != row && c != col)
          neighbours.add(parent.getMap()[r][c]);
      }
    }
    return neighbours;
  }
  
  public void advance ()
  {
    for (int i = 0; i < inhabitants.size(); i++)
    {
      if (inhabitants.get(i).act())
        i--;
    }
  }
  
  public void add (Organism baby)
  {
    baby.setParent(this);
    inhabitants.add(baby);
  }
  
  public void add (String speciesToAdd,int amount){
    SpeciesTable st=new SpeciesTable();
    for (int i=0;i<amount;i++){
      add(st.make(speciesToAdd));
    }
    
  }
  
  public Organism remove (Organism deceased)
  {
    inhabitants.remove(deceased);
    return deceased;
  }
  
  public void remove(String speciesToRemove,int amount){
    
    for (int i=0;i<amount;i++){
      for (Organism inhabitant : inhabitants)//For each loop
      {
        
        if(inhabitant.getSpecies().equals(speciesToRemove)){
          remove(inhabitant);
          break;
        }
        
      }
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