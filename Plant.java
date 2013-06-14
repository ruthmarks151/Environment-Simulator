/**********************************************************************************************************************/
class Plant extends Organism
{
  private static int photosynthesisSuccess;
  
  public synchronized int getPhotosynthesisSuccess(){return photosynthesisSuccess;}
  
  public Plant (Ecosystem eco,String createAs)
    //eco, THe ecoystem the organism lives in. 
    //createAs the species of the organism.
  {
    super(eco,createAs);
  }
  
  
  public Plant(String createAs,
        int foodPointValue,
        PreferenceTable placesToLive,
        int reproductiveSuccess,
        int photoSynthesisRequirements){
    
    super(createAs,foodPointValue,placesToLive,reproductiveSuccess);
    photosynthesisSuccess=photoSynthesisRequirements;
  }
  
  public synchronized boolean act ()
  {
    int rand =(int)(Math.random() * 100);
    if (rand < photosynthesisSuccess){
      photosynthesize();}
    reproduce();
    return super.act();
  }
  
  public synchronized void photosynthesize ()
  {
      addEnergy(1);
  }
  
  public synchronized void reproduce ()
  {
    if (Math.random() * 100 < getReproductiveSuccess())
      super.reproduce();
  }
  
  public synchronized Plant clone()
  {
    String createAs = getSpecies();
    int foodPointValue = getFoodValue();
    PreferenceTable placesToLive = getHabitats();
    int photoSynthesisRequirements = getPhotosynthesisSuccess();
      
    return new Plant(createAs,foodPointValue,placesToLive,reproductiveSuccess,photoSynthesisRequirements);
  }
}