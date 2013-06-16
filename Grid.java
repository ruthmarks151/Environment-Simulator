/**********************************************************************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.imageio.*; // allows image loading
import java.awt.Image;
import java.io.*;

class Grid extends JPanel implements MouseListener
{
  protected Ecosystem[][] map;
  protected int selectedRow,selectedCol;
  protected Image selector; 
  protected MainWindow parent=null;
  
//Getters
  public Ecosystem[][] getMap (){return map;}
  public int getRows() {return map.length;}
  public int getCols() {return map[0].length;}
  public Ecosystem getEcosystem(int x,int y){return map[x][y];}
  public Ecosystem getSelected(){return map[selectedRow][selectedCol];} 
  public MainWindow getParent() {return parent;}
  
  //Setters
  public void setParent(MainWindow mainWin){parent=mainWin;}
  public void setMap (Ecosystem[][] newEcos){map=newEcos;}
  
  public Grid (int r, int c)
  {
    selector=loadImage("Selector");
    selectedRow=0;
    selectedCol=0;
    map = new Ecosystem[r][c];
    for(int row=0;row<map.length;row++){//Standard iterating through the grid for loop
      for(int col=0;col<map[row].length;col++){//If we could use this tyle for everything that'd be great
        map[row][col]=new Ecosystem(this,row,col);//Declare each square as new ecosystem with 
      }}
    addMouseListener(this);
    
        super.setPreferredSize(new Dimension(c*32,r*32));
  }
  
  public void advance ()
  {
    for (Ecosystem[] line: map)
    {
      for (Ecosystem block: line)
      {
        block.advance();
      }
    }
  }
  
  public ArrayList<Manifest> manifest ()
  {
    // create manifest list
    ArrayList<Manifest> manifest = new ArrayList<Manifest> ();
    // create list of organisms
    ArrayList<String> organisms = new ArrayList<String>();
    // iterate through each block in the grid
    for (Ecosystem[] line: map)
    {
      for (Ecosystem block: line)
      {
        // iterate through all inhabitants of each block
        for (Organism inhabitant : block.getInhabitants())//For each loop
        {
          // add the species name of each organism to the big list
          organisms.add(inhabitant.getSpecies());
        }
      }
    }
    // get the list of unique species present in the environment
    Set<String> unique = new HashSet<String>(organisms);
    // iterate through each species in the unique list
    for (String key : unique) {
      // create a manifest object, with a string of the species name and an int of the total population, and add it to the manifest list
      manifest.add (new Manifest(key, Collections.frequency(organisms, key)));//THis can be changed. We want to maximize parseability
    }
    // return the global manifest list
    return manifest;
  }
  
  //Graphics
  @Override
  protected void paintComponent(Graphics g) {
    for(int row=0;row<map.length;row++){
      for(int col=0;col<map[row].length;col++){    
        g.drawImage(map[row][col].getImage(), row*32, col*32, null);} // see javadoc for more info on the parameters   
      
    }
    g.drawImage(selector,selectedRow*32,selectedCol*32,null);
  }
  //Image IO
  private Image loadImage (String name)  //loads image from file
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
  //Mouse Listener Components
  public void mousePressed(MouseEvent e){
    selectedRow=e.getX()/32;
    selectedCol=(e.getY()-32)/32;//This is a patch to fix weird errors in windows XP
    repaint();
    parent.refresh();
  }
  public void mouseReleased(MouseEvent e){} 
  public void mouseEntered(MouseEvent e){} 
  public void mouseExited(MouseEvent e){}
  public void mouseClicked(MouseEvent e){ }
}