import java.awt.*; 
import java.io.*;
class GridLoader{
  protected FileReader fr;
  protected BufferedReader filein;
  
  GridLoader(String fileName){
    
    try{
      fr = new FileReader (fileName); 
      filein = new BufferedReader (fr);
    }catch (IOException e){
      System.out.println("File not Found");
    }      
  }
  
}