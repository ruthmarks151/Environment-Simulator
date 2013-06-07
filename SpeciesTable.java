import java.io.*;
class SpeciesTable{
  static Organism[] species;
  
  SpeciesTable (){
  species= new Organism[0]; 
  }
  
  public static Organism make (String name){
    for (int i=0;i<species.length;i++){//Look Through the array of Organisms to see if one already exists with that name
      if (species[i].getSpecies().equals(name))
        return species[i];//If it has the requested name return it
    }
    
    //The organism with the requested name must not exist
    try{
      OrganismLoader ol=  new OrganismLoader(name);//Create a new OrganismLoader
      Organism org = ol.read();
      return org;
      
    }catch (IOException e){
      System.out.println("An IOException was thrown");
      System.out.println("Could not make "+name);
      System.out.println("Returning a bunny");
      return make("Bunny");//If Bunny throws an IOException we're in trouble
    }
  }
  
  private void addOrganismToTable(Organism creature){
    Organism[]newTable=new Organism[species.length+1];
    
    for(int i=0;i<species.length;i++){//Standard value copying for loop
      newTable[i]=species[i];
    }
    
    newTable[newTable.length]=creature;
    
    species=newTable;
  }
  
}