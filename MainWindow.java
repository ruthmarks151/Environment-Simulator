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
  protected JPanel sidebar;
  protected JPanel manifestArea;
  
  public Grid getGrid(){return myGrid;}
  
  MainWindow(Grid grid){
    super("Life Simulation");
    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    myGrid=grid;
    myGrid.setParent(this);
    editor=new EcosystemEditor(myGrid.getSelected(),this);
    // create an area to show the manifest
    manifestArea = new JPanel();
    // make this a 2-column layout, with as many rows as species
    manifestArea.setLayout(new GridLayout (myGrid.manifest().size(),2));
    // iterate through the global manifest
    for (Manifest species : myGrid.manifest())
    {
      // display the species name
      manifestArea.add(new JLabel (species.getSpecies()));
      // display the global population of the species
      manifestArea.add(new JLabel (Integer.toString(species.getPop())));
    }
    // create a sidebar and add the editor and manifest area to the sidebar
    sidebar = new JPanel();
    sidebar.add(editor, BorderLayout.NORTH);
    sidebar.add(manifestArea, BorderLayout.SOUTH);
    controls=new ControlBar(this);
    
    super.setPreferredSize(new Dimension(720,720));
    super.setLayout(new BorderLayout());
    
    super.add(myGrid,BorderLayout.CENTER);  
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
    manifestArea = new JPanel();
    manifestArea.setLayout(new GridLayout (myGrid.manifest().size(),2));
    for (Manifest species : myGrid.manifest())
    {
      manifestArea.add(new JLabel (species.getSpecies()));
      manifestArea.add(new JLabel (Integer.toString(species.getPop())));
    }
    // create a new sidebar, with the new ecosystem editor and new manifest area
    sidebar = new JPanel();
    sidebar.add(editor, BorderLayout.NORTH);
    sidebar.add(manifestArea, BorderLayout.SOUTH);
    sidebar.setSize(new Dimension (100, 200));
    super.add(sidebar);
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
