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
  private Ecosystem[][] map;
  private int selectedRow,selectedCol;
  private Image selector; 
  private MainWindow parent=null;
  
//Getters
  public Ecosystem[][] getMap (){return map;}
  public int getRows() {return map.length;}
  public int getCols() {return map[0].length;}
  public Ecosystem getEcosystem(int x,int y){return map[x][y];}
  public Ecosystem getSelected(){return map[selectedRow][selectedCol];} 
  
  //Setters
  public void setParent(MainWindow mainWin){parent=mainWin;}
  
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
  selectedCol=e.getY()/32;
  repaint();
  parent.refresh();
  }
  public void mouseReleased(MouseEvent e){} 
  public void mouseEntered(MouseEvent e){} 
  public void mouseExited(MouseEvent e){}
  public void mouseClicked(MouseEvent e){ }
}