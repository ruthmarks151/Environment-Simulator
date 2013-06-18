/**********************************************************************************************************************/
import java.io.*;
class SpeciesTable{

  // table of master organisms
  static Organism[] species=new Organism[0]; ;
  
  public static String[] getOrganisms(){
    // make new array with same size as organism list
    String[] organisms = new String[species.length];
    // iterate through array
    for (int i=0;i<species.length;i++){
      // copy each organism over
      organisms[i]=species[i].getSpecies();
    }
    // return the copied list
    return organisms;
  } 
  
  SpeciesTable (){
  }
  
  public static void knownSpecies(){
    // itrate through organism list
    for (int i=0;i<species.length;i++)
      //print species of each organism
      System.out.println(species[i].getSpecies());
  }
  public static Organism make (String name){
    // iterate through organism list
    for (int i=0;i<species.length;i++){
      if (species[i].getSpecies().equals(name))
        // return clone of master copy when the master organism of the correct species is found
        return species[i].clone();
    }
    
    //The organism with the requested name must not exist
    try{
      // create a new organism loader
      OrganismLoader ol=  new OrganismLoader(name);
      // read in the organism from the organism loader
      Organism org = ol.read();
      // add the organism to the table
      addOrganismToTable(org);
      // return the orgnaism
      return org;
      
    }catch (IOException e){
      // return a bunny if all else fails
      System.out.println("An IOException was thrown");
      System.out.println("Could not make "+name);
      System.out.println("Returning a bunny");
      return make("Bunny");
    }
  }
  
  private static void addOrganismToTable(Organism creature){
    // make new table that is one cell longer
    Organism[]newTable=new Organism[species.length+1];
    
    // iterate through array
    for(int i=0;i<species.length;i++){
      // copy each element over
      newTable[i]=species[i];
    }
    
    // add the new organism to the last cell
    newTable[newTable.length-1]=creature;
    
    // point the species table reference to the new table
    species=newTable;
    
   
  }
  
}