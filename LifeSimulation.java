/**********************************************************************************************************************/
import javax.swing.*;
import java.io.*;
import java.awt.event.*;

class Simulation extends JPanel
{
  private static Movement moveGrid;
  private static Grid myGrid;
  private static Timer t;
  
  //Getters
  public synchronized static Grid getGrid(){return myGrid;}
  public synchronized static Timer timer(){return t;}
  //Main
  public synchronized static void main(String[] args){
    
    //Declare a new grid   
    myGrid=new Grid(16,16);
    
//Make the species table
    SpeciesTable st=new SpeciesTable();
 
//Teach the species table the species that exist
    st.make("Fern");//Create a fern
    st.make("Bunny");//Create a bunny
    st.make("Bush");

    new MainWindow(myGrid);
    
    moveGrid = new Movement (myGrid);
    
    t = new Timer (1000, moveGrid);
    
    //t.start();
    
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