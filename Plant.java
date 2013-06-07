class Plant extends Organism
{
  private static int photosynthesisSuccess;
  
  Plant (Ecosystem eco,String createAs)
    //eco, THe ecoystem the organism lives in. 
    //createAs the species of the organism.
  {
    super(eco,createAs);
  }

  Plant(String createAs,int foodPointValue,PreferenceTable placesToLive,int photoSynthesisRequirements){
    super(createAs,foodPointValue,placesToLive);
  photosynthesisSuccess=photoSynthesisRequirements;
  }
}