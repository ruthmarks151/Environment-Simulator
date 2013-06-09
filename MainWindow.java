/**********************************************************************************************************************/
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


class MainWindow extends JFrame{
  
  private  EcosystemEditor editor;
  private  Grid myGrid;
  
  MainWindow(Grid grid){
    super("Life Simulation");
 myGrid=grid;
    editor=new EcosystemEditor(myGrid.getEcosystem(0,0),this);
    
    super.setPreferredSize(new Dimension(1024,768));
    super.setLayout(new BorderLayout());
    
    super.add(myGrid,BorderLayout.CENTER);  
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