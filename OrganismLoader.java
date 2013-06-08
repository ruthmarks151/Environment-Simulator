/**********************************************************************************************************************/
import java.io.*;

class OrganismLoader{
//Most of this is reused code from the QuestionLoader class
//https://github.com/ryanmarks/Who-Wants-To-Be-A-Millionaire
  protected FileReader in;
  protected int eofs;
  
  OrganismLoader (String species){
    eofs=0;  
    try{
      in = new FileReader(species+".organism");
    }
    catch(IOException e) {
      System.out.println("Organism file not found");
    }
  }
  //Output the numerical value of the first 2kb of a file
  public void pukeFile (){
    try{
      for (int i=0;i<2048;i++)
        System.out.print(in.read()+", ");
    }catch(IOException e){}
  }
  //boolean evaluates if a gotten character indicates the end of a file
  private void checkEOF(int read) throws IOException{
    eofs++;
    if (read==-1){//if it is the end of the file
      System.out.println("IOException"+eofs);
      throw new IOException();}//throw an exception to be caught in the queston deck
  }
  
  //Reads a line and returns it's string representation
  private String readLine() throws IOException{
    int read;
    String line="";
    while ((read=in.read())!=13&&read!=10){//Runs until newline
      checkEOF(read);  
      line+=(char)read;//Builds the string char by char
    }
    return line;
  }
  
  //Reads a line containing only an integer and returns the integer value
  private int readIntLine() throws IOException{
    String num=readLine();
    System.out.println(num);
    return Integer.parseInt(num);} 
  
  //Loads a series of lines begining with toLoad.
  //Each line contains a string which is the thing that is prefered
  //and a number representing the preference value
  //The section must ehd with \toLoad
  private PreferenceTable ptLoad (String toLoad)throws IOException{
    String line=readLine();
    PreferenceTable pt=new PreferenceTable();
    assert(line.equals(toLoad)) : "The file is broken";//Ensures the file isn't broken
    
    while(!(line=readLine()).equals("\\"+toLoad)){
      pt.add(line);
    }
    
    return pt;
    
  }
  
  
  public Organism read() throws IOException{
    String species;
    int foodValue;
    PreferenceTable lives;
    String type;
    
    species=readLine();
    foodValue=readIntLine();
    lives=ptLoad("Lives");   
    type=readLine();
    if (type.equals("Plant")){
      int photosynthesis=readIntLine(); 
      return new Plant(species,foodValue,lives,photosynthesis);
    }
    PreferenceTable eats;
    eats=ptLoad("Eats");
    
    return new Animal (species,foodValue,lives,eats);//Temporary return type
  }
}

