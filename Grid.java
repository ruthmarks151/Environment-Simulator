class Grid
{
  private Ecosystem[][] map;
  
  public Grid (int r, int c)
  {
    map = new Ecosystem[r][c];
  }
  
  public Ecosystem[][] getMap ()
  {
    return map;
  }
}