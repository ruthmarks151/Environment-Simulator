import java.util.*;
class God
{
  public static void move (Organism mover)
  {
    int row = mover.getRow();
    int col = mover.getCol();
    
    Ecosystem[][] theGrid = Simulation.getGrid().getMap();
    
    theGrid[row][col].remove(mover);
    
    getAdjacent(theGrid[row][col]);
    
    
  }
  
  public static ArrayList<Ecosystem> getAdjacent (Ecosystem origin)
  {
    Ecosystem[][] theGrid = Simulation.getGrid().getMap();
    int r = origin.getRow();
    int c = origin.getCol();
    ArrayList<Ecosystem> neighbours = new ArrayList<Ecosystem>();
    
    for (int row = r - 1; row <= r + 1; row++)
    {
      for (int col = r - 1; col <= r + 1; col++)
      {
        if (r >= 0 && r <= theGrid.length && c >= 0 && c <= theGrid[0].length)
          neighbours.add(theGrid[r][c]);
      }
    }
    
    return neighbours;
  }
}