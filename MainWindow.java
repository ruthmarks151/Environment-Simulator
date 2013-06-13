/**********************************************************************************************************************/
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


class MainWindow extends JFrame implements MouseListener{
  
  protected  EcosystemEditor editor;
  protected  Grid myGrid;
  protected ControlBar controls;
  
  MainWindow(Grid grid){
    super("Life Simulation");
    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    myGrid=grid;
    myGrid.setParent(this);
    editor=new EcosystemEditor(myGrid.getSelected(),this);
    controls=new ControlBar(this);
      
    super.setPreferredSize(new Dimension(720,720));
    super.setLayout(new BorderLayout());
    
    super.add(myGrid,BorderLayout.CENTER);  
    super.add(editor,BorderLayout.EAST);  
    super.add(controls,BorderLayout.SOUTH);  
    
    super.pack();
    super.setVisible(true);
  }

  
  public  void refresh(){
          super.remove(editor);
      editor= new EcosystemEditor(myGrid.getSelected(),this);
    super.add(editor,BorderLayout.EAST);
    super.repaint();
    super.pack();
    editor.setVisible(true);
    }
  
    //Mouse Listener Components
  public void mousePressed(MouseEvent e){}
  public void mouseReleased(MouseEvent e){} 
  public void mouseEntered(MouseEvent e){} 
  public void mouseExited(MouseEvent e){}
  public void mouseClicked(MouseEvent e){}
    
  }
  