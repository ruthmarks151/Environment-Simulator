/**********************************************************************************************************************/
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


class MainWindow extends JFrame{
  
  private  EcosystemEditor editor;
  private  Grid myGrid;
  
  MainWindow(){
    super("Life Simulation");
    //////////
        myGrid=new Grid(3,3);
    

    for(int i=0;i<3;i++)
      myGrid.getEcosystem(0,0).add(SpeciesTable.make("Fern"));//Create 3 fern
    for(int i=0;i<1;i++)
      myGrid.getEcosystem(0,0).add(SpeciesTable.make("Bunny"));//Create 1 bunny
    
    SpeciesTable.make("Bush");
    //////////
    editor=new EcosystemEditor(myGrid.getEcosystem(0,0),this);
    
    super.setPreferredSize(new Dimension(1024,768));
    super.setLayout(new BorderLayout());
    
    super.add(editor,BorderLayout.EAST);  
    super.pack();
    super.setVisible(true);
  }

  
  public  void replaceMe(EcosystemEditor caller){
    if (caller==editor){
      System.out.println("Replacing!");
          super.remove(editor);
      editor= new EcosystemEditor(editor.getEditted(),this);
    super.add(editor,BorderLayout.EAST);
    super.repaint();
    super.pack();
    editor.setVisible(true);
    }
    
  }
  
}