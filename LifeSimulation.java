import javax.swing.*;
import java.io.*;

class Simulation extends JPanel
{
  private static Grid myGrid;
  
  //Getters
  public static Grid getGrid(){return myGrid;}
  
  public static void main(String[] args){
  myGrid=new Grid(1,1);
  myGrid.getEcosystem(0,0);
  for(int i=0;i<10;i++)
    myGrid.getEcosystem(0,0).add(new Plant (myGrid.getEcosystem(0,0),"Fern"));//Create a fern
  for(int i=0;i<1;i++)
    myGrid.getEcosystem(0,0).add(new Animal (myGrid.getEcosystem(0,0),"Bunny"));//Create a bunny
  
    System.out.println(myGrid.getEcosystem(0,0).manifest());
  
    OrganismLoader ol = new OrganismLoader("Bunny");
    try{
      ol.read();} catch (IOException e) {
      System.out.println("aw snap");
      }
  }
}