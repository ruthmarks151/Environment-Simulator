/**********************************************************************************************************************/
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Comparable;
class Plant extends Organism
{
  private int photosynthesisSuccess;
  
  public int getPhotosynthesisSuccess(){return photosynthesisSuccess;}
  
  Plant (Ecosystem eco,String createAs)
    //eco, THe ecoystem the organism lives in. 
    //createAs the species of the organism.
  {
    super(eco,createAs);
  }
  
  
  Plant(String createAs,
        int foodPointValue,
        PreferenceTable placesToLive,
        int reproductiveSuccess,
        int photoSynthesisRequirements){
    
    super(createAs,foodPointValue,placesToLive,reproductiveSuccess);
    photosynthesisSuccess=photoSynthesisRequirements;
  }
  
  public boolean act ()
  {
    if (Math.random() * 100 < photosynthesisSuccess){
      // photosynthesize, depending on species photosynthesis success and randomness
      photosynthesize();}
    boolean success = super.act();
    // reproduce
    reproduce();
    return success;
  }
  
  public void photosynthesize ()
  {
    // increment energy
    addEnergy(1);
  }
  
  public void reproduce ()
  {
    if (Math.random() * 100 < getReproductiveSuccess()){
      ArrayList<Ecosystem> destinations = getAdjacent();
    
    // randomly select a neighbouring ecosystem
    int choice = (int) (Math.random() * destinations.size());
    // set the destination as the new parent, and add self to the destination
    Ecosystem newparent = destinations.get(choice);
    newparent.add(SpeciesTable.make(getSpecies()));
      super.reproduce();
    }
  }
  
  public Plant clone() // implements Cloneable
  {
    // create variables (only private variables belonging to superclass)
    String createAs = getSpecies();
    int foodPointValue = getFoodValue();
    PreferenceTable placesToLive = getHabitats();
    int photoSynthesisRequirements = getPhotosynthesisSuccess();
    
    // return clone of self
    return new Plant(createAs,foodPointValue,placesToLive,reproductiveSuccess,photoSynthesisRequirements);
  }
}