class Grid
{
  private Ecosystem[][] map;
  
  //Getters
  public Ecosystem[][] getMap (){return map;}
  
  public int getRows() {return map.length;}
  public int getCols() {return map[0].length;}
  public Ecosystem getEcosystem(int x,int y){return map[x][y];}
  
  public Grid (int r, int c)
  {
    map = new Ecosystem[r][c];
    for(int row=0;row<map.length;row++){//Standard iterating through the grid for loop
      for(int col=0;col<map[row].length;col++){//If we could use this tyle for everything that'd be great
      map[row][col]=new Ecosystem(this,row,col);//Declare each square as new ecosystem with 
      }}
  }
}