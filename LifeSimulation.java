/**********************************************************************************************************************/
import javax.swing.*;
import java.io.*;

class Simulation extends JPanel
{
  private static Grid myGrid;
  
  //Getters
  public static Grid getGrid(){return myGrid;}
  
  //Main
  public static void main(String[] args){
    
    //Declare a new grid   
    myGrid=new Grid(23,18);
    
//Make the species table
    SpeciesTable st=new SpeciesTable();
    for(int i=0;i<3;i++)
      myGrid.getEcosystem(0,0).add(st.make("Fern"));//Create 3 fern
    for(int i=0;i<1;i++)
      myGrid.getEcosystem(0,0).add(st.make("Bunny"));//Create 1 bunny
    
    st.make("Bush");
    System.out.println("New Window");
    new MainWindow(myGrid);
    
    //Code to test ecosystem Editor
    /*
     System.out.println(myGrid.getEcosystem(0,0).manifest());
     
     
     Animal predator = (Animal)(myGrid.getEcosystem(0,0).getInhabitants().get(3));
     
     predator.eat(myGrid.getEcosystem(0,0).getInhabitants().get(0));
     
     System.out.println(myGrid.getEcosystem(0,0).manifest());
     predator.move();
     System.out.println();
     System.out.println(myGrid.getEcosystem(0,0).manifest());
     System.out.println(myGrid.getEcosystem(0,1).manifest());
     System.out.println(myGrid.getEcosystem(1,0).manifest());
     System.out.println(myGrid.getEcosystem(1,1).manifest());
     */

  }
}