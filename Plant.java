/**********************************************************************************************************************/
class Plant extends Organism
{
  private static int photosynthesisSuccess;
  
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
    int rand =(int)(Math.random() * 100);
    if (rand < photosynthesisSuccess){
      //System.out.println(rand+"<"+photosynthesisSuccess);
      photosynthesize();}
    //else 
            //System.out.println(rand+">"+photosynthesisSuccess);
    return super.act();
  }
  
  public void photosynthesize ()
  {
      addEnergy(1);
  }
  
  public void reproduce ()
  {
    if (Math.random() * 100 < getReproductiveSuccess())
      super.reproduce();
  }
  
  public Plant clone()
  {
    String createAs = getSpecies();
    int foodPointValue = getFoodValue();
    PreferenceTable placesToLive = getHabitats();
    int photoSynthesisRequirements = getPhotosynthesisSuccess();
      
    return new Plant(createAs,foodPointValue,placesToLive,reproductiveSuccess,photoSynthesisRequirements);
  }
}