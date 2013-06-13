import java.awt.event.*;

class Movement implements ActionListener
{
  // data field colony, which is affected each time
  protected Grid grid;
  
  // constructor sets the object's colony to the inputted colony
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
    // repaint the panel area (must first get from the Life class)
    //Life.getLife().repaint();
    grid.repaint();
    grid.getParent().refresh();
  }
}