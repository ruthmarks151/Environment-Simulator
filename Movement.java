import java.awt.event.*;

class Movement implements ActionListener
{
  // data field colony, which is affected each time
  protected Grid grid;
  
  // constructorsets the object's grid to the grid
  public Movement (Grid thegrid)
  {
    grid = thegrid;
  }
  
  // each time the timer gives an action
  public void actionPerformed (ActionEvent event)
  {
    // advance the colony
    grid.advance ();
    System.out.println("Advanced");
    // repaint the grid
    grid.repaint();
    // refresh the main window
    grid.getParent().refresh();
  }
}