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
  protected ManifestArea manifestArea;
  protected JPanel sidebar;
  
  public Grid getGrid(){return myGrid;}
  
  MainWindow(Grid grid){
    super("Life Simulation");
    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    myGrid=grid;
    myGrid.setParent(this);
    editor=new EcosystemEditor(myGrid.getSelected(),this);
    // create an area to show the manifest
     manifestArea=new ManifestArea(myGrid.manifest());    
    
    // create a sidebar and add the editor and manifest area to the sidebar
    sidebar = new JPanel();
    sidebar.setLayout(new BorderLayout());
    sidebar.add(editor,BorderLayout.NORTH);
    sidebar.add(manifestArea,BorderLayout.CENTER);
    sidebar.setPreferredSize(new Dimension(200,520));
    controls=new ControlBar(this);
    
    super.setPreferredSize(new Dimension(750,720));
    super.setLayout(new BorderLayout());
    
    super.add(myGrid,BorderLayout.WEST);  
    super.add(sidebar,BorderLayout.EAST);  
    super.add(controls,BorderLayout.SOUTH);  
    
    super.pack();
    super.setVisible(true);
  }
  
  
  public  void refresh(){
    // remove the sidebar
    super.remove(sidebar);
    // create a new ecosystem editor
    editor= new EcosystemEditor(myGrid.getSelected(),this);
    // create and set up a new manifest area
    manifestArea.refresh(myGrid.manifest());   
// create a new sidebar, with the new ecosystem editor and new manifest area
    sidebar = new JPanel();
    sidebar.add(editor,BorderLayout.NORTH);
    sidebar.add(manifestArea,BorderLayout.CENTER);
    sidebar.setSize(new Dimension (100, 200));
    super.add(sidebar);
    super.repaint();
    super.pack();
    editor.setVisible(true);
    manifestArea.setVisible(true);
  }
  
  //Mouse Listener Components
  public void mousePressed(MouseEvent e){}
  public void mouseReleased(MouseEvent e){} 
  public void mouseEntered(MouseEvent e){} 
  public void mouseExited(MouseEvent e){}
  public void mouseClicked(MouseEvent e){}
  
}
