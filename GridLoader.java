/**********************************************************************************************************************/
import java.awt.*; 
import java.io.*;
class GridLoader{
  protected FileReader fr;
  protected BufferedReader filein;
  
  protected String line; 
  
  protected int rowSize,colSize;
  
  protected int row,col;

  Grid grid;
  Ecosystem[][] map;
  
  GridLoader(Grid givenGrid, String fileName){
  grid=givenGrid;
    try{
      fr = new FileReader (fileName+".grid"); 
      filein = new BufferedReader (fr);
    }catch (IOException e){
      System.out.println("File not Found");
    }      
  }
  
  public Ecosystem[][] read()throws IOException{
 
  headerRead();
  Ecosystem[][] map=new Ecosystem[rowSize][colSize];
  for(row=0;row<map.length;row++){
    for(col=0;col<map[row].length;col++){
      map[row][col]=ecoLoad(); 
    }
  }
   return map; 
  }
  
  private void headerRead() throws IOException{

  line = filein.readLine();//Name line
  line = filein.readLine ();//<Header>
  line = filein.readLine ();//rowxcol
  
  String rowString=line.substring(0,(line.indexOf("x")-1));
  String colString= line.substring((line.indexOf("x")+1),line.length());
  //NOOOOO DONT LOOK AT THE KLUDGE
  rowSize=16;//Integer.parseInt(rowString);
  colSize=16;//Integer.parseInt(colString);

  line = filein.readLine ();//</Header>
  }
  
  //Parsers
  private int afterColon(){return Integer.parseInt(line.substring((line.indexOf(":")+1),line.length()));} 
   private String beforeColon(){return (line.substring(0,(line.indexOf(":"))));}
  
   
   
  private Ecosystem ecoLoad()throws IOException{
   line = filein.readLine ();//Ecosystem:(row,col)
   Terrain ter=terrainLoad();
   Ecosystem eco=new Ecosystem(grid,row,col,ter);
   
   line =  filein.readLine ();//<Organism>
   line =  filein.readLine ();//First possible line
   while (!line.equals("</Organisms>")){
     eco.add(beforeColon(),afterColon());//Organism:00
     line=filein.readLine();}
   return eco;
   }
  
  private Terrain terrainLoad() throws IOException{
     line = filein.readLine ();//<Terrain>
    line = filein.readLine ();//Light:00
    int light = afterColon();
    line = filein.readLine ();//Water:00
    int water = afterColon();
    line = filein.readLine ();//Temp:00
    int temp = afterColon();
        line = filein.readLine ();//</Terrain>
    return new Terrain (light,water,temp);//Construct a new terrain
  }
  
 
  

}