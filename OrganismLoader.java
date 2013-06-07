import java.io.*;

class OrganismLoader{
//Most of this is reused code from the QuestionLoader class
//https://github.com/ryanmarks/Who-Wants-To-Be-A-Millionaire
  protected FileReader in;
  protected int eofs;
  
  OrganismLoader (String species){
    eofs=0;  
    System.out.println("Constructoring Question loader!");
    try{
      System.out.print("Making file reader...");
      in = new FileReader(species+".organism");
      System.out.println("...Succsess!");
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
  
  private String readLine() throws IOException{
    int read;
    String line="";
    while ((read=in.read())!=13&&read!=10){//Runs until newline
      checkEOF(read);  
      line+=(char)read;//Builds the string char by char
    }
   return line;
  }
  
  private int readIntLine() throws IOException{return Integer.parseInt(readLine());} 
  
  
  public Organism read() throws IOException{
    String species;
    String foodValueStr;
    String heading;
    int foodValue;
    
    species=readLine();
    foodValue=readIntLine();
    heading=readLine();
    assert(heading.equals("Lives")) : "The file is broken";//Ensures the file isn't broken

    
    return new Herbivore (null,"placeholder");//Temporary return type
  }
}
  
