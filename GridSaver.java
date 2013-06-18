/**********************************************************************************************************************/
import java.awt.*; 
import java.io.*;
class GridSaver{
  
  protected Grid grid;
  protected FileWriter fw;
  protected PrintWriter fileout;
  
  GridSaver (Grid gridToSave,String saveName){
    grid=gridToSave;
    
    String name=saveName;
    try{
     fw = new FileWriter ("Saves/"+name+".grid"); 
     fileout = new PrintWriter (fw);}
  catch (IOException e){
  System.out.println("Fill Creation Error");
  }
    
    Ecosystem[][] map=grid.getMap();
      fileout.println("<"+name+".grid"+">");
            fileout.println("<Header>");
      fileout.println(map.length+"x"+map[0].length);
                  fileout.println("</Header>");
    for(int row=0;row<map.length;row++){
      for(int col=0;col<map[row].length;col++){
      Ecosystem square=map[row][col];
      fileout.println("Ecosystem:("+row+","+col+")");
        fileout.println("<Terrain>");
      fileout.print(square.getTerrain().manifest());  
      fileout.println("</Terrain>");
      fileout.println("<Organisms>");
      fileout.print(square.manifest()); 
      fileout.println("</Organisms>");
      
      }
    }
    fileout.println("<"+name+".grid"+"/>");
    
    
    
    fileout.close (); 
  } 
}