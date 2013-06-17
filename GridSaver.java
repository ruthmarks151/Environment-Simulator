/**********************************************************************************************************************/
import java.awt.*; 
import java.io.*;
class GridSaver{
  
  protected Grid grid;
  protected FileWriter fw;
  protected PrintWriter fileout;
  
  GridSaver (Grid gridToSave,String saveName){
    grid=gridToSave;
    
    // set the name to the desired savename
    String name=saveName;
    try{
      // create new filewriter based on that file name and a .grid extension
      fw = new FileWriter (name+".grid"); 
      // create a new printwriter based on that filewriter
      fileout = new PrintWriter (fw);}
    catch (IOException e){
      // throw exception if failed
      System.out.println("Fill Creation Error");
    }
    // get the ecosystem array of the grid
    Ecosystem[][] map=grid.getMap();
    // add an opening .grid tag
    fileout.println("<"+name+".grid"+">");
    // add an opening header tag
    fileout.println("<Header>");
    // add the dimensions of the grid
    fileout.println(map.length+"x"+map[0].length);
    // add  the closing header tag
    fileout.println("</Header>");
    // iterate through rows
    for(int row=0;row<map.length;row++){
      // iterate through columns
      for(int col=0;col<map[row].length;col++){
        // set the working ecosystem
        Ecosystem square=map[row][col];
        // add the coordinates of the ecosystem
        fileout.println("Ecosystem:("+row+","+col+")");
        // add an opening terrain tag
        fileout.println("<Terrain>");
        // add the terrain manifest
        fileout.print(square.getTerrain().manifest());  
        // add the closing terrain tag
        fileout.println("</Terrain>");
        // add the opening orgnism tag
        fileout.println("<Organisms>");
        // add the orgnaism manifest
        fileout.print(square.manifest()); 
        // add the closing organism tag
        fileout.println("</Organisms>");
        
      }
    }
    // add the closing .grid tag
    fileout.println("<"+name+".grid"+"/>");
    
    
    // close the printwriter
    fileout.close (); 
  } 
}