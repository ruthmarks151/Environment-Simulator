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
  public static Grid getGrid(){return myGrid;}
  public static Timer timer(){return t;}
  //Main
  public static void main(String[] args){
    
    //Declare a new grid   
    myGrid=new Grid(16,16);
    
//Make the species table
    SpeciesTable st=new SpeciesTable();
 
//Teach the species table the species that exist
    st.make("Fern");//Create a fern
    st.make("Bunny");//Create a bunny
    st.make("Bush");

    MainWindow mw=new MainWindow(myGrid);
    
    moveGrid = new Movement (myGrid);
    
    t = new Timer (1000, moveGrid);
   

  }
}