class Plant extends Organism
{
  private static int photosynthesisSuccess;
  
  Plant (Ecosystem eco,String createAs,int valueAsFood)
    //eco, THe ecoystem the organism lives in. 
    //createAs the species of the organism.
    //valueAsFood How many "Food Points" the creature is worth 
  {
    super(eco,createAs,valueAsFood);
  }
}