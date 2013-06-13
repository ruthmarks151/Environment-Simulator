/**********************************************************************************************************************/
import java.io.*;
class SpeciesTable{

  
  static Organism[] species=new Organism[0]; ;
  
  public static String[] getOrganisms(){
    String[] organisms = new String[species.length]; 
    for (int i=0;i<species.length;i++){
      organisms[i]=species[i].getSpecies();
    }
    return organisms;
  } 
  
  SpeciesTable (){
  }
  
  public static void knownSpecies(){
    for (int i=0;i<species.length;i++)
      System.out.println(species[i].getSpecies());
  }
  public static Organism make (String name){
    for (int i=0;i<species.length;i++){//Look Through the array of Organisms to see if one already exists with that name
      if (species[i].getSpecies().equals(name))
        return species[i].clone();//If it has the requested name return it
    }
    
    //The organism with the requested name must not exist
    try{
      OrganismLoader ol=  new OrganismLoader(name);//Create a new OrganismLoader
      Organism org = ol.read();
      addOrganismToTable(org);
      return org;
      
    }catch (IOException e){
      System.out.println("An IOException was thrown");
      System.out.println("Could not make "+name);
      System.out.println("Returning a bunny");
      return make("Bunny");//If Bunny throws an IOException we're in trouble
    }
  }
  
  private static void addOrganismToTable(Organism creature){
    Organism[]newTable=new Organism[species.length+1];
    
    for(int i=0;i<species.length;i++){//Standard value copying for loop
      newTable[i]=species[i];
    }
    
    newTable[newTable.length-1]=creature;
    
    species=newTable;
    
   
  }
  
}